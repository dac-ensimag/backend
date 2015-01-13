package fr.ensimag.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "categorie")
public class CategorieEntity {
	private int                       categorieId;
	private String                    categorieLibele;
	private Collection<ArticleEntity> articlesByCategorieId;

	@Id
	@Column(name = "CATEGORIE_ID")
	public int getCategorieId() {
		return categorieId;
	}

	public void setCategorieId(int categorieId) {
		this.categorieId = categorieId;
	}

	@Basic
	@Column(name = "CATEGORIE_LIBELE")
	public String getCategorieLibele() {
		return categorieLibele;
	}

	public void setCategorieLibele(String categorieLibele) {
		this.categorieLibele = categorieLibele;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		CategorieEntity that = (CategorieEntity) o;

		if (categorieId != that.categorieId) {
			return false;
		}
		if (categorieLibele != null ? !categorieLibele.equals(that.categorieLibele) : that.categorieLibele != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = categorieId;
		result = 31 * result + (categorieLibele != null ? categorieLibele.hashCode() : 0);
		return result;
	}

	@OneToMany(mappedBy = "categorieByCategorieId")
	public Collection<ArticleEntity> getArticlesByCategorieId() {
		return articlesByCategorieId;
	}

	public void setArticlesByCategorieId(Collection<ArticleEntity> articlesByCategorieId) {
		this.articlesByCategorieId = articlesByCategorieId;
	}
}
