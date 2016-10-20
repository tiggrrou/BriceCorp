package com.wha.springmvc.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Dem_CreationClient")
@PrimaryKeyJoinColumn(name = "id")
public class Dem_CreationClient extends Demande {

	//#region Attributs

	/**
	 * Nom du client potentiel
	 */
	@Column(name = "nom")
	private String nom;
	/**
	 * Prénom du client potentiel
	 */
	@Column(name = "prenom")
	private String prenom;
	/**
	 * Mail du client potentiel
	 */
	@Column(name = "mail")
	private String mail;
	/**
	 * Adresse du client potentiel
	 */
	@Column(name = "adresse")
	private String adresse;
	/**
	 * telephone du client potentiel
	 */
	@Column(name = "telephone")
	private int telephone;
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private Set<Justificatif> justificatifs;


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


	public Set<Justificatif> getJustificatifs() {
		return justificatifs;
	}

	public void setJustificatifs(Set<Justificatif> justificatifs) {
		this.justificatifs = justificatifs;
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
	 * @param clientID
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @param adresse
	 * @param telephone
	 * @param justificatif
	 * @param justificatif2
	 * @param revenu
	 */
	public Dem_CreationClient() {
		super();
		this.justificatifs = new HashSet<Justificatif>();
	}

	@Override
	public String toString() {
		return "Dem_CreationClient [getNom()=" + getNom() + ", getPrenom()=" + getPrenom() + ", getMail()=" + getMail()
				+ ", getAdresse()=" + getAdresse() + ", getTelephone()=" + getTelephone() + ", getJustificatifs()="
				+ getJustificatifs() + ", getRevenu()=" + getRevenu() + "]";
	}



	
	
	//#endregion
	
	
	
	
	
}
