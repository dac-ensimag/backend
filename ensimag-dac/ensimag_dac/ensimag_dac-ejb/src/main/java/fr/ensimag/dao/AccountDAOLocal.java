/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.ensimag.dao;

import fr.ensimag.entity.Account;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Edward
 */
@Local
public interface AccountDAOLocal {

    void create(Account account);

    void edit(Account account);

    void remove(Account account);

    Account find(Object id);

    List<Account> findAll();

    int count();
    
}
