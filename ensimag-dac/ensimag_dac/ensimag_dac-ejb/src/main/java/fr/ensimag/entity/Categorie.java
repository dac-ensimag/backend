package fr.ensimag.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author dac
 */
@Entity
@Table(name = "CATEGORIE")
public class Categorie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "CATEGORIE_ID")
    private Integer categorieId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "CATEGORIE_LIBELE")
    private String categorieLibele;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categorie")
    private List<Article> articleList;

    public Categorie() {
    }

    public Categorie(Integer categorieId) {
        this.categorieId = categorieId;
    }

    public Categorie(Integer categorieId, String categorieLibele) {
        this.categorieId = categorieId;
        this.categorieLibele = categorieLibele;
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

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categorieId != null ? categorieId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorie)) {
            return false;
        }
        Categorie other = (Categorie) object;
        if ((this.categorieId == null && other.categorieId != null) || (this.categorieId != null && !this.categorieId.equals(other.categorieId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.ensimag.entity.Categorie[ categorieId=" + categorieId + " ]";
    }
    
}
