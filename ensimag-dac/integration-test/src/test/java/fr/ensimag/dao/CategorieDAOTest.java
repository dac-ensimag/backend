package fr.ensimag.dao;

import fr.ensimag.entity.Categorie;
import fr.ensimag.test.testdata.entity.CategorieTestdataBuilder;
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

import static org.hamcrest.Matchers.*;

@RunWith(Arquillian.class)
public class CategorieDAOTest {
	@EJB
	private CategorieDAOLocal utilisateurDAO;

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
		em.createQuery("delete from Categorie").executeUpdate();
		utx.commit();
	}

	@Test
	public void testCreate() throws Exception {
		Categorie categorie = new CategorieTestdataBuilder(em, utx).build();
		Categorie categorieFromDb;
		categorieFromDb = utilisateurDAO.create(categorie);

		Assert.assertEquals(categorie, categorieFromDb);
	}

	@Test
	public void testFind() throws Exception {
		Categorie categorie = new CategorieTestdataBuilder(em, utx).buildAndSave();

		Categorie categorieFromDb = utilisateurDAO.find(categorie.getCategorieId());

		Assert.assertEquals(categorie.getCategorieId(), categorieFromDb.getCategorieId());
		Assert.assertNotSame(categorie, categorieFromDb);

		categorieFromDb = utilisateurDAO.find(-1);

		Assert.assertNull(categorieFromDb);
	}

	@Test
	public void testRemove() throws Exception {
		Categorie categorie;

		// Delete an existing entity
		categorie = new CategorieTestdataBuilder(em, utx).buildAndSave();
		utilisateurDAO.remove(categorie);
		Categorie categorieFromDb = utilisateurDAO.find(categorie.getCategorieId());
		Assert.assertNull(categorieFromDb);

		// Delete a non existent entity
		categorie = new CategorieTestdataBuilder(em, utx).build();
		try {
			categorie.setCategorieId(-1);
			utilisateurDAO.remove(categorie);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertTrue(true);
		}

		// Delete a manually crafted entity (nont managed)
		Categorie categorie2 = new CategorieTestdataBuilder(em, utx).buildAndSave();
		categorieFromDb = utilisateurDAO.find(categorie2.getCategorieId());
		Assert.assertNotNull(categorieFromDb);

		categorie.setCategorieId(categorie2.getCategorieId());
		utilisateurDAO.remove(categorie);

		categorieFromDb = utilisateurDAO.find(categorie2.getCategorieId());
		Assert.assertNull(categorieFromDb);
	}

	@Test
	public void testEdit() throws Exception {
		final Categorie categorie = new CategorieTestdataBuilder(em, utx).buildAndSave();

		categorie.setCategorieLibele("Nouveau Libelle");
		utilisateurDAO.edit(categorie);

		Categorie categorieFromDb = utilisateurDAO.find(categorie.getCategorieId());

		Assert.assertEquals(categorieFromDb.getCategorieLibele(), "Nouveau Libelle");
	}

	@Test
	public void testFindAll() throws Exception {
		new CategorieTestdataBuilder(em, utx).buildAndSave();
		new CategorieTestdataBuilder(em, utx).buildAndSave();

		List<Categorie> all = utilisateurDAO.findAll();

		Assert.assertThat(all.size(), equalTo(2));
	}

	@Test
	public void testCount() throws Exception {
		new CategorieTestdataBuilder(em, utx).buildAndSave();
		new CategorieTestdataBuilder(em, utx).buildAndSave();

		int count = utilisateurDAO.count();

		Assert.assertThat(count, equalTo(2));
	}
}