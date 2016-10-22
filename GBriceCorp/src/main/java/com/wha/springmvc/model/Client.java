package com.wha.springmvc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
	private Set<Justificatif> justificatifs;
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private Set<Compte> comptes;

	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private Set<Notification> notifications;
	
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

	public Set<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(Set<Compte> comptes) {
		this.comptes = comptes;
	}
	
	public Set<Justificatif> getJustificatifs() {
		return justificatifs;
	}

	public void setJustificatifs(Set<Justificatif> justificatifs) {
		this.justificatifs = justificatifs;
	}

	public Set<Notification> getNotifications() {
		return notifications;
	}
	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}	
	
	//#endregion 



	//#region Constructeur
	public Client() {
		super();
		this.dateOuverture = new Date();
		this.notifications = new HashSet<Notification>();
		this.justificatifs = new HashSet<Justificatif>();
		this.comptes = new HashSet<Compte>();
	}

	@Override
	public String toString() {
		return "Client [getRevenu()=" + getRevenu() + ", getDateOuverture()=" + getDateOuverture()
				+ ", getDateCloture()=" + getDateCloture() + ", getConseiller()=" + getConseiller() + ", getComptes()="
				+ getComptes() + ", getJustificatifs()=" + getJustificatifs() + ", getNotifications()="
				+ getNotifications() + "]";
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
