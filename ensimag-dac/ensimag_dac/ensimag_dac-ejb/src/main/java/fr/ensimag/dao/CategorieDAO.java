package fr.ensimag.dao;

import fr.ensimag.entity.Account;
import fr.ensimag.entity.Categorie;
import fr.ensimag.foundation.INames;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 *
 * @author dac
 */
@Stateless
public class CategorieDAO extends AbstractDAO<Categorie> implements CategorieDAOLocal {
    
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

    public CategorieDAO() {
        super(Categorie.class);
    }

}
