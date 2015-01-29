package fr.ensimag.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.ensimag.logic.CatalogServiceLocal;
import fr.ensimag.vo.ArticleVO;

@ManagedBean(name = "cartBean")
@SessionScoped
public class CartBean implements Serializable {

	// AB: QUE pour tester -- a supprimer
	@EJB
	private CatalogServiceLocal catalog;

	private List<ArticleVO> articlesCatalog;

	// FIN AB:

	private List<ArticleVO> articles = new ArrayList<ArticleVO>();

	public List<ArticleVO> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleVO> articles) {
		this.articles = articles;
	}

	/**
	 * Creates a new instance of cart
	 */

	public CartBean() {
	}

	@PostConstruct
	public void init() {
		articlesCatalog = new ArrayList<ArticleVO>();
		try {
			this.articlesCatalog.addAll(this.catalog.getAllProducts());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		articles.add(this.articlesCatalog.get(1));
		articles.add(this.articlesCatalog.get(2));
		articles.add(this.articlesCatalog.get(3));

	}

	public void add(ArticleVO articlevo) {
		articles.add(articlevo);
	}

	public void remove(ArticleVO articlevo) {
		articles.remove(articlevo);
	}

	public Map<ArticleVO, Integer> getCartContents() {

		Map<ArticleVO, Integer> cartContents = new HashMap<ArticleVO, Integer>();
		for (ArticleVO obj : articles) {
			cartContents.put(obj, 1);
		}
		return cartContents;
	}

	public int getCartCount() {
		return articles.size();
	}

	public float getCartTotalPrice() {
		float sum = 0;
		for (ArticleVO obj : articles) {
			sum = sum + obj.getArticlePrix();
		}
		return sum;
	}

}
