package fr.ensimag.control;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import util.RepeatPaginator;
import fr.ensimag.logic.CatalogServiceLocal;
import fr.ensimag.vo.ArticleVO;

@ManagedBean
@RequestScoped
public class CatalogBean implements Serializable {

	@EJB
	private CatalogServiceLocal catalog;

	private List<ArticleVO> products;

	private RepeatPaginator paginator;

	private String error;

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
			this.products = new ArrayList<ArticleVO>();

			// Ajouter d'articles pour tester

			/*
			 * ArticleVO art = new ArticleVO();
			 * 
			 * ArticleVO art2 = new ArticleVO();
			 * 
			 * ArticleVO art3 = new ArticleVO();
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
			 * products.add(art); products.add(art2); products.add(art3);
			 */

			this.products = this.catalog.getAllProducts();

			this.paginator = new RepeatPaginator(this.products);
		} catch (final Exception e) {
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			final PrintStream ps = new PrintStream(baos);
			e.printStackTrace(ps);
			try {
				this.error = baos.toString("UTF-8");
			} catch (final UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
	}

	public List<ArticleVO> getProducts() {
		return this.products;
	}

	public String view() {
		return "view_item?faces-redirect=true";
	}

	@Override
	public String toString() {
		return this.getProducts().toString();
	}

	public void setError(final String error) {
		this.error = error;
	}

	public String getError() {
		return this.error;
	}

	public RepeatPaginator getPaginator() {
		return this.paginator;
	}

}
