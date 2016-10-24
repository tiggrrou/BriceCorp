package com.wha.springmvc.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wha.springmvc.model.Administrateur;
import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Conseiller;
import com.wha.springmvc.model.Dem_Chequier;
import com.wha.springmvc.model.Dem_CreationClient;
import com.wha.springmvc.model.Dem_ModificationCompte;
import com.wha.springmvc.model.Dem_ModificationInfo;
import com.wha.springmvc.model.Demande;
import com.wha.springmvc.model.EtatDemande;
import com.wha.springmvc.model.TypeDemandes;

@Repository("demandeDao")
public class DemandeDaoImpl extends AbstractDao<Integer, Demande> implements DemandeDao {

	@Override
	public Dem_CreationClient createDemandeInscription(Dem_CreationClient demandecreationclient) {
		demandecreationclient.setType(TypeDemandes.Creation.getType());

		Administrateur admin = (Administrateur) getEntityManager()
				.createQuery("SELECT a FROM Administrateur a WHERE a.id = 1").getSingleResult();

		admin.getDemandeCreationClient().add(demandecreationclient);
		
		return demandecreationclient;
	}

	@Override
	public void addDemandeChequierToCons(long id_conseiller, Dem_Chequier demandechequier){
		demandechequier.setType(TypeDemandes.Chequier.getType());
				
		Conseiller conseiller = (Conseiller) getEntityManager()
				.createQuery("SELECT c FROM Conseiller c WHERE c.id = :id").setParameter("id", id_conseiller)
				.getSingleResult();
		conseiller.getDemandes().add(demandechequier);
		
	}

	@Override
	public void addDemandeModificationCompteToCons(long id_conseiller, Dem_ModificationCompte demandeModificationCompte){
		demandeModificationCompte.setType(TypeDemandes.ModificationCompte.getType());
				
		Conseiller conseiller = (Conseiller) getEntityManager()
				.createQuery("SELECT c FROM Conseiller c WHERE c.id = :id").setParameter("id", id_conseiller)
				.getSingleResult();
		conseiller.getDemandes().add(demandeModificationCompte);
	
	}

