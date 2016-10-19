package com.wha.springmvc.service;

import java.util.List;

import com.wha.springmvc.model.Dem_Chequier;
import com.wha.springmvc.model.Dem_CreationClient;
import com.wha.springmvc.model.Dem_ModificationCompte;
import com.wha.springmvc.model.Demande;

public interface DemandeService {

	public Dem_CreationClient createDemandeInscription(Dem_CreationClient demandecreationclient);
	
	public void addDemandeChequierToCons(long id_conseiller, Dem_Chequier demandechequier);
	
	public void addDemandeModificationCompteToCons(long id_conseiller, Dem_ModificationCompte demandeModificationCompte);

	public void modifEtat_Demande(long demande_id, String nouvelEtat);
	

	
	/**
	 * Retourne toutes les demandes de modifications de compte attribuées à un conseiller
	 * @param id id du conseiller
	 * @return
	 */
	public List<Dem_ModificationCompte> findAllDemandesModifCompte(long id);
	
	/**
	 * Renvoie les demandes de creation liées à l'ADMIN
	 * @return
	 */
	public List<Dem_CreationClient> findAllDemandesCreationClient();
	
	/**
	 * Renvoie les demandes de creation liées à un CONSEILLER
	 * @param consID
	 * @return
	 */
	public List<Dem_CreationClient> findAllDemandesCreationClient(long consID);
	
	/**
	 * Renvoie la liste de demandes de chéquier attribuées à un CONSEILLER
	 * @param consID
	 * @return
	 */
	public List<Dem_Chequier> listAllDemandeChequier(long consID);
	
	public void attribution(long id_demande,long id_conseiller);
	
	public Demande findDemandeById(long id_demande);


	
	public void suppressionDemande(long id_demande);

	public void reaffectation(long client_id, long conseiller_id);

}
