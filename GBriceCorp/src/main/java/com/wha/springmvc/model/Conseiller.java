/**
 * 
 */
package com.wha.springmvc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Conseiller extends User implements Serializable{

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
	
	@OneToMany(mappedBy="conseiller", fetch=FetchType.EAGER)
//	 @JsonSerialize(using = CustomListSerializer.class)
	private List<Client> clients;
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<Demande> demandes;
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
	
	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}	
	
	public List<Demande> getDemandes() {
		return demandes;
	}

	public void setDemandes(List<Demande> demandes) {
		this.demandes = demandes;
	}	
	//#endregion 





	//#region Constructeurs
	public Conseiller() {
		super();
		this.debutContrat = new Date();
	}

	@Override
	public String toString() {
		return "Conseiller [getMatricule()=" + getMatricule()
				+ ", getDebutContrat()=" + getDebutContrat() + ", getFinContrat()=" + getFinContrat()
				+ ", getClients()=" + getClients() + ", getDemandes()=" + getDemandes() + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((clients == null) ? 0 : clients.hashCode());
		result = prime * result + ((debutContrat == null) ? 0 : debutContrat.hashCode());
		result = prime * result + ((demandes == null) ? 0 : demandes.hashCode());
		result = prime * result + ((finContrat == null) ? 0 : finContrat.hashCode());
		result = prime * result + matricule;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conseiller other = (Conseiller) obj;
		if (clients == null) {
			if (other.clients != null)
				return false;
		} else if (!clients.equals(other.clients))
			return false;
		if (debutContrat == null) {
			if (other.debutContrat != null)
				return false;
		} else if (!debutContrat.equals(other.debutContrat))
			return false;
		if (demandes == null) {
			if (other.demandes != null)
				return false;
		} else if (!demandes.equals(other.demandes))
			return false;
		if (finContrat == null) {
			if (other.finContrat != null)
				return false;
		} else if (!finContrat.equals(other.finContrat))
			return false;
		if (matricule != other.matricule)
			return false;
		return true;
	}



	
	//#endregion
	
	
	
}
