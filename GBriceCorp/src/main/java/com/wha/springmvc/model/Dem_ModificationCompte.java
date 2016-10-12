/**
 * 
 */
package com.wha.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author Nicolas Lourdeau
 *
 */
@Entity
@Table(name = "Dem_ModificationCompte")
@PrimaryKeyJoinColumn(name = "id")
public class Dem_ModificationCompte extends Demande {

	//#region Attributs
	/**
	 * ID du compte à modifier
	 */
	@Column(name = "compteID")
	private String compteID;
	/**
	 * Demande de modification d'autorisation de découvert
	 */
	@Column(name = "decouvert")
	private int decouvert;
	/**
	 * volonté de passer en compte rémunérateur
	 */
	@Column(name = "remunerateur")
	private boolean remunerateur;
	//#endregion
	
	//#region Accesseurs
	public String getCompteID() {
		return compteID;
	}
	public void setCompteID(String compteID) {
		this.compteID = compteID;
	}
	public int getDecouvert() {
		return decouvert;
	}
	public void setDecouvert(int decouvert) {
		this.decouvert = decouvert;
	}
	public boolean isRemunerateur() {
		return remunerateur;
	}
	public void setRemunerateur(boolean remunerateur) {
		this.remunerateur = remunerateur;
	}
	
	
	//#endregion
	
	//#region Constructeurs
	/**
	 * @param clientID
	 * @param compteID
	 * @param decouvert
	 * @param remunerateur
	 */
	public Dem_ModificationCompte(long clientID, String compteID, int decouvert, boolean remunerateur) {
		super(clientID);
		this.compteID = compteID;
		this.decouvert = decouvert;
		this.remunerateur = remunerateur;
	}
	/**
	 * @param clientID
	 */
	public Dem_ModificationCompte(long clientID) {
		super(clientID);
		// TODO Auto-generated constructor stub
	}
	//#endregion
	
}
