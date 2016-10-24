'use strict';

App.controller('DemandeController', ['$scope', '$location', '$resource', '$route', 'DemandeService', 'UserService', '$routeParams', function($scope, $location, $resource, $route, DemandeService, UserService,$routeParams) {
	

	$scope.tempUser = JSON.parse(sessionStorage.getItem("currentUser"));
	// General
	var self = this;
	 self.inscription_Client=inscription_Client;
	 self.fetchAllDemandes=fetchAllDemandes;
	 self.validation_Demande=validation_Demande;
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
	 self.trier_par=trier_par;
	 self.modifInfo=modifInfo;
	 self.users=[];
	 self.fetchAllDemandesInsAffectees = fetchAllDemandesInsAffectees;
	  self.genererPassword=genererPassword;
	 self.modifAffichRemuneration=modifAffichRemuneration;
	 self.modifAffichDecouvert=modifAffichDecouvert;
	 self.modifierRemuneration=modifierRemuneration;
	 self.modifierDecouvert=modifierDecouvert;
	 self.download=download;
	 
	 
	    $scope.menuDemandesCons = [{"id":"inscription", "valeur" :"Ouverture compte"},
	                               {"id":"chequier", "valeur" :"Chequier"},
	                               {"id":"modifinfo", "valeur" :"Modification Informations personnelle"},
	                               {"id":"modifcompte", "valeur" :"Modification compte"}];
	    
	    $scope.checkAffectations;
	    
	    $scope.value = $scope.menuDemandesCons[0].id;
	    $scope.valeur = $scope.menuDemandesCons[0].valeur;

	    $scope.client_id = $routeParams.client_id;
	    $scope.demande_id = $routeParams.demande_id;
	    $scope.demande_type = $routeParams.demande_type;	    
	    $scope.nom = $routeParams.nom;
	    $scope.prenom = $routeParams.prenom;
 // Fonctions User

	    $scope.demande={};
	    $scope.demandes=[];


	  function download(path,nomDuFichier){
			 DemandeService.download(path+nomDuFichier)
			 .then(
	                 function(data) {
	                	 console.log("ouverture du fichier");
	                	 var url = URL.createObjectURL(new Blob([data]));
	                     var a = document.createElement('a');
	                     a.href = url;
	                     a.download = nomDuFichier;
	                     a.target = '_blank';
	                     a.click();
	
	                 },
	         function(errResponse){
	             console.error('Erreur le download');
	         }
	     );	 
	  }
	 
	  function session_delete(){
		 	sessionStorage.clear();
				$location.path("/");
		 	location.reload();
		 	console.log("delete session");
		 };
		 
	  function modifInfo(){
		  var demande={"nom":$scope.tempUser.nom,"prenom":$scope.tempUser.prenom,
		             "telephone":$scope.tempUser.telephone,"mail":$scope.tempUser.mail,
		             "adresse":$scope.tempUser.adresse,"revenu":$scope.tempUser.revenu};	  

			 DemandeService.modifInfo(demande,$scope.currentUser.id)
			 .then(
	                 function() {
	                	 console.log("demande de modification dinfo perso en cours");
						$location.path("cli/");

	                 },
	         function(errResponse){
	             console.error('Erreur pendant la creation de la demande de modification d info perso');
	         }
	     );	 
	  }
	 
	 function modifAffichRemuneration(){
	    	$scope.modifierCompteRemuneration = !$scope.modifierCompteRemuneration
	    }
	    function modifAffichDecouvert(){
	    	$scope.modifierCompteDecouvert = !$scope.modifierCompteDecouvert
	    }	 

		 function modifierRemuneration(demande,tauxRemuneration,seuil)
		 {
			demande.compte.seuil = seuil;
		    demande.compte.tauxRemuneration = tauxRemuneration;
		    validation_Demande(demande)
		 }
		 
		 function modifierDecouvert(demande,tauxDecouvert,decouvert){
		   	demande.compte.decouvert = decouvert;
	    	demande.compte.tauxDecouvert = tauxDecouvert;
	       	validation_Demande(demande)
		 }
	    
	 
	    function trier_par(tri){
	    	//en cours d affinage
	    	if ($scope.tripar = tri){
	    		$scope.sens = !$scope.sens;		
	    	}
	    		$scope.tripar = tri;
	        }
	    

	 
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
	
	 function genererPassword(clientID)
	 {
		 DemandeService.genererPassword(clientID)
		 .then(
                 function() {
                	 console.log("Nouveau mot de passe envoye");
                	 session_delete();
                  },
         function(errResponse){
             console.error('Erreur pendant creation du nouveau mdp');
         }
     );	 
	 }
	 
	 function reaffectation(clientID, newConsID)
	 {
		 DemandeService.reaffectation(clientID, newConsID)
		 .then(
                 function() {
                	 console.log("ok pour la réaffectation");
                	// getConsById();
                	 $location.path("#/");
                 },
         function(errResponse){
             console.error('Erreur pendant la réaffectation');
         }
     );	 
	 }
	 
	 
	 
	 function getDemandesCreationClient(choixDemandes){
		 DemandeService.fetchDemandesWithType("inscription", choixDemandes)
         .then(
                 function(d) {
                	 self.demandeCreationClient = d;
                 },
         function(errResponse){
             console.error('Error while fetching creation request');
         }
     );	 
	 }
	 
	 function fetchAllDemandesInsAffectees(){
		 DemandeService.fetchAllDemandesInsAffectees()
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
		 //$scope.demandeCreationCompte.decouvert =($scope.decouvert)? $scope.montantDecouvert : 0 ;
		 
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
 			 $location.path("/cons/Demande_DetailCompte/" + demande.id + "&" + demande.type);
		 
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
 		console.log("1 "+ demande.type)
	DemandeService.validation_CreationCompteClient(demande,currentUser_id)
        .then(
                function(d) {
                	console.log(d);
                	$location.path("/cons/");	
                },
        function(errResponse){
            console.error('Error while Creation Client');
        }
    );	 
 	}else  	if(demande.type == 2){
 		demande.client.conseiller = null;
 		console.log("2 "+ demande.type)
    	DemandeService.validation_ModificationCompte(demande,currentUser_id)
            .then(
                    function(d) {
                    	console.log(d)
                    	$location.path("/cons/");
                    },
            function(errResponse){
                console.error('Error while Modification compte');
            }
        );	 
     	}else 	if(demande.type == 3){
     		demande.client.conseiller = null;
     		console.log("3 "+ demande.type)
        	DemandeService.validation_Chequier(demande,currentUser_id)
                .then(
                        function(d) {
                        	console.log(d)
                        	$location.path("/cons/");
                        },
                function(errResponse){
                    console.error('Error while Chequier');
                }
            );	 
         	}else 	if(demande.type == 4){
         		demande.client.conseiller = null;
         		console.log("4 "+ currentUser_id)
        	DemandeService.validation_ModificationInfoPerso(demande,currentUser_id)
                .then(
                        function(d) {
                        	console.log(d)
                        	$location.path("/cons/");
                        },
                function(errResponse){
                    console.error('Error while Modification info');
                }
            );	 
         	}


 	
	 };
 	 
 	


 	

 
	 function validation_attribution(id_demande,id_conseiller){
		 console.log("id_demande " + id_demande+ "id_conseiller " + id_conseiller)
	    	DemandeService.attributionConseiller(id_demande,id_conseiller)
            .then(
                    function(d) {
                    	console.log(d);
                    	getDemandesCreationClient (0);
                    	
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
    console.log(fd)	
if($scope.demande.client){
console.log("demandecompteclient")
	var id = $scope.demande.client.id
	var nom = $scope.demande.client.nom
	var prenom = $scope.demande.client.prenom
	var clientOuDemande = 1
	
}else if($scope.client){
	console.log("conseillercomptesclient")
	var id = $scope.client.id
	var nom = $scope.client.nom
	var prenom = $scope.client.prenom
	var clientOuDemande = 1

}else if($scope.demande_id){
	console.log("creation")
	var id = $scope.demande_id
	var nom = $scope.nom
	var prenom = $scope.prenom
	var clientOuDemande = 0

}else if($scope.currentUser){
	console.log("client")
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
     		   self.listeJustificatifs();
    			console.log(d)
    		},
    function(errResponse){
        console.error('Error while fetching demandes');
    }
);

};


   
}]);
