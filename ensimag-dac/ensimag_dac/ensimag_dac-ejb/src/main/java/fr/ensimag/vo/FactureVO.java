package fr.ensimag.vo;

import java.util.Date;

public class FactureVO implements IValueObject {

	private Integer    factureId;
	private Date       factureDate;
	private float      facturePrixht;
	private float      factureTva;
	private float      factureTotalht;
	private float      factureTotalttc;
	private Integer    commandeId;

	public FactureVO() {
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

	public Integer getCommandeId() {
		return commandeId;
	}

	public void setCommandeId(Integer commandeId) {
		this.commandeId = commandeId;
	}

}
