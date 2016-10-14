package com.wha.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "Dem_Chequier")
@PrimaryKeyJoinColumn(name = "id")
public class Dem_Chequier extends Demande {



	//#region Attributs
	@Column(name = "idCompte")
	private int idCompte;
	//#endregion
	//#region Accesseurs

	public int getIdCompte() {
		return idCompte;
	}

	public void setIdCompte(int i) {
		this.idCompte = i;
	}

	//#endregion
	//#region Constructeurs
	
	
	public Dem_Chequier() {
		super();

	}
	//#endregion 

}
