'use strict';

App.factory('DemandeService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/GestBank/demande/';

    var factory = {
    		fetchAllDemandesModifCompte:fetchAllDemandesModifCompte,
    	fetchAllDemandesChequier:fetchAllDemandesChequier,
    	fetchAllDemandesInscription:fetchAllDemandesInscription,
    	createDemandeInscription:createDemandeInscription,
        writeDemDecou:writeDemDecou,
        attributionConseiller:attributionConseiller,
        findDemandeById:findDemandeById,
        validation_CreationCompteClient:validation_CreationCompteClient,
        modifEtat_Demande:modifEtat_Demande
    };
    return factory;
 
    
    function modifEtat_Demande(demandeid,nouvelEtat){
    	  var deferred = $q.defer();
          $http.put(REST_SERVICE_URI+'modifEtat_Demande/'+demandeid+"&"+nouvelEtat)
              .then(
              function (response) {
                  deferred.resolve(response.data);
              },
              function(errResponse){
                  console.error('Error while fetching Demandes inscription');
                  deferred.reject(errResponse);
              }
          );
          return deferred.promise;
    	
    }
      
    
    
    function validation_CreationCompteClient(demande,id_conseiller){
  	  var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+'validationcreation/'+id_conseiller,demande)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Demandes inscription');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
  	
  }
    
    function attributionConseiller(id_demande,id_conseiller){
    	
    	  var deferred = $q.defer();
          $http.put(REST_SERVICE_URI+'attribution/'+id_demande+"&"+id_conseiller)
              .then(
              function (response) {
                  deferred.resolve(response.data);
              },
              function(errResponse){
                  console.error('Error while fetching Demandes inscription');
                  deferred.reject(errResponse);
              }
          );
          return deferred.promise;
    	
    }
    /*recherche de toutes les demandes de modification de compte */
    function fetchAllDemandesModifCompte() {

        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+'modifcompte/')
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Demandes modification de compte');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    /*recherche de toutes les demandes d inscription */
    function fetchAllDemandesInscription(value) {

        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+'inscription/'+value)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Demandes inscription');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    /*recherche de toutes les demandes de chequier */
    function fetchAllDemandesChequier() {

        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+'chequier/')
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Demandes chequier');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    /* Ajout d'une demande d'inscription d'un client */
    function createDemandeInscription(demande_inscription) {
    	console.log(demande_inscription)
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI+'inscription/', demande_inscription)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Client');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function writeDemDecou (userID, compteID, decouvert) {
    	var deferred = $q.defer();
    	$http.post(REST_SERVICE_URI+'decouvert/'+userID+"&"+compteID+"&"+decouvert)
    	.then(
    			//reponse OK du serveur
    			function (){
    				deferred.resolve();
    			},
    			//reponse pas cool du serveur
    			function(reponseServeur){
    				deferred.reject(reponseServeur);
    			}
    	)
    	return deferred.promise;
    } 
    
    function findDemandeById(idDemande) {
    	console.log(idDemande)
    	var deferred = $q.defer();
    	$http.get(REST_SERVICE_URI+idDemande)
    		.then(
    		function (response){
    			deferred.resolve(response.data);
    		},
    		function (errResponse){
    			console.error('Error while getting clients request')
    		})
    		return deferred.promise;
    }
}]);
