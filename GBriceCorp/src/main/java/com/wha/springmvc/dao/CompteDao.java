package com.wha.springmvc.dao;

import java.util.List;

import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Compte;

public interface CompteDao {

	
	Compte findCById(long compteid);
	
	List<Compte> findCByClientId(long clientId);
	
	public long saveCompte(Compte compte);
	
	void updateCompte(Compte compte);
	
	void deleteCompteById(long compteid);

	List<Compte> findAllComptes(); 
	
	void deleteAllComptes();
	
	public void mouvement(float montant, long compteDebiteurID, long compteCrediteurID);

	Client findOwnerByCountID(long idCompte);
}