	@Override
	public void addDemandeModificationInfoPersoToCons(long id_conseiller, Dem_ModificationInfo demandeModificationInfoPerso) {
		demandeModificationInfoPerso.setType(TypeDemandes.ModificationInformationPerso.getType());
		
		Conseiller conseiller = (Conseiller) getEntityManager()
				.createQuery("SELECT c FROM Conseiller c WHERE c.id = :id").setParameter("id", id_conseiller)
				.getSingleResult();
		conseiller.getDemandes().add(demandeModificationInfoPerso);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Dem_CreationClient> findAllDemandesCreationClient() {
		List<Dem_CreationClient> demandecreationclient = getEntityManager()
				.createQuery("SELECT D FROM Dem_CreationClient D")
				.getResultList();
		return demandecreationclient;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	/**
	 * Retourne toutes les demandes de modifications de compte attribuées à un conseiller
	 */
	public List<Dem_ModificationCompte> findAllDemandesModifCompte(long id) {
		List<Demande> demandes = getEntityManager()
				.createQuery("SELECT C.demandes FROM Conseiller C WHERE C.id = :id")
				.setParameter("id", id)
				.getResultList();
		List<Dem_ModificationCompte> demandemodificationcompte = new ArrayList<>();
		for (Demande dem : demandes) {
			if (dem.getType() == TypeDemandes.ModificationCompte.getType())
			{
				demandemodificationcompte.add((Dem_ModificationCompte)dem);
			}
		}
		
		return demandemodificationcompte;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	/**
	 * Retourne toutes les demandes de modifications de compte attribuées à un conseiller
	 */
	public List<Dem_ModificationInfo> findAllDemandesModifInfo(long id) {
		List<Demande> demandes = getEntityManager()
				.createQuery("SELECT C.demandes FROM Conseiller C WHERE C.id = :id")
				.setParameter("id", id)
				.getResultList();
		List<Dem_ModificationInfo> demandemodificationinfoperso = new ArrayList<>();
		for (Demande dem : demandes) {
			if (dem.getType() == TypeDemandes.ModificationInformationPerso.getType())
			{
				demandemodificationinfoperso.add((Dem_ModificationInfo)dem);
			}
		}
		
		return demandemodificationinfoperso;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Dem_CreationClient> findAllDemandesCreationClient(long id) {
		String query = "";
		String param = "";
		List<Dem_CreationClient> demandecreationclient = new ArrayList<>();
		if (id > 1)
		{
			
			
			query = "SELECT C.demandes FROM Conseiller C WHERE C.id = :param";

			
			List<Demande> demandes = getEntityManager()
					.createQuery(query)
					.setParameter("param", id)
					.getResultList();
			for (Demande dem : demandes) {
				if (dem.getType() == TypeDemandes.Creation.getType())
				{
					demandecreationclient.add((Dem_CreationClient)dem);
				}
			}
		}
		else
		{
			query = "SELECT D FROM Dem_CreationClient D WHERE D.etat = :param";
			param = (id == 0)? EtatDemande.Cree.name() : EtatDemande.Affectee.name();

			demandecreationclient = getEntityManager()
					.createQuery(query)
					.setParameter("param", param)
					.getResultList();
		}
		return demandecreationclient;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Dem_Chequier> listAllDemandeChequier(long consID) {
		List<Demande> demandes = getEntityManager()
				.createQuery("SELECT C.demandes FROM Conseiller C WHERE C.id = :id")
				.setParameter("id", consID)
				.getResultList();
		List<Dem_Chequier> demandechequier = new ArrayList<>();
		for (Demande dem : demandes) {
			if (dem.getType() == TypeDemandes.Chequier.getType())
			{
				demandechequier.add((Dem_Chequier)dem);
			}
		}
		return demandechequier;
	}

	@Override	
	public void attribution(long id_demande,long id_conseiller){

		Demande demande = (Demande) getEntityManager()
				.createQuery("SELECT d FROM Demande d WHERE d.id = :id").setParameter("id", id_demande)
				.getSingleResult();
		demande.setEtat(EtatDemande.Affectee.toString());
		
		Conseiller conseiller = (Conseiller) getEntityManager()
				.createQuery("SELECT c FROM Conseiller c WHERE c.id = :id").setParameter("id", id_conseiller)
				.getSingleResult();
		conseiller.getDemandes().add(demande);
		
	
	Administrateur admin = (Administrateur) getEntityManager().createQuery("SELECT a FROM Administrateur a WHERE a.id = 1").getSingleResult();
	admin.getDemandeCreationClient().remove(demande);	
	
	}

	@Override
	public Demande findDemandeById(long id_demande) {
		Demande demande = (Demande) getEntityManager().createQuery("SELECT d FROM Demande d WHERE d.id = :id")
				.setParameter("id", id_demande).getSingleResult();
		return demande;
	}

	@Override
	public void suppressionDemande(long id_demande,long id_conseiller) {
		Conseiller conseiller = (Conseiller) getEntityManager()
				.createQuery("SELECT c FROM Conseiller c WHERE c.id = :id").setParameter("id", id_conseiller)
				.getSingleResult();
		Demande demande = findDemandeById(id_demande);
		conseiller.getDemandes().remove(demande);
		delete(demande);
	}

	@Override
	public void modifEtat_Demande(long demande_id, String nouvelEtat){
		Demande demande2 = findDemandeById(demande_id);
		demande2.setEtat(nouvelEtat);
	}

	@Override
	public void reaffectation(long client_id, long conseiller_id) {
		Conseiller consNew = (Conseiller) getEntityManager().createQuery("SELECT c FROM Conseiller c WHERE c.id = :id")
				.setParameter("id", conseiller_id).getSingleResult();
		Client cli = (Client)getEntityManager().createQuery("SELECT c FROM Client c WHERE c.id = :id")
				.setParameter("id", client_id).getSingleResult();
		cli.setConseiller(consNew);
	}





}
