package com.wha.springmvc.service;

import java.util.List;

import com.wha.springmvc.model.Dem_Chequier;
import com.wha.springmvc.model.Dem_CreationClient;
import com.wha.springmvc.model.Dem_ModificationCompte;
import com.wha.springmvc.model.Dem_ModificationInfo;
import com.wha.springmvc.model.Demande;

public interface DemandeService {

	public void modifEtat_Demande(long demande_id, String nouvelEtat);
	
	public void attribution(long id_demande,long id_conseiller);

	public Demande findDemandeById(long id_demande);
	
	public void suppressionDemande(long id_demande,long id_conseiller);

	public void reaffectation(long client_id, long conseiller_id);	
	

	
	
	
	
	
	/**
	 * 
	 * Demande de Creation
	 * 
	 */
	public Dem_CreationClient createDemandeInscription(Dem_CreationClient demandecreationclient);
	/**
	 * Renvoie toutes les demandes de creation pour l'ADMIN
	 * @return
	 */
	public List<Dem_CreationClient> findAllDemandesCreationClient();
	/**
	 * Renvoie toutes les demandes de creation pour le CONSEILLER
	 * @param id
	 * @return
	 */
	public List<Dem_CreationClient> findAllDemandesCreationClient(long id);

	
	
	
	
	
	
	/**
	 * 
	 * Demande de Chequier
	 * 
	 */
	public void addDemandeChequierToCons(long id_conseiller, Dem_Chequier demandechequier);
	/**
	 * Renvoie les demandes de chéquier liées à un CONSEILLER
	 * @param consID
	 * @return
	 */
	public List<Dem_Chequier> listAllDemandeChequier(long consID);	

	
	
	
	
	
	/**
	 * 
	 * Demande de Modification de compte
	 * 
	 */
	public void addDemandeModificationCompteToCons(long id_conseiller, Dem_ModificationCompte demandeModificationCompte);
	/**
	 * Renvoie toutes les demandes de modification de compte attribuées à un conseiller
	 * @param id id du conseiller
	 * @return
	 */
	public List<Dem_ModificationCompte> findAllDemandesModifCompte(long id);

	
	
	
	
	
	
	
	/**
	 * 
	 * Demande de modification d'info perso
	 * 
	 */
	public void addDemandeModificationInfoPersoToCons(long id_conseiller, Dem_ModificationInfo demandeModificationInfoPerso);	
	/**
	 * Renvoie toutes les demandes de modification d'info perso attribuées à un conseiller
	 * @param id
	 * @return
	 */
	public List<Dem_ModificationInfo> findAllDemandesModifInfo(long id);


}
