/**
 * 
 */
package com.wha.springmvc.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author Nicolas Lourdeau
 *
 */
@Entity
@Table(name = "Administrateur")
@PrimaryKeyJoinColumn(name = "id")
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
