/**
 * 
 */
package com.wha.springmvc.model;

/**
 * @author Nicolas Lourdeau
 *
 */
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

	public CompteRemunerateur(long iD, String libelle, long clientID, int decouvert, float tauxDecouvert, int seuil, float tauxRemuneration) {
		super(iD, libelle, clientID, decouvert, tauxDecouvert);
		this.seuil = seuil;
		this.tauxRemuneration = tauxRemuneration;
		this.soldeRemuneration = 0;
	}
	//#endregion
	
}
