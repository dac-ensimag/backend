package fr.ensimag.entity;

import fr.ensimag.vo.RoleVO;
import fr.ensimag.vo.UtilisateurVO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ROLE")
public class Role implements Serializable, IEntity<RoleVO> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@NotNull
	@Column(name = "ROLE_ID")
	private Integer roleId;

	@Basic(optional = false)
	@NotNull
	@Lob
	@Column(name = "ROLE_LIBELE")
	private String roleLibele;

	@Basic(optional = false)
	@NotNull
	@Lob
	@Column(name = "ROLE_DESCRIPTION")
	private String roleDescription;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
	private List<Utilisateur> utilisateurList;

	public Role() {
	}

	public Role(Integer roleId) {
		this.roleId = roleId;
	}

	public Role(Integer roleId, String roleLibele, String roleDescription) {
		this.roleId = roleId;
		this.roleLibele = roleLibele;
		this.roleDescription = roleDescription;
	}

	@Override
	public int getId() {
		return this.getRoleId();
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

	public List<Utilisateur> getUtilisateurList() {
		return utilisateurList;
	}

	public void setUtilisateurList(List<Utilisateur> utilisateurList) {
		this.utilisateurList = utilisateurList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (roleId != null ? roleId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Role)) {
			return false;
		}
		Role other = (Role) object;
		if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "fr.ensimag.entity.Role[ roleId=" + roleId + " ]";
	}

	@Override
	public RoleVO toVO() {
		RoleVO vo = new RoleVO();
		vo.setRoleId(getRoleId());
		vo.setRoleLibele(getRoleLibele());
		vo.setRoleDescription(getRoleDescription());

		List<UtilisateurVO> utilisateurVOList = new ArrayList<>();
		for (Utilisateur utilisateur : getUtilisateurList()) {
			utilisateurVOList.add(utilisateur.toVO());
		}
		vo.setUtilisateurList(utilisateurVOList);

		return vo;
	}

}
