package com.wha.springmvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wha.springmvc.model.Administrateur;
import com.wha.springmvc.model.Dem_Chequier;
import com.wha.springmvc.model.Dem_CreationClient;
import com.wha.springmvc.model.Dem_ModificationCompte;
import com.wha.springmvc.model.Demande;

@Repository("demandeDao")
public class DemandeDaoImpl extends AbstractDao<Integer, Demande> implements DemandeDao {

	@Override
	public void createDemandeInscription(Dem_CreationClient demandecreationclient) {
		demandecreationclient.setType(1);
		
Administrateur admin = (Administrateur) getEntityManager().createQuery("SELECT a FROM Administrateur a WHERE a.id = 1").getSingleResult();
		
		List<Dem_CreationClient> listDemandes = admin.getDemandeCreationClient();
		listDemandes.add(demandecreationclient);
		admin.setDemandeCreationClient(listDemandes);
	}

	@Override
	public void createDemandeChequier(Dem_Chequier demandechequier) {
		demandechequier.setType(3);
		persist(demandechequier);
	}

	@Override
	public void createDemandeModificationCompte(Dem_ModificationCompte demandeModificationCompte) {
		demandeModificationCompte.setType(2);
		persist(demandeModificationCompte);
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
		List<Dem_CreationClient> demandecreationclient = getEntityManager().createQuery("FROM Dem_CreationClient")
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
	public boolean attribution(long id_demande, long id_conseiller) {
		// TODO Auto-generated method stub
		return false;
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


}
