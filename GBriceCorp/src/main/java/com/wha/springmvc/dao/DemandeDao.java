package com.wha.springmvc.dao;

import java.util.List;

import com.wha.springmvc.model.Dem_Chequier;
import com.wha.springmvc.model.Dem_CreationClient;
import com.wha.springmvc.model.Dem_ModificationCompte;
import com.wha.springmvc.model.Demande;

public interface DemandeDao {

	public Dem_CreationClient createDemandeInscription(Dem_CreationClient demandecreationclient);
	
	public void addDemandeChequierToCons(long id_conseiller, Dem_Chequier demandechequier);
	
	public void addDemandeModificationCompteToCons(long id_conseiller, Dem_ModificationCompte demandeModificationCompte);


	public void modifEtat_Demande(long demande_id, String nouvelEtat);
	
	public void attribution(long id_demande,long id_conseiller);
	
	/**
	 * Renvoie toutes les demandes de modification de compte attribuées à un conseiller
	 * @param id id du conseiller
	 * @return
	 */
	public List<Dem_ModificationCompte> findAllDemandesModifCompte(long id);
	
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
	 * Renvoie les demandes de chéquier liées à un CONSEILLER
	 * @param consID
	 * @return
	 */
	public List<Dem_Chequier> listAllDemandeChequier(long consID);
	
	public Demande findDemandeById(long id_demande);
	
	public void suppressionDemande(long id_demande);
}
