/**
 * 
 */
package com.wha.springmvc.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author Nicolas Lourdeau
 *
 */
@Entity
@Table(name = "Administrateur")
@PrimaryKeyJoinColumn(name = "id")
public class Administrateur extends User {

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Conseiller> conseillers;

	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<Dem_CreationClient> demandeCreationClient;	
	
	public List<Conseiller> getConseillers() {
		return conseillers;
	}

	public void setConseillers(List<Conseiller> conseillers) {
		this.conseillers = conseillers;
	}

	public List<Dem_CreationClient> getDemandeCreationClient() {
		return demandeCreationClient;
	}

	public void setDemandeCreationClient(List<Dem_CreationClient> demandeCreationClient) {
		this.demandeCreationClient = demandeCreationClient;
	}

	public Administrateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Administrateur [getConseillers()=" + getConseillers() +"]";
	}





}
