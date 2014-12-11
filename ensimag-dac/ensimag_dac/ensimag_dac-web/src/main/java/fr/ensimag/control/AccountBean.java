package fr.ensimag.control;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Edward
 */
@ManagedBean
@RequestScoped
public class AccountBean {
    
    @EJB
    private fr.ensimag.logic.AccountServiceLocal accountService;
    private String username;
    private String password;
    
    /**
     * Creates a new instance of AccountBean
     */
    public AccountBean() {
    }
    
    public String register() {
        accountService.register(getUsername(), getPassword());
        return "index";
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
    
    
    
}
