package com.wha.springmvc.model;

import java.util.Date;

public class Client extends User {

	//#region Attributs
	/**
	 * revenus d'un client
	 */
	private int revenus;
	
	/**
	 * Date d'ouverture de l'espace client
	 */
	private Date dateOuverture;
	
	/**
	 * Date de cloture de l'espace client
	 */
	private Date dateCloture;
	
	/**
	 * ID du conseiller attitré au client
	 */
	private long conseillerID;
	//#endregion

	//#region Accesseurs
	public int getRevenus() {
		return this.revenus;
	}

	public void setRevenus(int revenus) {
		this.revenus = revenus;
	}

	public Date getDateOuverture() {
		return this.dateOuverture;
	}

	public void setDateOuverture(Date dateOuverture) {
		this.dateOuverture = dateOuverture;
	}

	public Date getDateCloture() {
		return this.dateCloture;
	}

	public void setDateCloture(Date dateCloture) {
		this.dateCloture = dateCloture;
	}

	public long getConseillerID() {
		return this.conseillerID;
	}

	public void setConseillerID(long conseillerID) {
		this.conseillerID = conseillerID;
	}
	//#endregion 

	//#region Constructeur
	public Client() {
		super();
	}
	
	/**
	 * Constructeur d'un client héritant de la super class User
	 * @param id
	 * @param username
	 * @param address
	 * @param email
	 * @param login
	 * @param mdp
	 * @param revenus
	 * @param dateOuverture
	 * @param dateCloture
	 * @param conseillerID
	 */
	public Client(long id, String username, String address, String email, String login, String mdp, int revenus, Date dateOuverture, Date dateCloture, long conseillerID) {
		super(id, username, address, email, login, mdp, TypeUtilisateur.Client.getType());
		this.revenus = revenus;
		this.dateOuverture = dateOuverture;
		this.dateCloture = dateCloture;
		this.conseillerID = conseillerID;
	}
	//#endregion
	
}
