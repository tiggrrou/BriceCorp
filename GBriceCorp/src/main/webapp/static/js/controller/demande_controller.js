'use strict';

App.controller('DemandeController', ['$scope', '$location', '$resource', '$route', 'DemandeService', function($scope, $location, $resource, $route, DemandeService) {
	


	// General
	var self = this;
	 self.inscription_Client=inscription_Client;
	 self.fetchAllDemandes=fetchAllDemandes;
	 self.getDemandes=getDemandes;
	 self.writeDemDecou=writeDemDecou;
	 self.validation_attribution=validation_attribution;
 // Fonctions User
	 self.demande={clientID:'',compteID:'',decouvert:'',remunerateur:''};
	 self.demandes=[];

	 
	 
	 
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
        }
);
	
}
    
    
    };

    self.client={id:null,nom:'',prenom:'',adresse:'',mail:'',login:'',mdp:'',telephone:'',revenus:'',dateOuverture:null,dateCloture:null,conseillerID:null};
    self.clients=[];
  function inscription_Client() {

    	
        console.log('Saving New Client', self.client);
            
        DemandeService.createDemandeInscription(self.client)
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

function getDemandes(){
	// je demande les demandes en fournissant l'id client et l'id conseiller
	// ces valeurs peuvent être nulles dans ce cas on récupère les demandes d'admissions des nouveaux clients
	DemandeService.getDemandes(self.client.id,self.conseiller.id)
	.then(
			function(d){
				//je place les demandes récupérées dans demandes
				demandes = JSON.parse(sessionStorage.getItem("Demandes"));	
			},
			function (errResponse){
				console.error('Error while getting Clients request')
			});
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
