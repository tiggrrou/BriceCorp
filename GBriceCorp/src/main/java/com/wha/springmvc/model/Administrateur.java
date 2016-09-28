/**
 * 
 */
package com.wha.springmvc.model;

/**
 * @author Nicolas Lourdeau
 *
 */
public class Administrateur extends User {

	public Administrateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Administrateur(long id, String username, String address, String email, String login, String mdp) {
		super(id, username, address, email, login, mdp, TypeUtilisateur.Administrateur.getType());
		// TODO Auto-generated constructor stub
	}

}
