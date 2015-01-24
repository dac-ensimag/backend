package fr.ensimag.entity;

import fr.ensimag.vo.IValueObject;

/**
 * @param <T> The type of Value Object for this Entity
 */
public interface IEntity<T extends IValueObject> {

	int getId();

	T toVO();

}
