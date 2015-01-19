package fr.ensimag.logic;

import fr.ensimag.vo.ArticleVO;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CatalogServiceLocal {

	List<ArticleVO> getAllProducts() throws Exception;

}
