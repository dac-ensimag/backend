package fr.ensimag.logic;



import javax.ejb.Local;
import java.util.List;

@Local
public interface ArticleServiceLocal {

	void deleteArticle(Integer articleId);

}
