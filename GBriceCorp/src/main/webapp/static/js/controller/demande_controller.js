'use strict';

App.controller('DemandeController', ['$scope', '$location', '$resource', '$route', 'DemandeService', '$routeParams', function($scope, $location, $resource, $route, DemandeService,$routeParams) {
	


	// General
	var self = this;
	 self.inscription_Client=inscription_Client;
	 self.fetchAllDemandes=fetchAllDemandes;
self.validation_Demande=validation_Demande;
	 self.writeDemDecou=writeDemDecou;
	 self.validation_attribution=validation_attribution;
	    self.detailDemande=detailDemande;
	    
	    $scope.menuDemandesCons = [{"id":"creation", "valeur" :"Ouverture compte"},
	                               {"id":"chequier", "valeur" :"Chequier"},
	                               {"id":"modif", "valeur" :"Modification compte"}];
	    
	    $scope.value = $scope.menuDemandesCons[0].id;
 // Fonctions User
	 self.demande={};
	 self.demandes=[];


	 
 	  function detailDemande(demande){
 		 console.log(demande[0].type);
 		(demande[1]!=null)?demande[1].type:demande[0].type
 				
 				
 		 if(demande[0].type== 2){
 	 	   	 $location.path("/cons/Cons_DetailCompte/" + demande[0].id);
 		 }else if (demande[0].type == 1){
 	 	   	 $location.path("/cons/Cons_DetailCompte/" + demande[0].id); 
 		 }else if (demande[0].type == 3){
 	 	   	 $location.path("/cons/Cons_ValCheq/" + demande[0].id);
 		 }
 	    }     	
 	  	
 	 
 	 function validation_Demande(demande){
     	console.log(JSON.parse(sessionStorage.getItem("currentUser")).id)
     	
     	var currentUser_id = JSON.parse(sessionStorage.getItem("currentUser")).id;
 	if(demande[0].type == 1){
    	console.log("creation client")
	DemandeService.validation_CreationCompteClient(demande,currentUser_id)
        .then(
                function(d) {

                	console.log(d)
                },
        function(errResponse){
            console.error('Error while fetching Demandes');
        }
    );	 
 	}
	 };
 	 
 	
if($routeParams.demande_id){
 	DemandeService.findDemandeById($routeParams.demande_id)
 		.then(
 				function(d){
 					self.demande = d;
                	console.log(d)
 				},
 				function (errResponse){
 					console.error('Error while getting Clients request')
 				});
 	};
 	

 
	 function validation_attribution(id_demande,id_conseiller){
		 console.log("id_demande " + id_demande+ "id_conseiller " + id_conseiller)
	    	DemandeService.attributionConseiller(id_demande,id_conseiller)
            .then(
                    function(d) {
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
