package fr.ensimag.logic;

import fr.ensimag.exception.ExistingUserException;
import fr.ensimag.exception.InvalidEmailException;
import fr.ensimag.vo.UtilisateurVO;
import java.util.List;
import javax.ejb.Local;

@Local
public interface UtilisateurServiceLocal {
    
    UtilisateurVO logIn(UtilisateurVO vo);
    
    UtilisateurVO signUp(UtilisateurVO vo) throws ExistingUserException, InvalidEmailException;
    
    List<UtilisateurVO> getAllUsers() throws Exception;
    
    void updateUser(UtilisateurVO vo) throws Exception;
}
