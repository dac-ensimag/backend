package fr.ensimag.entity;

import fr.ensimag.vo.IValueObject;

/**
 *
 * @author Edward
 * @param <T> The type of Value Object for this Entity
 */
public interface IEntity <T extends IValueObject>{
    
    T toVO();
    
}
