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

import fr.ensimag.logic.ArticleServiceLocal;
import fr.ensimag.util.RepeatPaginator;
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
