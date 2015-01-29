package fr.ensimag.logic;

import fr.ensimag.test.testdata.entity.ArticleTestdataBuilder;
import fr.ensimag.entity.Article;
import fr.ensimag.vo.ArticleVO;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

@RunWith(Arquillian.class)
public class ArticleServiceTest {
	@EJB
	private ArticleServiceLocal service;

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserTransaction utx;

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
				.addPackages(true, "fr.ensimag")
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Before
	public void before() throws Exception {
		utx.begin();
		em.joinTransaction();
		em.createQuery("delete from Article").executeUpdate();
		utx.commit();
	}

	/**
	 * Method: deleteArticle(Integer articleId)
	 */
	@Test
	public void testDeleteArticle() throws Exception {
		// Insertion d'un article
		Article realArticle = new ArticleTestdataBuilder(em, utx).buildAndSave();
		Article fromDB;

		// Suppression d'un article existant
		this.service.deleteArticle(realArticle.getId());
		fromDB = em.find(Article.class, realArticle.getId());
		Assert.assertNull(fromDB);

		// Suppression d'un article inexistant
		try {
			this.service.deleteArticle(-1);
			Assert.fail("The article shouldn't exists");
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
	}

	/**
	 * Method: getArticle(Integer articleId)
	 */
	@Test
	public void testGetArticle() throws Exception {
		// Insertion d'un article
		Article realArticle = new ArticleTestdataBuilder(em, utx).buildAndSave();

		ArticleVO article;

		// Article existant
		article = this.service.getArticle(realArticle.getId());
		Assert.assertNotNull(article);
		Assert.assertEquals(realArticle.getId(), (int) article.getArticleId());

		// Article inexistant
		article = this.service.getArticle(-1);
		Assert.assertNull(article);
	}

	/**
	 * Method: createArticle(ArticleVO vo)
	 */
	@Test
	public void testCreateArticle() throws Exception {
		//Verification que la table est vide
		ArticleVO vo = this.service.getArticle(1);
		Assert.assertNull(vo);

		//Ajout d'article
		ArticleVO toCreate = new ArticleTestdataBuilder(em, utx).build().toVO();
		ArticleVO created = this.service.createArticle(toCreate);
		ArticleVO fromDB  = this.service.getArticle(created.getArticleId());

		Assert.assertNotNull(fromDB);
		Assert.assertNotSame(created, fromDB);
		Assert.assertEquals(created.getArticleId(), fromDB.getArticleId());
		Assert.assertEquals(created.getArticleLibele(), fromDB.getArticleLibele());
	}
} 
