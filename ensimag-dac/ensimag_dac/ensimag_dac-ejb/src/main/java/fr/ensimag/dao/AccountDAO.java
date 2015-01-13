package fr.ensimag.dao;

import fr.ensimag.entity.Account;
import fr.ensimag.foundation.INames;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 * @author Edward
 */
@Stateless(name = "accountDAO")
@TransactionManagement(TransactionManagementType.BEAN)
public class AccountDAO extends AbstractDAO<Account> implements AccountDAOLocal {

	@PersistenceContext(unitName = INames.PU_NAME)
	private EntityManager em;

	@Resource
	private EJBContext ctx;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	protected UserTransaction getUserTransaction() {
		return ctx.getUserTransaction();
	}

	public AccountDAO() {
		super(Account.class);
	}

}
