'use strict';

App.controller('DemandeController', ['$scope', '$location', '$resource', '$route', 'DemandeService', 'UserService', '$routeParams', function($scope, $location, $resource, $route, DemandeService, UserService,$routeParams) {
	


	// General
	var self = this;
	 self.inscription_Client=inscription_Client;
	 self.fetchAllDemandes=fetchAllDemandes;
	 self.validation_Demande=validation_Demande;
	 self.writeDemDecou=writeDemDecou;
	 self.validation_attribution=validation_attribution;
	 self.detailDemande=detailDemande;
	 self.demandeNouveauCompte = demandeNouveauCompte;
	 self.modifEtat_Demande=modifEtat_Demande;
	 self.getDemandesCreationClient = getDemandesCreationClient;
	 self.getConsById = getConsById;
	 self.reaffectation = reaffectation;
	 self.listeJustificatifs=listeJustificatifs;
	 self.findDemandeById=findDemandeById;
	 self.getClient=getClient;

	 
	 
	    $scope.menuDemandesCons = [{"id":"inscription", "valeur" :"Ouverture compte"},
	                               {"id":"chequier", "valeur" :"Chequier"},
	                               {"id":"modifcompte", "valeur" :"Modification compte"}];
	    
	    $scope.value = $scope.menuDemandesCons[0].id;
	    $scope.valeur = $scope.menuDemandesCons[0].valeur;

	    $scope.client_id = $routeParams.client_id;
	    $scope.demande_id = $routeParams.demande_id;
	    $scope.nom = $routeParams.nom;
	    $scope.prenom = $routeParams.prenom;
 // Fonctions User

	    $scope.demande={};
	    $scope.demandes=[];
	 self.demandeCreationClient;

	 	/* Recherche d'un conseiller par son ID */
		function getConsById(){
	    	// je récupère un client en fonction de son ID
			if ($routeParams.consId != null){
	    	UserService.getConsById($routeParams.consId)
	    	.then(
	    			function(d){
	    				$scope.cons = d;
	    			},
	    			function (errResponse){
	    				console.error('Error while getting a cons from an ID')
	    			});
			}
	    };
	 
	 function reaffectation(clientID, newConsID)
	 {
		 DemandeService.reaffectation(clientID, newConsID)
		 .then(
                 function() {
                	 console.log("ok pour la réaffectation");
                	 getConsById();
                 },
         function(errResponse){
             console.error('Erreur pendant la réaffectation');
         }
     );	 
	 }
	 
	 
	 
	 function getDemandesCreationClient(){
		 DemandeService.fetchDemandesWithType("inscription", 0)
         .then(
                 function(d) {
                	 self.demandeCreationClient = d;
                 },
         function(errResponse){
             console.error('Error while fetching creation request');
         }
     );	 
	 }
	 
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
	 

	 
 	 function detailDemande(demande){
 			 $location.path("/cons/Demande_DetailCompte/" + demande.id);
		 
  	 }     	


     function getClient(client_id){
     	UserService.getClient(client_id)
     	.then(
     			function(d){
     				$scope.client = d;
     			},
     			function (errResponse){
     				console.error('Error while getting a client from an ID')
     			});
     };

 	 
 	 
 	 function validation_Demande(demande){
       	var currentUser_id = JSON.parse(sessionStorage.getItem("currentUser")).id;
 	if(demande.type == 1){
	DemandeService.validation_CreationCompteClient(demande,currentUser_id)
        .then(
                function(d) {
                	console.log(d)
                },
        function(errResponse){
            console.error('Error while fetching Creation Client');
        }
    );	 
 	}else  	if(demande.type == 3){
    	DemandeService.validation_CreationCompteClient(demande,currentUser_id)
            .then(
                    function(d) {
                    	console.log(d)
                    },
            function(errResponse){
                console.error('Error while fetching Creation Client');
            }
        );	 
     	}else 	if(demande.type == 1){
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
 	 $location.path("/cons/");	
 	console.log("type de la demande" + demande.type )
 	
	 };
 	 
 	


 	

 
	 function validation_attribution(id_demande,id_conseiller){
		 console.log("id_demande " + id_demande+ "id_conseiller " + id_conseiller)
	    	DemandeService.attributionConseiller(id_demande,id_conseiller)
            .then(
                    function(d) {
                    	console.log(d);
                    	getDemandesCreationClient ();
                    	
                    },
            function(errResponse){
                console.error('Error while fetching Demandes');
            }
        );	 
	 }

    
    function fetchAllDemandes(value){
    	var i=0;
    	var len =$scope.menuDemandesCons.length;
        for (; i<len; i++) {
          if ($scope.menuDemandesCons[i].id == $scope.value) {
            $scope.valeur = $scope.menuDemandesCons[i].valeur;
          }
        }
    	DemandeService.fetchDemandesWithType(value, JSON.parse(sessionStorage.getItem("currentUser")).id)
        .then(
        		function(d) {
        			self.demandes = d;
        			console.log(d)
        		},
        		function(errResponse){
        			console.error('Error while fetching Demandes');
        		}
        	);
    };


  function inscription_Client() {
        console.log(self.demande_inscription);
        DemandeService.createDemandeInscription(self.demande_inscription)
        .then(
        		function(d) {
        			console.log(d)
        			$location.path("Fin_Inscription/" + d.id + "&" + d.nom + "&" + d.prenom);

        		},
        function(errResponse){
            console.error('Error while creating Client');
        }
    );
    window.history.back();
};


$scope.uploadFile = function(files, typeJustificatif) {
    var fd = new FormData();
    //Take the first selected file
    fd.append("file", files[0]);
    
	var file = fd
	
if($scope.demande.client){

	var id = $scope.demande.client.id
	var nom = $scope.demande.client.nom
	var prenom = $scope.demande.client.prenom
	var clientOuDemande = 1
}else if($scope.client){

	var id = $scope.client.id
	var nom = $scope.client.nom
	var prenom = $scope.client.prenom
	var clientOuDemande = 1

}else if($scope.demande_id){

	var id = $scope.demande_id
	var nom = $scope.nom
	var prenom = $scope.prenom
	var clientOuDemande = 0

}else if($scope.currentUser){

	var id = $scope.currentUser.id
	var nom = $scope.currentUser.nom
	var prenom = $scope.currentUser.prenom
	var clientOuDemande = 1

} 

    console.log("info "+id + nom + prenom + clientOuDemande)
    DemandeService.savefile(file, id, nom, prenom, typeJustificatif, clientOuDemande)
    .then(
    	function(d) {
    		if (typeJustificatif == "Domicile"){
    			$scope.Domicile = true
    		}else if (typeJustificatif == "Impot"){
    			$scope.Impot = true
    		}
    		   self.listeJustificatifs();
    	},
    function(errResponse){
        console.error('Error while uploading file');
    }
		
);
}

function listeJustificatifs() {

	if($scope.demande.client){
		var id = $scope.demande.client.id
		var clientOuDemande = 1
		
	}else if($scope.client){
		var id = $scope.client.id
		var clientOuDemande = 1

	}else if($scope.demande_id){
		var id = $scope.demande_id
		var clientOuDemande = 0

	}else if($scope.currentUser){
		var id = $scope.currentUser.id
		var clientOuDemande = 1

	} 
	
    DemandeService.listeJustificatifs(id, clientOuDemande)
    .then(
    		function(d) {
    			$scope.justificatifs = d
    		},
    function(errResponse){
        console.error('Error while fetching justificatifs');
    }
);

};



function findDemandeById(id) {
    DemandeService.findDemandeById(id)
    .then(
    		function(d) {
    			$scope.demande = d;
    			console.log(d)
    		},
    function(errResponse){
        console.error('Error while fetching demandes');
    }
);

};

function writeDemDecou() {
	DemandeService.writeDemDecou(JSON.parse(sessionStorage.getItem("currentUser")).id, compte.id, decouvert)
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
