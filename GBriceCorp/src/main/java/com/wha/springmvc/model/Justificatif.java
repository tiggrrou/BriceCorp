/**
 * 
 */
package com.wha.springmvc.model;

import java.util.Date;

/**
 * @author Nicolas Lourdeau
 *
 */
public class Justificatif {
//#region Attributs
	private long ID;
	
	private long clientID;
	
	private Date date;
	
	private TypeJustificatif type;
	//#endregion
	//#region Accesseurs
	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public long getClientID() {
		return clientID;
	}

	public void setClientID(long clientID) {
		this.clientID = clientID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	//#endregion

	public TypeJustificatif getType() {
		return this.type;
	}

	public void setType(TypeJustificatif type) {
		this.type = type;
	}

	/**
	 * @param clientID
	 * @param date
	 */
	public Justificatif(long clientID, Date date, TypeJustificatif type) {
		this.clientID = clientID;
		this.date = date;
		this.type = type;
	}
	
	
}
