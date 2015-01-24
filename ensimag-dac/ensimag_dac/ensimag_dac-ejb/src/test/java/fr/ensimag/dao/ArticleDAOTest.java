package fr.ensimag.dao;

import de.akquinet.jbosscc.needle.annotation.ObjectUnderTest;
import de.akquinet.jbosscc.needle.junit.DatabaseRule;
import de.akquinet.jbosscc.needle.junit.NeedleRule;
import fr.ensimag.entity.Article;
import fr.ensimag.entity.testdata.ArticleTestdataBuilder;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public class ArticleDAOTest  {
	@Rule
	public DatabaseRule databaseRule = new DatabaseRule();

	@Rule
	public NeedleRule needleRule = new NeedleRule(databaseRule);

	@ObjectUnderTest
	private ArticleDAO articleDAO;

	/*@Test
	public void testFindByUsername() throws Exception {
		final User user = new UserTestdataBuilder(
				databaseRule.getEntityManager()).buildAndSave();

		User findBy = articleDAO.findBy(user.getUsername(), user.getPassword());

		Assert.assertEquals(user.getId(), findBy.getId());
	}*/

	@Test
	public void testFindAll() throws Exception {
		new ArticleTestdataBuilder(databaseRule.getEntityManager()).buildAndSave();

		List<Article> all = articleDAO.findAll();

		Assert.assertEquals(1, all.size());
	}
}