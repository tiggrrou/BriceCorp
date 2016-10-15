/**
 * 
 */
package com.wha.springmvc.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ManyToAny;

/**

 * @author Nicolas Lourdeau
 *
 */
@Entity
@Table(name = "Compte")
@Inheritance(strategy = InheritanceType.JOINED)
public class Compte {

	//#region Attributs
	/**
	 * ID d'un compte (correspond à l'IBAN)
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long ID;
	/**
	 * Libellé de description du compte
	 */
	private String libelle;
	/**
	 * Date d'ouverture du compte
	 */
	@Temporal(TemporalType.DATE)
	private Date dateOuverture;
	/**
	 * Date de cloture du compte
	 */
	@Temporal(TemporalType.DATE)
	private Date dateCloture;

	@OneToMany(cascade={CascadeType.ALL})
	private List<Mouvement> mouvements;
	
	/**
	 * Booléen traduisant l'activité sur le compte
	 */
	private boolean actif;
	/**
	 * Solde courant du compte
	 */
	private float solde;
	/**
	 * Solde de l'agio en cours
	 */
	private float soldeAgio;
	/**
	 * Montant du découvert autorisé, peut être égale à zéro
	 */
	private int decouvert;
	/**
	 * Taux du découvert pour calcul de l'agio
	 */
	private float tauxDecouvert;
	//#endregion
	
	//#region accesseurs
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Date getDateOuverture() {
		return dateOuverture;
	}
	public void setDateOuverture(Date dateOuverture) {
		this.dateOuverture = dateOuverture;
	}
	public Date getDateCloture() {
		return dateCloture;
	}
	public void setDateCloture(Date dateCloture) {
		this.dateCloture = dateCloture;
	}
	public boolean isActif() {
		return actif;
	}
	public void setActif(boolean actif) {
		this.actif = actif;
	}
	public float getSolde() {
		return solde;
	}
	public void setSolde(float solde) {
		this.solde = solde;
	}
	public float getSoldeAgio() {
		return soldeAgio;
	}
	public void setSoldeAgio(float soldeAgio) {
		this.soldeAgio = soldeAgio;
	}
	public int getDecouvert() {
		return decouvert;
	}
	public void setDecouvert(int decouvert) {
		this.decouvert = decouvert;
	}
	public float getTauxDecouvert() {
		return tauxDecouvert;
	}
	public void setTauxDecouvert(float tauxDecouvert) {
		this.tauxDecouvert = tauxDecouvert;
	}
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	//#endregion
	
	//#region Constructeurs
	public Compte(){
		this.dateOuverture = new Date();
		this.actif = true;
		this.solde = 0;
		this.soldeAgio = 0;
		this.decouvert = 0;
		this.tauxDecouvert = 0;
		
	}


	//#endregion
	
	
	
}
