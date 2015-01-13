package fr.ensimag.logic;

import fr.ensimag.vo.AccountVO;

import javax.ejb.Local;

/**
 * @author Edward
 */
@Local
public interface AccountServiceLocal {

	void register(AccountVO accountVO);

	AccountVO login(AccountVO accountVO);

}
