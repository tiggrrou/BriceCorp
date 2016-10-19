package com.wha.springmvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wha.springmvc.dao.DemandeDao;
import com.wha.springmvc.model.Dem_Chequier;
import com.wha.springmvc.model.Dem_CreationClient;
import com.wha.springmvc.model.Dem_ModificationCompte;
import com.wha.springmvc.model.Demande;


@Service("demandeService")
@Transactional
public class DemandeServiceImpl implements DemandeService {


	
	@Autowired
	private DemandeDao dao;

	/**
	 * Retourne toutes les demandes de modifications de compte attribuées à un conseiller
	 * @param id
	 * @return
	 */
	public List<Dem_ModificationCompte> findAllDemandesModifCompte(long id) {
		// TODO Auto-generated method stub
		return dao.findAllDemandesModifCompte(id);
	}

	@Override
	/**
	 * Récupère les demandes de creation liées à l'ADMIN
	 */
	public List<Dem_CreationClient> findAllDemandesCreationClient() {
		
		return dao.findAllDemandesCreationClient();
	}

	@Override
	/**
	 * Récupère les demandes de creation liées à un CONSEILLER
	 */
	public List<Dem_CreationClient> findAllDemandesCreationClient(long consID) {
		
		return dao.findAllDemandesCreationClient(consID);
	}
	
	@Override
	/**
	 * Récupère les demandes de chequier liées à un CONSEILLER
	 */
	public List<Dem_Chequier> listAllDemandeChequier(long consID) {
		// TODO Auto-generated method stub
		return dao.listAllDemandeChequier(consID);		
	}
	
	
	@Override
	public void attribution(long id_demande,long id_conseiller){
		System.out.println("Attribution du conseiller" +id_conseiller + " a la demande " + id_demande);
		dao.attribution(id_demande,id_conseiller);
	}



	@Override
	public Dem_CreationClient createDemandeInscription(Dem_CreationClient demandecreationclient) {

		return dao.createDemandeInscription(demandecreationclient);	
	}

	@Override
	public Demande findDemandeById(long id_demande) {
		return dao.findDemandeById(id_demande);

	}

	@Override
	public void suppressionDemande(long id_demande) {
			dao.suppressionDemande(id_demande);
		
	}

	@Override
	public void addDemandeChequierToCons(long id_conseiller, Dem_Chequier demandechequier){
dao.addDemandeChequierToCons(id_conseiller, demandechequier);
		
	}

	@Override
	public void addDemandeModificationCompteToCons(long id_conseiller, Dem_ModificationCompte demandeModificationCompte){
dao.addDemandeModificationCompteToCons(id_conseiller, demandeModificationCompte);
		
	}

	@Override
	public void modifEtat_Demande(long demande_id, String nouvelEtat){
dao.modifEtat_Demande(demande_id, nouvelEtat);
	}

	@Override
	public void reaffectation(long client_id, long conseiller_id) {
		dao.reaffectation(client_id, conseiller_id);
		
	}



	




	
	
	
}
