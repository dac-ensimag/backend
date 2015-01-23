package fr.ensimag.control;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import fr.ensimag.logic.CatalogServiceLocal;
import fr.ensimag.vo.ArticleVO;

@ManagedBean
@RequestScoped
public class CatalogBean implements Serializable {

	@EJB
	private CatalogServiceLocal catalog;

	private List<ArticleVO> products;

	/*
	 * Des articles pour tester
	 *
	 * ArticleVO art;
	 *
	 * ArticleVO art2;
	 *
	 * ArticleVO art3;
	 */
	public CatalogBean() {
	}

	@PostConstruct
	public void init() {
		try {
			products = catalog.getAllProducts();
			/*
			 * Ajouter d'articles pour tester
			 * 
			 * art = new ArticleVO();
			 * 
			 * art2 = new
			 * 
			 * ArticleVO(); art3 = new ArticleVO();
			 * 
			 * art.setArticleId(1);
			 * 
			 * art2.setArticleId(2);
			 * 
			 * art3.setArticleId(3);
			 * 
			 * art.setArticleLibele("Projet A");
			 * art2.setArticleLibele("Projet B");
			 * art3.setArticleLibele("Projet C");
			 * 
			 * art.setArticlePrix(100);
			 * 
			 * art2.setArticlePrix(200); art3.setArticlePrix(300);
			 * 
			 * products.add(art);
			 * 
			 * products.add(art2); p
			 * 
			 * roducts.add(art3);
			 */
		} catch (Exception e) {
		}
	}

	public List<ArticleVO> getProducts() {
		return products;
	}

	public String view() {
		return "failure";
	}

	public String addToCart() {
		return "failure";
	}

	@Override
	public String toString() {
		return getProducts().toString();
	}

}
