'use strict';

App.controller('CompteController', ['$scope','$location', 'CompteService', function($scope,$location, CompteService) {
    var self = this;
    self.compte={id:null,nom:'',adresse:'',mail:'',identifiant:'',motDePasse:''};
    self.comptes=[];

    self.client={id:null,nom:'',prenom:''};
    self.clients=[];
    
    self.conseiller={id:null,nom:'',prenom:'',matricule:''};
    self.conseillers = [];
    
    self.demandes = [];
    
    self.notifications = [];
    
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    self.connect = connect;
    self.searchClients = searchClients;
    self.getDemandes = getDemandes;
    self.getClient = getClient;
    self.getNotifs = getNotifs;

    
  
    

  
    if(sessionStorage.getItem("currentUser") == null)
    {
    	console.log("pas de session");
    	$scope.connexion_cache=false;
    	$scope.admin_cache=true;
    	$scope.conseiller_cache=true;
    	$scope.client_cache=true;
    }
    else 
    {
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
		else if(utilisateur.typeuser == 3)
		{
			$scope.client_cache=false;
		} 
    }
    
    $scope.session_delete = function() {
    	sessionStorage.removeItem('currentuser');
		$location.path("/");
    	location.reload();
    	console.log("delete session");
    };
    

        $scope.printToCart = function(printSectionId) {
          var innerContents = document.getElementById(printSectionId).innerHTML;
          var popupWindow = window.open('', '_blank', 'width=600,height=700,scrollbars=no,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
          popupWindow.document.open();
          popupWindow.document.write('<html><head><link rel="stylesheet" type="text/css" href="static/css/print.css" /></head><body >' + innerContents + '</html>');
          popupWindow.document.close();
        }

    
    
    fetchAllComptes();

    function connexion(login, pwd){
    	CompteService.connectCompte(login, pwd)
    		.then(
    		function(d) {
    			var compte = JSON.parse(sessionStorage.getItem("currentCompte"));
    			var compteType = (compte == null)? 0 : compte.typeCompte;
    			switch(compteType){
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
    			console.log("mon d est juste au dessus")
    	        location.reload();
    		}, 
    		function (errResponse){
    			console.error('Error while connection');
    		}
    	);

    }
    
    function fetchAllComptes(){
        CompteService.fetchAllComptes()
            .then(
            function(d) {
                self.comptes = d;
            },
            function(errResponse){
                console.error('Error while fetching Comptes');
            }
        );
    }

    function createCompte(compte){
        CompteService.createCompte(compte)
            .then(
            fetchAllComptes,
            function(errResponse){
                console.error('Error while creating Compte');
            }
        );
    }

    function updateCompte(compte, id){
        CompteService.updateCompte(compte, id)
            .then(
            fetchAllComptes,
            function(errResponse){
                console.error('Error while updating Compte' + errResponse);
            }
        );
    }

    function deleteCompte(id){
        CompteService.deleteCompte(id)
            .then(
            fetchAllComptes,
            function(errResponse){
                console.error('Error while deleting Compte');
            }
        );
    }

    function submit() {
        if(self.compte.id===null){
            console.log('Saving New Compte', self.compte);
            createCompte(self.compte);
        }else{
            updateCompte(self.compte, self.compte.id);
            console.log('Compte updated with id ', self.compte.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.comptes.length; i++){
            if(self.comptes[i].id === id) {
                self.compte = angular.copy(self.comptes[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.compte.id === id) {//clean form if the compte to be deleted is shown there.
            reset();
        }
        deleteCompte(id);
    }


    function reset(){
        self.compte={id:null,nom:'',adresse:'',mail:'',identifiant:'',motDePasse:''};
        $scope.myForm.$setPristine(); //reset Form
    }

    function connect() {
    	connexion(self.compte.identifiant, self.compte.motDePasse);
    }
    
    function searchClients() {
    	// je lance la recherche avec le nom et prenom du client et l'ID du conseiller
    	CompteService.searchCompte(self.client.prenom, self.client.nom, self.compte.id)
		.then(
		function(d) {
			// je met les clients récupérés dans clients
			clients = JSON.parse(sessionStorage.getItem("Clients"));
		}, 
		function (errResponse){
			console.error('Error while getting clients');
		}
	);	
    }
    
    function getDemandes(){
    	// je demande les demandes en fournissant l'id client et l'id conseiller
    	// ces valeurs peuvent être nulles dans ce cas on récupère les demandes d'admissions des nouveaux clients
    	CompteService.getDemandes(self.client.id,self.conseiller.id)
    	.then(
    			function(d){
    				//je place les demandes récupérées dans demandes
    				demandes = JSON.parse(sessionStorage.getItem("Demandes"));	
    			},
    			function (errResponse){
    				console.error('Error while getting Clients request')
    			});
    }
    
    function getClient(){
    	// je récupère un client en fonction de son ID
    	CompteService.getClient(self.client.id)
    	.then(
    			function(d){
    				client = JSON.parse(sessionStorage.getItem("Client"));
    			},
    			function (errResponse){
    				console.error('Error while getting a client from an ID')
    			});
    }
  
    function getNotifs(){
    	// je récupère les notifs associées à un client
    	CompteService.getNotifs(self.client.id)
    	.then(
    			function(d){
    				notifications = JSON.parse(sessionStorage.getItem("Notifications"));
    			},
    			function (errResponse){
    				console.error('Error while getting Notifications')
    			})
    }
}]);
