package com.wha.springmvc.service;

import java.util.List;

import com.wha.springmvc.model.Compte;



public interface CompteService {
	
	Compte findById(String id);
	
	Compte findByLibelle(String name);
	
	List<Compte> findByClientId(long clientId);
	
	void saveCompte(Compte compte, String id);
	
	void updateCompte(Compte compte);
	
	void deleteCompteById(String id);

	List<Compte> findAllComptes(); 
	
	void deleteAllComptes();
	
	public boolean isCompteExist(Compte compte);
	
	
}
