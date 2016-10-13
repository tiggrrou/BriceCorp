package com.wha.springmvc.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Client")
@PrimaryKeyJoinColumn(name = "id")
public class Client extends User {

	//#region Attributs
	/**
	 * revenus d'un client
	 */
	private int revenus;
	
	/**
	 * Date d'ouverture de l'espace client
	 */
	@Temporal(TemporalType.DATE)
	private Date dateOuverture;
	
	/**
	 * Date de cloture de l'espace client
	 */
	@Temporal(TemporalType.DATE)
	private Date dateCloture;
	
	/**
	 * ID du conseiller attitré au client
	 */
	private long conseillerID;

	@OneToMany
	private List<Compte> comptes;
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
	
	public List<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
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
	public Client(long id, String nom, String prenom, String address, String email, String login, String mdp, int telephone, int revenus, Date dateOuverture, Date dateCloture, long conseillerID) {
		super(id, nom, prenom, address, email, login, mdp, TypeUtilisateur.Client.getType(), telephone);
		this.revenus = revenus;
		this.dateOuverture = dateOuverture;
		this.dateCloture = dateCloture;
		this.conseillerID = conseillerID;
	}
	
	public Client(User user, int revenus, Date debut, Date fin, long idCons){
		super(user.getId(), user.getNom(), user.getPrenom(), 
				user.getAdresse(), user.getMail(), user.getIdentifiant(), 
				user.getMotDePasse(), TypeUtilisateur.Conseiller.getType(),user.getTelephone());
		this.revenus = revenus;
		this.dateOuverture = debut;
		this.dateCloture = fin;
		this.conseillerID = idCons;
		
	}
	//#endregion

	@Override
	public String toString() {
		return "Client [getRevenus()=" + getRevenus() + ", getDateOuverture()=" + getDateOuverture()
				+ ", getDateCloture()=" + getDateCloture() + ", getConseillerID()=" + getConseillerID()
				+ ", getComptes()=" + getComptes() + "]";
	}
	
	
}
