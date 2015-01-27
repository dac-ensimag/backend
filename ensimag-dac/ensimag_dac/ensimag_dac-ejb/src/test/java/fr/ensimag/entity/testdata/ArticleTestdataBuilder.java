package fr.ensimag.entity.testdata;

import de.akquinet.jbosscc.needle.db.testdata.AbstractTestdataBuilder;
import fr.ensimag.entity.Article;
import fr.ensimag.entity.Categorie;

import javax.persistence.EntityManager;

public class ArticleTestdataBuilder extends AbstractTestdataBuilder<Article> {

	private static final String DEFAULT_PASSWORD = "secret";
	private Categorie withCategorie;
	private String    withDescription;
	private String    withLibele;
	private boolean withDisponibilite = true;
	private float   withPrix          = 499.99f;

	public ArticleTestdataBuilder() {
		super();
	}

	public ArticleTestdataBuilder(EntityManager entityManager) {
		super(entityManager);
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

	private String getArticleLibele() {
		return withLibele != null ? withLibele : "Super article";
	}

	private Categorie getArticleCategorie() {
		if (withCategorie != null) {
			return withCategorie;
		}

		return hasEntityManager() ? new CategorieTestdataBuilder(getEntityManager())
				.buildAndSave() : new CategorieTestdataBuilder().build();
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

		return article;
	}
}
