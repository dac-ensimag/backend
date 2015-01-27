package fr.ensimag.dao;

import fr.ensimag.entity.Utilisateur;
import fr.ensimag.entity.Utilisateur_;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UtilisateurDAO extends AbstractDAO<Utilisateur> implements UtilisateurDAOLocal {
	@Override
	public Utilisateur findByLogin(String login) {
		CriteriaBuilder builder = getCriteriaBuilder();
		CriteriaQuery<Utilisateur> query = builder.createQuery(Utilisateur.class);

		Root<Utilisateur> user = query.from(Utilisateur.class);

		query.where(builder.equal(user.get(Utilisateur_.utilisateurLogin), login));

		return getSingleResult(query);
	}
}
