package fr.ensimag.dao;

import fr.ensimag.entity.Utilisateur;
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