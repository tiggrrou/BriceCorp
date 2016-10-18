package com.wha.springmvc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Client")
@PrimaryKeyJoinColumn(name = "id")
public class Client extends User  implements Serializable{

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

	@ManyToOne
	private Conseiller conseiller;

	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<Justificatif> justificatifs;
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<Compte> comptes;

	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<Notification> notifications;
	
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

	public long getConseiller() {
		return conseiller.getId();
	}

	public void setConseiller(Conseiller conseiller) {
		this.conseiller = conseiller;
	}

	public List<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}
	public List<Notification> getNotifications() {
		return notifications;
	}
	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}	
	
	//#endregion 



	//#region Constructeur
	public Client() {
		super();
		this.dateOuverture = new Date();
		this.notifications = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Client [getRevenu()=" + getRevenu() + ", getDateOuverture()=" + getDateOuverture()
				+ ", getDateCloture()=" + getDateCloture() + ", getConseiller()=" + getConseiller() + ", getComptes()="
				+ getComptes() + ", getNotifications()=" + getNotifications() + "]";
	}



	
	//#endregion


	
	
}
