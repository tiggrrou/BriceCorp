package com.wha.springmvc.model;

public enum TypeDemandes {
	Creation (1),
	ModificationCompte (2),
	Chequier (3),
	ModificationInformationPerso (4);
	
	
	private int type;

	/**
	 * @param type
	 */
	private TypeDemandes(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
}
