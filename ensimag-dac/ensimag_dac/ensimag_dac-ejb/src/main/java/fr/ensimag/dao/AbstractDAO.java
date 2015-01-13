package fr.ensimag.dao;

import fr.ensimag.entity.IEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.UserTransaction;
import java.util.List;

/**
 * @param <T> The type of Entity for this DAO
 */
public abstract class AbstractDAO<T extends IEntity> {

	private Class<T> entityClass;

	public AbstractDAO() {

	}

	public AbstractDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected abstract EntityManager getEntityManager();

	protected abstract UserTransaction getUserTransaction();

	public void create(T entity) throws Exception {

		EntityManager em = null;
		UserTransaction utx = null;
		try {
			em = getEntityManager();
			utx = getUserTransaction();
			utx.begin();
			em.persist(entity);
			em.flush();
			utx.commit();
		} catch (Exception e) {
			if (utx != null) {
				utx.rollback();
			}
			throw e;
		}
	}

	public void edit(T entity) throws Exception {
		EntityManager em = null;
		UserTransaction utx = null;
		try {
			em = getEntityManager();
			utx = getUserTransaction();
			utx.begin();
			entity = em.merge(entity);
			em.flush();
			utx.commit();
		} catch (Exception e) {
			if (utx != null) {
				utx.rollback();
			}
			throw e;
		}
	}

	public void remove(T entity) throws Exception {
		EntityManager em = null;
		UserTransaction utx = null;
		try {
			em = getEntityManager();
			utx = getUserTransaction();
			utx.begin();
			em.remove(em.merge(entity));
			em.flush();
			utx.commit();
		} catch (Exception e) {
			if (utx != null) {
				utx.rollback();
			}
			throw e;
		}
	}

	public T find(Object id) throws Exception {
		EntityManager em = null;
		em = getEntityManager();
		try {
			return em.find(entityClass, id);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<T> findAll() throws Exception {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(entityClass));
			Query q = em.createQuery(cq);
			return q.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	public int count() throws Exception {
		return findAll().size();
	}

}
