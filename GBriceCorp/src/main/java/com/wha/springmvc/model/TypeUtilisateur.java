/**
 * 
 */
package com.wha.springmvc.model;

/**
 * @author Nicolas Lourdeau
 *
 */
public enum TypeUtilisateur {

	Administrateur (1),
	Conseiller (2),
	Client (3);
	
	private int type;

	/**
	 * @param type
	 */
	private TypeUtilisateur(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
}
