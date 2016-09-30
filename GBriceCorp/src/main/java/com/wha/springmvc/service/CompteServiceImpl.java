package com.wha.springmvc.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.wha.springmvc.model.Compte;

@Service("compteService")
public class CompteServiceImpl implements CompteService {

	private static final AtomicLong counter = new AtomicLong();

	private static List<Compte> comptes;

	static {
		comptes = populateDummyComptes();
	}

	public List<Compte> findAllComptes() {
		return comptes;
	}

	public Compte findById(String id) {
		for (Compte compte : comptes) {
			if (compte.getID() == id) {
				return compte;
			}
		}
		return null;
	}
	
	public List<Compte> findByClientId(long clientId) {
		List<Compte> clientComptes = new ArrayList<Compte>();
		for (Compte c: comptes){
			if (c.getClientID() == clientId){
				clientComptes.add(c);			
			}
		}		
		return clientComptes;
	}

	public Compte findByLibelle(String name) {
		for (Compte compte : comptes) {
			if (compte.getLibelle().equalsIgnoreCase(name)) {
				return compte;
			}
		}
		return null;
	}


	public void saveCompte(Compte compte, String id) {
		compte.setID(id);
		comptes.add(compte);
	}

	public void updateCompte(Compte compte) {
		int index = comptes.indexOf(compte);
		comptes.set(index, compte);
	}

	public void deleteCompteById(String id) {

		for (Iterator<Compte> iterator = comptes.iterator(); iterator.hasNext();) {
			Compte compte = iterator.next();
			if (compte.getID() == id) {
				iterator.remove();
			}
		}
	}

	public boolean isCompteExist(Compte compte) {
		return findById(compte.getID()) != null;
	}

	public void deleteAllComptes() {
		comptes.clear();
	}

	private static List<Compte> populateDummyComptes() {
		List<Compte> comptes = new ArrayList<Compte>();
		/*comptes.add(String iD, String libelle, long clientID, int decouvert, float tauxDecouvert);*/
		comptes.add(new Compte("1", "Compte Courant", 4, 0, (float) 0.5));
		comptes.add(new Compte("2", "Compte Joint non-rémunéré", 4, 0, (float) 0.5));
		comptes.add(new Compte("3", "Compte Vampire", 2, 0, (float) 0.5));
		comptes.add(new Compte("4", "Compte de blanchiement d'argent", 2, 0, (float) 0.5));
		comptes.add(new Compte("33", "Compte Joint rémunéré", 5, 0, (float) 0.5));
		comptes.add(new Compte("12", "Compte Courant", 5, 0,(float) 0.5));
		comptes.add(new Compte("44", "Compte à retraits différés", 5, 0,(float) 0.5));
		comptes.add(new Compte("90", "Compte Courant",5, 0, (float) 0.5));		
		return comptes;
	}

}
