package com.wha.springmvc.dao;

import java.util.List;

import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Compte;
import com.wha.springmvc.model.Mouvement;

public interface CompteDao {

	
	Compte findCompteById(long compteid);
	
	List<Compte> findCByClientId(long clientId);
	
	public List<Compte> findComptesByIdCons(long consId);

	
	public long saveCompte(long clientId, Compte compte);
	
	void updateCompte(Compte compte);
	
	void deleteCompteById(long compteid);

	List<Compte> findAllComptes(); 
	
	void deleteAllComptes();
	
	public Mouvement mouvement(float montant, long compteDebiteurID, long compteCrediteurID);

	Client findOwnerByCountID(long idCompte);
}
