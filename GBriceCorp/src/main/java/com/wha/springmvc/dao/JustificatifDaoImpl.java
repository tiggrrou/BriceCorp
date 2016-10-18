package com.wha.springmvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

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
	public void saveJustificatif(long demande_id, Justificatif justificatif) {
		persist(justificatif);

		Dem_CreationClient demandecreation = (Dem_CreationClient) getEntityManager()
				.createQuery("SELECT d FROM Dem_CreationClient d WHERE d.id = :id").setParameter("id", demande_id)
				.getSingleResult();

		List<Justificatif> listJustificatifs = demandecreation.getJustifictifs();
		listJustificatifs.add(justificatif);
		demandecreation.setJustifictifs(listJustificatifs);
	}

	@Override
	public void deleteJustificatifById(long id) {
		// TODO Auto-generated method stub

	}

}
