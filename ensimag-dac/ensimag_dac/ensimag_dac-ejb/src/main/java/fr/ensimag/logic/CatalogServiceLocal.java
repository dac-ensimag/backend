package fr.ensimag.logic;

import java.util.List;

import javax.ejb.Local;

import fr.ensimag.vo.ArticleVO;
import fr.ensimag.vo.CategorieVO;

@Local
public interface CatalogServiceLocal {

	List<ArticleVO> getAllProducts() throws Exception;

        List<CategorieVO> getAllCategories() throws Exception;

}
