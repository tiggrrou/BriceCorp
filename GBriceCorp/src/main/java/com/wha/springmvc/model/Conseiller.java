/**
 * 
 */
package com.wha.springmvc.model;

import java.util.Date;
import java.util.List;

/**
 * @author Nicolas Lourdeau
 *
 */
public class Conseiller extends User {

	//#region Attributs
	/**
	 * Matricule unique permettant l'identification d'un conseiller
	 */
	private int matricule;
	
	/**
	 * Date de début de contrat
	 */
	private Date debutContrat;
	
	/**
	 * Date de fin de contrat
	 */
	private Date finContrat;
	
	private List<Client> clients;
	//#endregion

	//#region Accesseurs
	
	public int getMatricule() {
		return this.matricule;
	}

	public void setMatricule(int matricule) {
		this.matricule = matricule;
	}

	public Date getDebutContrat() {
		return this.debutContrat;
	}

	public void setDebutContrat(Date debutContrat) {
		this.debutContrat = debutContrat;
	}

	public Date getFinContrat() {
		return this.finContrat;
	}

	public void setFinContrat(Date finContrat) {
		this.finContrat = finContrat;
	}	
	//#endregion 

	//#region Constructeurs
	public Conseiller() {
		super();
	}

	public Conseiller(long id, String nom, String prenom, String address, String email, String login, String mdp, int telephone, int matricule, Date debutContrat, Date finContrat) {
		super(id, nom, prenom, address, email, login, mdp, TypeUtilisateur.Conseiller.getType(),telephone);
		this.matricule = matricule;
		this.debutContrat = debutContrat;
		this.finContrat = finContrat;
	}
	
	public Conseiller(User user, int mat, Date debut, Date fin){
		super(user.getId(), user.getNom(), user.getPrenom(), 
				user.getAdresse(), user.getMail(), user.getIdentifiant(), 
				user.getMotDePasse(), TypeUtilisateur.Conseiller.getType(),user.getTelephone());
		this.matricule = mat;
		this.debutContrat = debut;
		this.finContrat = fin;
		
	}
	
	//#endregion
}
