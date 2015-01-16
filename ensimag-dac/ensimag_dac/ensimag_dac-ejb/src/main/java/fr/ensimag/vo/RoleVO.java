package fr.ensimag.vo;

import java.util.List;

/**
 *
 * @author dac
 */
public class RoleVO implements IValueObject {

    private Integer roleId;
    private String roleLibele;
    private String roleDescription;
    private List<UtilisateurVO> utilisateurList;

    public RoleVO() {
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleLibele() {
        return roleLibele;
    }

    public void setRoleLibele(String roleLibele) {
        this.roleLibele = roleLibele;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public List<UtilisateurVO> getUtilisateurList() {
        return utilisateurList;
    }

    public void setUtilisateurList(List<UtilisateurVO> utilisateurList) {
        this.utilisateurList = utilisateurList;
    }

}
