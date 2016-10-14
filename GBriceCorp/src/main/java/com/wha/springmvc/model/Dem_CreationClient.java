package com.wha.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Dem_CreationClient")
@PrimaryKeyJoinColumn(name = "id")
public class Dem_CreationClient extends Demande {

	//#region Attributs
	/**
	 * id du conseiller attribue à cette demande
	 */
	@Column(name = "conseillerId")
	private long conseillerId;
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
	/**
	 * Justificatif de domicile
	 */
//	@OneToMany
//	private List<Justificatif> domicile;
//	/**
//	 * Justificatif du salaire
//	 */
//	@OneToMany
//	private List<Justificatif> salaire;
	/**
	 * revenu mensuel du client
	 */
	@Column(name = "revenu")
	private int revenu;
	//#endregion

	//#region Accesseurs
	
	public long getConseillerId() {
		return conseillerId;
	}

	public void setConseillerId(long conseillerId) {
		this.conseillerId = conseillerId;
	}
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

//	public Collection<Justificatif> getDomicile() {
//		return domicile;
//	}
//
//	public void setDomicile(Justificatif domicile) {
//		this.domicile = (List<Justificatif>) domicile;
//	}
//
//	public Collection<Justificatif> getSalaire() {
//		return salaire;
//	}
//
//	public void setSalaire(Justificatif salaire) {
//		this.salaire = (List<Justificatif>) salaire;
//	}

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
	}


	@Override
	public String toString() {
		return "Dem_CreationClient [getConseillerId()=" + getConseillerId() + ", getNom()=" + getNom()
				+ ", getPrenom()=" + getPrenom() + ", getMail()=" + getMail() + ", getAdresse()=" + getAdresse()
				+ ", getTelephone()=" + getTelephone() + ", getRevenu()=" + getRevenu() + "]";
	}

	//#endregion
	
}
