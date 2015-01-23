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

@ManagedBean(name = "CartBean")
@SessionScoped
public class CartBean implements Serializable {
	private List<ArticleVO> articles = new ArrayList<ArticleVO>();

	/**
	 * Creates a new instance of cart
	 */

	public CartBean() {
	}

	@PostConstruct
	public void init() {
		try {

		} catch (Exception e) {
		}
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
