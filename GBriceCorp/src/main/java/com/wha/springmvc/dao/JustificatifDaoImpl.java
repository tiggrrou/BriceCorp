package com.wha.springmvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wha.springmvc.model.Justificatif;


@Repository("justificatifDao")
public class JustificatifDaoImpl extends AbstractDao<Integer, Justificatif> implements JustificatifDao {

	@Override
	public List<Justificatif> findByClientId(long clientId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void saveJustificatif(Justificatif justificatif) {
		persist(justificatif);
	}

	@Override
	public void deleteJustificatifById(long id) {
		// TODO Auto-generated method stub

	}


}
