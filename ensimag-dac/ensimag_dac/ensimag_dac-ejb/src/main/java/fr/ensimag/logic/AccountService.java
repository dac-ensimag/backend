package fr.ensimag.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.ensimag.dao.AccountDAOLocal;
import fr.ensimag.entity.Account;

/**
 *
 * @author Edward
 */
@Stateless
public class AccountService implements AccountServiceLocal {

	@EJB
	AccountDAOLocal accountDBAcces;

	public void register(final String username, final String password) {
		final Account account = new Account();
		account.setUsername(username);
		account.setPassword(password);
		this.accountDBAcces.create(account);
	}

}
