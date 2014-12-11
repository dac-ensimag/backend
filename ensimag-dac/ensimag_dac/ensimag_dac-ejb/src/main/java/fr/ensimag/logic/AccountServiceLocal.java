package fr.ensimag.logic;

import javax.ejb.Local;

/**
 *
 * @author Edward
 */
@Local
public interface AccountServiceLocal {

    void register(String username, String password);
    
}
