/**
 * 
 */
package com.wha.springmvc.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Nicolas Lourdeau
 *
 */
@Entity
@Table(name = "Conseiller")
@PrimaryKeyJoinColumn(name = "id")
public class Conseiller extends User {

	//#region Attributs
	/**
	 * Matricule unique permettant l'identification d'un conseiller
	 */
	private int matricule;
	
	/**
	 * Date de début de contrat
	 */
	@Temporal(TemporalType.DATE)
	private Date debutContrat;
	
	/**
	 * Date de fin de contrat
	 */
	@Temporal(TemporalType.DATE)
	private Date finContrat;
	
	@OneToMany
	private List<Client> clients;
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
		this.debutContrat = new Date();
	}



	@Override
	public String toString() {
		return "Conseiller [getMatricule()=" + getMatricule() + ", getDebutContrat()=" + getDebutContrat()
				+ ", getFinContrat()=" + getFinContrat() + "]";
	}
	
	//#endregion
	
	
	
}
