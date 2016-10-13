package com.wha.springmvc.service;

import java.util.List;

import com.wha.springmvc.model.Dem_Chequier;
import com.wha.springmvc.model.Dem_CreationClient;
import com.wha.springmvc.model.Dem_ModificationCompte;

public interface DemandeService {

	public void createDemandeInscription(Dem_CreationClient demandecreationclient);
	
	
	public List<Dem_ModificationCompte> findAllDemandesModifCompte();
	
	public List<Dem_CreationClient> findAllDemandesCreationClient(long id);
	
	public List<Dem_Chequier> listAllDemandeChequier();
	
	public boolean attribution(long id_demande,long id_conseiller);
	
	
}
