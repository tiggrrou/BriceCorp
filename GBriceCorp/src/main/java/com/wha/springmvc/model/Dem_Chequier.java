package com.wha.springmvc.model;

public class Dem_Chequier extends Demande {



	//#region Attributs
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
