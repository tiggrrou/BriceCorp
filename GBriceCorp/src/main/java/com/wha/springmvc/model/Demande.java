/**
 * 
 */
package com.wha.springmvc.model;

/**
 * @author Nicolas Lourdeau
 *
 */
public abstract class Demande {

	//#region Attributs
	private static int nbDemandes = 0;
	/**
	 * ID de la demande
	 */
	private long ID;
	/**
	 * ID du client (peut être nul si demande d'inscription)
	 */
	private long clientID;
	/**
	 * Traduit l'état d'une demande (en cours/refusée/acceptée)
	 */
	private String etat;
	//#endregion 
	
	//#region Accesseurs
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
	//#endregion
	
	//#region constructeur
	/**
	 * @param iD
	 * @param clientID
	 * @param etat
	 */
	public Demande(long clientID) {
		super();
		nbDemandes++;
		this.ID = nbDemandes;
		this.clientID = clientID;
		this.etat = EtatDemande.EnCours.toString();
	}
	//#endregion
	
	
}
