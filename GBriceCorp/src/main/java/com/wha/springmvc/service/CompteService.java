package com.wha.springmvc.service;

import java.util.List;

import com.wha.springmvc.model.Compte;



public interface CompteService {
	
	public Compte findById(long id);
	
	public Compte findByLibelle(String name);
	
	public List<Compte> findByClientId(long clientId);
	
	public long saveCompte(Compte compte);
	
	public void updateCompte(Compte compte);
	
	public void deleteCompteById(long id);

	public List<Compte> findAllComptes(); 
	
	public void deleteAllComptes();
	
	public boolean isCompteExist(Compte compte);
	
	public void mouvement( float montant, long compteDebiteurID, long compteCrediteurID);
	
	
}
