'use strict';

App.factory('DemandeService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/GestBank/demande/';

    var factory = {
    		fetchAllDemandesModifCompte:fetchAllDemandesModifCompte,
    	fetchAllDemandesChequier:fetchAllDemandesChequier,
    	fetchAllDemandesInscription:fetchAllDemandesInscription,
    	createDemandeInscription:createDemandeInscription,
        writeDemDecou:writeDemDecou,
        getDemandes:getDemandes,
        attributionConseiller:attributionConseiller
    };
    return factory;
    
    
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
    function createDemandeInscription(client) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI+'inscription/', client)
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
    	$http.post(REST_SERVICE_URI_COMPTES+'decouvert/'+userID+"&"+compteID+"&"+decouvert)
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
    
    function getDemandes(idClient,idConseiller) {
    	var deferred = $q.defer();
    	$http.get(REST_SERVICE_URI_COMPTES+'demandes/'+idClient+"&"+idConseiller)
    		.then(
    		function (response){
    			sessionStorage.setItem("Demandes",JOSN.stringify(response.data));
    			deferred.resolve(response.data);
    		},
    		function (errResponse){
    			console.error('Error while getting clients request')
    		})
    		return deferred.promise;
    }
}]);
