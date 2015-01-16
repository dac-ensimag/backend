package fr.ensimag.entity;

import fr.ensimag.vo.ArticleVO;
import fr.ensimag.vo.CommandeVO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ARTICLE")
public class Article implements Serializable, IEntity<ArticleVO> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@NotNull
	@Column(name = "ARTICLE_ID")
	private Integer articleId;

	@Basic(optional = false)
	@NotNull
	@Lob
	@Column(name = "ARTICLE_LIBELE")
	private String articleLibele;

	@Basic(optional = false)
	@NotNull
	@Column(name = "ARTICLE_PRIX")
	private float articlePrix;

	@Basic(optional = false)
	@NotNull
	@Column(name = "ARTICLE_DISPONIBILITE")
	private boolean articleDisponibilite;

	@Basic(optional = false)
	@NotNull
	@Lob
	@Column(name = "ARTICLE_DESCRIPTION")
	private String articleDescription;

	@JoinTable(name = "ARTICLECOMMANDE", joinColumns = {
			@JoinColumn(name = "ARTICLE_ID", referencedColumnName = "ARTICLE_ID")}, inverseJoinColumns = {
			@JoinColumn(name = "COMMANDE_ID", referencedColumnName = "COMMANDE_ID")})
	@ManyToMany
	private List<Commande> commandeList;

	@JoinColumn(name = "CATEGORIE_ID", referencedColumnName = "CATEGORIE_ID")
	@ManyToOne(optional = false)
	private Categorie categorie;

	public Article() {
	}

	public Article(Integer articleId) {
		this.articleId = articleId;
	}

	public Article(Integer articleId, String articleLibele, float articlePrix, boolean articleDisponibilite, String articleDescription) {
		this.articleId = articleId;
		this.articleLibele = articleLibele;
		this.articlePrix = articlePrix;
		this.articleDisponibilite = articleDisponibilite;
		this.articleDescription = articleDescription;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getArticleLibele() {
		return articleLibele;
	}

	public void setArticleLibele(String articleLibele) {
		this.articleLibele = articleLibele;
	}

	public float getArticlePrix() {
		return articlePrix;
	}

	public void setArticlePrix(float articlePrix) {
		this.articlePrix = articlePrix;
	}

	public boolean getArticleDisponibilite() {
		return articleDisponibilite;
	}

	public void setArticleDisponibilite(boolean articleDisponibilite) {
		this.articleDisponibilite = articleDisponibilite;
	}

	public String getArticleDescription() {
		return articleDescription;
	}

	public void setArticleDescription(String articleDescription) {
		this.articleDescription = articleDescription;
	}

	public List<Commande> getCommandeList() {
		return commandeList;
	}

	public void setCommandeList(List<Commande> commandeList) {
		this.commandeList = commandeList;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (articleId != null ? articleId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Article)) {
			return false;
		}
		Article other = (Article) object;
		if ((this.articleId == null && other.articleId != null) || (this.articleId != null && !this.articleId.equals(other.articleId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "fr.ensimag.entity.Article[ articleId=" + articleId + " ]";
	}

	@Override
	public ArticleVO toVO() {
		ArticleVO vo = new ArticleVO();
		vo.setArticleId(getArticleId());
		vo.setArticleDescription(getArticleDescription());
		vo.setArticleDisponibilite(getArticleDisponibilite());
		vo.setArticleLibele(getArticleLibele());
		vo.setArticlePrix(getArticlePrix());

		List<CommandeVO> commandVOList = new ArrayList<>();
		for (Commande commande : getCommandeList()) {
			commandVOList.add(commande.toVO());
		}
		vo.setCommandeList(commandVOList);

		return vo;
	}

}
