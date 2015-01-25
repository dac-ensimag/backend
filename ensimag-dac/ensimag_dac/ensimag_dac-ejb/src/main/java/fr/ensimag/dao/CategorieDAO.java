package fr.ensimag.dao;

import fr.ensimag.entity.Categorie;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class CategorieDAO extends AbstractDAO<Categorie> implements CategorieDAOLocal {
}
