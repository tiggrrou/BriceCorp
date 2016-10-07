'use strict';

App.controller('UserController', ['$scope', '$location', '$resource', '$route', 'UserService', 'translationService', function($scope, $location, $resource, $route, UserService, translationService) {
	
	/*Index fonctions
	 *  self.change_langue = change_langue;
	 *  self.trier_par = trier_par;
	 *  self.connect = connect;
	 *  self.connexion = connexion;
	 *  self.session_delete = session_delete;
	 *  self.submit = submit;
	 *  self.edit = edit;
	 *  self.remove = remove;
	 *  self.reset = reset;
	 *  self.getMyUserBack = getMyUserBack;
	 *  self.getDemandes = getDemandes;
	 *  self.searchClients = searchClients;
	 *  self.getClient = getClient;
	 *  self.getNotifs = getNotifs;
	 *  self.detailCompte = detailCompte;
	 * */

    
	// General
	var self = this;

    
    // Fonctions I18n
    $scope.$route = $route;   
    self.change_langue = change_langue;

    //Initialisation de la langue par defaut a francais
    var language = sessionStorage.getItem("langue");
    if(language == null)
    {	
	sessionStorage.setItem("langue","fr");
    }
    
    //Choix du drapeau cache en fonction de la langue en session
    if(language == "fr"){
    	$scope.lang_cache=true;
    }else{
    	$scope.lang_cache=false;	
    }
    //chargement du fichier de traduction dans le $scope grace au service dedie
    translationService.getTranslation($scope, language); 	

    //fonction de changement de la langue par le ngclick sur le drapeau
    function change_langue(pays){
    	$scope.lang_cache=!$scope.lang_cache;
		sessionStorage.setItem("langue",pays);
    	location.reload();
	    };
	    
	    
    // Fonctions vues
self.trier_par = trier_par;

	    
function trier_par(tri){
	//en cours d affinage
	$scope.tripar = tri;
    }



 //Fonctions connection
 self.connect = connect;
 self.connexion = connexion;
 self.session_delete = session_delete;
 
 
 function connect() {
 	connexion(self.user.identifiant, self.user.motDePasse);
 };
 
 function connexion(login, pwd){
 	UserService.connectUser(login, pwd)
 		.then(
 		function(d) {
 			var user = JSON.parse(sessionStorage.getItem("currentUser"));
 			var userType = (user == null)? 0 : user.typeUser;

 			switch(userType){
 			case 0 : 
 				$location.path("/");
 				break;
 			case 1 : 
 				$location.path("admin/");
 				break;
 			case 2 : 
 				$location.path("cons/");
 				break;    			
 			case 3 : 

 				$location.path("cli/");
 				break;
 			default :
 				$location.path("/");
 				break;
 			}
 			console.log(d);
 	        location.reload();
 		}, 
 		function (errResponse){
 			console.error('Error while connection');
 		}
 	);
 }; 

 if(sessionStorage.getItem("currentUser") == null)
 {
 	console.log("pas de session");
 	$scope.connexion_cache=false;
 	$scope.admin_cache=true;
 	$scope.conseiller_cache=true;
 	$scope.client_cache=true;
 }else{
		console.log("une session existe");
		var utilisateur = JSON.parse(sessionStorage.getItem("currentUser"));
		console.log("utilisateur.typeUser" +utilisateur.typeUser);
		console.log("utilisateur.nom" +utilisateur.nom);
 	$scope.connexion_cache=true;
 	$scope.admin_cache=true;
 	$scope.conseiller_cache=true;
 	$scope.client_cache=true;
		if(utilisateur.typeUser == 1)
		{
	    	$scope.admin_cache=false;
		}
		else if(utilisateur.typeUser == 2)
		{
			$scope.conseiller_cache=false;
		}
		else if(utilisateur.typeUser == 3)
		{
			$scope.client_cache=false;
		} 
 };

 function session_delete(){
 	sessionStorage.clear();
		$location.path("/");
 	location.reload();
 	console.log("delete session");
 };
 
 // Fonctions User
 self.user={id:null,prenom:'',nom:'',adresse:'',mail:'',identifiant:'',motDePasse:''};
 self.users=[];
 self.submit = submit;
 self.edit = edit;
 self.remove = remove;
 self.reset = reset; 
 self.getMyUserBack = getMyUserBack;
 self.recherche_userParType = recherche_userParType;
  
    
    
    function fetchAllUsers(){
        UserService.fetchAllUsers()
            .then(
            function(d) {
                self.users = d;
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    };

    function createUser(user){
        UserService.createUser(user)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while creating User');
            }
        );
    };

    function updateUser(user, id){
        UserService.updateUser(user, id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while updating User' + errResponse);
            }
        );
    };

    function deleteUser(id){
        UserService.deleteUser(id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while deleting User');
            }
        );
    };

    function submit() {
        if(self.user.id===null){
            console.log('Saving New User', self.user);
            createUser(self.user);
        }else{
            updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.user.id);
        }
        reset();
    };

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.users.length; i++){
            if(self.users[i].id === id) {
                self.user = angular.copy(self.users[i]);
                break;
            }
        }
    };

    function remove(id){
        console.log('id to be deleted', id);
        if(self.user.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteUser(id);
    };


    
    function recherche_userParType(usertype) {
    UserService.recherche_userParType(usertype)
    	.then(
    			function(response){
    				$scope.Conseillers = response;
    			},
    			function (errResponse){
    				console.error('Error while getting Notifications')
    			})
    };
    
    function getMyUserBack()
    {
    	self.user = JSON.parse(sessionStorage.getItem("currentUser"));
    }
    
  
    // Fonctions Client
    self.client={id:null,nom:'',prenom:''};
    self.clients;
    self.demandes = [];
    self.getDemandes = getDemandes;    
    self.notifications;
    self.searchClients = searchClients;
    self.getClient = getClient; 
    self.getNotifs = getNotifs;
    


 	
 	
    function searchClients() {
    	// je lance la recherche avec le nom et prenom du client et l'ID du conseiller
    	UserService.searchUser(self.client.prenom, self.client.nom, self.user.id)
		.then(
		function(d) {
			// je met les clients récupérés dans clients
			clients = JSON.parse(sessionStorage.getItem("Clients"));
		}, 
		function (errResponse){
			console.error('Error while getting clients');
		}
	);	
    };
    

  
    function getClient(){
    	// je récupère un client en fonction de son ID
    	UserService.getClient(self.client.id)
    	.then(
    			function(d){
    				client = JSON.parse(sessionStorage.getItem("Client"));
    			},
    			function (errResponse){
    				console.error('Error while getting a client from an ID')
    			});
    };
    
    function getNotifs(){
    	// je récupère les notifs associées à un client
    	UserService.getNotifs(JSON.parse(sessionStorage.getItem("currentUser")).id)
    	.then(
    			function(dataFromService){
    				notifications = JSON.parse(dataFromService);
    			},
    			function (errResponse){
    				console.error('Error while getting Notifications')
    			})
    };
    
    
    // Fonctions Conseiller
    self.conseiller={id:null,nom:'',prenom:'',matricule:''};
    self.conseillers = [];
    self.detailCompte = detailCompte;
    
    
    
  //Fonction du lien par ngclick dans le ng-repeat du recherche compte
   function detailCompte(iban){
  	 $location.path("/cons/Cons_DetailCompte/" + iban);
   }     
    
    
    function getDemandes(){
    	// je demande les demandes en fournissant l'id client et l'id conseiller
    	// ces valeurs peuvent être nulles dans ce cas on récupère les demandes d'admissions des nouveaux clients
    	UserService.getDemandes(self.client.id,self.conseiller.id)
    	.then(
    			function(d){
    				//je place les demandes récupérées dans demandes
    				demandes = JSON.parse(sessionStorage.getItem("Demandes"));	
    			},
    			function (errResponse){
    				console.error('Error while getting Clients request')
    			});
    };
    
  
    
    // Fonctions Admin
    self.creacons = creacons;
	
    /* Ajout d'un conseiller puis refresh de la liste des users */
   function creaCons(){
        UserService.creaCons2(self.user)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while creating User');
            }
        );
    };


	    
    // Fonctions Print
	 function printToCart(printSectionId){
          var innerContents = document.getElementById(printSectionId).innerHTML;
          var popupWindow = window.open('', '_blank', 'width=600,height=700,scrollbars=no,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
          popupWindow.document.open();
          popupWindow.document.write('<html><head><link rel="stylesheet" type="text/css" href="static/css/print.css" /></head><body >' + innerContents + '</html>');
          popupWindow.document.close();
        };

    

    function reset(){
        self.user={id:null,prenom:'',nom:'',adresse:'',mail:'',identifiant:'',motDePasse:''};
        $scope.myForm.$setPristine(); //reset Form
    };  
   
}]);
