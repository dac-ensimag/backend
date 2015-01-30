package fr.ensimag.control;

import fr.ensimag.logic.UtilisateurServiceLocal;
import fr.ensimag.vo.UtilisateurVO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "userManagementBean")
@ViewScoped
public class UserManagementBean {
    
    @EJB
    private UtilisateurServiceLocal utilisateurService;
    
    private UtilisateurVO selectedUser;
    private List<UtilisateurVO> users;
    

    public UserManagementBean() {
    }
            
    public void init() {

        try {
            users = utilisateurService.getAllUsers();
            if (selectedUser == null) {
                selectedUser = users.get(0);
            }
        } catch (Exception ex) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/login.xhtml");
            } catch (IOException ex1) {
                
            }
        }
    }
    
    public void update() throws Exception {
            utilisateurService.updateUser(selectedUser); 
    }
    
    public void remove() throws Exception {
        utilisateurService.removeUser(selectedUser.getUtilisateurId());
        selectedUser = null;
    }
    
    public void fillFields() {
        selectedUser = getSelectedUser();
    }

    public UtilisateurVO getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(UtilisateurVO selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<UtilisateurVO> getUsers() {
        return users;
    }

    public void setUsers(List<UtilisateurVO> users) {
        this.users = users;
    }
        
    
}
