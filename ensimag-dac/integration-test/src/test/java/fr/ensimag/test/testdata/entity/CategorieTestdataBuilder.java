package fr.ensimag.test.testdata.entity;

import fr.ensimag.entity.Article;
import fr.ensimag.entity.Categorie;
import fr.ensimag.test.testdata.AbstractTestdataBuilder;

import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
import java.util.ArrayList;

public class CategorieTestdataBuilder extends AbstractTestdataBuilder<Categorie> {

	private String withLibele;

	public CategorieTestdataBuilder() {
		super();
	}

	public CategorieTestdataBuilder(EntityManager entityManager, UserTransaction userTransaction) {
		super(entityManager, userTransaction);
	}

	public CategorieTestdataBuilder withLibele(String libele) {
		this.withLibele = libele;
		return this;
	}

	private String getCategorieLibele() {
		return withLibele != null ? withLibele : "utilitaire";
	}

	@Override
	public Categorie build() {
		final Categorie categorie = new Categorie();
		categorie.setCategorieLibele(getCategorieLibele());
		categorie.setArticleList(new ArrayList<Article>());

		return categorie;
	}
}
