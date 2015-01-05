package fr.ensimag.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public abstract class AbstractDAO<T> {

	private Class<T> entityClass;
	private EntityManager em;

	public AbstractDAO() {

	}

	public AbstractDAO(final Class<T> entityClass) {
		this.entityClass = entityClass;
		this.em = this.getEntityManager();
	}

	protected abstract EntityManager getEntityManager();

	public void create(final T entity) {
		this.em.persist(entity);
	}

	public void edit(T entity) {
		this.em = this.getEntityManager();
		entity = this.em.merge(entity);
	}

	public void remove(final T entity) {
		this.em = this.getEntityManager();
		this.em.remove(this.em.merge(entity));
	}

	public T find(final Object id) {
		return this.em.find(this.entityClass, id);
	}

	public List<T> findAll() {
		final CriteriaQuery<T> cq = this.em.getCriteriaBuilder().createQuery(
				this.entityClass);
		cq.select(cq.from(this.entityClass));
		final Query q = this.em.createQuery(cq);
		return q.getResultList();
	}

	public int count() {
		return this.findAll().size();
	}

}
