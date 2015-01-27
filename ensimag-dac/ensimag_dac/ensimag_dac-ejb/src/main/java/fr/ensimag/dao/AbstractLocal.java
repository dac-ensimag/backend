package fr.ensimag.dao;

import fr.ensimag.entity.IEntity;

import java.util.List;

/**
 * @param <T> The type of Entity for this EJB
 */
public interface AbstractLocal<T extends IEntity> {

	T create(T support);

	T edit(T support);

	void remove(T support);

	T find(Object id);

	List<T> findAll();

	int count();

}