package fr.ensimag.logic;

import javax.ejb.Local;

import fr.ensimag.entity.Article;
import fr.ensimag.vo.ArticleVO;

@Local
public interface ArticleServiceLocal {

	public void deleteArticle(Integer articleId) throws Exception;

	public ArticleVO getArticle(Integer articleId);

	public ArticleVO createArticle(ArticleVO vo);

}
