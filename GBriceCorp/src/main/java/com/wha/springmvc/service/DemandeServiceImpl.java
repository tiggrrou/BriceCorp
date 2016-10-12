package com.wha.springmvc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wha.springmvc.dao.DemandeDao;
import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Dem_Chequier;
import com.wha.springmvc.model.Dem_CreationClient;
import com.wha.springmvc.model.Dem_ModificationCompte;
import com.wha.springmvc.model.Justificatif;
import com.wha.springmvc.model.TypeJustificatif;


@Service("demandeService")
@Transactional
public class DemandeServiceImpl implements DemandeService {


//	private static List<Dem_ModificationCompte> demandesmodifcompte;	
//	private static List<Dem_CreationClient> demandescreationcli;
//	private static List<Dem_Chequier> demandeschequier;
//	static {
//		demandesmodifcompte = populateDummyDemandesModifCompte();
//		demandescreationcli = populateDummyDemandescreationcli();
//		demandeschequier = populateDummyDemandeschequier();
//	}
	
	@Autowired
	private DemandeDao dao;

	// private static final AtomicLong counter = new AtomicLong();
	//
	// private static List<User> users;
	//
	// static{
	// users= populateDummyUsers();
	// }

//	public List<User> findAllUsers() {
//		return dao.findAllUsers();
//	}
	
	
	
	public List<Dem_ModificationCompte> findAllDemandesModifCompte() {
		// TODO Auto-generated method stub
		return dao.findAllDemandesModifCompte();
	}

	@Override
	public List<Dem_CreationClient> findAllDemandesCreationClient(long id) {
		// TODO Auto-generated method stub

		List<Dem_CreationClient> listedemandecreationpartype = new ArrayList<Dem_CreationClient>();
		
		if(id == 0){
		for (Dem_CreationClient demandecreationcli : dao.findAllDemandesCreationClient()) {
			if (demandecreationcli.getClientID() == id && demandecreationcli.getConseillerId() == 0) {
				listedemandecreationpartype.add(demandecreationcli);

			}
		}
		}else{

			for (Dem_CreationClient demandecreationcli : dao.findAllDemandesCreationClient()) {
				if (demandecreationcli.getConseillerId() == id) {
					listedemandecreationpartype.add(demandecreationcli);
				}
			}
		}

		return listedemandecreationpartype;
	}

	@Override
	public List<Dem_Chequier> listAllDemandeChequier() {
		// TODO Auto-generated method stub
		return dao.listAllDemandeChequier();		
	}
	
	
	@Override
	public boolean attribution(long id_demande, long id_conseiller) {
		System.out.println("coucou" + id_demande + " "+ id_conseiller);
		for (Dem_CreationClient demandecreationcli : dao.findAllDemandesCreationClient()) {
			if (demandecreationcli.getID() == id_demande) {
				demandecreationcli.setConseillerId(id_conseiller);
				System.out.println(demandecreationcli);
				return true;
			}
		}
		return false;
	}



	@Override
	public void createDemandeInscription(Dem_CreationClient demandecreationclient) {

		dao.createDemandeInscription(demandecreationclient);	
	}



	
	

//	private static List<Dem_ModificationCompte> populateDummyDemandesModifCompte() {
//		demandesmodifcompte = new ArrayList<Dem_ModificationCompte>();
//		demandesmodifcompte.add(new Dem_ModificationCompte(02, "1", 0, true));
//		demandesmodifcompte.add(new Dem_ModificationCompte(04, "5", 100, false));
//	return demandesmodifcompte;
//	}

//	private static List<Dem_CreationClient> populateDummyDemandescreationcli() {
//		demandescreationcli = new ArrayList<Dem_CreationClient>();
//		demandescreationcli.add(new Dem_CreationClient(0, 1, "MOREL", "brice", "brice@fezf.com", "25 e dez de z", 676762393,
//			new Justificatif(00, new Date(), TypeJustificatif.Domicile), new Justificatif(00, new Date(), TypeJustificatif.Salaire), 2000));
//		
//		demandescreationcli.add(new Dem_CreationClient(0, 0, "gdfgd", "dfgdhds", "brdfhs.com", "25dfh z", 676762393,
//				new Justificatif(00, new Date(), TypeJustificatif.Domicile), new Justificatif(00, new Date(), TypeJustificatif.Salaire), 1000));
//	return demandescreationcli;
//	}
	
//	private static List<Dem_Chequier> populateDummyDemandeschequier() {
//		demandeschequier = new ArrayList<Dem_Chequier>();
//		demandeschequier.add(new Dem_Chequier(2, "1"));
//		demandeschequier.add(new Dem_Chequier(4, "5"));
//	return demandeschequier;
//	}




	
	
	
}
