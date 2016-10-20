package com.wha.springmvc.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.wha.springmvc.model.Administrateur;
import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Conseiller;
import com.wha.springmvc.model.Dem_Chequier;
import com.wha.springmvc.model.Dem_CreationClient;
import com.wha.springmvc.model.Dem_ModificationCompte;
import com.wha.springmvc.model.Demande;
import com.wha.springmvc.model.TypeDemandes;

@Repository("demandeDao")
public class DemandeDaoImpl extends AbstractDao<Integer, Demande> implements DemandeDao {

	@Override
	public Dem_CreationClient createDemandeInscription(Dem_CreationClient demandecreationclient) {
		demandecreationclient.setType(1);

		Administrateur admin = (Administrateur) getEntityManager()
				.createQuery("SELECT a FROM Administrateur a WHERE a.id = 1").getSingleResult();

		Set<Dem_CreationClient> listDemandes = admin.getDemandeCreationClient();
		listDemandes.add(demandecreationclient);
		admin.setDemandeCreationClient(listDemandes);
		
		return demandecreationclient;
	}

	@Override
	public void addDemandeChequierToCons(long id_conseiller, Dem_Chequier demandechequier){
		demandechequier.setType(3);
		persist(demandechequier);
		
		
		Conseiller conseiller = (Conseiller) getEntityManager()
				.createQuery("SELECT c FROM Conseiller c WHERE c.id = :id").setParameter("id", id_conseiller)
				.getSingleResult();
		Set<Demande> listDemandes = conseiller.getDemandes();
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
		Set<Demande> listDemandes = conseiller.getDemandes();
		listDemandes.add(demandeModificationCompte);
		conseiller.setDemandes(listDemandes);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * Retourne toutes les demandes de modifications de compte attribuées à un conseiller
	 */
	public List<Dem_ModificationCompte> findAllDemandesModifCompte(long id) {
		List<Demande> demandes = getEntityManager()
				.createQuery("SELECT C.demandes FROM Conseiller C WHERE C.id = :id")
				.setParameter("id", id)
				.getResultList();
		List<Dem_ModificationCompte> demandemodificationcompte = new ArrayList<>();
		for (Demande dem : demandes) {
			if (dem.getType() == TypeDemandes.Modification.getType())
			{
				demandemodificationcompte.add((Dem_ModificationCompte)dem);
			}
		}
		
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
	public List<Dem_CreationClient> findAllDemandesCreationClient(long id) {
		List<Demande> demandes = getEntityManager()
				.createQuery("SELECT C.demandes FROM Conseiller C WHERE C.id = :id")
				.setParameter("id", id)
				.getResultList();
		List<Dem_CreationClient> demandecreationclient = new ArrayList<>();
		for (Demande dem : demandes) {
			if (dem.getType() == TypeDemandes.Creation.getType())
			{
				demandecreationclient.add((Dem_CreationClient)dem);
			}
		}
		return demandecreationclient;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Dem_Chequier> listAllDemandeChequier(long consID) {
		List<Demande> demandes = getEntityManager()
				.createQuery("SELECT C.demandes FROM Conseiller C WHERE C.id = :id")
				.setParameter("id", consID)
				.getResultList();
		List<Dem_Chequier> demandechequier = new ArrayList<>();
		for (Demande dem : demandes) {
			if (dem.getType() == TypeDemandes.Chequier.getType())
			{
				demandechequier.add((Dem_Chequier)dem);
			}
		}
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
		Set<Demande> listDemandes = conseiller.getDemandes();
		listDemandes.add(demande);
		conseiller.setDemandes(listDemandes);
			
		System.out.println("Conseiller a modifier "+conseiller);
		
	Administrateur admin = (Administrateur) getEntityManager().createQuery("SELECT a FROM Administrateur a WHERE a.id = 1").getSingleResult();
		
	Set<Dem_CreationClient> listDemandeCreation = admin.getDemandeCreationClient();
	Set<Dem_CreationClient> newListDemandeCreation = new HashSet<Dem_CreationClient>();
	
	
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

	@Override
	public void reaffectation(long client_id, long conseiller_id) {
		Conseiller consNew = (Conseiller) getEntityManager().createQuery("SELECT c FROM Conseiller c WHERE c.id = :id")
				.setParameter("id", conseiller_id).getSingleResult();
		Client cli = (Client)getEntityManager().createQuery("SELECT c FROM Client c WHERE c.id = :id")
				.setParameter("id", client_id).getSingleResult();
		cli.setConseiller(consNew);
	}



}
