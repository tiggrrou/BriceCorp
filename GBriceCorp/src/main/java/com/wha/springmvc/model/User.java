package com.wha.springmvc.model;

public class User {

	//#region Variables
	/**
	 * ID de l'utilisateur
	 */
	private long id;
	
	/**
	 * Nom de l'utilisateur
	 */
	private String nom;
	
	/**
	 * Prénom de l'utilisateur
	 */
	private String prenom;
	
	/**
	 * Adresse de l'utilisateur
	 */
	private String adresse;
	
	/**
	 * mail de l'utilisateur
	 */
	private String mail;
	
	/**
	 * Pseudonyme de l'utilisateur
	 */
	private String identifiant;
	
	/**
	 * Mot de passe dde l'utilisateur
	 */
	private String motDePasse;
	
	/**
	 * Téléphone de l'utilisateur
	 */
	private int telephone;
	
	
	//#endregion
	
	//#region Constructeurs
	public User(){
		id=0;
	}
	
	public User(long id, String username, String address, String email, String login, String mdp){
		this.id = id;
		this.nom = username;
		this.adresse = address;
		this.mail = email;
		this.identifiant = login;
		this.motDePasse = mdp;
	}
	//#endregion
	
	//#region Accesseurs
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	
	//#endregion

	//#region Méthodes redéfinies
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [getId()=" + getId() + ", getNom()=" + getNom() + ", getPrenom()=" + getPrenom()
				+ ", getAdresse()=" + getAdresse() + ", getMail()=" + getMail() + ", getIdentifiant()="
				+ getIdentifiant() + ", getMotDePasse()=" + getMotDePasse() + ", getTelephone()=" + getTelephone()
				+ ", hashCode()=" + hashCode() + "]";
	}

	
	//#endregion

	//#region Méthodes
	
	//#endregion
	
}
