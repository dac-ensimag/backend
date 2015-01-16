package fr.ensimag.dao;

import fr.ensimag.entity.Utilisateur;
import javax.ejb.Local;

/**
 *
 * @author dac
 */
@Local
public interface UtilisateurDAOLocal extends AbstractLocal<Utilisateur> {
    
}
