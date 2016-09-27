/**
 * 
 */
package com.wha.springmvc.model;

import java.util.Date;

/**
 * @author Nicolas Lourdeau
 *
 */
public abstract class Mouvement {

	//#region Attributs
	/**
	 * ID d'un mouvement
	 */
	private long ID;
	/**
	 * montant du mouvement (toujours positif)
	 */
	private float montant;
	/**
	 * date d'impact du mouvement sur le compte
	 */
	private Date dateMouvement;
	/**
	 * référence au compte auquel est lié le mouvement
	 */
	private long compteID;
	/**
	 * libellé descriptif associé au mouvement
	 */
	private String libelle;
	//#region
	//#region Accesseurs
	public long getID() {
		return this.ID;
	}

	public void setID(long iD) {
		this.ID = iD;
	}

	public float getMontant() {
		return this.montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}

	public long getCompteID() {
		return this.compteID;
	}

	public void setCompteID(long compteID) {
		this.compteID = compteID;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Date getDateMouvement() {
		return this.dateMouvement;
	}

	public void setDateMouvement(Date dateMouvement) {
		this.dateMouvement = dateMouvement;
	}
	// #endregion
	//#region Constructeurs 

	public Mouvement(long iD, float montant, Date dateMouvement, long compteID, String libelle) {
		super();
		ID = iD;
		this.montant = montant;
		this.dateMouvement = dateMouvement;
		this.compteID = compteID;
		this.libelle = libelle;
	}

	public Mouvement() {
		super();
	}
	//#endregion
}
