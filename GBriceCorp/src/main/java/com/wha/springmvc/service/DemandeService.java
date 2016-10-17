package com.wha.springmvc.service;

import java.util.List;

import com.wha.springmvc.model.Dem_Chequier;
import com.wha.springmvc.model.Dem_CreationClient;
import com.wha.springmvc.model.Dem_ModificationCompte;
import com.wha.springmvc.model.Demande;

public interface DemandeService {

	public void createDemandeInscription(Dem_CreationClient demandecreationclient);
	
	public void addDemandeChequierToCons(long id_conseiller, Dem_Chequier demandechequier);
	
	public void addDemandeModificationCompteToCons(long id_conseiller, Dem_ModificationCompte demandeModificationCompte);

	
	
	
	public List<Dem_ModificationCompte> findAllDemandesModifCompte();
	
	public List<Dem_CreationClient> findAllDemandesCreationClient(long id);
	
	public List<Dem_Chequier> listAllDemandeChequier();
	
	public void attribution(long id_demande,long id_conseiller);
	
	public Demande findDemandeById(long id_demande);


	
	public void suppressionDemande(long id_demande);

}
