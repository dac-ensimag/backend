package fr.ensimag.dao;

import fr.ensimag.entity.Commande;
import fr.ensimag.entity.Utilisateur;
import fr.ensimag.test.testdata.entity.CommandeTestdataBuilder;
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
public class CommandeDAOTest {
	@EJB
	private CommandeDAOLocal utilisateurDAO;

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
	public void testCreate() throws Exception {
		Commande commande = new CommandeTestdataBuilder(em, utx).build();
		Commande commandeFromDb;
		commandeFromDb = utilisateurDAO.create(commande);

		Assert.assertEquals(commande, commandeFromDb);
	}

	@Test
	public void testFind() throws Exception {
		Commande commande = new CommandeTestdataBuilder(em, utx).buildAndSave();

		Commande commandeFromDb = utilisateurDAO.find(commande.getCommandeId());

		Assert.assertEquals(commande.getCommandeId(), commandeFromDb.getCommandeId());
		Assert.assertNotSame(commande, commandeFromDb);

		commandeFromDb = utilisateurDAO.find(-1);

		Assert.assertNull(commandeFromDb);
	}

	@Test
	public void testRemove() throws Exception {
		Commande commande;
		Utilisateur utilisateur = new UtilisateurTestdataBuilder(em, utx).buildAndSave();

		// Delete an existing entity
		commande = new CommandeTestdataBuilder(em, utx).withUtilisateur(utilisateur).buildAndSave();
		utilisateurDAO.remove(commande);
		Commande commandeFromDb = utilisateurDAO.find(commande.getCommandeId());
		Assert.assertNull(commandeFromDb);

		// Delete a non existent entity
		commande = new CommandeTestdataBuilder(em, utx).withUtilisateur(utilisateur).build();
		try {
			commande.setCommandeId(-1);
			utilisateurDAO.remove(commande);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertTrue(true);
		}

		// Delete a manually crafted entity (nont managed)
		Commande commande2 = new CommandeTestdataBuilder(em, utx).withUtilisateur(utilisateur).buildAndSave();
		commandeFromDb = utilisateurDAO.find(commande2.getCommandeId());
		Assert.assertNotNull(commandeFromDb);

		commande.setCommandeId(commande2.getCommandeId());
		utilisateurDAO.remove(commande);

		commandeFromDb = utilisateurDAO.find(commande2.getCommandeId());
		Assert.assertNull(commandeFromDb);
	}

	@Test
	public void testEdit() throws Exception {
		final Commande commande = new CommandeTestdataBuilder(em, utx).buildAndSave();

		commande.setCommandeDescription("Nouvelle description");
		utilisateurDAO.edit(commande);

		Commande commandeFromDb = utilisateurDAO.find(commande.getCommandeId());

		Assert.assertEquals(commandeFromDb.getCommandeDescription(), "Nouvelle description");
	}

	@Test
	public void testFindAll() throws Exception {
        Utilisateur utilisateur = new UtilisateurTestdataBuilder(em, utx).buildAndSave();
        new CommandeTestdataBuilder(em, utx).withUtilisateur(utilisateur).buildAndSave();
        new CommandeTestdataBuilder(em, utx).withUtilisateur(utilisateur).buildAndSave();

		List<Commande> all = utilisateurDAO.findAll();

		Assert.assertThat(all.size(), equalTo(2));
	}

	@Test
	public void testCount() throws Exception {
        Utilisateur utilisateur = new UtilisateurTestdataBuilder(em,utx).buildAndSave();
		new CommandeTestdataBuilder(em, utx).withUtilisateur(utilisateur).buildAndSave();
		new CommandeTestdataBuilder(em, utx).withUtilisateur(utilisateur).buildAndSave();

		int count = utilisateurDAO.count();

		Assert.assertThat(count, equalTo(2));
	}
}