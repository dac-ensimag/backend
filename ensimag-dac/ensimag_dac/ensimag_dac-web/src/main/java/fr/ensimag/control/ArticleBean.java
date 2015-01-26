package fr.ensimag.control;

import fr.ensimag.logic.ArticleServiceLocal;
import fr.ensimag.vo.ArticleVO;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

@ManagedBean(name = "articleBean")
@ViewScoped
public class ArticleBean implements Serializable {

	@EJB
	private ArticleServiceLocal articleService;

	//    @ManagedProperty("#{param.id}")
	private Integer id;

	private ArticleVO product;
	private String    error;

	public ArticleBean() {

	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setArticle() {
		try {
			this.product = articleService.getArticle(id);
		} catch (Exception e) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintStream ps = new PrintStream(baos);
			e.printStackTrace(ps);
			try {
				this.error = baos.toString("UTF-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
	}

	public String getArticleLibele() {
		return product.getArticleLibele();
	}

	public String getArticleDescription() {
		return product.getArticleDescription();
	}

	public float getArticlePrix() {
		return product.getArticlePrix();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String deleteArticle() {
		return "/admin/supr_item.xhtml?faces-redirect=true";
	}

}
