package fr.ensimag.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "facture")
public class FactureEntity {
	private int                        factureId;
	private Date                       factureDate;
	private float                      facturePrixht;
	private float                      factureTva;
	private float                      factureTotalht;
	private float                      factureTotalttc;
	private Collection<CommandeEntity> commandesByFactureId;
	private CommandeEntity             commandeByCommandeId;

	@Id
	@Column(name = "FACTURE_ID")
	public int getFactureId() {
		return factureId;
	}

	public void setFactureId(int factureId) {
		this.factureId = factureId;
	}

	@Basic
	@Column(name = "FACTURE_DATE")
	public Date getFactureDate() {
		return factureDate;
	}

	public void setFactureDate(Date factureDate) {
		this.factureDate = factureDate;
	}

	@Basic
	@Column(name = "FACTURE_PRIXHT")
	public float getFacturePrixht() {
		return facturePrixht;
	}

	public void setFacturePrixht(float facturePrixht) {
		this.facturePrixht = facturePrixht;
	}

	@Basic
	@Column(name = "FACTURE_TVA")
	public float getFactureTva() {
		return factureTva;
	}

	public void setFactureTva(float factureTva) {
		this.factureTva = factureTva;
	}

	@Basic
	@Column(name = "FACTURE_TOTALHT")
	public float getFactureTotalht() {
		return factureTotalht;
	}

	public void setFactureTotalht(float factureTotalht) {
		this.factureTotalht = factureTotalht;
	}

	@Basic
	@Column(name = "FACTURE_TOTALTTC")
	public float getFactureTotalttc() {
		return factureTotalttc;
	}

	public void setFactureTotalttc(float factureTotalttc) {
		this.factureTotalttc = factureTotalttc;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		FactureEntity that = (FactureEntity) o;

		if (factureId != that.factureId) {
			return false;
		}
		if (Float.compare(that.facturePrixht, facturePrixht) != 0) {
			return false;
		}
		if (Float.compare(that.factureTotalht, factureTotalht) != 0) {
			return false;
		}
		if (Float.compare(that.factureTotalttc, factureTotalttc) != 0) {
			return false;
		}
		if (Float.compare(that.factureTva, factureTva) != 0) {
			return false;
		}
		if (factureDate != null ? !factureDate.equals(that.factureDate) : that.factureDate != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = factureId;
		result = 31 * result + (factureDate != null ? factureDate.hashCode() : 0);
		result = 31 * result + (facturePrixht != +0.0f ? Float.floatToIntBits(facturePrixht) : 0);
		result = 31 * result + (factureTva != +0.0f ? Float.floatToIntBits(factureTva) : 0);
		result = 31 * result + (factureTotalht != +0.0f ? Float.floatToIntBits(factureTotalht) : 0);
		result = 31 * result + (factureTotalttc != +0.0f ? Float.floatToIntBits(factureTotalttc) : 0);
		return result;
	}

	@OneToMany(mappedBy = "factureByFactureId")
	public Collection<CommandeEntity> getCommandesByFactureId() {
		return commandesByFactureId;
	}

	public void setCommandesByFactureId(Collection<CommandeEntity> commandesByFactureId) {
		this.commandesByFactureId = commandesByFactureId;
	}

	@ManyToOne
	@JoinColumn(name = "COMMANDE_ID", referencedColumnName = "COMMANDE_ID", nullable = false)
	public CommandeEntity getCommandeByCommandeId() {
		return commandeByCommandeId;
	}

	public void setCommandeByCommandeId(CommandeEntity commandeByCommandeId) {
		this.commandeByCommandeId = commandeByCommandeId;
	}
}
