package fr.ensimag.dao;

import fr.ensimag.entity.Account;
import fr.ensimag.foundation.INames;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Edward
 */
@Stateless
public class AccountDAO extends AbstractDAO<Account> implements AccountDAOLocal {
    @PersistenceContext(unitName = INames.PU_NAME)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountDAO() {
        super(Account.class);
    }
    
}
