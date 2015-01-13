package fr.ensimag.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "article")
public class ArticleEntity {
	private int                               articleId;
	private String                            articleLibele;
	private float                             articlePrix;
	private byte                              articleDisponibilite;
	private String                            articleDescription;
	private CategorieEntity                   categorieByCategorieId;
	private Collection<ArticlecommandeEntity> articlecommandesByArticleId;

	@Id
	@Column(name = "ARTICLE_ID")
	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	@Basic
	@Column(name = "ARTICLE_LIBELE")
	public String getArticleLibele() {
		return articleLibele;
	}

	public void setArticleLibele(String articleLibele) {
		this.articleLibele = articleLibele;
	}

	@Basic
	@Column(name = "ARTICLE_PRIX")
	public float getArticlePrix() {
		return articlePrix;
	}

	public void setArticlePrix(float articlePrix) {
		this.articlePrix = articlePrix;
	}

	@Basic
	@Column(name = "ARTICLE_DISPONIBILITE")
	public byte getArticleDisponibilite() {
		return articleDisponibilite;
	}

	public void setArticleDisponibilite(byte articleDisponibilite) {
		this.articleDisponibilite = articleDisponibilite;
	}

	@Basic
	@Column(name = "ARTICLE_DESCRIPTION")
	public String getArticleDescription() {
		return articleDescription;
	}

	public void setArticleDescription(String articleDescription) {
		this.articleDescription = articleDescription;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		ArticleEntity that = (ArticleEntity) o;

		if (articleDisponibilite != that.articleDisponibilite) {
			return false;
		}
		if (articleId != that.articleId) {
			return false;
		}
		if (Float.compare(that.articlePrix, articlePrix) != 0) {
			return false;
		}
		if (articleDescription != null ? !articleDescription.equals(that.articleDescription) : that.articleDescription != null) {
			return false;
		}
		if (articleLibele != null ? !articleLibele.equals(that.articleLibele) : that.articleLibele != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = articleId;
		result = 31 * result + (articleLibele != null ? articleLibele.hashCode() : 0);
		result = 31 * result + (articlePrix != +0.0f ? Float.floatToIntBits(articlePrix) : 0);
		result = 31 * result + (int) articleDisponibilite;
		result = 31 * result + (articleDescription != null ? articleDescription.hashCode() : 0);
		return result;
	}

	@ManyToOne
	@JoinColumn(name = "CATEGORIE_ID", referencedColumnName = "CATEGORIE_ID", nullable = false)
	public CategorieEntity getCategorieByCategorieId() {
		return categorieByCategorieId;
	}

	public void setCategorieByCategorieId(CategorieEntity categorieByCategorieId) {
		this.categorieByCategorieId = categorieByCategorieId;
	}

	@OneToMany(mappedBy = "articleByArticleId")
	public Collection<ArticlecommandeEntity> getArticlecommandesByArticleId() {
		return articlecommandesByArticleId;
	}

	public void setArticlecommandesByArticleId(Collection<ArticlecommandeEntity> articlecommandesByArticleId) {
		this.articlecommandesByArticleId = articlecommandesByArticleId;
	}
}
