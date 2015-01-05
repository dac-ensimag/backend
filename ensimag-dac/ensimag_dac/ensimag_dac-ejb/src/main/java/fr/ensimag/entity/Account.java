package fr.ensimag.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "account")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
	@NamedQuery(name = "Account.findByUsername", query = "SELECT a FROM Account a WHERE a.username = :username"),
	@NamedQuery(name = "Account.findByPassword", query = "SELECT a FROM Account a WHERE a.password = :password") })
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	public Account() {
	}

	public Account(final String username) {
		this.username = username;
	}

	public Account(final String username, final String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += this.username != null ? this.username.hashCode() : 0;
		return hash;
	}

	@Override
	public boolean equals(final Object object) {
		if (!(object instanceof Account)) {
			return false;
		}
		final Account other = (Account) object;
		if (this.username == null && other.username != null
				|| this.username != null
				&& !this.username.equals(other.username)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "fr.ensimag.model.Account[ username=" + this.username + " ]";
	}

}
