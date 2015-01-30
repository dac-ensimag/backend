package fr.ensimag.test.testdata.entity;

import fr.ensimag.entity.Commande;
import fr.ensimag.entity.Article;
import fr.ensimag.entity.Categorie;
import fr.ensimag.test.testdata.AbstractTestdataBuilder;

import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
import java.util.ArrayList;

public class ArticleTestdataBuilder extends AbstractTestdataBuilder<Article> {

	private Categorie withCategorie;
	private String    withDescription;
	private String    withLibele;
	private String    withImg;
	private boolean withDisponibilite = true;
	private float   withPrix          = 499.99f;

	public ArticleTestdataBuilder() {
		super();
	}

	public ArticleTestdataBuilder(EntityManager entityManager, UserTransaction userTransaction) {
		super(entityManager, userTransaction);
	}

	public ArticleTestdataBuilder withCategorie(Categorie categorie) {
		this.withCategorie = categorie;
		return this;
	}

	public ArticleTestdataBuilder withDescription(String description) {
		this.withDescription = description;
		return this;
	}

	public ArticleTestdataBuilder withLibele(String libele) {
		this.withLibele = libele;
		return this;
	}

	public ArticleTestdataBuilder withImg(String libele) {
		this.withImg = libele;
		return this;
	}

	public ArticleTestdataBuilder withDisponibilite(boolean disponibilite) {
		this.withDisponibilite = disponibilite;
		return this;
	}

	public ArticleTestdataBuilder withPrix(float prix) {
		this.withPrix = prix;
		return this;
	}

	private String getArticleDescription() {
		return withDescription != null ? withDescription : "Super article très utile";
	}

	private String getArticleImg() {
		return withImg != null ? withImg : "test";
	}

	private String getArticleLibele() {
		return withLibele != null ? withLibele : "Super article";
	}

	private Categorie getArticleCategorie() {
		if (withCategorie != null) {
			return withCategorie;
		}

		return hasEntityManager() ? new CategorieTestdataBuilder(getEntityManager(), getUserTransaction()).buildAndSave() : new CategorieTestdataBuilder().build();
	}

	private boolean getArticleDisponibilite() {
		return withDisponibilite;
	}

	private float getArticlePrix() {
		return withPrix;
	}

	@Override
	public Article build() {
		final Article article = new Article();
		article.setArticleDescription(getArticleDescription());
		article.setArticleDisponibilite(getArticleDisponibilite());
		article.setArticleLibele(getArticleLibele());
		article.setArticlePrix(getArticlePrix());
		article.setCategorie(getArticleCategorie());
		article.setArticleImg(getArticleImg());
		article.setCommandeList(new ArrayList<Commande>());

		return article;
	}
}
