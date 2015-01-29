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
public class AbstractDAOTest {
	@EJB
	private CategorieDAOLocal categorieDAO;

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
		categorieFromDb = categorieDAO.create(categorie);

		Assert.assertEquals(categorie, categorieFromDb);
	}

	@Test
	public void testFind() throws Exception {
		Categorie categorie = new CategorieTestdataBuilder(em, utx).buildAndSave();

		Categorie categorieFromDb = categorieDAO.find(categorie.getCategorieId());

		Assert.assertEquals(categorie.getCategorieId(), categorieFromDb.getCategorieId());
		Assert.assertNotSame(categorie, categorieFromDb);

		categorieFromDb = categorieDAO.find(-1);

		Assert.assertNull(categorieFromDb);
	}

	@Test
	public void testRemove() throws Exception {
		Categorie categorie;

		// Delete an existing entity
		categorie = new CategorieTestdataBuilder(em, utx).buildAndSave();
		categorieDAO.remove(categorie);
		Categorie categorieFromDb = categorieDAO.find(categorie.getCategorieId());
		Assert.assertNull(categorieFromDb);

		// Delete a non existent entity
		categorie = new CategorieTestdataBuilder(em, utx).build();
		try {
			categorie.setCategorieId(-1);
			categorieDAO.remove(categorie);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertTrue(true);
		}

		// Delete a manually crafted entity (nont managed)
		Categorie categorie2 = new CategorieTestdataBuilder(em, utx).buildAndSave();
		categorieFromDb = categorieDAO.find(categorie2.getCategorieId());
		Assert.assertNotNull(categorieFromDb);

		categorie.setCategorieId(categorie2.getCategorieId());
		categorieDAO.remove(categorie);

		categorieFromDb = categorieDAO.find(categorie2.getCategorieId());
		Assert.assertNull(categorieFromDb);
	}

	@Test
	public void testEdit() throws Exception {
		final Categorie categorie = new CategorieTestdataBuilder(em, utx).buildAndSave();

		categorie.setCategorieLibele("Nouveau Libelle");
		categorieDAO.edit(categorie);

		Categorie categorieFromDb = categorieDAO.find(categorie.getCategorieId());

		Assert.assertEquals(categorieFromDb.getCategorieLibele(), "Nouveau Libelle");
	}

	@Test
	public void testFindAll() throws Exception {
		new CategorieTestdataBuilder(em, utx).buildAndSave();
		new CategorieTestdataBuilder(em, utx).buildAndSave();

		List<Categorie> all = categorieDAO.findAll();

		Assert.assertThat(all.size(), equalTo(2));
	}

	@Test
	public void testCount() throws Exception {
		new CategorieTestdataBuilder(em, utx).buildAndSave();
		new CategorieTestdataBuilder(em, utx).buildAndSave();

		int count = categorieDAO.count();

		Assert.assertThat(count, equalTo(2));
	}
	/*@Rule
	public DatabaseRule databaseRule = new DatabaseRule();

	@Rule
	public NeedleRule needleRule = new NeedleRule(databaseRule);

	@Inject
	private EntityManager entityManager;

	@Inject
	private EntityTransaction entityTransaction;
        
	@ObjectUnderTest
	private CategorieDAO categorieDAO;

	@Test
	public void testCreate() {
		Categorie categorie = new CategorieTestdataBuilder(entityManager).build();

		Categorie categorieFromDb = null;
		try {
			categorieFromDb = categorieDAO.create(categorie);
			Assert.assertEquals(categorie, categorieFromDb);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void testFind() {
		Categorie categorie = new CategorieTestdataBuilder(entityManager).buildAndSave();

		Categorie categorieFromDb = categorieDAO.find(categorie.getCategorieId());

		Assert.assertEquals(categorie.getCategorieId(), categorieFromDb.getCategorieId());
		Assert.assertNotSame(categorie, categorieFromDb);

		categorieFromDb = categorieDAO.find(-1);

		Assert.assertNull( categorieFromDb);
	}*/

//	@Test
//	public void testRemove() {
//		Categorie categorie = new CategorieTestdataBuilder(entityManager).buildAndSave();
//		categorieDAO.remove(categorie);
//		Categorie categorieFromDb = categorieDAO.find(categorie.getCategorieId());
//		Assert.assertNull(categorieFromDb);
//
//		Categorie categorie2 = new CategorieTestdataBuilder(entityManager).buildAndSave();
//		categorie = new CategorieTestdataBuilder(entityManager).build();
//
//		try {
//			categorie.setCategorieId(-1);
//			categorieDAO.remove(categorie);
//			Assert.fail();
//		} catch (Exception e) {
//			Assert.assertTrue(true);
//		}
//
//		categorieFromDb = categorieDAO.find(categorie2.getCategorieId());
//		Assert.assertNotNull(categorieFromDb);
//
//		categorie.setCategorieId(categorie2.getCategorieId());
//		categorieDAO.remove(categorie);
//
//		categorieFromDb = categorieDAO.find(categorie2.getCategorieId());
//		Assert.assertNull(categorieFromDb);
//	}

//	@Test
//	@Transactional
//	public void testEdit() {
//		final Categorie categorie = new CategorieTestdataBuilder(entityManager).buildAndSave();
//
//		categorie.setCategorieLibele("Nouveau Libelle");
//
//		databaseRule.getTransactionHelper().executeInTransaction(new VoidRunnable() {
//			@Override
//			public void doRun(EntityManager entityManager) throws Exception {
//				categorieDAO.edit(categorie);
//			}
//		});
//
//		Categorie categorieFromDb = categorieDAO.find(categorie.getCategorieId());
//
//		Assert.assertEquals(categorieFromDb.getCategorieLibele(), "Nouveau Libelle");
//	}

	/*@Test
	public void testFindAll() {
		new CategorieTestdataBuilder(entityManager).buildAndSave();
		new CategorieTestdataBuilder(entityManager).buildAndSave();

		List<Categorie> all = categorieDAO.findAll();

		Assert.assertThat(all.size(), greaterThanOrEqualTo(2));
	}

	@Test
	public void testCount() {
		new CategorieTestdataBuilder(entityManager).buildAndSave();
		new CategorieTestdataBuilder(entityManager).buildAndSave();

		int count = categorieDAO.count();

		Assert.assertThat(count, greaterThanOrEqualTo(2));
	}*/
}