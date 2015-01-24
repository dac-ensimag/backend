package fr.ensimag.dao;

import de.akquinet.jbosscc.needle.annotation.ObjectUnderTest;
import de.akquinet.jbosscc.needle.junit.DatabaseRule;
import de.akquinet.jbosscc.needle.junit.NeedleRule;
import fr.ensimag.entity.Categorie;
import fr.ensimag.entity.testdata.ArticleTestdataBuilder;
import fr.ensimag.entity.testdata.CategorieTestdataBuilder;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

import javax.persistence.EntityManager;
import java.util.List;

public class CategorieDAOTest {
	@Rule
	public  DatabaseRule  databaseRule  = new DatabaseRule();
	private EntityManager entityManager = databaseRule.getEntityManager();

	@Rule
	public  NeedleRule    needleRule    = new NeedleRule(databaseRule);

	@ObjectUnderTest
	private CategorieDAO categorieDAO;

	@Test
	public void testCreate() throws Exception {
		Categorie categorie = new CategorieTestdataBuilder(entityManager).build();

		Categorie categorieFromDb = categorieDAO.create(categorie);

		Assert.assertEquals(categorie, categorieFromDb);
	}

	@Test
	public void testFind() throws Exception {
		Categorie categorie = new CategorieTestdataBuilder(entityManager).buildAndSave();

		Categorie categorieFromDb = categorieDAO.find(categorie.getCategorieId());

		Assert.assertEquals(categorie.getCategorieId(), categorieFromDb.getCategorieId());
		Assert.assertNotSame(categorie, categorieFromDb);
	}

	@Test
	public void testRemove() throws Exception {
		Categorie categorie = new CategorieTestdataBuilder(entityManager).buildAndSave();

		categorieDAO.remove(categorie);

		Categorie categorieFromDb = categorieDAO.find(categorie.getCategorieId());

		Assert.assertNull(categorieFromDb);
	}

	@Test
	public void testFindAll() throws Exception {
		new CategorieTestdataBuilder(entityManager).buildAndSave();
		new CategorieTestdataBuilder(entityManager).buildAndSave();

		List<Categorie> all = categorieDAO.findAll();

		Assert.assertThat(all.size(), greaterThanOrEqualTo(2));
	}
}