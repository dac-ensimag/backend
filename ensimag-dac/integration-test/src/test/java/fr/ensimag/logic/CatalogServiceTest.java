package fr.ensimag.logic;

import fr.ensimag.entity.Article;
import fr.ensimag.entity.Categorie;
import fr.ensimag.test.testdata.entity.ArticleTestdataBuilder;
import fr.ensimag.test.testdata.entity.CategorieTestdataBuilder;
import fr.ensimag.vo.ArticleVO;
import fr.ensimag.vo.CategorieVO;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.List;

@RunWith(Arquillian.class)
public class CatalogServiceTest {

    @EJB
    private CatalogServiceLocal service;

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
        em.createQuery("delete from Categorie").executeUpdate();
        utx.commit();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        Article article1 = new ArticleTestdataBuilder(em, utx).buildAndSave();
        Article article2 = new ArticleTestdataBuilder(em, utx).buildAndSave();;

        List<ArticleVO> listeArticles = this.service.getAllProducts();
        Assert.assertNotNull(listeArticles);
        Assert.assertEquals(listeArticles.size(),2);

        //On vérifie que les ids sont bons
        Assert.assertTrue((article1.getArticleId() == listeArticles.get(0).getArticleId()
                && article2.getArticleId() == listeArticles.get(1).getArticleId())
                        ||(article1.getArticleId() == listeArticles.get(1).getArticleId()
                && article2.getArticleId() == listeArticles.get(0).getArticleId())
        );
    }

    @Test
    public void testGetAllCategories() throws Exception {
        Categorie categorie1 = new CategorieTestdataBuilder(em, utx).buildAndSave();
        Categorie categorie2 = new CategorieTestdataBuilder(em, utx).buildAndSave();;

        List<CategorieVO> listeCategories = this.service.getAllCategories();
        Assert.assertNotNull(listeCategories);
        Assert.assertEquals(listeCategories.size(),2);

        //On vérifie que les ids sont bons
        Assert.assertTrue((categorie1.getCategorieId() == listeCategories.get(0).getCategorieId()
                        && categorie2.getCategorieId() == listeCategories.get(1).getCategorieId())
                        ||(categorie1.getCategorieId() == listeCategories.get(1).getCategorieId()
                        && categorie2.getCategorieId() == listeCategories.get(0).getCategorieId())
        );
    }
}
