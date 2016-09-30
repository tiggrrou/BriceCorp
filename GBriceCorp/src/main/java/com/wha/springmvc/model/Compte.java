/**
 * 
 */
package com.wha.springmvc.model;

import java.util.Date;

/**
 * @author Nicolas Lourdeau
 *
 */
public class Compte {

	//#region Attributs
	/**
	 * ID d'un compte (correspond à l'IBAN)
	 */
	private String ID;
	/**
	 * Libellé de description du compte
	 */
	private String libelle;
	/**
	 * Date d'ouverture du compte
	 */
	private Date dateOuverture;
	/**
	 * Date de cloture du compte
	 */
	private Date dateCloture;
	/**
	 * ID du client possédant le compte
	 */
	private long clientID;
	/**
	 * Booléen traduisant l'activité sur le compte
	 */
	private boolean actif;
	/**
	 * Solde courant du compte
	 */
	private float solde;
	/**
	 * Solde de l'agio en cours
	 */
	private float soldeAgio;
	/**
	 * Montant du découvert autorisé, peut être égale à zéro
	 */
	private int decouvert;
	/**
	 * Taux du découvert pour calcul de l'agio
	 */
	private float tauxDecouvert;
	//#endregion
	
	//#region accesseurs
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Date getDateOuverture() {
		return dateOuverture;
	}
	public void setDateOuverture(Date dateOuverture) {
		this.dateOuverture = dateOuverture;
	}
	public Date getDateCloture() {
		return dateCloture;
	}
	public void setDateCloture(Date dateCloture) {
		this.dateCloture = dateCloture;
	}
	public long getClientID() {
		return clientID;
	}
	public void setClientID(long clientID) {
		this.clientID = clientID;
	}
	public boolean isActif() {
		return actif;
	}
	public void setActif(boolean actif) {
		this.actif = actif;
	}
	public float getSolde() {
		return solde;
	}
	public void setSolde(float solde) {
		this.solde = solde;
	}
	public float getSoldeAgio() {
		return soldeAgio;
	}
	public void setSoldeAgio(float soldeAgio) {
		this.soldeAgio = soldeAgio;
	}
	public int getDecouvert() {
		return decouvert;
	}
	public void setDecouvert(int decouvert) {
		this.decouvert = decouvert;
	}
	public float getTauxDecouvert() {
		return tauxDecouvert;
	}
	public void setTauxDecouvert(float tauxDecouvert) {
		this.tauxDecouvert = tauxDecouvert;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	//#endregion
	
	//#region Constructeurs
	public Compte(){
		
	}
	
	/**
	 * Constructeur avec les champs pouvant être renseignés
	 * @param iD
	 * @param libelle
	 * @param clientID
	 * @param decouvert
	 * @param tauxDecouvert
	 */
	public Compte(String iD, String libelle, long clientID, int decouvert, float tauxDecouvert) {
		super();
		ID = iD;
		this.libelle = libelle;
		this.dateOuverture = new Date();
		this.dateCloture = null;
		this.clientID = clientID;
		this.actif = true;
		this.solde = 0;
		this.soldeAgio = 0;
		this.decouvert = decouvert;
		this.tauxDecouvert = tauxDecouvert;
	}
	//#endregion
	
	
	
}
