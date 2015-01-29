package fr.ensimag.logic;

import fr.ensimag.dao.ArticleDAOLocal;
import fr.ensimag.dao.CategorieDAOLocal;
import fr.ensimag.entity.Article;
import fr.ensimag.entity.Categorie;
import fr.ensimag.vo.ArticleVO;
import fr.ensimag.vo.CategorieVO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class CatalogService implements CatalogServiceLocal {

	@EJB
	ArticleDAOLocal articleDAO;
        
	@EJB
	CategorieDAOLocal categorieDAO;

	@Override
	public List<ArticleVO> getAllProducts() throws Exception {
        List<Article> articles = articleDAO.findAll();
        return Article.toVo(articles);
	}
        
	@Override
	public List<CategorieVO> getAllCategories() throws Exception {
		List<Categorie> cats = categorieDAO.findAll();
		return Categorie.toVo(cats);
	}

}
