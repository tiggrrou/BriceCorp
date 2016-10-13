/**
 * 
 */
package com.wha.springmvc.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**

 * @author Nicolas Lourdeau
 *
 */
@Entity
@Table(name = "Compte")
@Inheritance(strategy = InheritanceType.JOINED)
public class Compte {

	//#region Attributs
	/**
	 * ID d'un compte (correspond à l'IBAN)
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String ID;
	/**
	 * Libellé de description du compte
	 */
	private String libelle;
	/**
	 * Date d'ouverture du compte
	 */
	@Temporal(TemporalType.DATE)
	private Date dateOuverture;
	/**
	 * Date de cloture du compte
	 */
	@Temporal(TemporalType.DATE)
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
	
	/**
	 * Constructeur pour dummy  BDD
	 * @param iD
	 * @param libelle
	 * @param clientID
	 * @param decouvert
	 * @param tauxDecouvert
	 */
	public Compte(String iD, String libelle, long clientID, int decouvert, float tauxDecouvert, float solde) {
		super();
		ID = iD;
		this.libelle = libelle;
		this.dateOuverture = new Date();
		this.dateCloture = null;
		this.clientID = clientID;
		this.actif = true;
		this.solde = solde;
		this.soldeAgio = 0;
		this.decouvert = decouvert;
		this.tauxDecouvert = tauxDecouvert;
	}
	//#endregion
	
	
	
}
