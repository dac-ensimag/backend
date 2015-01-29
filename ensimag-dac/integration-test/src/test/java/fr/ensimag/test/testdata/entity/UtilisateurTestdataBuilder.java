package fr.ensimag.test.testdata.entity;

import fr.ensimag.entity.Commande;
import fr.ensimag.test.testdata.AbstractTestdataBuilder;
import fr.ensimag.entity.Role;
import fr.ensimag.entity.Utilisateur;

import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
import java.util.ArrayList;

public class UtilisateurTestdataBuilder extends AbstractTestdataBuilder<Utilisateur> {

	private Role withRole;
	private String withUtilisateurAdresse;
	private String withUtilisateurCp;
	private String withUtilisateurLogin;
	private String withUtilisateurMail;
	private String withUtilisateurNom;
	private String withUtilisateurPass;
	private String withUtilisateurPrenom;
	private String withUtilisateurTel;

	public UtilisateurTestdataBuilder() {
		super();
	}

	public UtilisateurTestdataBuilder(EntityManager entityManager, UserTransaction userTransaction) {
		super(entityManager, userTransaction);
	}

	public UtilisateurTestdataBuilder withRole(Role role) {
		this.withRole = role;
		return this;
	}

	public UtilisateurTestdataBuilder withUtilisateurAdresse(String withUtilisateurAdresse) {
		this.withUtilisateurAdresse = withUtilisateurAdresse;
		return this;
	}

	public UtilisateurTestdataBuilder withUtilisateurCp(String withUtilisateurCp) {
		this.withUtilisateurCp = withUtilisateurCp;
		return this;
	}

	public UtilisateurTestdataBuilder withUtilisateurLogin(String withUtilisateurLogin) {
		this.withUtilisateurLogin = withUtilisateurLogin;
		return this;
	}

	public UtilisateurTestdataBuilder withUtilisateurMail(String withUtilisateurMail) {
		this.withUtilisateurMail = withUtilisateurMail;
		return this;
	}

	public UtilisateurTestdataBuilder withUtilisateurNom(String withUtilisateurNom) {
		this.withUtilisateurNom = withUtilisateurNom;
		return this;
	}

	public UtilisateurTestdataBuilder withUtilisateurPass(String withUtilisateurPass) {
		this.withUtilisateurPass = withUtilisateurPass;
		return this;
	}

	public UtilisateurTestdataBuilder withUtilisateurPrenom(String withUtilisateurPrenom) {
		this.withUtilisateurPrenom = withUtilisateurPrenom;
		return this;
	}

	public UtilisateurTestdataBuilder withUtilisateurTel(String withUtilisateurTel) {
		this.withUtilisateurTel = withUtilisateurTel;
		return this;
	}

	private Role getRole() {
		if (withRole != null) {
			return withRole;
		}

		return hasEntityManager() ? new RoleTestdataBuilder(getEntityManager(), getUserTransaction()).buildAndSave() : new RoleTestdataBuilder().build();
	}

	private String getUtilisateurAdresse() {
		return withUtilisateurAdresse != null ? withUtilisateurAdresse : "4 rue de la gloire 38000 Grenoble";
	}

	private String getUtilisateurCp() {
		return withUtilisateurCp != null ? withUtilisateurCp : "38000";
	}

	private String getUtilisateurLogin() {
		return withUtilisateurLogin != null ? withUtilisateurLogin : "login";
	}

	private String getUtilisateurMail() {
		return withUtilisateurMail != null ? withUtilisateurMail : "user@example.com";
	}

	private String getUtilisateurNom() {
		return withUtilisateurNom != null ? withUtilisateurNom : "User";
	}

	private String getUtilisateurPass() {
		return withUtilisateurPass != null ? withUtilisateurPass : "password";
	}

	private String getUtilisateurPrenom() {
		return withUtilisateurPrenom != null ? withUtilisateurPrenom : "Name";
	}

	private String getUtilisateurTel() {
		return withUtilisateurTel != null ? withUtilisateurTel : "0600112233";
	}

	@Override
	public Utilisateur build() {
		final Utilisateur utilisateur = new Utilisateur();
		utilisateur.setRole(getRole());
		utilisateur.setUtilisateurAdresse(getUtilisateurAdresse());
		utilisateur.setUtilisateurCp(getUtilisateurCp());
		utilisateur.setUtilisateurLogin(getUtilisateurLogin());
		utilisateur.setUtilisateurMail(getUtilisateurMail());
		utilisateur.setUtilisateurNom(getUtilisateurNom());
		utilisateur.setUtilisateurPass(getUtilisateurPass());
		utilisateur.setUtilisateurPrenom(getUtilisateurPrenom());
		utilisateur.setUtilisateurTel(getUtilisateurTel());
		utilisateur.setCommandeList(new ArrayList<Commande>());

		return utilisateur;
	}
}
