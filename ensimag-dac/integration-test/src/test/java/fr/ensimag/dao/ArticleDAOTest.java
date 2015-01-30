package fr.ensimag.dao;

import fr.ensimag.entity.Article;
import fr.ensimag.test.testdata.entity.ArticleTestdataBuilder;
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
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

@RunWith(Arquillian.class)
public class ArticleDAOTest {
	@EJB
	private ArticleDAOLocal utilisateurDAO;

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

	@Test
	public void testCreate() throws Exception {
		Article article = new ArticleTestdataBuilder(em, utx).build();
		Article articleFromDb;
		articleFromDb = utilisateurDAO.create(article);

		Assert.assertEquals(article, articleFromDb);
	}

	@Test
	public void testFind() throws Exception {
		Article article = new ArticleTestdataBuilder(em, utx).buildAndSave();

		Article articleFromDb = utilisateurDAO.find(article.getArticleId());

		Assert.assertEquals(article.getArticleId(), articleFromDb.getArticleId());
		Assert.assertNotSame(article, articleFromDb);

		articleFromDb = utilisateurDAO.find(-1);

		Assert.assertNull(articleFromDb);
	}

	@Test
	public void testRemove() throws Exception {
		Article article;

		// Delete an existing entity
		article = new ArticleTestdataBuilder(em, utx).buildAndSave();
		utilisateurDAO.remove(article);
		Article articleFromDb = utilisateurDAO.find(article.getArticleId());
		Assert.assertNull(articleFromDb);

		// Delete a non existent entity
		article = new ArticleTestdataBuilder(em, utx).build();
		try {
			article.setArticleId(-1);
			utilisateurDAO.remove(article);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertTrue(true);
		}

		// Delete a manually crafted entity (nont managed)
		Article article2 = new ArticleTestdataBuilder(em, utx).buildAndSave();
		articleFromDb = utilisateurDAO.find(article2.getArticleId());
		Assert.assertNotNull(articleFromDb);

		article.setArticleId(article2.getArticleId());
		utilisateurDAO.remove(article);

		articleFromDb = utilisateurDAO.find(article2.getArticleId());
		Assert.assertNull(articleFromDb);
	}

	@Test
	public void testEdit() throws Exception {
		final Article article = new ArticleTestdataBuilder(em, utx).buildAndSave();

		article.setArticleLibele("Nouveau Libelle");
		utilisateurDAO.edit(article);

		Article articleFromDb = utilisateurDAO.find(article.getArticleId());

		Assert.assertEquals(articleFromDb.getArticleLibele(), "Nouveau Libelle");
	}

	@Test
	public void testFindAll() throws Exception {
		new ArticleTestdataBuilder(em, utx).buildAndSave();
		new ArticleTestdataBuilder(em, utx).buildAndSave();

		List<Article> all = utilisateurDAO.findAll();

		Assert.assertThat(all.size(), equalTo(2));
	}

	@Test
	public void testCount() throws Exception {
		new ArticleTestdataBuilder(em, utx).buildAndSave();
		new ArticleTestdataBuilder(em, utx).buildAndSave();

		int count = utilisateurDAO.count();

		Assert.assertThat(count, equalTo(2));
	}
}