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
import org.springframework.web.util.UriComponentsBuilder;

import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Compte;
import com.wha.springmvc.model.Conseiller;
import com.wha.springmvc.model.Dem_Chequier;
import com.wha.springmvc.model.Dem_CreationClient;
import com.wha.springmvc.model.Dem_ModificationCompte;
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
    			demande_user.add(userService.findById(demande.getClientID()));   	
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
			demande_user.add(userService.findById(demande.getClientID()));   	
			demandes_user.add(demande_user);   
			}
		
		}
    	
    	
    	return new ResponseEntity<List<List<Object>>>(demandes_user, HttpStatus.OK);
    }
    
    
    
    //-------------------Create demande d'inscription--------------------------------------------------------
    
    @RequestMapping(value = "/demande/inscription", method = RequestMethod.POST)
    public ResponseEntity<Void> DemandeInscription(@RequestBody Client client,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating demande inscription " + client);

       
 
        demandeService.createDemandeInscription(client);
 
        return new ResponseEntity<Void>( HttpStatus.CREATED);

    }
 
    
    //-------------------Retrieve All Users--------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
 
 
    
    //-------------------Retrieve Single User--------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
   
    
     
    //------------------- Update a User --------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Updating User " + id);
         
        User currentUser = userService.findById(id);
         
        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
 
        currentUser.setNom(user.getNom());
        currentUser.setAdresse(user.getAdresse());
        currentUser.setMail(user.getMail());
         
        userService.updateUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }
 
    //------------------- Login a User --------------------------------------------------------
    
    @RequestMapping(value = "/user/connect/{login}&{password}", method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> connectUser(@PathVariable("login") String login, @PathVariable("password") String pwd) {
        System.out.println("logging User " + login);
         
        User currentUser = userService.findByLogin(login);
        
        if (currentUser==null) {
            System.out.println("User with login " + login + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
         
        String responseConnect = userService.connexion(login, pwd);
        System.out.println(responseConnect);
        if ("identification réussie".equalsIgnoreCase(responseConnect))
        {
        	//TODO routage vers bonne page
        	return new ResponseEntity<User>(currentUser,HttpStatus.OK);
        }
        else 
        	return new ResponseEntity<User>(HttpStatus.NOT_ACCEPTABLE);
    }
    

    
    //------------------- get user --------------------------------------------------------
    
    @RequestMapping(value = "/user/client/{idClient}", method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getClient(@PathVariable("idClient") String idClient) {
        //TODO obtenir le client
    	return new ResponseEntity<User>(HttpStatus.OK);

    }
    
    //------------------- Delete a User --------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id " + id);
 
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
 
        userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
 
     
    
    //------------------- Delete All Users --------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers() {
        System.out.println("Deleting All Users");
 
        userService.deleteAllUsers();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
    
  //------------------- Liste Compte d'un client --------------------------------------------------------
    
    @RequestMapping(value = "/compte/{idClient}", method = RequestMethod.GET)
    public ResponseEntity<List<Compte>> getListeClientComptes(@PathVariable("idClient") long ID) {
    	System.out.println("compte"+ID);
        List<Compte> listComptes = compteService.findByClientId(ID);        
        return new ResponseEntity<List<Compte>>(listComptes ,HttpStatus.OK);
    }
/**    
  //------------------- Liste des users de type --------------------------------------------------------
    
    @RequestMapping(value = "/user/Conseiller/{typeUser}", method = RequestMethod.GET)
    public ResponseEntity<List<User>> trouveParType(@PathVariable("typeUser") int typeUser) {

    	List<User> listUsers = userService.trouveParType(typeUser);   
		return new ResponseEntity<List<User>>(listUsers ,HttpStatus.OK);
    }
*/	
  //-------------------Retrieve Conseiller Users--------------------------------------------------------
    
    @RequestMapping(value = "/user/Conseiller/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listUser_Cons(@PathVariable("id") long id) {
        System.out.println("Fetching Clients du Conseiller with id " + id);
        List<User> users = userService.getUser_Cons(id);
        System.out.println("cou" + users);
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }   
    
//-------------------Retrieve Admin Conseillers --------------------------------------------------------
    
    @RequestMapping(value = "/user/Admin/Conseillers", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listUser_Admin_Cons() {
        List<User> users = userService.getUser_Admin_Cons();
        System.out.println(users);
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
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
    public ResponseEntity<Void> creaCons3(@RequestBody User user) {
        System.out.println("Creating a conseiller " + user);
 
        /*if (userService.isUserExist(user)) {
            System.out.println("A User with name " + user.getNom() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }*/
 
        userService.saveConseiller(user);
 
        return new ResponseEntity<Void>( HttpStatus.CREATED);
    }
    
  //------------------- Virement --------------------------------------------------------
    @RequestMapping(value = "/compte/virement/{Debiteur}&{Crediteur}&{Montant}", method = RequestMethod.POST)
    public ResponseEntity<Void> virement(	@PathVariable("Debiteur") String debiteur,
    										@PathVariable("Crediteur") String crediteur,
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
}
