package fr.ensimag.dao;

import fr.ensimag.entity.Role;

import javax.ejb.Stateless;

@Stateless
public class RoleDAO extends AbstractDAO<Role> implements RoleDAOLocal {
}
