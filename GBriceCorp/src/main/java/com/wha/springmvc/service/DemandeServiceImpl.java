package com.wha.springmvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wha.springmvc.model.Dem_ModificationCompte;

@Service("demandeService")
public class DemandeServiceImpl implements DemandeService {

	private static List<Dem_ModificationCompte> demandes;

	static {
		demandes = populateDemandes();
	}

	public List<Dem_ModificationCompte> findAllDemandes() {
		return demandes;
	}
	
	private static List<Dem_ModificationCompte> populateDemandes() {
		List<Dem_ModificationCompte> demandes = new ArrayList<Dem_ModificationCompte>();

		demandes.add(new Dem_ModificationCompte(2, "fdhdfhdfh", 0, 0));
		demandes.add(new Dem_ModificationCompte(4, "fezrg", 1, 0));
		demandes.add(new Dem_ModificationCompte(5, "ergqgrqg", 1, 0));
		demandes.add(new Dem_ModificationCompte(4, "qgrqgr", 1, 0));
		demandes.add(new Dem_ModificationCompte(2, "gqreg", 1, 0));
		return demandes;
	}
	
	
	
}
