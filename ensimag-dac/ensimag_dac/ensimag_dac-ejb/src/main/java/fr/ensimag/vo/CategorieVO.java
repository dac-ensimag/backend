package fr.ensimag.vo;

import java.util.List;

/**
 *
 * @author dac
 */
public class CategorieVO implements IValueObject {

    private Integer categorieId;
    private String categorieLibele;
    private List<ArticleVO> articleList;

    public CategorieVO() {
    }

    public Integer getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Integer categorieId) {
        this.categorieId = categorieId;
    }

    public String getCategorieLibele() {
        return categorieLibele;
    }

    public void setCategorieLibele(String categorieLibele) {
        this.categorieLibele = categorieLibele;
    }

    public List<ArticleVO> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<ArticleVO> articleList) {
        this.articleList = articleList;
    }
    
}
