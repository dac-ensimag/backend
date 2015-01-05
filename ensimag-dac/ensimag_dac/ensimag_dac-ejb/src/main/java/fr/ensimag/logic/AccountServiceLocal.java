package fr.ensimag.logic;

import javax.ejb.Local;

@Local
public interface AccountServiceLocal {

	public void register(String username, String password);
}
