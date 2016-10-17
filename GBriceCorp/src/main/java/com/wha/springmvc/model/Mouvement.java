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
@Table(name = "Mouvement")
public abstract class Mouvement {

	//#region Attributs
	/**
	 * ID d'un mouvement
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long ID;
	/**
	 * montant du mouvement (toujours positif)
	 */
	private float montant;
	/**
	 * date d'impact du mouvement sur le compte
	 */
	@Temporal(TemporalType.DATE)
	private Date dateMouvement;

	
	/**
	 * libellé descriptif associé au mouvement
	 */
	private String libelle;
	//#region
	//#region Accesseurs
	public long getID() {
		return this.ID;
	}

	public void setID(long iD) {
		this.ID = iD;
	}

	public float getMontant() {
		return this.montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Date getDateMouvement() {
		return this.dateMouvement;
	}

	public void setDateMouvement(Date dateMouvement) {
		this.dateMouvement = dateMouvement;
	}
	// #endregion
	
	//#region Constructeurs 

	public Mouvement() {
		this.dateMouvement = new Date();
	}

	@Override
	public String toString() {
		return "Mouvement [getID()=" + getID() + ", getMontant()=" + getMontant() + ", getLibelle()=" + getLibelle()
				+ ", getDateMouvement()=" + getDateMouvement() + "]";
	}




	//#endregion
	
	
	
}
