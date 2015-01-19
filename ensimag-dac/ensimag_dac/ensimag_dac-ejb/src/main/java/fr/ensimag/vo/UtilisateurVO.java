package fr.ensimag.vo;

import java.util.List;

/**
 *
 * @author dac
 */
public class UtilisateurVO implements IValueObject {
    
    private Integer utilisateurId;
    private String utilisateurMail;
    private String utilisateurLogin;
    private String utilisateurPass;
    private String utilisateurNom;
    private String utilisateurPrenom;
    private String utilisateurTel;
    private String utilisateurAdresse;
    private String utilisateurCp;
    private List<CommandeVO> commandeList;
    private Integer roleId;

    public UtilisateurVO() {
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

    public List<CommandeVO> getCommandeList() {
        return commandeList;
    }

    public void setCommandeList(List<CommandeVO> commandeList) {
        this.commandeList = commandeList;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}
