package fr.ensimag.vo;

import java.util.Date;
import java.util.List;

public class CommandeVO implements IValueObject {

	private Integer commandeId;
	private Date commandeDate;
	private String commandeDescription;
	private double commandeTotale;
	private List<ArticleVO> articleList;
	private Integer utilisateurId;
	private FactureVO facture;

	public CommandeVO() {
	}

	public Integer getCommandeId() {
		return this.commandeId;
	}

	public void setCommandeId(final Integer commandeId) {
		this.commandeId = commandeId;
	}

	public Date getCommandeDate() {
		return this.commandeDate;
	}

	public void setCommandeDate(final Date commandeDate) {
		this.commandeDate = commandeDate;
	}

	public String getCommandeDescription() {
		return this.commandeDescription;
	}

	public void setCommandeDescription(final String commandeDescription) {
		this.commandeDescription = commandeDescription;
	}

	public double getCommandeTotale() {
		return this.commandeTotale;
	}

	public void setCommandeTotale(final double commandeTotale) {
		this.commandeTotale = commandeTotale;
	}

	public List<ArticleVO> getArticleList() {
		return this.articleList;
	}

	public void setArticleList(final List<ArticleVO> articleList) {
		this.articleList = articleList;
	}

	public Integer getUtilisateurId() {
		return this.utilisateurId;
	}

	public void setUtilisateurId(final Integer utilisateurId) {
		this.utilisateurId = utilisateurId;
	}

	public FactureVO getFacture() {
		return this.facture;
	}

	public void setFacture(final FactureVO facture) {
		this.facture = facture;
	}

}
