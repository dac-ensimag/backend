package fr.ensimag.dao;

import de.akquinet.jbosscc.needle.annotation.ObjectUnderTest;
import de.akquinet.jbosscc.needle.junit.DatabaseRule;
import de.akquinet.jbosscc.needle.junit.NeedleRule;
import de.akquinet.jbosscc.needle.testng.DatabaseTestcase;
import fr.ensimag.entity.Utilisateur;
import fr.ensimag.entity.testdata.UtilisateurTestdataBuilder;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class UtilisateurDAOTest extends DatabaseTestcase {
	@Rule
	public DatabaseRule databaseRule = new DatabaseRule();

	@Rule
	public NeedleRule needleRule = new NeedleRule(databaseRule);

	@Inject
	private EntityManager entityManager;

	@ObjectUnderTest
	private UtilisateurDAO utilisateurDAO;

	@Test
	public void testFindByLogin() {

		Utilisateur utilisateur = new UtilisateurTestdataBuilder(entityManager).buildAndSave();

		Utilisateur findByLogin = utilisateurDAO.findByLogin(utilisateur.getUtilisateurLogin());

		Assert.assertEquals(utilisateur.getId(), findByLogin.getId());

		Utilisateur other = utilisateurDAO.findByLogin("__no-name__");
		Assert.assertNull(other);
	}
}