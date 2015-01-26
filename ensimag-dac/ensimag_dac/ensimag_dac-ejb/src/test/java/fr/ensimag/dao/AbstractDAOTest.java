package fr.ensimag.dao;

import de.akquinet.jbosscc.needle.annotation.ObjectUnderTest;
import de.akquinet.jbosscc.needle.db.operation.hsql.HSQLDeleteOperation;
import de.akquinet.jbosscc.needle.db.transaction.TransactionHelper;
import de.akquinet.jbosscc.needle.db.transaction.VoidRunnable;
import de.akquinet.jbosscc.needle.junit.DatabaseRule;
import de.akquinet.jbosscc.needle.junit.NeedleRule;
import de.akquinet.jbosscc.needle.testng.DatabaseTestcase;
import fr.ensimag.entity.Categorie;
import fr.ensimag.entity.testdata.CategorieTestdataBuilder;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;
import java.util.List;

public class AbstractDAOTest extends DatabaseTestcase {
	@Rule
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

		Categorie categorieFromDb = categorieDAO.create(categorie);

		Assert.assertEquals(categorie, categorieFromDb);
	}

	@Test
	public void testFind() {
		Categorie categorie = new CategorieTestdataBuilder(entityManager).buildAndSave();

		Categorie categorieFromDb = categorieDAO.find(categorie.getCategorieId());

		Assert.assertEquals(categorie.getCategorieId(), categorieFromDb.getCategorieId());
		Assert.assertNotSame(categorie, categorieFromDb);

		categorieFromDb = categorieDAO.find(-1);

		Assert.assertNull( categorieFromDb);
	}

	@Test
	public void testRemove() {
		Categorie categorie = new CategorieTestdataBuilder(entityManager).buildAndSave();
		categorieDAO.remove(categorie);
		Categorie categorieFromDb = categorieDAO.find(categorie.getCategorieId());
		Assert.assertNull(categorieFromDb);

		Categorie categorie2 = new CategorieTestdataBuilder(entityManager).buildAndSave();
		categorie = new CategorieTestdataBuilder(entityManager).build();

		try {
			categorie.setCategorieId(-1);
			categorieDAO.remove(categorie);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertTrue(true);
		}

		categorieFromDb = categorieDAO.find(categorie2.getCategorieId());
		Assert.assertNotNull(categorieFromDb);

		categorie.setCategorieId(categorie2.getCategorieId());
		categorieDAO.remove(categorie);

		categorieFromDb = categorieDAO.find(categorie2.getCategorieId());
		Assert.assertNull(categorieFromDb);
	}

	@Test
	@Transactional
	public void testEdit() throws Exception {
		final Categorie categorie = new CategorieTestdataBuilder(entityManager).buildAndSave();

		categorie.setCategorieLibele("Nouveau Libelle");

		databaseRule.getTransactionHelper().executeInTransaction(new VoidRunnable() {
			@Override
			public void doRun(EntityManager entityManager) throws Exception {
				categorieDAO.edit(categorie);
			}
		});

		Categorie categorieFromDb = categorieDAO.find(categorie.getCategorieId());

		Assert.assertEquals(categorieFromDb.getCategorieLibele(), "Nouveau Libelle");
	}

	@Test
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
	}
}