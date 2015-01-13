package fr.ensimag.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "utilisateur")
public class UtilisateurEntity {
	private int                        utilisateurId;
	private String                     utilisateurMail;
	private String                     utilisateurLogin;
	private String                     utilisateurPass;
	private String                     utilisateurNom;
	private String                     utilisateurPrenom;
	private String                     utilisateurTel;
	private String                     utilisateurAdresse;
	private String                     utilisateurCp;
	private Collection<CommandeEntity> commandesByUtilisateurId;
	private CommandeEntity             commandeByCommandeId;
	private RoleEntity                 roleByRoleId;

	@Id
	@Column(name = "UTILISATEUR_ID")
	public int getUtilisateurId() {
		return utilisateurId;
	}

	public void setUtilisateurId(int utilisateurId) {
		this.utilisateurId = utilisateurId;
	}

	@Basic
	@Column(name = "UTILISATEUR_MAIL")
	public String getUtilisateurMail() {
		return utilisateurMail;
	}

	public void setUtilisateurMail(String utilisateurMail) {
		this.utilisateurMail = utilisateurMail;
	}

	@Basic
	@Column(name = "UTILISATEUR_LOGIN")
	public String getUtilisateurLogin() {
		return utilisateurLogin;
	}

	public void setUtilisateurLogin(String utilisateurLogin) {
		this.utilisateurLogin = utilisateurLogin;
	}

	@Basic
	@Column(name = "UTILISATEUR_PASS")
	public String getUtilisateurPass() {
		return utilisateurPass;
	}

	public void setUtilisateurPass(String utilisateurPass) {
		this.utilisateurPass = utilisateurPass;
	}

	@Basic
	@Column(name = "UTILISATEUR_NOM")
	public String getUtilisateurNom() {
		return utilisateurNom;
	}

	public void setUtilisateurNom(String utilisateurNom) {
		this.utilisateurNom = utilisateurNom;
	}

	@Basic
	@Column(name = "UTILISATEUR_PRENOM")
	public String getUtilisateurPrenom() {
		return utilisateurPrenom;
	}

	public void setUtilisateurPrenom(String utilisateurPrenom) {
		this.utilisateurPrenom = utilisateurPrenom;
	}

	@Basic
	@Column(name = "UTILISATEUR_TEL")
	public String getUtilisateurTel() {
		return utilisateurTel;
	}

	public void setUtilisateurTel(String utilisateurTel) {
		this.utilisateurTel = utilisateurTel;
	}

	@Basic
	@Column(name = "UTILISATEUR_ADRESSE")
	public String getUtilisateurAdresse() {
		return utilisateurAdresse;
	}

	public void setUtilisateurAdresse(String utilisateurAdresse) {
		this.utilisateurAdresse = utilisateurAdresse;
	}

	@Basic
	@Column(name = "UTILISATEUR_CP")
	public String getUtilisateurCp() {
		return utilisateurCp;
	}

	public void setUtilisateurCp(String utilisateurCp) {
		this.utilisateurCp = utilisateurCp;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		UtilisateurEntity that = (UtilisateurEntity) o;

		if (utilisateurId != that.utilisateurId) {
			return false;
		}
		if (utilisateurAdresse != null ? !utilisateurAdresse.equals(that.utilisateurAdresse) : that.utilisateurAdresse != null) {
			return false;
		}
		if (utilisateurCp != null ? !utilisateurCp.equals(that.utilisateurCp) : that.utilisateurCp != null) {
			return false;
		}
		if (utilisateurLogin != null ? !utilisateurLogin.equals(that.utilisateurLogin) : that.utilisateurLogin != null) {
			return false;
		}
		if (utilisateurMail != null ? !utilisateurMail.equals(that.utilisateurMail) : that.utilisateurMail != null) {
			return false;
		}
		if (utilisateurNom != null ? !utilisateurNom.equals(that.utilisateurNom) : that.utilisateurNom != null) {
			return false;
		}
		if (utilisateurPass != null ? !utilisateurPass.equals(that.utilisateurPass) : that.utilisateurPass != null) {
			return false;
		}
		if (utilisateurPrenom != null ? !utilisateurPrenom.equals(that.utilisateurPrenom) : that.utilisateurPrenom != null) {
			return false;
		}
		if (utilisateurTel != null ? !utilisateurTel.equals(that.utilisateurTel) : that.utilisateurTel != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = utilisateurId;
		result = 31 * result + (utilisateurMail != null ? utilisateurMail.hashCode() : 0);
		result = 31 * result + (utilisateurLogin != null ? utilisateurLogin.hashCode() : 0);
		result = 31 * result + (utilisateurPass != null ? utilisateurPass.hashCode() : 0);
		result = 31 * result + (utilisateurNom != null ? utilisateurNom.hashCode() : 0);
		result = 31 * result + (utilisateurPrenom != null ? utilisateurPrenom.hashCode() : 0);
		result = 31 * result + (utilisateurTel != null ? utilisateurTel.hashCode() : 0);
		result = 31 * result + (utilisateurAdresse != null ? utilisateurAdresse.hashCode() : 0);
		result = 31 * result + (utilisateurCp != null ? utilisateurCp.hashCode() : 0);
		return result;
	}

	@OneToMany(mappedBy = "utilisateurByUtilisateurId")
	public Collection<CommandeEntity> getCommandesByUtilisateurId() {
		return commandesByUtilisateurId;
	}

	public void setCommandesByUtilisateurId(Collection<CommandeEntity> commandesByUtilisateurId) {
		this.commandesByUtilisateurId = commandesByUtilisateurId;
	}

	@ManyToOne
	@JoinColumn(name = "COMMANDE_ID", referencedColumnName = "COMMANDE_ID")
	public CommandeEntity getCommandeByCommandeId() {
		return commandeByCommandeId;
	}

	public void setCommandeByCommandeId(CommandeEntity commandeByCommandeId) {
		this.commandeByCommandeId = commandeByCommandeId;
	}

	@ManyToOne
	@JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID", nullable = false)
	public RoleEntity getRoleByRoleId() {
		return roleByRoleId;
	}

	public void setRoleByRoleId(RoleEntity roleByRoleId) {
		this.roleByRoleId = roleByRoleId;
	}
}
