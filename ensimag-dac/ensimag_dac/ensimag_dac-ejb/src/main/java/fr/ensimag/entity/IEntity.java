package fr.ensimag.entity;

import fr.ensimag.vo.IValueObject;

/**
 * @param <T> The type of Value Object for this Entity
 * @author Edward
 */
public interface IEntity<T extends IValueObject> {

	T toVO();

}
