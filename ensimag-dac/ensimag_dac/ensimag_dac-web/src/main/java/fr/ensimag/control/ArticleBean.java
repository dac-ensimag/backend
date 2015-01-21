package fr.ensimag.control;

import fr.ensimag.entity.Article;
import fr.ensimag.logic.ArticleServiceLocal;
import fr.ensimag.logic.CatalogServiceLocal;
import fr.ensimag.vo.ArticleVO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name="articleBean")
@RequestScoped
public class ArticleBean implements Serializable {

	@EJB
	private ArticleServiceLocal articleService;

	private Article product;

	public ArticleBean() {

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

	public String getCategorieName() {
		return product.getCategorie().getCategorieLibele();
	}

	public void setProduct(Article product) {
		this.product = product;
	}


}
