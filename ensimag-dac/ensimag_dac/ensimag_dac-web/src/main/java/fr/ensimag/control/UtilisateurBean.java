package fr.ensimag.control;

import fr.ensimag.vo.UtilisateurVO;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
        UtilisateurVO signUp = utilisateurService.signUp(getUser());
        user = new UtilisateurVO();
        if (signUp != null) {
            return "index";
        } else {
            return "error";
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
