package fr.ensimag.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.ensimag.vo.ArticleVO;

@ManagedBean(name = "cartBean")
@SessionScoped
public class CartBean implements Serializable {

	private List<ArticleVO> articles = new ArrayList<ArticleVO>();

	public List<ArticleVO> getArticles() {
		return this.articles;
	}

	public void setArticles(final List<ArticleVO> articles) {
		this.articles = articles;
	}

	/**
	 * Creates a new instance of cart
	 */

	public CartBean() {
	}

	@PostConstruct
	public void init() {

		try {

		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void clear() {
		this.articles = new ArrayList<ArticleVO>();
	}

	public void add(final ArticleVO articlevo) {
		this.articles.add(articlevo);
	}

	public void remove(final ArticleVO articlevo) {
		this.articles.remove(articlevo);
	}

	public Map<ArticleVO, Integer> getCartContents() {

		final Map<ArticleVO, Integer> cartContents = new HashMap<>();
		for (final ArticleVO obj : this.articles) {
			if (cartContents.containsKey(obj)) {
				cartContents.put(obj, cartContents.get(obj) + 1);
			} else {
				cartContents.put(obj, 1);
			}
		}
		return cartContents;
	}

	public int getCartCount() {
		return this.articles.size();
	}

	public float getCartTotalPrice() {
		float sum = 0;
		for (final ArticleVO obj : this.articles) {
			sum = sum + obj.getArticlePrix();
		}
		return sum;
	}

}
