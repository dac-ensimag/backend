package fr.ensimag.entity;

import fr.ensimag.vo.ArticleVO;
import fr.ensimag.vo.CategorieVO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CATEGORIE")
public class Categorie implements Serializable, IEntity<CategorieVO> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@NotNull
	@Column(name = "CATEGORIE_ID")
	private Integer categorieId;

	@Basic(optional = false)
	@NotNull
	@Lob
	@Column(name = "CATEGORIE_LIBELE")
	private String categorieLibele;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "categorie")
	private List<Article> articleList;

	public Categorie() {
	}

	public Categorie(Integer categorieId) {
		this.categorieId = categorieId;
	}

	public Categorie(Integer categorieId, String categorieLibele) {
		this.categorieId = categorieId;
		this.categorieLibele = categorieLibele;
	}

	@Override
	public int getId() {
		return this.getCategorieId();
	}

	public Integer getCategorieId() {
		return categorieId;
	}

	public void setCategorieId(Integer categorieId) {
		this.categorieId = categorieId;
	}

	public String getCategorieLibele() {
		return categorieLibele;
	}

	public void setCategorieLibele(String categorieLibele) {
		this.categorieLibele = categorieLibele;
	}

	public List<Article> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (categorieId != null ? categorieId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Categorie)) {
			return false;
		}
		Categorie other = (Categorie) object;
		if ((this.categorieId == null && other.categorieId != null) || (this.categorieId != null && !this.categorieId.equals(other.categorieId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "fr.ensimag.entity.Categorie[ categorieId=" + categorieId + " ]";
	}

	@Override
	public CategorieVO toVO() {
		CategorieVO vo = new CategorieVO();
		vo.setCategorieId(getCategorieId());
		vo.setCategorieLibele(getCategorieLibele());

		List<ArticleVO> articleVOList = new ArrayList<>();
		for (Article article : getArticleList()) {
			articleVOList.add(article.toVO());
		}
		vo.setArticleList(articleVOList);

		return vo;
	}

}
