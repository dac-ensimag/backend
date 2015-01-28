package fr.ensimag.logic;

import javax.ejb.Local;

import fr.ensimag.entity.Article;
import fr.ensimag.vo.ArticleVO;

@Local
public interface ArticleServiceLocal {

	void deleteArticle(Integer articleId) throws Exception;

	ArticleVO getArticle(Integer articleId) throws Exception;

	Article getArticleEntity(Integer articleId) throws Exception;

}
