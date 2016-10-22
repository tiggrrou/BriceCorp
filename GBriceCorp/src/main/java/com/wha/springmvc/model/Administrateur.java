/**
 * 
 */
package com.wha.springmvc.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
public class Administrateur extends User implements Serializable{

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Conseiller> conseillers;

	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private Set<Dem_CreationClient> demandeCreationClient;	
	
	public Set<Conseiller> getConseillers() {
		return conseillers;
	}

	public void setConseillers(Set<Conseiller> conseillers) {
		this.conseillers = conseillers;
	}

	public Set<Dem_CreationClient> getDemandeCreationClient() {
		return demandeCreationClient;
	}

	public void setDemandeCreationClient(Set<Dem_CreationClient> demandeCreationClient) {
		this.demandeCreationClient = demandeCreationClient;
	}

	public Administrateur() {
		super();
		this.demandeCreationClient = new HashSet<Dem_CreationClient>();
		this.conseillers = new HashSet<Conseiller>();		
	}

	@Override
	public String toString() {
		return "Administrateur [getConseillers()=" + getConseillers() +"]";
	}





}
