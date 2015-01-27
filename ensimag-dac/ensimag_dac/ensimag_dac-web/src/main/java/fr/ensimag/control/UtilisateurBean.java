package fr.ensimag.control;

import fr.ensimag.exception.ExistingUserException;
import fr.ensimag.exception.InvalidEmailException;
import fr.ensimag.vo.UtilisateurVO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "utilisateurBean")
@SessionScoped
public class UtilisateurBean {

    @EJB
    private fr.ensimag.logic.UtilisateurServiceLocal utilisateurService;

    private UtilisateurVO user;
    private boolean loggedIn;

    public UtilisateurBean() {
    }

    @PostConstruct
    public void init() {
        user = new UtilisateurVO();
    }

    public String register() {
        UtilisateurVO signUp = null;
        try {
            signUp = utilisateurService.signUp(getUser());
            user = new UtilisateurVO();
            if (signUp != null) {
                return "index";
            } else {
                return "error";
            }
        } catch (ExistingUserException ex) {
            FacesContext.getCurrentInstance().addMessage(
                    "signUpForm:inputUsername", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Login non valide","Le nom d'utilisateur est déjà utilisé"));
            user.setUtilisateurLogin("");
            return "index";
        } catch (InvalidEmailException ex) {
            FacesContext.getCurrentInstance().addMessage(
                    "signUpForm:inputMail", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Mail non valid","Veuillez écrire un mail valid"));
            //user.setUtilisateurMail("");
            return "index";
        }
    }

    public String logOut() {
        user = new UtilisateurVO();
        loggedIn = false;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "logout";
    }

    public UtilisateurVO getUser() {
        return user;
    }

    public void setUser(UtilisateurVO user) {
        this.user = user;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

}
