package com.wha.springmvc.dao;

import java.util.List;

import com.wha.springmvc.model.Compte;

public interface CompteDao {

	
	Compte findCById(long compteid);
	
	List<Compte> findCByClientId(long clientId);
	
	public long saveCompte(Compte compte);
	
	void updateCompte(Compte compte);
	
	void deleteCompteById(long compteid);

	List<Compte> findAllComptes(); 
	
	void deleteAllComptes();
}
