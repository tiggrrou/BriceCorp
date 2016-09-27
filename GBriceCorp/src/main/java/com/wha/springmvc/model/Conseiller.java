/**
 * 
 */
package com.wha.springmvc.model;

import java.util.Date;

/**
 * @author Nicolas Lourdeau
 *
 */
public class Conseiller extends User {

	//#region Attributs
	/**
	 * Matricule unique permettant l'identification d'un conseiller
	 */
	private int matricule;
	
	/**
	 * Date de début de contrat
	 */
	private Date debutContrat;
	
	/**
	 * Date de fin de contrat
	 */
	private Date finContrat;
	//#endregion

	//#region Accesseurs
	
	public int getMatricule() {
		return this.matricule;
	}

	public void setMatricule(int matricule) {
		this.matricule = matricule;
	}

	public Date getDebutContrat() {
		return this.debutContrat;
	}

	public void setDebutContrat(Date debutContrat) {
		this.debutContrat = debutContrat;
	}

	public Date getFinContrat() {
		return this.finContrat;
	}

	public void setFinContrat(Date finContrat) {
		this.finContrat = finContrat;
	}	
	//#endregion 

	//#region Constructeurs
	public Conseiller() {
		super();
	}

	public Conseiller(long id, String username, String address, String email, String login, String mdp, int matricule, Date debutContrat, Date finContrat) {
		super(id, username, address, email, login, mdp);
		this.matricule = matricule;
		this.debutContrat = debutContrat;
		this.finContrat = finContrat;
	}
	
	//#endregion
}
