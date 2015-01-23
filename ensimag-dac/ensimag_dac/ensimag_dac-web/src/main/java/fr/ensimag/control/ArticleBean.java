package fr.ensimag.control;

import fr.ensimag.entity.Article;
import fr.ensimag.logic.ArticleServiceLocal;
import fr.ensimag.vo.ArticleVO;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean(name = "articleBean")
@ViewScoped
public class ArticleBean implements Serializable {

	@EJB
	private ArticleServiceLocal articleService;

    @ManagedProperty("#{param.id}")
    private Integer id;

	private ArticleVO product;

	public ArticleBean() {

	}

    public void setArticle() {
        try{ 
            this.product = articleService.getArticle(id);
        } catch (Exception e) {
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

	public String getCategorieName() {
		return product.getCategorie().getCategorieLibele();
	}

	public void setProduct(ArticleVO product) {
		this.product = product;
	}

	public void deleteArticle() {this.articleService.deleteArticle(this.product.getArticleId());}

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
