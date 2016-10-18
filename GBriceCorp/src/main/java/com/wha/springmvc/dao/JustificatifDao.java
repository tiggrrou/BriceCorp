package com.wha.springmvc.dao;

import java.util.List;

import com.wha.springmvc.model.Justificatif;

public interface JustificatifDao {

	
	public List<Justificatif> findByClientId(long clientId);
	
	public void saveJustificatif(long demande_id, Justificatif justificatif);
	
	public void deleteJustificatifById(long id);
	
}
