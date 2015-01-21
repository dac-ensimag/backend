package fr.ensimag.control;

import fr.ensimag.logic.CatalogServiceLocal;
import fr.ensimag.vo.ArticleVO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@RequestScoped
public class CatalogBean implements Serializable {

	@EJB
	private CatalogServiceLocal catalog;
        

	private List<ArticleVO> products;

	public CatalogBean() {
	}

	@PostConstruct
	public void init() {
		try {
			this.products = catalog.getAllProducts();
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

	public String toString() {
		return getProducts().toString();
	}

}
