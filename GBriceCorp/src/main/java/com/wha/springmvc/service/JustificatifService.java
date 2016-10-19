package com.wha.springmvc.service;

import java.util.List;

import com.wha.springmvc.model.Justificatif;

public interface JustificatifService {

	
	public List<Justificatif> findById(long id,int clientOuDemande);
	
	public void saveJustificatif(long id, Justificatif justificatif,int clientOuDemande);
	
	public void deleteJustificatifById(long id);
	
}
