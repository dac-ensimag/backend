package fr.ensimag.dao;

import fr.ensimag.entity.Role;
import fr.ensimag.test.testdata.entity.RoleTestdataBuilder;
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
public class RoleDAOTest {
	@EJB
	private RoleDAOLocal roleDAO;

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
		em.createQuery("delete from Role").executeUpdate();
		utx.commit();
	}

	@Test
	public void testCreate() throws Exception {
		Role categorie = new RoleTestdataBuilder(em, utx).build();
		Role roleFromDb;
		roleFromDb = roleDAO.create(categorie);

		Assert.assertEquals(categorie, roleFromDb);
	}

	@Test
	public void testFind() throws Exception {
		Role categorie = new RoleTestdataBuilder(em, utx).buildAndSave();

		Role roleFromDb = roleDAO.find(categorie.getRoleId());

		Assert.assertEquals(categorie.getRoleId(), roleFromDb.getRoleId());
		Assert.assertNotSame(categorie, roleFromDb);

		roleFromDb = roleDAO.find(-1);

		Assert.assertNull(roleFromDb);
	}

	@Test
	public void testRemove() throws Exception {
		Role categorie;

		// Delete an existing entity
		categorie = new RoleTestdataBuilder(em, utx).buildAndSave();
		roleDAO.remove(categorie);
		Role roleFromDb = roleDAO.find(categorie.getRoleId());
		Assert.assertNull(roleFromDb);

		// Delete a non existent entity
		categorie = new RoleTestdataBuilder(em, utx).build();
		try {
			categorie.setRoleId(-1);
			roleDAO.remove(categorie);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertTrue(true);
		}

		// Delete a manually crafted entity (nont managed)
		Role categorie2 = new RoleTestdataBuilder(em, utx).buildAndSave();
		roleFromDb = roleDAO.find(categorie2.getRoleId());
		Assert.assertNotNull(roleFromDb);

		categorie.setRoleId(categorie2.getRoleId());
		roleDAO.remove(categorie);

		roleFromDb = roleDAO.find(categorie2.getRoleId());
		Assert.assertNull(roleFromDb);
	}

	@Test
	public void testEdit() throws Exception {
		final Role categorie = new RoleTestdataBuilder(em, utx).buildAndSave();

		categorie.setRoleLibele("Nouveau Libelle");
		roleDAO.edit(categorie);

		Role roleFromDb = roleDAO.find(categorie.getRoleId());

		Assert.assertEquals(roleFromDb.getRoleLibele(), "Nouveau Libelle");
	}

	@Test
	public void testFindAll() throws Exception {
		new RoleTestdataBuilder(em, utx).buildAndSave();
		new RoleTestdataBuilder(em, utx).buildAndSave();

		List<Role> all = roleDAO.findAll();

		Assert.assertThat(all.size(), equalTo(2));
	}

	@Test
	public void testCount() throws Exception {
		new RoleTestdataBuilder(em, utx).buildAndSave();
		new RoleTestdataBuilder(em, utx).buildAndSave();

		int count = roleDAO.count();

		Assert.assertThat(count, equalTo(2));
	}
}