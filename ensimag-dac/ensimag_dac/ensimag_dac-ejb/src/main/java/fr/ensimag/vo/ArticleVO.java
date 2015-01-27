package fr.ensimag.vo;

import java.util.List;

public class ArticleVO implements IValueObject {

	private Integer          articleId;
	private String           articleLibele;
	private float            articlePrix;
	private boolean          articleDisponibilite;
	private String           articleDescription;
	private List<CommandeVO> commandeList;
	private Integer      categorieId;

	public ArticleVO() {
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

	public List<CommandeVO> getCommandeList() {
		return commandeList;
	}

	public void setCommandeList(List<CommandeVO> commandeList) {
		this.commandeList = commandeList;
	}

	public Integer getCategorieId() {
		return categorieId;
	}

	public void setCategorieId(Integer categorieId) {
		this.categorieId = categorieId;
	}

}
