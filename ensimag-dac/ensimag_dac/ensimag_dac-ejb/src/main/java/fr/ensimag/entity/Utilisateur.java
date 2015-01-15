package fr.ensimag.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author dac
 */
@Entity
@Table(name = "UTILISATEUR")
public class Utilisateur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "UTILISATEUR_ID")
    private Integer utilisateurId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "UTILISATEUR_MAIL")
    private String utilisateurMail;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "UTILISATEUR_LOGIN")
    private String utilisateurLogin;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "UTILISATEUR_PASS")
    private String utilisateurPass;
    @Basic(optional = false)
    @NotNull
    @Lob    
    @Column(name = "UTILISATEUR_NOM")
    private String utilisateurNom;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "UTILISATEUR_PRENOM")
    private String utilisateurPrenom;
    @Lob
    @Column(name = "UTILISATEUR_TEL")
    private String utilisateurTel;
    @Lob
    @Column(name = "UTILISATEUR_ADRESSE")
    private String utilisateurAdresse;
    @Lob
    @Column(name = "UTILISATEUR_CP")
    private String utilisateurCp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utilisateur")
    private List<Commande> commandeList;
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
    @ManyToOne(optional = false)
    private Role role;

    public Utilisateur() {
    }

    public Utilisateur(Integer utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public Utilisateur(Integer utilisateurId, String utilisateurMail, String utilisateurLogin, String utilisateurPass, String utilisateurNom, String utilisateurPrenom) {
        this.utilisateurId = utilisateurId;
        this.utilisateurMail = utilisateurMail;
        this.utilisateurLogin = utilisateurLogin;
        this.utilisateurPass = utilisateurPass;
        this.utilisateurNom = utilisateurNom;
        this.utilisateurPrenom = utilisateurPrenom;
    }

    public Integer getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Integer utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public String getUtilisateurMail() {
        return utilisateurMail;
    }

    public void setUtilisateurMail(String utilisateurMail) {
        this.utilisateurMail = utilisateurMail;
    }

    public String getUtilisateurLogin() {
        return utilisateurLogin;
    }

    public void setUtilisateurLogin(String utilisateurLogin) {
        this.utilisateurLogin = utilisateurLogin;
    }

    public String getUtilisateurPass() {
        return utilisateurPass;
    }

    public void setUtilisateurPass(String utilisateurPass) {
        this.utilisateurPass = utilisateurPass;
    }

    public String getUtilisateurNom() {
        return utilisateurNom;
    }

    public void setUtilisateurNom(String utilisateurNom) {
        this.utilisateurNom = utilisateurNom;
    }

    public String getUtilisateurPrenom() {
        return utilisateurPrenom;
    }

    public void setUtilisateurPrenom(String utilisateurPrenom) {
        this.utilisateurPrenom = utilisateurPrenom;
    }

    public String getUtilisateurTel() {
        return utilisateurTel;
    }

    public void setUtilisateurTel(String utilisateurTel) {
        this.utilisateurTel = utilisateurTel;
    }

    public String getUtilisateurAdresse() {
        return utilisateurAdresse;
    }

    public void setUtilisateurAdresse(String utilisateurAdresse) {
        this.utilisateurAdresse = utilisateurAdresse;
    }

    public String getUtilisateurCp() {
        return utilisateurCp;
    }

    public void setUtilisateurCp(String utilisateurCp) {
        this.utilisateurCp = utilisateurCp;
    }

    public List<Commande> getCommandeList() {
        return commandeList;
    }

    public void setCommandeList(List<Commande> commandeList) {
        this.commandeList = commandeList;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (utilisateurId != null ? utilisateurId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.utilisateurId == null && other.utilisateurId != null) || (this.utilisateurId != null && !this.utilisateurId.equals(other.utilisateurId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.ensimag.entity.Utilisateur[ utilisateurId=" + utilisateurId + " ]";
    }
    
}
