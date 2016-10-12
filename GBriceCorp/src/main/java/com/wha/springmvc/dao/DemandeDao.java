package com.wha.springmvc.dao;

import java.util.List;

import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Dem_Chequier;
import com.wha.springmvc.model.Dem_CreationClient;
import com.wha.springmvc.model.Dem_ModificationCompte;

public interface DemandeDao {

	public void createDemandeInscription(Client client);
		
	public List<Dem_ModificationCompte> findAllDemandesModifCompte();
	
	public List<Dem_CreationClient> findAllDemandesCreationClient(long id);
	
	public List<Dem_Chequier> listAllDemandeChequier();
	
	public boolean attribution(long id_demande,long id_conseiller);
}
