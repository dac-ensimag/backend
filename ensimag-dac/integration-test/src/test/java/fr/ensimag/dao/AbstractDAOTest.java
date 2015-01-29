package fr.ensimag.dao;

public class AbstractDAOTest /*extends DatabaseTestcase*/ {
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