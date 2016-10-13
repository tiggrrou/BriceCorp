/**
 * 
 */
package com.wha.springmvc.model;

import java.util.Date;

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
@Table(name = "Notification")
public class Notification {
	// #region Attributs
	/**
	 * ID de la notification
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long ID;
	/**
	 * ID du client à qui est adressée la notification
	 */
	private long ClientID;
	/**
	 * Booléen traduisant l'état de la notification lu ou non lu
	 */
	private boolean lu;
	/**
	 * Date d'émission de la notification
	 */
	@Temporal(TemporalType.DATE)
	private Date dateNotif;
	/**
	 * Message accompagnant la notification
	 */
	private String Message;
	// #endregion

	// #region Accesseurs
	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		this.ID = iD;
	}

	public long getClientID() {
		return ClientID;
	}

	public void setClientID(long clientID) {
		this.ClientID = clientID;
	}

	public boolean isLu() {
		return this.lu;
	}

	public void setLu(boolean lu) {
		this.lu = lu;
	}

	public Date getDateNotif() {
		return this.dateNotif;
	}

	public void setDateNotif(Date dateNotif) {
		this.dateNotif = dateNotif;
	}

	public String getMessage() {
		return this.Message;
	}

	public void setMessage(String message) {
		this.Message = message;
	}
	// #endregion

	// #region Constructeurs
	/**
	 * @param iD
	 * @param clientID
	 * @param message
	 */
	public Notification(long iD, long clientID, String message) {
		this.ID = iD;
		this.ClientID = clientID;
		this.Message = message;
		this.dateNotif = new Date();
		this.lu = false;

	}

	public Notification() {

	}
	// #endregion

	// #region Méthodes héritées
	// #endregion

	// #region Méthodes
	// #endregion
}
