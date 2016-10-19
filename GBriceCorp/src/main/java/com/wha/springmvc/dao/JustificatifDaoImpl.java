package com.wha.springmvc.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Dem_CreationClient;
import com.wha.springmvc.model.Justificatif;

@Repository("justificatifDao")
public class JustificatifDaoImpl extends AbstractDao<Integer, Justificatif> implements JustificatifDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Justificatif> findById(long id, int clientOuDemande) {
		List<Justificatif> justificatifs = new ArrayList<Justificatif>();
		if (clientOuDemande == 0) {
			justificatifs = getEntityManager()
					.createQuery("SELECT d.justificatifs FROM Demande d where d.ID = :id")
					.setParameter("id", id).getResultList();

			

		} else if (clientOuDemande == 1) {
			justificatifs = getEntityManager()
					.createQuery("SELECT u.justificatifs FROM User u where u.id = :id")
					.setParameter("id", id).getResultList();

			

		}
		
		return justificatifs;
	}

	@Override
	public void saveJustificatif(long id, Justificatif justificatif, int clientOuDemande) {
		persist(justificatif);

		if (clientOuDemande == 0) {
			Dem_CreationClient demandecreation = (Dem_CreationClient) getEntityManager()
					.createQuery("SELECT d FROM Dem_CreationClient d WHERE d.id = :id").setParameter("id", id)
					.getSingleResult();

			List<Justificatif> listJustificatifs = demandecreation.getJustificatifs();
			listJustificatifs.add(justificatif);
			demandecreation.setJustificatifs(listJustificatifs);
		} else if (clientOuDemande == 1) {

			Client client = (Client) getEntityManager().createQuery("SELECT c FROM Client c WHERE c.id = :id")
					.setParameter("id", id).getSingleResult();

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
