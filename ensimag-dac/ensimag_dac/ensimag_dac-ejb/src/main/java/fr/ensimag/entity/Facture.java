package fr.ensimag.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "FACTURE")
public class Facture implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "FACTURE_ID")
    private Integer factureId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FACTURE_DATE")
    @Temporal(TemporalType.DATE)
    private Date factureDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FACTURE_PRIXHT")
    private float facturePrixht;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FACTURE_TVA")
    private float factureTva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FACTURE_TOTALHT")
    private float factureTotalht;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FACTURE_TOTALTTC")
    private float factureTotalttc;
    @JoinColumn(name = "COMMANDE_ID", referencedColumnName = "COMMANDE_ID")
    @OneToOne(optional = false)
    private Commande commande;

    public Facture() {
    }

    public Facture(Integer factureId) {
        this.factureId = factureId;
    }

    public Facture(Integer factureId, Date factureDate, float facturePrixht, float factureTva, float factureTotalht, float factureTotalttc) {
        this.factureId = factureId;
        this.factureDate = factureDate;
        this.facturePrixht = facturePrixht;
        this.factureTva = factureTva;
        this.factureTotalht = factureTotalht;
        this.factureTotalttc = factureTotalttc;
    }

    public Integer getFactureId() {
        return factureId;
    }

    public void setFactureId(Integer factureId) {
        this.factureId = factureId;
    }

    public Date getFactureDate() {
        return factureDate;
    }

    public void setFactureDate(Date factureDate) {
        this.factureDate = factureDate;
    }

    public float getFacturePrixht() {
        return facturePrixht;
    }

    public void setFacturePrixht(float facturePrixht) {
        this.facturePrixht = facturePrixht;
    }

    public float getFactureTva() {
        return factureTva;
    }

    public void setFactureTva(float factureTva) {
        this.factureTva = factureTva;
    }

    public float getFactureTotalht() {
        return factureTotalht;
    }

    public void setFactureTotalht(float factureTotalht) {
        this.factureTotalht = factureTotalht;
    }

    public float getFactureTotalttc() {
        return factureTotalttc;
    }

    public void setFactureTotalttc(float factureTotalttc) {
        this.factureTotalttc = factureTotalttc;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (factureId != null ? factureId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facture)) {
            return false;
        }
        Facture other = (Facture) object;
        if ((this.factureId == null && other.factureId != null) || (this.factureId != null && !this.factureId.equals(other.factureId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.ensimag.entity.Facture[ factureId=" + factureId + " ]";
    }
    
}
