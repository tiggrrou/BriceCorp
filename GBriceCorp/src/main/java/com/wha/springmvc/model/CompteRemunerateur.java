/**
 * 
 */
package com.wha.springmvc.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author Nicolas Lourdeau
 *
 */
@Entity
@Table(name = "CompteRemunerateur")
@PrimaryKeyJoinColumn(name = "id")
public class CompteRemunerateur extends Compte {

	//#region Attributs
	/**
	 * Seuil à partir duquel le compte perçoit des rémunérations
	 */
	private int seuil;
	/**
	 * Taux de rémunération 
	 */
	private float tauxRemuneration;
	/**
	 * Solde de rémunération pour le mois en cours
	 */
	private float soldeRemuneration;

	//#endregion 
	
	//#region Accesseurs
	public int getSeuil() {
		return this.seuil;
	}

	public void setSeuil(int seuil) {
		this.seuil = seuil;
	}

	public float getTauxRemuneration() {
		return this.tauxRemuneration;
	}

	public void setTauxRemuneration(float tauxRemuneration) {
		this.tauxRemuneration = tauxRemuneration;
	}

	public float getSoldeRemuneration() {
		return this.soldeRemuneration;
	}

	public void setSoldeRemuneration(float soldeRemuneration) {
		this.soldeRemuneration = soldeRemuneration;
	}
	//#endregion
	
	//#region Constructeurs
	public CompteRemunerateur() {
		super();
		// TODO Auto-generated constructor stub
	}


	//#endregion
	
}
