package fr.ensimag.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "commande")
public class CommandeEntity {
	private int                               commandeId;
	private Date                              commandeDate;
	private String                            commandeDescription;
	private double                            commandeTotale;
	private Collection<ArticlecommandeEntity> articlecommandesByCommandeId;
	private UtilisateurEntity                 utilisateurByUtilisateurId;
	private FactureEntity                     factureByFactureId;
	private Collection<FactureEntity>         facturesByCommandeId;
	private Collection<UtilisateurEntity>     utilisateursByCommandeId;

	@Id
	@Column(name = "COMMANDE_ID")
	public int getCommandeId() {
		return commandeId;
	}

	public void setCommandeId(int commandeId) {
		this.commandeId = commandeId;
	}

	@Basic
	@Column(name = "COMMANDE_DATE")
	public Date getCommandeDate() {
		return commandeDate;
	}

	public void setCommandeDate(Date commandeDate) {
		this.commandeDate = commandeDate;
	}

	@Basic
	@Column(name = "COMMANDE_DESCRIPTION")
	public String getCommandeDescription() {
		return commandeDescription;
	}

	public void setCommandeDescription(String commandeDescription) {
		this.commandeDescription = commandeDescription;
	}

	@Basic
	@Column(name = "COMMANDE_TOTALE")
	public double getCommandeTotale() {
		return commandeTotale;
	}

	public void setCommandeTotale(double commandeTotale) {
		this.commandeTotale = commandeTotale;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		CommandeEntity that = (CommandeEntity) o;

		if (commandeId != that.commandeId) {
			return false;
		}
		if (Double.compare(that.commandeTotale, commandeTotale) != 0) {
			return false;
		}
		if (commandeDate != null ? !commandeDate.equals(that.commandeDate) : that.commandeDate != null) {
			return false;
		}
		if (commandeDescription != null ? !commandeDescription.equals(that.commandeDescription) : that.commandeDescription != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = commandeId;
		result = 31 * result + (commandeDate != null ? commandeDate.hashCode() : 0);
		result = 31 * result + (commandeDescription != null ? commandeDescription.hashCode() : 0);
		temp = Double.doubleToLongBits(commandeTotale);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@OneToMany(mappedBy = "commandeByCommandeId")
	public Collection<ArticlecommandeEntity> getArticlecommandesByCommandeId() {
		return articlecommandesByCommandeId;
	}

	public void setArticlecommandesByCommandeId(Collection<ArticlecommandeEntity> articlecommandesByCommandeId) {
		this.articlecommandesByCommandeId = articlecommandesByCommandeId;
	}

	@ManyToOne
	@JoinColumn(name = "UTILISATEUR_ID", referencedColumnName = "UTILISATEUR_ID", nullable = false)
	public UtilisateurEntity getUtilisateurByUtilisateurId() {
		return utilisateurByUtilisateurId;
	}

	public void setUtilisateurByUtilisateurId(UtilisateurEntity utilisateurByUtilisateurId) {
		this.utilisateurByUtilisateurId = utilisateurByUtilisateurId;
	}

	@ManyToOne
	@JoinColumn(name = "FACTURE_ID", referencedColumnName = "FACTURE_ID")
	public FactureEntity getFactureByFactureId() {
		return factureByFactureId;
	}

	public void setFactureByFactureId(FactureEntity factureByFactureId) {
		this.factureByFactureId = factureByFactureId;
	}

	@OneToMany(mappedBy = "commandeByCommandeId")
	public Collection<FactureEntity> getFacturesByCommandeId() {
		return facturesByCommandeId;
	}

	public void setFacturesByCommandeId(Collection<FactureEntity> facturesByCommandeId) {
		this.facturesByCommandeId = facturesByCommandeId;
	}

	@OneToMany(mappedBy = "commandeByCommandeId")
	public Collection<UtilisateurEntity> getUtilisateursByCommandeId() {
		return utilisateursByCommandeId;
	}

	public void setUtilisateursByCommandeId(Collection<UtilisateurEntity> utilisateursByCommandeId) {
		this.utilisateursByCommandeId = utilisateursByCommandeId;
	}
}
