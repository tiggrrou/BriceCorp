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

@SuppressWarnings("serial")
@Entity
@Table(name = "Client")
@PrimaryKeyJoinColumn(name = "id")
public class Client extends User implements Serializable{

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
	
	public List<Justificatif> getJustificatifs() {
		return justificatifs;
	}

	public void setJustificatifs(List<Justificatif> justificatifs) {
		this.justificatifs = justificatifs;
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
				+ getComptes() + ", getJustificatifs()=" + getJustificatifs() + ", getNotifications()="
				+ getNotifications() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((comptes == null) ? 0 : comptes.hashCode());
		result = prime * result + ((conseiller == null) ? 0 : conseiller.hashCode());
		result = prime * result + ((dateCloture == null) ? 0 : dateCloture.hashCode());
		result = prime * result + ((dateOuverture == null) ? 0 : dateOuverture.hashCode());
		result = prime * result + ((justificatifs == null) ? 0 : justificatifs.hashCode());
		result = prime * result + ((notifications == null) ? 0 : notifications.hashCode());
		result = prime * result + revenu;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (comptes == null) {
			if (other.comptes != null)
				return false;
		} else if (!comptes.equals(other.comptes))
			return false;
		if (conseiller == null) {
			if (other.conseiller != null)
				return false;
		} else if (!conseiller.equals(other.conseiller))
			return false;
		if (dateCloture == null) {
			if (other.dateCloture != null)
				return false;
		} else if (!dateCloture.equals(other.dateCloture))
			return false;
		if (dateOuverture == null) {
			if (other.dateOuverture != null)
				return false;
		} else if (!dateOuverture.equals(other.dateOuverture))
			return false;
		if (justificatifs == null) {
			if (other.justificatifs != null)
				return false;
		} else if (!justificatifs.equals(other.justificatifs))
			return false;
		if (notifications == null) {
			if (other.notifications != null)
				return false;
		} else if (!notifications.equals(other.notifications))
			return false;
		if (revenu != other.revenu)
			return false;
		return true;
	}




	
	//#endregion


	
	
}
