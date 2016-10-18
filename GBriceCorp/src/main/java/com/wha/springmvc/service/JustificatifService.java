package com.wha.springmvc.service;

import java.util.List;

import com.wha.springmvc.model.Justificatif;

public interface JustificatifService {

	
	public List<Justificatif> findByClientId(long clientId);
	
	public void saveJustificatif(Justificatif justificatif);
	
	public void deleteJustificatifById(long id);
	
}