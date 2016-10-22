package com.wha.springmvc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Dem_ModifInfo")
@PrimaryKeyJoinColumn(name = "id")
public class Dem_ModificationInfo extends Demande implements Serializable{

	//#region Attributs

	/**
	 * Nom du client
	 */
	@Column(name = "nom")
	private String nom;
	/**
	 * Prénom du client
	 */
	@Column(name = "prenom")
	private String prenom;
	/**
	 * Mail du client
	 */
	@Column(name = "mail")
	private String mail;
	/**
	 * Adresse du client
	 */
	@Column(name = "adresse")
	private String adresse;
	/**
	 * telephone du client
	 */
	@Column(name = "telephone")
	private int telephone;
	
	/**
	 * revenu mensuel du client
	 */
	@Column(name = "revenu")
	private int revenu;
	
	//#endregion

	//#region Accesseurs
	

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getTelephone() {
		return this.telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public int getRevenu() {
		return revenu;
	}

	public void setRevenu(int revenu) {
		this.revenu = revenu;
	}
	
	
	
	//#endregion

	//#region Constructeurs
	
	
	/**
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @param adresse
	 * @param telephone
	 * @param revenu
	 */
	public Dem_ModificationInfo() {
		super();
	}

	@Override
	public String toString() {
		return "Dem_ModificationInfo [getNom()=" + getNom() + ", getPrenom()=" + getPrenom() + ", getMail()="
				+ getMail() + ", getAdresse()=" + getAdresse() + ", getTelephone()=" + getTelephone() + ", getRevenu()="
				+ getRevenu() + "]";
	}



	
	
	//#endregion
	
	
	
	
	
}
