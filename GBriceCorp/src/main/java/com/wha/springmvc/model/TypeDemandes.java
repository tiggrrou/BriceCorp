package com.wha.springmvc.model;

public enum TypeDemandes {
	Creation (1),
	Modification (2),
	Chequier (3);
	
	
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
