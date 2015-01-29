package fr.ensimag.logic;

import fr.ensimag.dao.ArticleDAOLocal;
import fr.ensimag.entity.Article;
import fr.ensimag.vo.ArticleVO;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ArticleService implements ArticleServiceLocal {

	@EJB
	ArticleDAOLocal articleDAO;

	@Override
	public void deleteArticle(Integer articleId) throws Exception {
		articleDAO.remove(articleDAO.find(articleId));
	}

	@Override
	public ArticleVO getArticle(Integer articleId) {
		Article article = articleDAO.find(articleId);
		if (article != null) {
			return article.toVO();
		}

		return null;
	}

}
