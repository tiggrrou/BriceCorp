package com.wha.springmvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Dem_CreationClient;
import com.wha.springmvc.model.Justificatif;

@Repository("justificatifDao")
public class JustificatifDaoImpl extends AbstractDao<Integer, Justificatif> implements JustificatifDao {

	@Override
	public List<Justificatif> findByClientId(long clientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveJustificatif(long id, Justificatif justificatif,String clientOuDemande){
		persist(justificatif);
			
		String clientOuDemande_demande = "demande";
		String clientOuDemande_client = "client";
		
		
		if(clientOuDemande == clientOuDemande_demande){
			Dem_CreationClient demandecreation = (Dem_CreationClient) getEntityManager()
					.createQuery("SELECT d FROM Dem_CreationClient d WHERE d.id = :id").setParameter("id", id)
					.getSingleResult();
			
			List<Justificatif> listJustificatifs = demandecreation.getJustificatifs();
			listJustificatifs.add(justificatif);
			demandecreation.setJustificatifs(listJustificatifs);
		}else if(clientOuDemande == clientOuDemande_client){

			Client client = (Client) getEntityManager()
					.createQuery("SELECT c FROM Client c WHERE c.id = :id").setParameter("id", id)
					.getSingleResult();
			
			List<Justificatif> listJustificatifs = client.getJustificatifs();
			listJustificatifs.add(justificatif);
			client.setJustificatifs(listJustificatifs);
			
		}


	}

	@Override
	public void deleteJustificatifById(long id) {
		// TODO Auto-generated method stub

	}

}
