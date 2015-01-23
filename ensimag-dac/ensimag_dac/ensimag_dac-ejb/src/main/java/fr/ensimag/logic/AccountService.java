package fr.ensimag.logic;

import javax.ejb.Stateless;

//@Stateless
public class AccountService /*implements AccountServiceLocal*/ {

	/*@EJB
	AccountDAOLocal accountDBAcces;

	@Override
	public void register(AccountVO accountVO) {
		Account account = new Account();
		account.setUsername(accountVO.getUsername());
		account.setPassword(accountVO.getPassword());
		try {
			accountDBAcces.create(account);
		} catch (Exception e) {
			//TODO
			e.printStackTrace();
		}
	}

	@Override
	public AccountVO login(AccountVO accountVO) {
		Account account = new Account();
		account.setUsername(accountVO.getUsername());
		account.setPassword(accountVO.getPassword());
		try {
			Account login = accountDBAcces.find(account.getUsername());
			if (login != null && login.getPassword().equals(account.getPassword())) {
				return login.toVO();
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}*/

}
