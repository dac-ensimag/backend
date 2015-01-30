package fr.ensimag.control;

import fr.ensimag.logic.ArticleServiceLocal;
import fr.ensimag.vo.ArticleVO;

import javax.annotation.PostConstruct;
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

	@PostConstruct
	public void init() { product = new ArticleVO(); }

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

	public void setArticleLibele(String articleLibele) {
		product.setArticleLibele(articleLibele);
	}

	public String getArticleDescription() {
		return product.getArticleDescription();
	}

	public void setArticleDescription(String articleDescription) {
		product.setArticleDescription(articleDescription);
	}

	public String getArticlePrix() {
		return new Float(product.getArticlePrix()).toString();
	}

	public void setArticlePrix(String articlePrix) {
		product.setArticlePrix(Float.parseFloat(articlePrix));
	}
        
	public String getArticleImg(){
		if( product.getArticleImg()==null || (product.getArticleImg().isEmpty())){
			return "http://morpheo.inrialpes.fr/people/hetroy/data/uploads/ensimag.jpg"; //IMAGE DE BASE (TODO : CHANGER?)
		} else {
			return product.getArticleImg();
		}
	}

	public void setArticleImg(String articleImg){
		if (!articleImg.equals("")) {
			product.setArticleImg(articleImg);
		}
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String deleteArticle() {
		return "admin/supr_item?faces-redirect=true";
	}

	public String createArticle() throws Exception {
		ArticleVO res = null;
		res = articleService.createArticle(this.product);
		this.product = new ArticleVO();
		if (res != null) {
			return "/catalogue?faces-redirect=true";
		} else {
			return "/login?faces-redirect=true";
		}
	}

}
