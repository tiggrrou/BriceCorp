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

	private int matricule;
	
	
	
	public int getMatricule() {
		return matricule;
	}

	public void setMatricule(int matricule) {
		this.matricule = matricule;
	}

	public Administrateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Administrateur [getMatricule()=" + getMatricule() + "]";
	}



}
