/**
 * 
 */
package com.wha.springmvc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Nicolas Lourdeau
 *
 */

@Entity
@Table(name = "Justificatif")
public class Justificatif implements Serializable{
	
	
	//#region Attributs
	
	@Id
	private long ID;
	
	@Temporal(TemporalType.DATE)
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
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public TypeJustificatif getType() {
		return this.type;
	}

	public void setType(TypeJustificatif type) {
		this.type = type;
	}
	//#endregion



	/**
	 * @param clientID
	 * @param date
	 */
	public Justificatif(long clientID, TypeJustificatif type) {
		this.date = new Date();
		this.type = type;
	}
	
	
}
