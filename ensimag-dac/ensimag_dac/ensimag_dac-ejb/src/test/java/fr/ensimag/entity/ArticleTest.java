package fr.ensimag.entity;
import fr.ensimag.vo.ArticleVO;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class ArticleTest {
    
    private void verifyVO(Article art, ArticleVO artVO){
        Assert.assertEquals(art.getArticleId(), artVO.getArticleId());
        Assert.assertEquals(art.getArticleLibele(), artVO.getArticleLibele());
        Assert.assertEquals(art.getArticlePrix(), artVO.getArticlePrix(),0.001);
        Assert.assertEquals(art.getArticleDescription(), artVO.getArticleDescription());
        Assert.assertEquals(art.getArticleImg(), artVO.getArticleImg());
        Assert.assertEquals(art.getCategorie().getCategorieId(), artVO.getCategorieId());
    }
    
    @Test
    public void testArticleToVo(){
        Article art = new Article(1,"Super Article", 500, true, "Cet article est trop bien.", "http://swer");
        art.setCommandeList(new ArrayList<Commande>());
        art.setCategorie(new Categorie(1, "Random Categorie"));
        ArticleVO artVO = art.toVO();
        verifyVO(art, artVO);
    }
    
    @Test
    public void testArticleListToVo(){
        Categorie cat = new Categorie(1, "Random Categorie");
        Categorie cat2 = new Categorie(2, "Random Categorie 2");
        
        Article art1 = new Article(1,"Super Article", 500, true, "Cet article est trop bien.", "http://swer1");
        art1.setCommandeList(new ArrayList<Commande>());
        art1.setCategorie(cat);
        
        Article art2 = new Article(1,"Génial Article", 315, true, "Cet article est trop trop bien.", "http://swer2");
        art2.setCommandeList(new ArrayList<Commande>());
        art2.setCategorie(cat);
        
        Article art3 = new Article(1,"Ultra Article", 95, true, "Cet article est giga bien.", "http://swer3");
        art3.setCommandeList(new ArrayList<Commande>());
        art3.setCategorie(cat);
        
        Article art4 = new Article(1,"Mega Article", 30, true, "Cet article est tera bien.", "http://swer4");
        art4.setCommandeList(new ArrayList<Commande>());
        art4.setCategorie(cat2);
        
        List<Article> list = new ArrayList<Article>();
        list.add(art1);
        list.add(art2);
        list.add(art3);
        list.add(art4);
        
        List<ArticleVO> listVO = Article.toVo(list);
        for(int i = 0; i<list.size(); i++){
            verifyVO(list.get(i), listVO.get(i));
        }
    }
}
