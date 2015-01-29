package fr.ensimag.dao;

import fr.ensimag.entity.Commande;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class CommandeDAO extends AbstractDAO<Commande> implements CommandeDAOLocal {
}
