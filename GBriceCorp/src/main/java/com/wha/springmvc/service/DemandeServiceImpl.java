package com.wha.springmvc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Dem_Chequier;
import com.wha.springmvc.model.Dem_CreationClient;
import com.wha.springmvc.model.Dem_ModificationCompte;
import com.wha.springmvc.model.Justificatif;
import com.wha.springmvc.model.TypeJustificatif;
import com.wha.springmvc.model.User;


@Service("demandeService")
public class DemandeServiceImpl implements DemandeService {


	private static List<Dem_ModificationCompte> demandesmodifcompte;	
	private static List<Dem_CreationClient> demandescreationcli;
	private static List<Dem_Chequier> demandeschequier;
	static {
		demandesmodifcompte = populateDummyDemandesModifCompte();
		demandescreationcli = populateDummyDemandescreationcli();
		demandeschequier = populateDummyDemandeschequier();
	}
	
	
	@Override
	public List<Dem_ModificationCompte> findAllDemandesModifCompte() {
		// TODO Auto-generated method stub
		return demandesmodifcompte;
	}

	@Override
	public List<Dem_CreationClient> findAllDemandesCreationClient(long id) {
		// TODO Auto-generated method stub

		List<Dem_CreationClient> listedemandecreationpartype = new ArrayList<Dem_CreationClient>();
		
		if(id == 0){
		for (Dem_CreationClient demandecreationcli : demandescreationcli) {
			if (demandecreationcli.getClientID() == id) {
				listedemandecreationpartype.add(demandecreationcli);
				System.out.println(demandecreationcli);
			}
		}
		}else{

			for (Dem_CreationClient demandecreationcli : demandescreationcli) {
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
		return demandeschequier;
	}
	
	
	


	@Override
	public void createDemandeInscription(Client client) {
System.out.println("creation demande inscription de "+ client);
demandescreationcli.add(new Dem_CreationClient(0, 0, client.getNom(), client.getPrenom(), client.getMail(), client.getAdresse(), client.getTelephone(),
			new Justificatif(00, new Date(), TypeJustificatif.Domicile), new Justificatif(00, new Date(), TypeJustificatif.Salaire), 1200));	
	System.out.println("creation de la demande "+ demandescreationcli);
	}



	
	

	private static List<Dem_ModificationCompte> populateDummyDemandesModifCompte() {
		demandesmodifcompte = new ArrayList<Dem_ModificationCompte>();
		demandesmodifcompte.add(new Dem_ModificationCompte(02, "1", 0, true));
		demandesmodifcompte.add(new Dem_ModificationCompte(04, "5", 100, false));
	return demandesmodifcompte;
	}

	private static List<Dem_CreationClient> populateDummyDemandescreationcli() {
		demandescreationcli = new ArrayList<Dem_CreationClient>();
		demandescreationcli.add(new Dem_CreationClient(0, 1, "MOREL", "brice", "brice@fezf.com", "25 e dez de z", 676762393,
			new Justificatif(00, new Date(), TypeJustificatif.Domicile), new Justificatif(00, new Date(), TypeJustificatif.Salaire), 1200));
		
		demandescreationcli.add(new Dem_CreationClient(0, 0, "gdfgd", "dfgdhds", "brdfhs.com", "25dfh z", 676762393,
				new Justificatif(00, new Date(), TypeJustificatif.Domicile), new Justificatif(00, new Date(), TypeJustificatif.Salaire), 1200));
	return demandescreationcli;
	}
	
	private static List<Dem_Chequier> populateDummyDemandeschequier() {
		demandeschequier = new ArrayList<Dem_Chequier>();
		demandeschequier.add(new Dem_Chequier(2, "1"));
		demandeschequier.add(new Dem_Chequier(4, "5"));
	return demandeschequier;
	}


	
	
	
}
