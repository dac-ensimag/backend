package fr.ensimag.control;

import fr.ensimag.vo.AccountVO;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

//@ManagedBean
//@RequestScoped
public class AccountBean implements Serializable {

	//@EJB
	private fr.ensimag.logic.AccountServiceLocal accountService;
	private String                               username;
	private String                               password;
	//@ManagedProperty(value = "#{userBean}")
	private UserBean                             user;

	public AccountBean() {
	}

	public String register() {
		AccountVO accountVO = new AccountVO();
		accountVO.setUsername(getUsername());
		accountVO.setPassword(getPassword());
		accountService.register(accountVO);
		return "index";
	}

	public String login() {
		AccountVO accountVO = new AccountVO();
		accountVO.setUsername(getUsername());
		accountVO.setPassword(getPassword());
		AccountVO login = accountService.login(accountVO);
		if (login != null) {
			user.setUsername(login.getUsername());
			user.setLoggedIn(true);
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

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public String toString() {
		return getUsername() + ";" + getPassword();
	}

}
