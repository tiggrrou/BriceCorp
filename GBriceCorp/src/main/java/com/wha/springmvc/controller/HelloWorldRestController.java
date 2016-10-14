package com.wha.springmvc.controller;
 
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wha.springmvc.model.Administrateur;
import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Compte;
import com.wha.springmvc.model.Conseiller;
import com.wha.springmvc.model.Dem_Chequier;
import com.wha.springmvc.model.Dem_CreationClient;
import com.wha.springmvc.model.Dem_ModificationCompte;
import com.wha.springmvc.model.Demande;
import com.wha.springmvc.model.TypeUtilisateur;
import com.wha.springmvc.model.User;
import com.wha.springmvc.service.CompteService;
import com.wha.springmvc.service.DemandeService;
import com.wha.springmvc.service.UserService;

 
@RestController
public class HelloWorldRestController {
 
    @Autowired
    UserService userService;  //Service which will do all data retrieval/manipulation work
    
    @Autowired
    CompteService compteService;  //Service which will do all data retrieval/manipulation work

    @Autowired
    DemandeService demandeService;  //Service which will do all data retrieval/manipulation work

    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////DEMANDES////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
 
//-------------------Validation Creation Client--------------------------------------------------------
    
    @RequestMapping(value = "/demande/validationcreation/{id_conseil}", method = RequestMethod.PUT)
    public ResponseEntity<Void> demandeById(@PathVariable("id_conseil") long id_conseiller, @RequestBody Dem_CreationClient demande_inscription) {
    	System.out.println("validation de la demande d'isncription " + demande_inscription.getID());
    	userService.creationClient(id_conseiller,demande_inscription);
		demandeService.suppressionDemande(demande_inscription.getID());
        
		return new ResponseEntity<Void>( HttpStatus.CREATED);
    }
    //-------------------Retrieve Demande by Id--------------------------------------------------------
    
