package fr.ensimag.logic;

import fr.ensimag.vo.ArticleVO;

import javax.ejb.Local;

@Local
public interface ArticleServiceLocal {

	void deleteArticle(Integer articleId);

	ArticleVO getArticle(Integer articleId) throws Exception;
}
