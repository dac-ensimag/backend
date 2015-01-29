package fr.ensimag.dao;

import fr.ensimag.entity.Facture;

import javax.ejb.Stateless;

@Stateless
public class FactureDAO extends AbstractDAO<Facture> implements FactureDAOLocal {
}
