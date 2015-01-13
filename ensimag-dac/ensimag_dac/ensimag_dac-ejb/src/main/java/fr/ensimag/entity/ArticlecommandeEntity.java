package fr.ensimag.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "articlecommande")
public class ArticlecommandeEntity {
	private CommandeEntity commandeByCommandeId;
	private ArticleEntity  articleByArticleId;

	@ManyToOne
	@JoinColumn(name = "COMMANDE_ID", referencedColumnName = "COMMANDE_ID")
	public CommandeEntity getCommandeByCommandeId() {
		return commandeByCommandeId;
	}

	public void setCommandeByCommandeId(CommandeEntity commandeByCommandeId) {
		this.commandeByCommandeId = commandeByCommandeId;
	}

	@ManyToOne
	@JoinColumn(name = "ARTICLE_ID", referencedColumnName = "ARTICLE_ID")
	public ArticleEntity getArticleByArticleId() {
		return articleByArticleId;
	}

	public void setArticleByArticleId(ArticleEntity articleByArticleId) {
		this.articleByArticleId = articleByArticleId;
	}
}
