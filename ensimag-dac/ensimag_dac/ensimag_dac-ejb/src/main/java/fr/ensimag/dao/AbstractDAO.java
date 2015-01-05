package fr.ensimag.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Edward
 */
public abstract class AbstractDAO<T> {

    private Class<T> entityClass;

    public AbstractDAO() {

    }

    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;        
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        EntityManager em = null;
        try {

            em = getEntityManager();

//            em.getTransaction().begin();

            em.persist(entity);

  //          em.getTransaction().commit();

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(T entity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            entity = em.merge(entity);

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void remove(T entity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            em.remove(em.merge(entity));

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public T find(Object id) {
        EntityManager em = null;
        em = getEntityManager();
        try {

            return em.find(entityClass, id);
        } finally {
            em.close();
        }
    }

    public List<T> findAll() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }


    public int count() {
        return findAll().size();
    }
    
}
