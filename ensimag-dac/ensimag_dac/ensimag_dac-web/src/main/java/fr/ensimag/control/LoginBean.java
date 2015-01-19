package fr.ensimag.control;

import fr.ensimag.vo.UtilisateurVO;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="loginBean")
@RequestScoped
public class LoginBean implements Serializable {
    
    @EJB
    private fr.ensimag.logic.UtilisateurServiceLocal utilisateurService;
    private String username;
    private String password;
    @ManagedProperty(value = "#{utilisateurBean}")
    private UtilisateurBean utilisateur;
    
    public LoginBean() {
    }        
    
    public String login() {
        UtilisateurVO utilisateurVO = new UtilisateurVO();
        utilisateurVO.setUtilisateurLogin(getUsername());
        utilisateurVO.setUtilisateurPass(getPassword());
        UtilisateurVO login = utilisateurService.logIn(utilisateurVO);
        if (login != null) {
            utilisateur.setUser(login);
            utilisateur.setLoggedIn(true);
            return "welcome";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    "loginForm:inputUsername", new FacesMessage(
                            "Invalid Username or Password"));
            FacesContext.getCurrentInstance().addMessage(
                    "loginForm:inputPassword", new FacesMessage(
                    "Invalid Username or Password"));
            return "failure";
        }
        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UtilisateurBean getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UtilisateurBean utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    public String toString(){
        return getUsername() + ";" + getPassword();
    }
    
}
