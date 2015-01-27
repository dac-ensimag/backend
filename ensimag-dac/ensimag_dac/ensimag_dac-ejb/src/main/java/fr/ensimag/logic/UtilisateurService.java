package fr.ensimag.logic;

import fr.ensimag.dao.RoleDAOLocal;
import fr.ensimag.dao.UtilisateurDAOLocal;
import fr.ensimag.entity.Role;
import fr.ensimag.entity.Utilisateur;
import fr.ensimag.exception.ExistingUserException;
import fr.ensimag.exception.InvalidEmailException;
import fr.ensimag.foundation.INames;
import fr.ensimag.vo.UtilisateurVO;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class UtilisateurService implements UtilisateurServiceLocal {

	@EJB
	private UtilisateurDAOLocal utilisateurDAO;

	@EJB
	private RoleDAOLocal        roleDAO;

	@Override
	public UtilisateurVO logIn(UtilisateurVO vo) {
		Utilisateur entity = utilisateurDAO.findByLogin(vo.getUtilisateurLogin());

		if (entity != null) {
			boolean checkpw = BCrypt.checkpw(vo.getUtilisateurPass(), entity.getUtilisateurPass().trim());
			if (checkpw) {
				return entity.toVO();
			}
		}
		return null;
	}

	@Override
	public UtilisateurVO signUp(UtilisateurVO vo) {
		Utilisateur entity = new Utilisateur();
		entity.setUtilisateurNom(vo.getUtilisateurNom());
		entity.setUtilisateurPrenom(vo.getUtilisateurPrenom());
		entity.setUtilisateurMail(vo.getUtilisateurMail());
		entity.setUtilisateurTel(vo.getUtilisateurTel());
		entity.setUtilisateurAdresse(vo.getUtilisateurAdresse());
		entity.setUtilisateurCp(vo.getUtilisateurCp());

		entity.setUtilisateurLogin(vo.getUtilisateurLogin());
		entity.setUtilisateurPass(BCrypt.hashpw(vo.getUtilisateurPass(), BCrypt.gensalt(12)));

		Role role = roleDAO.find(INames.ROLE_USER_ID);
		entity.setRole(role);
		entity = utilisateurDAO.create(entity);

		return entity.toVO();
	}

}
