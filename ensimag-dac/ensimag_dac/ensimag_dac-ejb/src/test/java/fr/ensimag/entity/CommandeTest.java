package fr.ensimag.entity;

import fr.ensimag.vo.CommandeVO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class CommandeTest {
    private void verifyVO(Commande com, CommandeVO comVO){
        Assert.assertEquals(com.getCommandeId(),comVO.getCommandeId());
        Assert.assertEquals(com.getCommandeDate(),comVO.getCommandeDate());
        Assert.assertEquals(com.getCommandeDescription(),comVO.getCommandeDescription());
        Assert.assertEquals(com.getCommandeTotale(),comVO.getCommandeTotale(), 0.001);
        Assert.assertEquals((long)com.getUtilisateur().getId(),(long)comVO.getUtilisateurId());
    }
    
    @Test
    public void testCommandeToVo(){
        Commande com = new Commande(1, new Date(), "Commande du 30 janvier", 0.0);
        com.setArticleList(new ArrayList<Article>());
        com.setUtilisateur(new Utilisateur(1, "util@mail.com", "toto", "titi", "Walker", "Allan"));
        
        CommandeVO comVO = com.toVO();
        verifyVO(com, comVO);
    }
    
    @Test
    public void testCommandeListToVo(){
        Commande com1 = new Commande(1, new Date(), "Commande du 20 janvier", 0.0);
        com1.setArticleList(new ArrayList<Article>());
        com1.setUtilisateur(new Utilisateur(2, "lawliet@mail.com", "L", "detec", "Llyod", "Bob"));
        
        Commande com2 = new Commande(2, new Date(), "Commande du 30 janvier", 0.0);
        com2.setArticleList(new ArrayList<Article>());
        com2.setUtilisateur(new Utilisateur(1, "util@mail.com", "toto", "titi", "Walker", "Allan"));
        
        Commande com3 = new Commande(3, new Date(), "Commande du 10 janvier", 0.0);
        com3.setArticleList(new ArrayList<Article>());
        com3.setUtilisateur(new Utilisateur(3, "u@mail.com", "tete", "swer", "Bobby", "Jr"));
        
        List<Commande> list = new ArrayList<Commande>();
        list.add(com1);
        list.add(com2);
        list.add(com3);
        
        List<CommandeVO> listVO = Commande.toVo(list);
        for(int i = 0; i<list.size(); i++){
            verifyVO(list.get(i), listVO.get(i));
        }
    }
}
