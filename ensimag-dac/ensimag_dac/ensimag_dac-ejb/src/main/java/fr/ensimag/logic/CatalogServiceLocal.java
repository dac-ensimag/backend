package fr.ensimag.logic;

import java.util.List;

import javax.ejb.Local;

import fr.ensimag.vo.ArticleVO;

@Local
public interface CatalogServiceLocal {

	List<ArticleVO> getAllProducts() throws Exception;

}
