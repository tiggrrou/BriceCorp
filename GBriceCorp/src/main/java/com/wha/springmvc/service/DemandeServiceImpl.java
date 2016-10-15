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

	
	public List<Dem_ModificationCompte> findAllDemandesModifCompte() {
		// TODO Auto-generated method stub
		return dao.findAllDemandesModifCompte();
	}

	@Override
	public List<Dem_CreationClient> findAllDemandesCreationClient(long id) {
		// TODO Auto-generated method stub

		List<Dem_CreationClient> listedemandecreationpartype = new ArrayList<Dem_CreationClient>();
		
//		if(id == 0){
//		for (Dem_CreationClient demandecreationcli : dao.findAllDemandesCreationClient()) {
//			if (demandecreationcli.getClientID() == id && demandecreationcli.getConseillerId() == 0) {
//				listedemandecreationpartype.add(demandecreationcli);
//
//			}
//		}
//		}else{
//
//			for (Dem_CreationClient demandecreationcli : dao.findAllDemandesCreationClient()) {
//				if (demandecreationcli.getConseillerId() == id) {
//					listedemandecreationpartype.add(demandecreationcli);
//				}
//			}
//		}

		return listedemandecreationpartype;
	}

	@Override
	public List<Dem_Chequier> listAllDemandeChequier() {
		// TODO Auto-generated method stub
		return dao.listAllDemandeChequier();		
	}
	
	
	@Override
	public void attribution(long id_demande,long id_conseiller){
		System.out.println("Attribution du conseiller" +id_conseiller + " a la demande " + id_demande);
		dao.attribution(id_demande,id_conseiller);
	}



	@Override
	public void createDemandeInscription(Dem_CreationClient demandecreationclient) {

		dao.createDemandeInscription(demandecreationclient);	
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
	public void createDemandeChequier(Dem_Chequier demandechequier) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createDemandeModificationCompte(Dem_ModificationCompte demandeModificationCompte) {
		// TODO Auto-generated method stub
		
	}



	




	
	
	
}
