package fr.ensimag.logic;

import fr.ensimag.vo.UtilisateurVO;
import javax.ejb.Local;

@Local
public interface UtilisateurServiceLocal {
    
    UtilisateurVO logIn(UtilisateurVO vo);
    
    UtilisateurVO signUp(UtilisateurVO vo);
}
