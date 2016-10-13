package com.wha.springmvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wha.springmvc.model.Conseiller;
import com.wha.springmvc.model.Dem_Chequier;
import com.wha.springmvc.model.Dem_CreationClient;
import com.wha.springmvc.model.Dem_ModificationCompte;
import com.wha.springmvc.model.Demande;


@Repository("demandeDao")
public class DemandeDaoImpl extends AbstractDao<Integer, Demande> implements DemandeDao {

	@Override
	public void createDemandeInscription(Dem_CreationClient demandecreationclient) {
		persist(demandecreationclient);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dem_ModificationCompte> findAllDemandesModifCompte() {
		List<Dem_ModificationCompte> demandemodificationcompte = getEntityManager().createQuery("FROM Dem_ModificationCompte")
				.getResultList();
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
		List<Dem_Chequier> demandechequier = getEntityManager().createQuery("FROM Dem_Chequier")
				.getResultList();
		return demandechequier;
	}

	@Override
	public boolean attribution(long id_demande, long id_conseiller) {
		// TODO Auto-generated method stub
		return false;
	}
	//#region Attributs

	@Override
	public Demande findDemandeById(long id_demande) {
		Demande demande = (Demande) getEntityManager()
				.createQuery("SELECT d FROM Demande d WHERE d.id = :id").setParameter("id", id_demande)
				.getSingleResult();
return demande;
	}

	//#endregion

	//#region Constructeur

	//#endregion

	//#region Methode

	//#endregion

}
