package fr.ensimag.control;

import fr.ensimag.entity.Article;
import fr.ensimag.logic.ArticleServiceLocal;
import fr.ensimag.vo.ArticleVO;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean(name="articleBean")
@RequestScoped
public class ArticleBean implements Serializable {

	@EJB
	private ArticleServiceLocal articleService;

	private ArticleVO product;

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

	public void setProduct(ArticleVO product) {
		this.product = product;
	}

	public void deleteArticle() {this.articleService.deleteArticle(this.product.getArticleId());}

}
