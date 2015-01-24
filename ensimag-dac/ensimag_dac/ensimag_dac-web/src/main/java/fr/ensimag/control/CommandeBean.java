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

@ManagedBean(name = "CommandeBean")
@SessionScoped
public class CommandeBean implements Serializable {
	private final List<ArticleVO> articles = new ArrayList<ArticleVO>();

	/**
	 * Creates a new instance of cart
	 */

	public CommandeBean() {
	}

	@PostConstruct
	public void init() {
		try {

		} catch (final Exception e) {
		}
	}

	public void add(final ArticleVO articlevo) {
		this.articles.add(articlevo);
	}

	public void remove(final ArticleVO articlevo) {
		this.articles.remove(articlevo);
	}

	public Map<ArticleVO, Integer> getCartContents() {

		final Map<ArticleVO, Integer> cartContents = new HashMap<ArticleVO, Integer>();
		for (final ArticleVO obj : this.articles) {
			cartContents.put(obj, 1);
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
