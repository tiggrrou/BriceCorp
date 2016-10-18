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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long ID;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private String url;
	
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
	
	
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
	public Justificatif() {
		this.date = new Date();
	}
	
	
}
