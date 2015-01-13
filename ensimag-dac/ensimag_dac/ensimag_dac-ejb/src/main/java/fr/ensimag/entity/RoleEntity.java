package fr.ensimag.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "role")
public class RoleEntity {
	private int                           roleId;
	private String                        roleLibele;
	private String                        roleDescription;
	private Collection<UtilisateurEntity> utilisateursByRoleId;

	@Id
	@Column(name = "ROLE_ID")
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Basic
	@Column(name = "ROLE_LIBELE")
	public String getRoleLibele() {
		return roleLibele;
	}

	public void setRoleLibele(String roleLibele) {
		this.roleLibele = roleLibele;
	}

	@Basic
	@Column(name = "ROLE_DESCRIPTION")
	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		RoleEntity that = (RoleEntity) o;

		if (roleId != that.roleId) {
			return false;
		}
		if (roleDescription != null ? !roleDescription.equals(that.roleDescription) : that.roleDescription != null) {
			return false;
		}
		if (roleLibele != null ? !roleLibele.equals(that.roleLibele) : that.roleLibele != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = roleId;
		result = 31 * result + (roleLibele != null ? roleLibele.hashCode() : 0);
		result = 31 * result + (roleDescription != null ? roleDescription.hashCode() : 0);
		return result;
	}

	@OneToMany(mappedBy = "roleByRoleId")
	public Collection<UtilisateurEntity> getUtilisateursByRoleId() {
		return utilisateursByRoleId;
	}

	public void setUtilisateursByRoleId(Collection<UtilisateurEntity> utilisateursByRoleId) {
		this.utilisateursByRoleId = utilisateursByRoleId;
	}
}