    @RequestMapping(value = "/demande/{id_demande}", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> demandeById(@PathVariable("id_demande") long id_demande) {
    	System.out.println("fetch demande " + id_demande);
        Demande demande = demandeService.findDemandeById(id_demande);
    	
        if(demande.isEmpty()){
            return new ResponseEntity<List<Object>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }else{
    	    	List<Object> demande_user = new ArrayList();   			
    			demande_user.add(demande);
    			demande_user.add(userService.findCliById(demande.getClientID()));   	
    		 	return new ResponseEntity<List<Object>>(demande_user, HttpStatus.OK);
    			}
     		}

    
    //-------------------Attribution du conseiller à une demande d'ouverture de compte--------------------------------------------------------
    
    @RequestMapping(value = "/demande/attribution/{id_demande}&{id_conseiller}", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> attribution(@PathVariable("id_demande") long id_demande, @PathVariable("id_conseiller") long id_conseiller) {
    	System.out.println("Attribution du conseiller" + id_conseiller + " a la demande " + id_demande);
        boolean attribution = demandeService.attribution(id_demande,id_conseiller);
        if(!attribution){
            return new ResponseEntity<Boolean>(attribution,HttpStatus.NOT_FOUND);
        }else{
        return new ResponseEntity<Boolean>(attribution, HttpStatus.OK);
        }
        
    }

    
    //-------------------Retrieve All Demandes de modif compte--------------------------------------------------------
    
    @RequestMapping(value = "/demande/modifcompte", method = RequestMethod.GET)
    public ResponseEntity<List<List<Object>>> listAllDemandeModifCompte() {
    	System.out.println("fetch demandes modif compte");
        List<Dem_ModificationCompte> demandes = demandeService.findAllDemandesModifCompte();
    	List<List<Object>> demandes_user = new ArrayList();

        if(demandes.isEmpty()){
            return new ResponseEntity<List<List<Object>>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }else{


    		for (Dem_ModificationCompte demande : demandes) {
    	    	List<Object> demande_user = new ArrayList();   			
    			demande_user.add(demande);
    			demande_user.add(userService.findCliById(demande.getClientID()));   	
    			demandes_user.add(demande_user);   
    			}
    		
    		}
        	
        	
        	return new ResponseEntity<List<List<Object>>>(demandes_user, HttpStatus.OK);
        }


    //-------------------Retrieve All Demandes d inscription attribuee ou non--------------------------------------------------------
    
    @RequestMapping(value = "/demande/inscription/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<List<Object>>> listAllDemandeIscription(@PathVariable("id") long id) {
    	System.out.println("fetch demandes inscription " + id);
        List<Dem_CreationClient> demandes = demandeService.findAllDemandesCreationClient(id);
    	List<List<Object>> demandes_user = new ArrayList();

        if(demandes.isEmpty()){
            return new ResponseEntity<List<List<Object>>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }else{


    		for (Dem_CreationClient demande : demandes) {
    	    	List<Object> demande_user = new ArrayList();   			
    			demande_user.add(demande);
    			demande_user.add(null);   	
    			demandes_user.add(demande_user);   
    			}
    		
    		}
        	
        	
        	return new ResponseEntity<List<List<Object>>>(demandes_user, HttpStatus.OK);
        }
    
    //-------------------Retrieve All Demandes de chequier--------------------------------------------------------
    
    @RequestMapping(value = "/demande/chequier", method = RequestMethod.GET)
    public ResponseEntity<List<List<Object>>> listAllDemandeChequier() {
    	System.out.println("fetch demandes chequier");
        List<Dem_Chequier> demandes = demandeService.listAllDemandeChequier();

    List<List<Object>> demandes_user = new ArrayList();

    if(demandes.isEmpty()){
        return new ResponseEntity<List<List<Object>>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
    }else{


		for (Dem_Chequier demande : demandes) {
	    	List<Object> demande_user = new ArrayList();   			
			demande_user.add(demande);
			demande_user.add(userService.findCliById(demande.getClientID()));   	
			demandes_user.add(demande_user);   
			}
		
		}
    	
    	
    	return new ResponseEntity<List<List<Object>>>(demandes_user, HttpStatus.OK);
    }
    
    
    
    //-------------------Create demande d'inscription--------------------------------------------------------
    
    @RequestMapping(value = "/demande/inscription", method = RequestMethod.POST)
    public ResponseEntity<Void> DemandeInscription(@RequestBody Dem_CreationClient demande_inscription) {
        System.out.println("Creating demande inscription " + demande_inscription);

        
 
        demandeService.createDemandeInscription(demande_inscription);
 
        return new ResponseEntity<Void>( HttpStatus.CREATED);

    }
   
    
  
 

    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////CONNEXION///////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
 
    //------------------- Login a User --------------------------------------------------------
    
    @RequestMapping(value = "/user/connect/{login}&{password}", method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> connectUser(@PathVariable("login") String login, @PathVariable("password") String pwd) {
        System.out.println("logging User " + login + " / " + pwd);
         
        User currentUser = userService.connexion(login,pwd);
        System.out.println(currentUser);
        if (currentUser==null) {
            System.out.println("User with login " + login + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }else{
        	//TODO routage vers bonne page
        	return new ResponseEntity<User>(currentUser,HttpStatus.OK);
        }
         
    }
    


    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////DEMANDES////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////   
    //------------------- get Client --------------------------------------------------------
    
    @RequestMapping(value = "/user/client/{idClient}", method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getClient(@PathVariable("idClient") String idClient) {
        //TODO obtenir le client
    	return new ResponseEntity<User>(HttpStatus.OK);

    }
    
  
    


    
 
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////CONSEILLER//////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
  //-------------------Retrieve Conseiller Users--------------------------------------------------------
    
    @RequestMapping(value = "/user/Conseiller/{idConseiller}", method = RequestMethod.GET)
    public ResponseEntity<List<Client>> listUser_Cons(@PathVariable("idConseiller") long idConseiller) {
        System.out.println("Fetching Clients du Conseiller with id " + idConseiller);
        List<Client> clients = userService.listeDeClientDuConseiller(idConseiller);
        if(clients.isEmpty()){
            return new ResponseEntity<List<Client>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
    }   
    

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////ADMIN///////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
//-------------------Retrieve all Conseillers --------------------------------------------------------
    
    @RequestMapping(value = "/user/Admin/Conseillers", method = RequestMethod.GET)
    public ResponseEntity<List<Conseiller>> listUser_Admin_Cons() {
        System.out.println("Fetching all Conseillers");
        List<Conseiller> conseillers = userService.findAllConseillers();
        System.out.println(conseillers);
        if(conseillers.isEmpty()){
            return new ResponseEntity<List<Conseiller>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Conseiller>>(conseillers, HttpStatus.OK);
    }
    
    
  //-------------------Retrieve Single Conseiller--------------------------------------------------------
    
    @RequestMapping(value = "/user/ADMIN/EditCons/{idCons}", method = RequestMethod.GET)
    public ResponseEntity<Conseiller> getCons(@PathVariable("idCons") long idCons) {
        System.out.println("Fetching Conseiller with id " + idCons);
        Conseiller conseiller = userService.findConsById(idCons);
        if (conseiller == null) {
            System.out.println("Conseiller with id " + idCons + " not found");
            return new ResponseEntity<Conseiller>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Conseiller>(conseiller, HttpStatus.OK);
    }
 
    
    //-------------------Create a Conseiller--------------------------------------------------------
    
    @RequestMapping(value = "/user/ADMIN/creaCons", method = RequestMethod.POST)
    public ResponseEntity<Void> creaCons3(@RequestBody Conseiller conseiller) {
        System.out.println("Creating a conseiller " + conseiller);
 
        /*if (userService.isUserExist(user)) {
            System.out.println("A User with name " + user.getNom() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }*/
 
        userService.createConseiller(conseiller);
 
        return new ResponseEntity<Void>( HttpStatus.CREATED);
    }
    
  //------------------- Update a Conseiller --------------------------------------------------------
    
    @RequestMapping(value = "/user/consEdit", method = RequestMethod.PUT)
    public ResponseEntity<Conseiller> updateCons(@RequestBody Conseiller cons) {
        System.out.println("Updating du Conseiller " + cons.getNom());
         
        userService.updateConseiller(cons);
         
       return new ResponseEntity<Conseiller>(cons, HttpStatus.OK);
    }
 
    
  //-------------------Create a Admin--------------------------------------------------------
    
    @RequestMapping(value = "/user/Dummy", method = RequestMethod.POST)
    public ResponseEntity<Void> populateDummy() {
        System.out.println("Populate Dummy");

        Administrateur admin = new Administrateur();
        admin.setTypeUser(TypeUtilisateur.Administrateur.getType());
        admin.setNom("NomAdmin");
        admin.setPrenom("PrenomAdmin");
        admin.setMail("mailadmin@bidul");
        admin.setAdresse("85 adresse de l'admin 88954 rennes");
        admin.setMatricule(1111);
        admin.setMotDePasse("a");
        admin.setTelephone(13456);
        admin.setIdentifiant("a");
        userService.createAdmin(admin);
        
        Conseiller conseiller = new Conseiller();
        conseiller.setNom("NomConseiller1");
        conseiller.setPrenom("PrenomConseiller1");
        conseiller.setAdresse("35 rue du machin 58650 Saint Machin");
        conseiller.setIdentifiant("b");
        conseiller.setMotDePasse("b");
        conseiller.setMail("machintruc@bidul.com");
        conseiller.setMatricule(12345);
        conseiller.setTelephone(11111111);
        userService.createConseiller(conseiller);
      
        Dem_CreationClient demande_inscription = new Dem_CreationClient();
        demande_inscription.setNom("NomClient1");
        demande_inscription.setPrenom("PrenomClient1");
        demande_inscription.setAdresse("25 adresse du client 1 44896 Paris");
        demande_inscription.setMail("mailClient1@bidul");
        demande_inscription.setRevenu(2500);
        demande_inscription.setTelephone(78987);
        userService.creationClient(2, demande_inscription);
       
 
        return new ResponseEntity<Void>( HttpStatus.CREATED);
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////COMPTE//////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
  //------------------- Virement --------------------------------------------------------
    @RequestMapping(value = "/compte/virement/{Debiteur}&{Crediteur}&{Montant}", method = RequestMethod.POST)
    public ResponseEntity<Void> virement(	@PathVariable("Debiteur") long debiteur,
    										@PathVariable("Crediteur") long crediteur,
    										@PathVariable("Montant") float montant){
    		Compte debit = compteService.findById(debiteur);
    		Compte credit = compteService.findById(crediteur);
    		debit.setSolde(debit.getSolde()- montant);
    		credit.setSolde(credit.getSolde()+ montant);
    		
        /*if (userService.isUserExist(user)) {
            System.out.println("A User with name " + user.getNom() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }*/
 
        return new ResponseEntity<Void>( HttpStatus.OK);
    }
    
    
    
  //------------------- Liste Compte d'un client --------------------------------------------------------
    
    @RequestMapping(value = "/compte/{idClient}", method = RequestMethod.GET)
    public ResponseEntity<List<Compte>> getListeClientComptes(@PathVariable("idClient") long ID) {
    	System.out.println("compte"+ID);
        List<Compte> listComptes = compteService.findByClientId(ID);        
        return new ResponseEntity<List<Compte>>(listComptes ,HttpStatus.OK);
    }

    
    
    
    
}
