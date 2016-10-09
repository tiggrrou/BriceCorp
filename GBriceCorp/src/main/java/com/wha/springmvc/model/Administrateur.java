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

	public Administrateur(long id, String nom, String prenom, String address, String email, String login, String mdp, int telephone) {
		super(id, nom, prenom, address, email, login, mdp, TypeUtilisateur.Administrateur.getType(), telephone);
		// TODO Auto-generated constructor stub
	}

}
