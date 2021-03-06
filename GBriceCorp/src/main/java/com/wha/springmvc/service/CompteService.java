package com.wha.springmvc.service;

import java.util.List;

import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Compte;
import com.wha.springmvc.model.Mouvement;



public interface CompteService {
	
	public Compte findCompteById(long id);
	
	
	public Compte findByLibelle(String name);
	
	public List<Compte> findByClientId(long clientId);
	

	public long saveCompte(long clientId, Compte compte);
	
	public void updateCompte(Compte compte);
	
	public void deleteCompteById(long id);

	public List<Compte> findAllComptes(); 
	
	public void deleteAllComptes();
	
	public boolean isCompteExist(Compte compte);
	
	public Mouvement mouvement( float montant, long compteDebiteurID, long compteCrediteurID);
	
	public List<Mouvement> mouvements(long idCompte, int dateRange);
	
	public Client findOwnerByCountID (long idCompte);


	public List<Compte> findComptesByIdCons(long idCons);
	
	
}
