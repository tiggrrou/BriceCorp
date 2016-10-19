package com.wha.springmvc.dao;

import java.util.List;

import com.wha.springmvc.model.Justificatif;

public interface JustificatifDao {

	
	public List<Justificatif> findById(long id,int clientOuDemande);
	
	public void saveJustificatif(long id_demandeouclient, Justificatif justificatif,int clientOuDemande);
	
	public void deleteJustificatifById(long id);
	
}
