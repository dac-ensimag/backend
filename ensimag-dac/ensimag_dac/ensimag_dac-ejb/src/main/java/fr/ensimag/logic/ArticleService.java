package fr.ensimag.logic;

import fr.ensimag.dao.ArticleDAOLocal;
import fr.ensimag.dao.CategorieDAOLocal;
import fr.ensimag.entity.Article;
import fr.ensimag.entity.Categorie;
import fr.ensimag.vo.ArticleVO;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ArticleService implements ArticleServiceLocal {

	@EJB
	ArticleDAOLocal articleDAO;

	@EJB
	CategorieDAOLocal categorieDAO;

	@Override
	public void deleteArticle(Integer articleId) throws Exception {
		try {
			articleDAO.remove(articleDAO.find(articleId));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public ArticleVO getArticle(Integer articleId) throws Exception {
		return articleDAO.find(articleId).toVO();
	}

	@Override
	public ArticleVO createArticle(ArticleVO vo) throws Exception {
		Article entity = new Article();
		entity.setArticleLibele(vo.getArticleLibele());
		entity.setArticlePrix(vo.getArticlePrix());
		entity.setArticleDisponibilite(true);
		entity.setArticleDescription(vo.getArticleDescription());
		entity.setArticleImg(vo.getArticleImg());
		Categorie cat = categorieDAO.find(1);
		if (cat != null) {
			entity.setCategorie(cat);
			cat.getArticleList().add(entity);
		}

		entity = articleDAO.create(entity);

		return entity.toVO();
	}

	@Override
	public Article getArticleEntity(Integer articleId) throws Exception {
		return articleDAO.find(articleId);
	}

}
