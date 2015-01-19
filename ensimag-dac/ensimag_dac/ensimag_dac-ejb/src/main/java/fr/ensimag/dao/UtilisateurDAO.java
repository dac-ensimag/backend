package fr.ensimag.dao;

import fr.ensimag.entity.Utilisateur;
import fr.ensimag.foundation.INames;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UtilisateurDAO extends AbstractDAO<Utilisateur> implements UtilisateurDAOLocal {

	@PersistenceContext(unitName = INames.PU_NAME)
	private EntityManager em;

	@Resource
	private EJBContext ctx;

	public UtilisateurDAO() {
		super(Utilisateur.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	protected UserTransaction getUserTransaction() {
		return ctx.getUserTransaction();
	}

    @Override
    public Utilisateur findByLogin(Utilisateur entity) {
        EntityManager em = getEntityManager();
        try {
            Query loginQuery = em.createNamedQuery("Utilisateur.findByLogin");
            loginQuery.setParameter("login", entity.getUtilisateurLogin());
            Utilisateur utilisateur = (Utilisateur) loginQuery.getSingleResult();

            return utilisateur;
        } catch (Exception ex) {
            throw ex;
        }
    }

}
