package fr.ensimag.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.ensimag.dao.ArticleDAOLocal;
import fr.ensimag.entity.Article;
import fr.ensimag.vo.ArticleVO;

@Stateless
public class ArticleService implements ArticleServiceLocal {

	@EJB
	ArticleDAOLocal articleDAO;

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
	public Article getArticleEntity(Integer articleId) throws Exception {
		return articleDAO.find(articleId);
	}

}
