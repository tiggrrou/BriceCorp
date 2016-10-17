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
	 self.demandeNouveauCompte = demandeNouveauCompte;
	 self.uploadFile=uploadFile;   
	 self.uploadFile=uploadFile;
	 self.modifEtat_Demande=modifEtat_Demande;
	    
	    $scope.menuDemandesCons = [{"id":"creation", "valeur" :"Ouverture compte"},
	                               {"id":"chequier", "valeur" :"Chequier"},
	                               {"id":"modif", "valeur" :"Modification compte"}];
	    
	    $scope.value = $scope.menuDemandesCons[0].id;
 // Fonctions User
	 self.demande={};
	 self.demandes=[];

	 
	 function modifEtat_Demande(demande,nouvelEtat){
    	DemandeService.modifEtat_Demande(demande.id,nouvelEtat)
         .then(
                 function(d) {
                 	console.log(d)
                 	$location.path("cons/");
                 },
         function(errResponse){
             console.error('Error while fetching Demandes');
         }
     );	 
	 }
	 
	 
	 function demandeNouveauCompte(){
		 console.log("je fais une demande pour un nouveau compte");
 
		 //$scope.demandeCreationCompte.client = JSON.parse(sessionStorage.getItem("currentUser"));
		 $scope.demandeCreationCompte.decouvert =($scope.decouvert)? $scope.montantDecouvert : 0 ;
		 
		 DemandeService.demandeNouveauCompte($scope.demandeCreationCompte, JSON.parse(sessionStorage.getItem("currentUser")).id).then(
	 				function(){
	 					alert("demande de nouveau compte transmise au conseiller, retour a la liste des comptes");
	 					$location.path("cli/Cli_ListeComptes");
	 				},
	 				function (errResponse){
	 					console.error('Error while creating request of new compte')
	 				});
	 }
	 
	 //http://jsfiddle.net/JeJenny/ZG9re/	 
	 function uploadFile(){
		 var file = $scope.myFile;
	        console.log('file is ' + file );
	        console.dir(file);
 
	 }
	 
 	  function detailDemande(demande){
 		 console.log(demande.type);
				
 		 if(demande.type== 1){
 	 	   	 $location.path("/cons/Cons_DetailCompte/" + demande.id);
 		 }else if (demande.type == 2){
 	 	   	 $location.path("/cons/Cons_DetailCompte/" + demande.id); 
 		 }else if (demande.type == 3){
 	 	   	 $location.path("/cons/Cons_ValCheq/" + demande.id);
 		 }
 	    }     	

 	 

 	  
 	 if($routeParams.demande_id != null){	
 			for (var i = 1; i < JSON.parse(sessionStorage.getItem("currentUser")).demandes.length ; i++) {
 			   if(JSON.parse(sessionStorage.getItem("currentUser")).demandes[i].id == $routeParams.demande_id ){
 				  $scope.demande =  JSON.parse(sessionStorage.getItem("currentUser")).demandes[i]; 
 				  console.log($scope.demande)
 			   }
 			}
  	 }
 	 
 	 
 	 
 	 function validation_Demande(demande){
     	console.log("demande " + demande)
     	
     	var currentUser_id = JSON.parse(sessionStorage.getItem("currentUser")).id;
     	
 	if(demande.type == 1){
    	console.log("creation client")
	DemandeService.validation_CreationCompteClient(demande,currentUser_id)
        .then(
                function(d) {
                	console.log(d)
                },
        function(errResponse){
            console.error('Error while fetching Creation Client');
        }
    );	 
 	}
 	console.log("type de la demande" + demande.type )
 	
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
