package fr.ensimag.logic;

import fr.ensimag.dao.RoleDAOLocal;
import fr.ensimag.dao.UtilisateurDAOLocal;
import fr.ensimag.entity.Role;
import fr.ensimag.entity.Utilisateur;
import fr.ensimag.exception.ExistingUserException;
import fr.ensimag.exception.InvalidEmailException;
import fr.ensimag.foundation.INames;
import fr.ensimag.vo.UtilisateurVO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.mindrot.jbcrypt.BCrypt;

@Stateless
public class UtilisateurService implements UtilisateurServiceLocal {

    @EJB
    private UtilisateurDAOLocal utilisateurDAO;
    @EJB
    private RoleDAOLocal roleDAO;

    @Override
    public UtilisateurVO logIn(UtilisateurVO vo) {
        Utilisateur entity = new Utilisateur();
        entity.setUtilisateurLogin(vo.getUtilisateurLogin());
        try {
            entity = utilisateurDAO.findByLogin(entity.getUtilisateurLogin());
            boolean checkpw = BCrypt.checkpw(vo.getUtilisateurPass(), entity.getUtilisateurPass().trim());
            if (checkpw) {
                return entity.toVO();
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public UtilisateurVO signUp(UtilisateurVO vo) throws ExistingUserException, InvalidEmailException {
        Utilisateur entity = new Utilisateur();
        entity.setUtilisateurNom(vo.getUtilisateurNom());
        entity.setUtilisateurPrenom(vo.getUtilisateurPrenom());
        entity.setUtilisateurMail(vo.getUtilisateurMail());
        entity.setUtilisateurTel(vo.getUtilisateurTel());
        entity.setUtilisateurAdresse(vo.getUtilisateurAdresse());
        entity.setUtilisateurCp(vo.getUtilisateurCp());

        entity.setUtilisateurLogin(vo.getUtilisateurLogin());
        entity.setUtilisateurPass(BCrypt.hashpw(vo.getUtilisateurPass(), BCrypt.gensalt(12)));
        if (isValidEmail(vo.getUtilisateurMail())) {
            try {
                Utilisateur findByLogin = utilisateurDAO.findByLogin(vo.getUtilisateurLogin());
                if (findByLogin == null) {
                    Role role = roleDAO.find(INames.ROLE_USER_ID);
                    entity.setRole(role);
                    entity = utilisateurDAO.create(entity);
                    return entity.toVO();
                } else {
                    throw new ExistingUserException();
                }
            } catch (ExistingUserException ex) {
                throw ex;
            } catch (Exception ex) {
                return null;
            }
        } else {
            throw new InvalidEmailException();
        }
    }

    @Override
    public List<UtilisateurVO> getAllUsers() throws Exception {
        List<Utilisateur> users = utilisateurDAO.findAll();
        List<UtilisateurVO> usersVo = new ArrayList<>();
        for (Utilisateur user : users) {
            usersVo.add(user.toVO());
        }
        return usersVo;
    }

    @Override
    public void updateUser(UtilisateurVO vo) throws Exception {
        Utilisateur user = new Utilisateur(vo.getUtilisateurId());
        Role role = roleDAO.find(vo.getRoleId());
        user.setRole(role);
        user.setUtilisateurNom(vo.getUtilisateurNom());
        user.setUtilisateurPrenom(vo.getUtilisateurPrenom());
        user.setUtilisateurMail(vo.getUtilisateurMail());
        user.setUtilisateurTel(vo.getUtilisateurTel());
        user.setUtilisateurLogin(vo.getUtilisateurLogin());
        user.setUtilisateurAdresse(vo.getUtilisateurAdresse());
        user.setUtilisateurCp(vo.getUtilisateurCp());
        utilisateurDAO.edit(user);
    }

    private boolean isValidEmail(String email) {
        String re = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*"
                + "|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\"
                + "\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*"
                + "[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4]"
                + "[0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|"
                + "[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x5"
                + "3-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        return email.matches(re);
    }

}
