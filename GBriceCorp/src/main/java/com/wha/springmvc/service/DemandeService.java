package com.wha.springmvc.service;

import java.util.List;

import com.wha.springmvc.model.Dem_ModificationCompte;

public interface DemandeService {

	
	public List<Dem_ModificationCompte> findAllDemandes();
}
