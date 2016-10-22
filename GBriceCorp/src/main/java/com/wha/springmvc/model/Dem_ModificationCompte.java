/**
 * 
 */
package com.wha.springmvc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author Nicolas Lourdeau
 *
 */
@Entity
@Table(name = "Dem_ModificationCompte")
@PrimaryKeyJoinColumn(name = "id")
public class Dem_ModificationCompte extends Demande implements Serializable{

	//#region Attributs
	/**
	 * ID du compte à modifier
	 */
	@ManyToOne
	private Compte compte;
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
	
	@Column(name = "libelle")
	private String libelle;
	//#endregion
	
	//#region Accesseurs


	public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
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
		public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	//#endregion
	
	//#region Constructeurs


	public Dem_ModificationCompte() {
		super();
	}
	@Override
	public String toString() {
		return "Dem_ModificationCompte [getCompte()=" + getCompte() + ", getDecouvert()=" + getDecouvert()
				+ ", isRemunerateur()=" + isRemunerateur() + ", libelle :" +getLibelle() + "]";
	}

	//#endregion
	
	
	
	
}
