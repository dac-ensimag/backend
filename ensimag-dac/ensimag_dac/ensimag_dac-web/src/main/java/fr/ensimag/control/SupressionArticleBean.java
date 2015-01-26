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

@ManagedBean(name = "suprArticleBean")
@ViewScoped
public class SupressionArticleBean implements Serializable {

	@EJB
	private ArticleServiceLocal articleService;

	//    @ManagedProperty("#{param.id}")
	private Integer id;

	private ArticleVO product;
	private String    error;

	public SupressionArticleBean() {

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

	public String cancelDeletion() { return "catalogue.xhtml"; }

	public String deleteArticle() {
		this.articleService.deleteArticle(this.id);
		return "catalogue.xhtml";
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
