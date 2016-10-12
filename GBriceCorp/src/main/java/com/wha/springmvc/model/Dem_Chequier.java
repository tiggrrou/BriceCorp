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
	private String idCompte;
	//#endregion
	//#region Accesseurs

	public String getIdCompte() {
		return idCompte;
	}

	public void setIdCompte(String idCompte) {
		this.idCompte = idCompte;
	}

	//#endregion
	//#region Constructeurs
	public Dem_Chequier(long clientID, String idCompte) {
		super(clientID);
		
		this.idCompte = idCompte;
	}
	//#endregion 

}
