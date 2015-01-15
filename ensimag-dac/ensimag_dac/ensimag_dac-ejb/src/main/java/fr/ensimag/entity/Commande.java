package fr.ensimag.entity;

import fr.ensimag.vo.ArticleVO;
import fr.ensimag.vo.CommandeVO;
import fr.ensimag.vo.FactureVO;
import fr.ensimag.vo.UtilisateurVO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author dac
 */
@Entity
@Table(name = "COMMANDE")
public class Commande implements Serializable, IEntity<CommandeVO> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "COMMANDE_ID")
    private Integer commandeId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COMMANDE_DATE")
    @Temporal(TemporalType.DATE)
    private Date commandeDate;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "COMMANDE_DESCRIPTION")
    private String commandeDescription;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COMMANDE_TOTALE")
    private double commandeTotale;
    @ManyToMany(mappedBy = "commandeList")
    private List<Article> articleList;
    @JoinColumn(name = "UTILISATEUR_ID", referencedColumnName = "UTILISATEUR_ID")
    @ManyToOne(optional = false)
    private Utilisateur utilisateur;
    @OneToOne(mappedBy = "commande")
    private Facture facture;

    public Commande() {
    }

    public Commande(Integer commandeId) {
        this.commandeId = commandeId;
    }

    public Commande(Integer commandeId, Date commandeDate, String commandeDescription, double commandeTotale) {
        this.commandeId = commandeId;
        this.commandeDate = commandeDate;
        this.commandeDescription = commandeDescription;
        this.commandeTotale = commandeTotale;
    }

    public Integer getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Integer commandeId) {
        this.commandeId = commandeId;
    }

    public Date getCommandeDate() {
        return commandeDate;
    }

    public void setCommandeDate(Date commandeDate) {
        this.commandeDate = commandeDate;
    }

    public String getCommandeDescription() {
        return commandeDescription;
    }

    public void setCommandeDescription(String commandeDescription) {
        this.commandeDescription = commandeDescription;
    }

    public double getCommandeTotale() {
        return commandeTotale;
    }

    public void setCommandeTotale(double commandeTotale) {
        this.commandeTotale = commandeTotale;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commandeId != null ? commandeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        if ((this.commandeId == null && other.commandeId != null) || (this.commandeId != null && !this.commandeId.equals(other.commandeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.ensimag.entity.Commande[ commandeId=" + commandeId + " ]";
    }    
    
    @Override
    public CommandeVO toVO() {
        CommandeVO vo =  new CommandeVO();
        vo.setCommandeId(getCommandeId());
        vo.setCommandeDate(getCommandeDate());
        vo.setCommandeDescription(getCommandeDescription());
        vo.setCommandeTotale(getCommandeTotale());
        vo.setFacture(getFacture().toVO());
        vo.setUtilisateur(getUtilisateur().toVO());
        
        List<ArticleVO> articleVOList = new ArrayList<>();
        for (Article article : getArticleList()) {
            articleVOList.add(article.toVO());
        }
        vo.setArticleList(articleVOList);
        
        return vo;
    }
}
