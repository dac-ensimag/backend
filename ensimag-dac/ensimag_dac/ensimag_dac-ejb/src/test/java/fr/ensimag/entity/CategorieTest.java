package fr.ensimag.entity;

import fr.ensimag.vo.CategorieVO;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;


public class CategorieTest {
    
    
    @Test
    public void testCategorieToVo(){
        Categorie cat = new Categorie(1,"Cat");
        cat.setArticleList(new ArrayList<Article>());
        CategorieVO catVO = cat.toVO();
        Assert.assertEquals(cat.getCategorieId(),catVO.getCategorieId());
        Assert.assertEquals(cat.getCategorieLibele(), catVO.getCategorieLibele());
    }
    
    @Test
    public void testCategorieListToVo(){
        Categorie cat1 = new Categorie(1,"Cat1");
        Categorie cat2 = new Categorie(2,"Cat2");
        Categorie cat3 = new Categorie(3,"Cat3");
        Categorie cat4 = new Categorie(4,"Cat4");
        Categorie cat5 = new Categorie(5,"Cat5");
        
        cat1.setArticleList(new ArrayList<Article>());
        cat2.setArticleList(new ArrayList<Article>());
        cat3.setArticleList(new ArrayList<Article>());
        cat4.setArticleList(new ArrayList<Article>());
        cat5.setArticleList(new ArrayList<Article>());
        
        List<Categorie> list = new ArrayList<Categorie>();
        list.add(cat1);
        list.add(cat2);
        list.add(cat3);
        list.add(cat4);
        list.add(cat5);
        
        List<CategorieVO>listVO = Categorie.toVo(list);
        for(int i = 0; i<list.size(); i++){
            Assert.assertEquals(list.get(i).getCategorieId(),listVO.get(i).getCategorieId());
            Assert.assertEquals(list.get(i).getCategorieLibele(), listVO.get(i).getCategorieLibele());
        }
    }
}
