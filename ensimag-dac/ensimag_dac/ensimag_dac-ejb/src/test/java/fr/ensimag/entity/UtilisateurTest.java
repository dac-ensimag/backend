package fr.ensimag.entity;

import fr.ensimag.vo.UtilisateurVO;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

public class UtilisateurTest {
    
    @Test
    public void testUtilisateurVO(){
        Utilisateur util = new Utilisateur(1, "util@mail.com", "toto", "titi", "Walker", "Allan");
        util.setCommandeList(new ArrayList<Commande>());
        util.setRole(new Role(1,"tutu","tototiti tutu"));
        UtilisateurVO utilVO = util.toVO();
        
        Assert.assertEquals(util.getUtilisateurId(), utilVO.getUtilisateurId());
        Assert.assertEquals((long)util.getRole().getId(), (long)utilVO.getRoleId());
        Assert.assertEquals(util.getUtilisateurAdresse(), utilVO.getUtilisateurAdresse());
        Assert.assertEquals(util.getUtilisateurCp(), utilVO.getUtilisateurCp());
        Assert.assertEquals(util.getUtilisateurLogin(), utilVO.getUtilisateurLogin());
        Assert.assertEquals(util.getUtilisateurMail(), utilVO.getUtilisateurMail());
        Assert.assertEquals(util.getUtilisateurNom(), utilVO.getUtilisateurNom());
        Assert.assertEquals(util.getUtilisateurPrenom(), utilVO.getUtilisateurPrenom());
        Assert.assertEquals(util.getUtilisateurTel(), utilVO.getUtilisateurTel());
    }
}
