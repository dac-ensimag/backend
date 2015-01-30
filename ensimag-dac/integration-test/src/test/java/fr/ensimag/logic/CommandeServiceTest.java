package fr.ensimag.logic;

import fr.ensimag.dao.CommandeDAO;
import fr.ensimag.dao.CommandeDAOLocal;
import fr.ensimag.entity.Article;
import fr.ensimag.entity.Categorie;
import fr.ensimag.entity.Commande;
import fr.ensimag.entity.Utilisateur;
import fr.ensimag.test.testdata.entity.ArticleTestdataBuilder;
import fr.ensimag.test.testdata.entity.CategorieTestdataBuilder;
import fr.ensimag.test.testdata.entity.CommandeTestdataBuilder;
import fr.ensimag.test.testdata.entity.UtilisateurTestdataBuilder;
import fr.ensimag.vo.ArticleVO;
import fr.ensimag.vo.CategorieVO;
import fr.ensimag.vo.CommandeVO;
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
import java.util.*;

@RunWith(Arquillian.class)
public class CommandeServiceTest {

    @EJB
    private CommandeServiceLocal service;

    @EJB
    private CommandeDAOLocal dao;

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
        em.createQuery("delete from Commande").executeUpdate();
        em.createQuery("delete from Utilisateur").executeUpdate();
        utx.commit();
    }

    @Test
    public void testGetAllCommands() throws Exception {
        Utilisateur utilisateur = new UtilisateurTestdataBuilder(em, utx).buildAndSave();
        Commande commande1 = new CommandeTestdataBuilder(em, utx).withUtilisateur(utilisateur).buildAndSave();
        Commande commande2 = new CommandeTestdataBuilder(em, utx).withUtilisateur(utilisateur).buildAndSave();

        List<CommandeVO> listeCommandes = this.service.getAllCommands();
        Assert.assertNotNull(listeCommandes);
        Assert.assertEquals(listeCommandes.size(),2);

        //On vérifie que les ids sont bons
        Assert.assertTrue((commande1.getCommandeId() == listeCommandes.get(0).getCommandeId()
                        && commande2.getCommandeId() == listeCommandes.get(1).getCommandeId())
                        ||(commande1.getCommandeId() == listeCommandes.get(1).getCommandeId()
                        && commande2.getCommandeId() == listeCommandes.get(0).getCommandeId())
        );

    }

    @Test
    public void testAddCommand() throws Exception {
        Utilisateur utilisateur = new UtilisateurTestdataBuilder(em, utx).buildAndSave();

        ArticleVO article1 = new ArticleTestdataBuilder(em, utx).buildAndSave().toVO();
        ArticleVO article2 = new ArticleTestdataBuilder(em, utx).buildAndSave().toVO();
        ArticleVO article3 = new ArticleTestdataBuilder(em, utx).buildAndSave().toVO();
        ArticleVO article4 = new ArticleTestdataBuilder(em, utx).buildAndSave().toVO();

        CommandeVO commandeVO = new CommandeVO();

        List<ArticleVO> listeArticles = new ArrayList<>();
        listeArticles.add(article1);
        listeArticles.add(article2);
        listeArticles.add(article3);
        listeArticles.add(article4);

        Calendar c = new GregorianCalendar();
        c.set(Calendar.HOUR_OF_DAY, 0); //anything 0 - 23
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        Date date = c.getTime();

        double total = 0;
        for (ArticleVO art : listeArticles) {
            total += art.getArticlePrix();
        }

        commandeVO.setCommandeTotale(total);
        commandeVO.setCommandeDescription("Commande 1");
        commandeVO.setCommandeDate(date);
        commandeVO.setArticleList(listeArticles);
        commandeVO.setUtilisateurId(utilisateur.getUtilisateurId());

        CommandeVO res = this.service.addCommande(commandeVO);

        Assert.assertSame(1, this.dao.findAll().size());
        Assert.assertNotNull(this.dao.find(res.getCommandeId()));
    }

}
