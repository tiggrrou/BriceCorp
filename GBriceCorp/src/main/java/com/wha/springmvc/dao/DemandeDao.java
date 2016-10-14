package com.wha.springmvc.dao;

import java.util.List;

import com.wha.springmvc.model.Dem_Chequier;
import com.wha.springmvc.model.Dem_CreationClient;
import com.wha.springmvc.model.Dem_ModificationCompte;
import com.wha.springmvc.model.Demande;

public interface DemandeDao {

	public void createDemandeInscription(Dem_CreationClient demandecreationclient);
	
	public void createDemandeChequier(Dem_Chequier demandechequier);
	
	public void createDemandeModificationCompte(Dem_ModificationCompte demandeModificationCompte);

	
	
		
	public List<Dem_ModificationCompte> findAllDemandesModifCompte();
	
	public List<Dem_CreationClient> findAllDemandesCreationClient();
	
	public List<Dem_Chequier> listAllDemandeChequier();
	
	public boolean attribution(long id_demande,long id_conseiller);
	
	public Demande findDemandeById(long id_demande);
	
	public void suppressionDemande(long id_demande);
}
