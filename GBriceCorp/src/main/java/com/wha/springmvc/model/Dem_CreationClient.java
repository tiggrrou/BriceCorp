package com.wha.springmvc.model;

public class Dem_CreationClient extends Demande {

	//#region Attributs
	/**
	 * id du conseiller attribue à cette demande
	 */
	private long conseillerId;
	/**
	 * Nom du client potentiel
	 */
	private String nom;
	/**
	 * Prénom du client potentiel
	 */
	private String prenom;
	/**
	 * Mail du client potentiel
	 */
	private String mail;
	/**
	 * Adresse du client potentiel
	 */
	private String adresse;
	/**
	 * telephone du client potentiel
	 */
	private int telephone;
	/**
	 * Justificatif de domicile
	 */
	private Justificatif domicile;
	/**
	 * Justificatif du salaire
	 */
	private Justificatif salaire;
	/**
	 * revenu mensuel du client
	 */
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

	public Justificatif getDomicile() {
		return domicile;
	}

	public void setDomicile(Justificatif domicile) {
		this.domicile = domicile;
	}

	public Justificatif getSalaire() {
		return salaire;
	}

	public void setSalaire(Justificatif salaire) {
		this.salaire = salaire;
	}

	public int getRevenu() {
		return revenu;
	}

	public void setRevenu(int revenu) {
		this.revenu = revenu;
	}
	//#endregion

	/**
	 * @param clientID
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @param adresse
	 * @param telephone
	 * @param domicile
	 * @param salaire
	 * @param revenu
	 */
	public Dem_CreationClient(long clientID,long conseillerId, String nom, String prenom, String mail, String adresse, int telephone,
			Justificatif domicile, Justificatif salaire, int revenu) {
		super(clientID);
		this.conseillerId = conseillerId;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.adresse = adresse;
		this.telephone = telephone;
		this.domicile = domicile;
		this.salaire = salaire;
		this.revenu = revenu;
	}

	/**
	 * @param clientID
	 */
	public Dem_CreationClient(long clientID) {
		super(clientID);
	}

	@Override
	public String toString() {
		return "Dem_CreationClient [getConseillerId()=" + getConseillerId() + ", getNom()=" + getNom()
				+ ", getPrenom()=" + getPrenom() + ", getMail()=" + getMail() + ", getAdresse()=" + getAdresse()
				+ ", getTelephone()=" + getTelephone() + ", getDomicile()=" + getDomicile() + ", getSalaire()="
				+ getSalaire() + ", getRevenu()=" + getRevenu() + "]";
	}

	
	
}
