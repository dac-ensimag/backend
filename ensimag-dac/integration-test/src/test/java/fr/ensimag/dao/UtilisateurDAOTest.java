package fr.ensimag.dao;

import fr.ensimag.entity.Categorie;
import fr.ensimag.entity.Utilisateur;
import fr.ensimag.test.testdata.entity.UtilisateurTestdataBuilder;
import fr.ensimag.test.testdata.entity.UtilisateurTestdataBuilder;
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
public class UtilisateurDAOTest {
	@EJB
	private UtilisateurDAOLocal utilisateurDAO;

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
		em.createQuery("delete from Utilisateur").executeUpdate();
		utx.commit();
	}

    @Test
    public void testCreate() throws Exception {
        Utilisateur utilisateur = new UtilisateurTestdataBuilder(em, utx).build();
        Utilisateur utilisateurFromDb;
        utilisateurFromDb = utilisateurDAO.create(utilisateur);

        Assert.assertEquals(utilisateur, utilisateurFromDb);
    }

    @Test
    public void testFind() throws Exception {
        Utilisateur utilisateur = new UtilisateurTestdataBuilder(em, utx).buildAndSave();

        Utilisateur utilisateuFromDb = utilisateurDAO.find(utilisateur.getUtilisateurId());

        Assert.assertEquals(utilisateur.getUtilisateurId(), utilisateuFromDb.getUtilisateurId());
        Assert.assertNotSame(utilisateur, utilisateuFromDb);

        utilisateuFromDb = utilisateurDAO.find(-1);

        Assert.assertNull(utilisateuFromDb);
    }

    @Test
    public void testRemove() throws Exception {
        Utilisateur utilisateur;

        // Delete an existing entity
        utilisateur = new UtilisateurTestdataBuilder(em, utx).buildAndSave();
        utilisateurDAO.remove(utilisateur);
        Utilisateur utilisateurFromDb = utilisateurDAO.find(utilisateur.getUtilisateurId());
        Assert.assertNull(utilisateurFromDb);

        // Delete a non existent entity
        utilisateur = new UtilisateurTestdataBuilder(em, utx).build();
        try {
            utilisateur.setUtilisateurId(-1);
            utilisateurDAO.remove(utilisateur);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        }

        // Delete a manually crafted entity (nont managed)
        Utilisateur utilisateur2 = new UtilisateurTestdataBuilder(em, utx).buildAndSave();
        utilisateurFromDb = utilisateurDAO.find(utilisateur2.getUtilisateurId());
        Assert.assertNotNull(utilisateurFromDb);

        utilisateur.setUtilisateurId(utilisateur2.getUtilisateurId());
        utilisateurDAO.remove(utilisateur);

        utilisateurFromDb = utilisateurDAO.find(utilisateur2.getUtilisateurId());
        Assert.assertNull(utilisateurFromDb);
    }

    @Test
    public void testEdit() throws Exception {
        final Utilisateur utilisateur = new UtilisateurTestdataBuilder(em, utx).buildAndSave();

        utilisateur.setUtilisateurAdresse("autre addresse");
        utilisateurDAO.edit(utilisateur);

        Utilisateur utilisateurFromDb = utilisateurDAO.find(utilisateur.getUtilisateurId());

        Assert.assertEquals("autre addresse", utilisateurFromDb.getUtilisateurAdresse());
    }

    @Test
    public void testFindAll() throws Exception {
        new UtilisateurTestdataBuilder(em, utx).withUtilisateurLogin("login1").buildAndSave();
        new UtilisateurTestdataBuilder(em, utx).withUtilisateurLogin("login2").buildAndSave();

        List<Utilisateur> all = utilisateurDAO.findAll();

        Assert.assertThat(all.size(), equalTo(2));
    }

    @Test
    public void testCount() throws Exception {
        new UtilisateurTestdataBuilder(em, utx).withUtilisateurLogin("login1").buildAndSave();
        new UtilisateurTestdataBuilder(em, utx).withUtilisateurLogin("login2").buildAndSave();

        int count = utilisateurDAO.count();

        Assert.assertThat(count, equalTo(2));
    }

	@Test
	public void testFindByLogin() {
		Utilisateur utilisateur = new UtilisateurTestdataBuilder(em, utx).buildAndSave();

		// Find existent user
		Utilisateur findByLogin = utilisateurDAO.findByLogin(utilisateur.getUtilisateurLogin());
		Assert.assertEquals(utilisateur.getId(), findByLogin.getId());

		// Find non existent user
		Utilisateur other = utilisateurDAO.findByLogin("__no-name__");
		Assert.assertNull(other);
	}
}