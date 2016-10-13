'use strict';

App.controller('DemandeController', ['$scope', '$location', '$resource', '$route', 'DemandeService', '$routeParams', function($scope, $location, $resource, $route, DemandeService,$routeParams) {
	


	// General
	var self = this;
	 self.inscription_Client=inscription_Client;
	 self.fetchAllDemandes=fetchAllDemandes;

	 self.writeDemDecou=writeDemDecou;
	 self.validation_attribution=validation_attribution;
	    self.detailDemande=detailDemande;
	    


  	  
 // Fonctions User
	 self.demande={};
	 self.demandes=[];


	 
 	  function detailDemande(demande_type, demande_id){
 		 console.log(demande_type);
 		 if(demande_type== "modif"){
 	 	   	 $location.path("/cons/Cons_DetailCompte/" + demande_id);
 		 }else if (demande_type == "creation"){
 	 	   	 $location.path("/cons/Cons_DetailCompte/" + demande_id); 
 		 }else if (demande_type == "chequier"){
 	 	   	 $location.path("/cons/Cons_ValCheq/" + demande_id);
 		 }
 	    }     	
 	  	
 		console.log("routeparam" + $routeParams.demande_id)
 	 function findDemandeById(){
	console.log($routeParams.demande_id)
 	DemandeService.findDemandeById($routeParams.demande_id)
 		.then(
 				function(d){
 					demandes = d;
                	console.log(d)
 				},
 				function (errResponse){
 					console.error('Error while getting Clients request')
 				});
 	};
 	

	 self.demande_inscription={clientID:0,conseillerId:0,nom:'',prenom:'',mail:'',adresse:'',telephone:0,revenu:0};
	 
	 function validation_attribution(id_demande,id_conseiller){
		 console.log("id_demande " + id_demande+ "i d_conseiller " + id_conseiller)
	    	DemandeService.attributionConseiller(id_demande,id_conseiller)
            .then(
                    function(d) {
                    	fetchAllDemandes("admin")
                    	console.log(d)
                    },
            function(errResponse){
                console.error('Error while fetching Demandes');
            }
        );	 
	 }

    
    function fetchAllDemandes(value){
    	$scope.demande_type = "";
console.log(value)
self.demandes = '';
    if(value == "modif"){
    	DemandeService.fetchAllDemandesModifCompte()
            .then(
            function(d) {
            	$scope.demande_type = "modif";
            	self.demandes = d;
            	console.log(d)
            },
            function(errResponse){
                console.error('Error while fetching Demandes');
            }
        );
    }else if (value == "creation"){
    	DemandeService.fetchAllDemandesInscription(JSON.parse(sessionStorage.getItem("currentUser")).id)
	        .then(
	        function(d) {
            	$scope.demande_type = "creation";
	        	self.demandes = d;
	            console.log(d)
	        },
	        function(errResponse){
	            console.error('Error while fetching Demandes');
	        }
    );
    }else if (value == "chequier"){
    	DemandeService.fetchAllDemandesChequier()
	        .then(
	        function(d) {
            	$scope.demande_type = "chequier";
	        	self.demandes = d;
	            console.log(d)
	        },
	        function(errResponse){
	            console.error('Error while fetching Demandes');
	        }
    );
    	
    }else if (value == "admin"){
    	DemandeService.fetchAllDemandesInscription(0)
        .then(
        function(d) {
        	$scope.demande_type = "admin";
        	self.demandes = d;
            console.log(d)
        },
        function(errResponse){
            console.error('Error while fetching Demandes');
        });}    };


  function inscription_Client() {
  
	  
        console.log(self.demande_inscription);
        DemandeService.createDemandeInscription(self.demande_inscription)
        .then(
        		function(d) {
        			// envoi d'un mail de confirmation de demande
        		},
        function(errResponse){
            console.error('Error while creating Client');
        }
    );
    window.history.back();
};



function writeDemDecou() {
	DemandeService.writeDemDecou(JSON.parse(sessionStorage.getItem("User").id), compte.id, decouvert)
			.then(
					function(){
						// cas normal, pop up ?
						alert("Votre demande a bien été transmise")
					},
					function(messageErreurFromService){
						alert("Une erreur est survenue pendant la création de votre demande\n" + messageErreurFromService )
					})
};
   
}]);
