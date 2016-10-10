package com.wha.springmvc.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.wha.springmvc.model.Compte;
import com.wha.springmvc.model.Conseiller;
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

    //-------------------Retrieve All Demandes--------------------------------------------------------
    
    @RequestMapping(value = "/demandes", method = RequestMethod.GET)
    public ResponseEntity<List<Dem_ModificationCompte>> listAllDemande() {
        List<Dem_ModificationCompte> demandes = demandeService.findAllDemandes();
        if(demandes.isEmpty()){
            return new ResponseEntity<List<Dem_ModificationCompte>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Dem_ModificationCompte>>(demandes, HttpStatus.OK);
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
 
  
     
    //-------------------Create a User--------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getNom());
 
        if (userService.isUserExist(user)) {
            System.out.println("A User with name " + user.getNom() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        userService.saveUser(user);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
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
    
    //------------------- get Demandes --------------------------------------------------------
    //non utilisée car service dedié créé
    @RequestMapping(value = "/user/demandes/{idClient}&{idConseiller}", method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getDemandes(@PathVariable("idClient") String idClient, @PathVariable("idConseiller") String idConseiller) {
        //TODO obtenir les bonnes demandes
    	return new ResponseEntity<User>(HttpStatus.OK);

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
    
  //-------------------Create a Conseiller--------------------------------------------------------
    
    @RequestMapping(value = "/user/ADMIN/creaCons", method = RequestMethod.POST)
    public ResponseEntity<Void> creaCons3(@RequestBody Conseiller conseiller) {
        System.out.println("Creating a conseiller " + conseiller);
 
        /*if (userService.isUserExist(user)) {
            System.out.println("A User with name " + user.getNom() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }*/
 
        userService.saveConseiller(conseiller);
 
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
    		credit.setSolde(credit.getSolde()- montant);
    		
        /*if (userService.isUserExist(user)) {
            System.out.println("A User with name " + user.getNom() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }*/
 
        return new ResponseEntity<Void>( HttpStatus.OK);
    }
}
