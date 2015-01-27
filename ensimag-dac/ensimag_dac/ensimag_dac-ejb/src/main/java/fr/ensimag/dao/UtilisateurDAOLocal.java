package fr.ensimag.dao;

import fr.ensimag.entity.Utilisateur;

import javax.ejb.Local;

@Local
public interface UtilisateurDAOLocal extends AbstractLocal<Utilisateur> {

    public Utilisateur findByLogin(String login) throws Exception;
    
}
