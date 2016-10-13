/**
 * 
 */
package com.wha.springmvc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
	private static int nbDemandes = 0;
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
	@Column(name = "clientID")
	protected long clientID;
	/**
	 * 
	 * Traduit l'état d'une demande (en cours/refusée/acceptée)
	 */

	private String etat;

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

	public long getClientID() {
		return this.clientID;
	}

	public void setClientID(long clientID) {
		this.clientID = clientID;
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
	// #endregion

	// #region constructeur
	/**
	 * @param iD
	 * @param clientID
	 * @param etat
	 */
	public Demande(long clientID) {
		super();

		this.ID = ID;
		this.clientID = clientID;
		this.etat = EtatDemande.EnCours.toString();
		this.dateCreation = new Date();
	}
	// #endregion

	public boolean isEmpty() {
		if (this.getID() != 0) {
			return false;
		}
		return true;

	}

}
