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
	private int compteID;
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
	public int getCompteID() {
		return compteID;
	}
	public void setCompteID(int compteID) {
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

	public Dem_ModificationCompte() {
		super();
	}

	//#endregion
	
}
