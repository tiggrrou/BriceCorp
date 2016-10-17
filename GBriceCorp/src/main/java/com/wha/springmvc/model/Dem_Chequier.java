package com.wha.springmvc.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "Dem_Chequier")
@PrimaryKeyJoinColumn(name = "id")
public class Dem_Chequier extends Demande {



	//#region Attributs
	
	
	@ManyToOne
	private Compte compte;
	
	
	//#endregion
	
	
	
	
	//#region Accesseurs
	public Compte getCompte() {
		return compte;
	}



	public void setCompte(Compte compte) {
		this.compte = compte;
	}



	//#endregion
	//#region Constructeurs
	
	
	public Dem_Chequier() {
		super();

	}
	//#endregion 



	@Override
	public String toString() {
		return "Dem_Chequier [getCompte()=" + getCompte() + "]";
	}




}
