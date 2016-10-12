package com.wha.springmvc.dao;

import java.util.List;

import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Dem_Chequier;
import com.wha.springmvc.model.Dem_CreationClient;
import com.wha.springmvc.model.Dem_ModificationCompte;
import com.wha.springmvc.model.Demande;
import com.wha.springmvc.model.User;

public class DemandeDaoImpl extends AbstractDao<Integer, Demande> implements DemandeDao {

	@Override
	public void createDemandeInscription(Client client) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dem_ModificationCompte> findAllDemandesModifCompte() {
		List<Dem_ModificationCompte> demandemodificationcompte = getEntityManager().createQuery("FROM demande_modification")
				.getResultList();
		return demandemodificationcompte;
	}

	@Override
	public List<Dem_CreationClient> findAllDemandesCreationClient(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dem_Chequier> listAllDemandeChequier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean attribution(long id_demande, long id_conseiller) {
		// TODO Auto-generated method stub
		return false;
	}
	//#region Attributs

	//#endregion

	//#region Constructeur

	//#endregion

	//#region Methode

	//#endregion

}
