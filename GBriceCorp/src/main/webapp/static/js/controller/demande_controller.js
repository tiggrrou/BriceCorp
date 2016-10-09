'use strict';

App.controller('DemandeController', ['$scope', '$location', '$resource', '$route', 'UserService', 'translationService', function($scope, $location, $resource, $route, UserService, translationService) {
	


	// General
	var self = this;
	
 // Fonctions User
 self.demande={clientID:'',compteID:'',decouvert:'',remunerateur:''};
 self.demandes=[];



  
 console.log("demandes");
    
    function fetchAllDemandes(){
        DemandesService.fetchAllDemandes()
            .then(
            function(d) {
                self.demandes = d;
                console.log(d);
            },
            function(errResponse){
                console.error('Error while fetching Demandes');
            }
        );
    };


   
}]);
