/**
 * 
 */
package com.wha.springmvc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Nicolas Lourdeau
 *
 */

@Entity
@Table(name = "Demande")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Demande implements Serializable {

	// #region Attributs
	/**
	 * 
	 * ID de la demande
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ID;
	/**
	 * 
	 * ID du client (peut être nul si demande d'inscription)
	 */
	@ManyToOne
	protected Client client;
	/**
	 * 
	 * Traduit l'état d'une demande (en cours/refusée/acceptée)
	 */

	private String etat;

	/**
	 * 1 : Creation, 2 : Modification, 3 : Chequier
	 */	
	private int type;
	/**
	 * 
	 * Traduit la date de creatin de la demande
	 */
	@Temporal(TemporalType.DATE)
	private Date dateCreation;

	// #endregion

	// #region Accesseurs
	public long getID() {
		return this.ID;
	}

	public void setID(long iD) {
		this.ID = iD;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getEtat() {
		return this.etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	// #endregion



	// #region constructeur
	/**
	 * @param iD
	 * @param clientID
	 * @param etat
	 */
	public Demande() {
		super();
		this.etat = EtatDemande.Cree.toString();
		this.dateCreation = new Date();
	}
	// #endregion

	public boolean isEmpty() {
		if (this.getID() != 0) {
			return false;
		}
		return true;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (ID ^ (ID >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Demande other = (Demande) obj;
		if (ID != other.ID)
			return false;
		return true;
	}

	
	
}
