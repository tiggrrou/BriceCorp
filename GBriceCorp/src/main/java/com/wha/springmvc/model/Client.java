package com.wha.springmvc.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
	private int revenu;
	
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

	@OneToMany(cascade={CascadeType.ALL})
	private List<Compte> comptes;
	
	//#endregion

	//#region Accesseurs
	public int getRevenu() {
		return this.revenu;
	}

	public void setRevenu(int revenu) {
		this.revenu = revenu;
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
		this.dateOuverture = new Date();
	}
	

	
	//#endregion

	@Override
	public String toString() {
		return "Client [getRevenu()=" + getRevenu() + ", getDateOuverture()=" + getDateOuverture()
				+ ", getDateCloture()=" + getDateCloture() + ", getConseillerID()=" + getConseillerID()
				+ ", getComptes()=" + getComptes() + "]";
	}
	
	
}
