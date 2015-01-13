package fr.ensimag.entity;

import fr.ensimag.vo.AccountVO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Edward
 */
@Entity
@Table(name = "account")
@XmlRootElement
@NamedQueries({
		              @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
		              @NamedQuery(name = "Account.findByUsername", query = "SELECT a FROM Account a WHERE a.username = :username")})
public class Account implements Serializable, IEntity<AccountVO> {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "username")
	private String username;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "password")
	private String password;

	public Account() {
	}

	public Account(String username) {
		this.username = username;
	}

	public Account(String username, String password) {
		this.username = username;
		this.password = password;
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

	public AccountVO toVO() {
		AccountVO vo = new AccountVO();
		vo.setUsername(getUsername());
		vo.setPassword(getPassword());
		return vo;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (username != null ? username.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Account)) {
			return false;
		}
		Account other = (Account) object;
		if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "fr.ensimag.model.Account[ username=" + username + " ]";
	}

}
