package com.wha.springmvc.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wha.springmvc.model.Administrateur;
import com.wha.springmvc.model.Conseiller;
import com.wha.springmvc.model.Dem_Chequier;
import com.wha.springmvc.model.Dem_CreationClient;
import com.wha.springmvc.model.Dem_ModificationCompte;
import com.wha.springmvc.model.Demande;

@Repository("demandeDao")
public class DemandeDaoImpl extends AbstractDao<Integer, Demande> implements DemandeDao {

	@Override
	public void createDemandeInscription(Dem_CreationClient demandecreationclient) {
		demandecreationclient.setType(1);

		Administrateur admin = (Administrateur) getEntityManager()
				.createQuery("SELECT a FROM Administrateur a WHERE a.id = 1").getSingleResult();

		List<Dem_CreationClient> listDemandes = admin.getDemandeCreationClient();
		listDemandes.add(demandecreationclient);
		admin.setDemandeCreationClient(listDemandes);
	}

	@Override
	public void addDemandeChequierToCons(long id_conseiller, Dem_Chequier demandechequier){
		demandechequier.setType(3);
		persist(demandechequier);
		
		
		Conseiller conseiller = (Conseiller) getEntityManager()
				.createQuery("SELECT c FROM Conseiller c WHERE c.id = :id").setParameter("id", id_conseiller)
				.getSingleResult();
		List<Demande> listDemandes = conseiller.getDemandes();
		listDemandes.add(demandechequier);
		conseiller.setDemandes(listDemandes);
		
	}

	@Override
	public void addDemandeModificationCompteToCons(long id_conseiller, Dem_ModificationCompte demandeModificationCompte){
		demandeModificationCompte.setType(2);
		persist(demandeModificationCompte);
		
		Conseiller conseiller = (Conseiller) getEntityManager()
				.createQuery("SELECT c FROM Conseiller c WHERE c.id = :id").setParameter("id", id_conseiller)
				.getSingleResult();
		List<Demande> listDemandes = conseiller.getDemandes();
		listDemandes.add(demandeModificationCompte);
		conseiller.setDemandes(listDemandes);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dem_ModificationCompte> findAllDemandesModifCompte() {
		List<Dem_ModificationCompte> demandemodificationcompte = getEntityManager()
				.createQuery("FROM Dem_ModificationCompte").getResultList();
		return demandemodificationcompte;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dem_CreationClient> findAllDemandesCreationClient() {
		List<Dem_CreationClient> demandecreationclient = getEntityManager()
				.createQuery("SELECT A.demandeCreationClient FROM Administrateur A")
				.getResultList();
		return demandecreationclient;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dem_Chequier> listAllDemandeChequier() {
		List<Dem_Chequier> demandechequier = getEntityManager().createQuery("FROM Dem_Chequier").getResultList();
		return demandechequier;
	}

	@Override	
	public void attribution(long id_demande,long id_conseiller){

		Demande demande = (Demande) getEntityManager()
				.createQuery("SELECT d FROM Demande d WHERE d.id = :id").setParameter("id", id_demande)
				.getSingleResult();
		
		
		System.out.println("Demande a transferer : "+ demande);
		
		
		Conseiller conseiller = (Conseiller) getEntityManager()
				.createQuery("SELECT c FROM Conseiller c WHERE c.id = :id").setParameter("id", id_conseiller)
				.getSingleResult();
		List<Demande> listDemandes = conseiller.getDemandes();
		listDemandes.add(demande);
		conseiller.setDemandes(listDemandes);
			
		System.out.println("Conseiller a modifier "+conseiller);
		
	Administrateur admin = (Administrateur) getEntityManager().createQuery("SELECT a FROM Administrateur a WHERE a.id = 1").getSingleResult();
		
	List<Dem_CreationClient> listDemandeCreation = admin.getDemandeCreationClient();
	List<Dem_CreationClient> newListDemandeCreation = new ArrayList<Dem_CreationClient>();
	
	
	    for (Dem_CreationClient demandeCreation : listDemandeCreation ){
		      
			    if (demandeCreation.getID() != demande.getID()) {
			    	newListDemandeCreation.add(demandeCreation);
			    } 
		    }
	    System.out.println("Nouvelle liste des demandes "+newListDemandeCreation);
	    admin.setDemandeCreationClient(newListDemandeCreation);
	    
		System.out.println("Conseiller a modifier "+admin);
	

	
	
	}

	@Override
	public Demande findDemandeById(long id_demande) {
		Demande demande = (Demande) getEntityManager().createQuery("SELECT d FROM Demande d WHERE d.id = :id")
				.setParameter("id", id_demande).getSingleResult();
		return demande;
	}

	@Override
	public void suppressionDemande(long id_demande) {
		Demande demande = findDemandeById(id_demande);
		delete(demande);
	}

	@Override
	public void modifEtat_Demande(long demande_id, String nouvelEtat){

		Demande demande2 = findDemandeById(demande_id);
		demande2.setEtat(nouvelEtat);
		
	}



}
