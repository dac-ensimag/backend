package fr.ensimag.vo;

import java.util.Date;
import java.util.List;

public class CommandeVO implements IValueObject {

	private Integer             commandeId;
	private Date                commandeDate;
	private String              commandeDescription;
	private double              commandeTotale;
	private List<ArticleVO>     articleList;
	private Integer             utilisateurId;
	private FactureVO           facture;

	public CommandeVO() {
	}

	public Integer getCommandeId() {
		return commandeId;
	}

	public void setCommandeId(Integer commandeId) {
		this.commandeId = commandeId;
	}

	public Date getCommandeDate() {
		return commandeDate;
	}

	public void setCommandeDate(Date commandeDate) {
		this.commandeDate = commandeDate;
	}

	public String getCommandeDescription() {
		return commandeDescription;
	}

	public void setCommandeDescription(String commandeDescription) {
		this.commandeDescription = commandeDescription;
	}

	public double getCommandeTotale() {
		return commandeTotale;
	}

	public void setCommandeTotale(double commandeTotale) {
		this.commandeTotale = commandeTotale;
	}

	public List<ArticleVO> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<ArticleVO> articleList) {
		this.articleList = articleList;
	}

	public Integer getUtilisateurId() {
		return utilisateurId;
	}

	public void setUtilisateurId(Integer utilisateurId) {
		this.utilisateurId = utilisateurId;
	}

	public FactureVO getFacture() {
		return facture;
	}

	public void setFacture(FactureVO facture) {
		this.facture = facture;
	}

}
