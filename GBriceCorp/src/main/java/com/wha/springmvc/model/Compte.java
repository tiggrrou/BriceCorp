/**
 * 
 */
package com.wha.springmvc.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
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
	/**
	 * Seuil avant renumeration
	 */
	private int seuil;
	/**
	 * Taux de rémunération 
	 */
	private float tauxRemuneration;
	/**
	 * Solde de rémunération pour le mois en cours
	 */
	private float soldeRemuneration;
	/**
	 * IBAN du compte
	 */
	private String IBAN;
	
	
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
	public List<Mouvement> getMouvements() {
		return mouvements;
	}
	public void setMouvements(List<Mouvement> mouvements) {
		this.mouvements = mouvements;
	}
	public int getSeuil() {
		return seuil;
	}
	public void setSeuil(int seuil) {
		this.seuil = seuil;
	}
	public float getTauxRemuneration() {
		return tauxRemuneration;
	}
	public void setTauxRemuneration(float tauxRemuneration) {
		this.tauxRemuneration = tauxRemuneration;
	}
	public float getSoldeRemuneration() {
		return soldeRemuneration;
	}
	public void setSoldeRemuneration(float soldeRemuneration) {
		this.soldeRemuneration = soldeRemuneration;
	}
	public String getIBAN() {
		return IBAN;
	}
	public void setIBAN(String newIBAN) {
		this.IBAN = newIBAN;	
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
		this.mouvements = new ArrayList<>();
		
	}
	
	
	@Override
	public String toString() {
		return "Compte [getLibelle()=" + getLibelle() + ", getDateOuverture()=" + getDateOuverture()
				+ ", getDateCloture()=" + getDateCloture() + ", isActif()=" + isActif() + ", getSolde()=" + getSolde()
				+ ", getSoldeAgio()=" + getSoldeAgio() + ", getDecouvert()=" + getDecouvert() + ", getTauxDecouvert()="
				+ getTauxDecouvert() + ", getID()=" + getID() + ", getMouvements()=" + getMouvements() + ", getSeuil()="
				+ getSeuil() + ", getTauxRemuneration()=" + getTauxRemuneration() + ", getSoldeRemuneration()="
				+ getSoldeRemuneration() + "]";
	}
	
	
	


	//#endregion
	
	
	
}
