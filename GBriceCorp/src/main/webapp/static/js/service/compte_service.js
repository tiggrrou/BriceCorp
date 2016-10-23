'use strict';

App.factory('CompteService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI_COMPTES = 'http://localhost:8080/GestBank/compte/';

    var factory = {
    	getComptesClient:getComptesClient,
    	virement:virement,
    	getAllComptesByCons:getAllComptesByCons,
    	findCompteById:findCompteById
    };
    
    return factory;
    
   
    
    
    function virement(compteDebiteurID, compteCrediteID, montant){

    	var deferred = $q.defer();
    	$http.post(REST_SERVICE_URI_COMPTES+'virement/'+compteDebiteurID+"&"+compteCrediteID+"&"+montant)
    	.then(
    			function (){
    				deferred.resolve();
    			},
    			function (){
    				deferred.reject();
    			})
    			return deferred.promise;
    }
    
    /**
     * Demande au serveur la liste des comptes d'un client par son ID
     */
    function getComptesClient(idClient) {
    	var deferred = $q.defer();
    	console.log(idClient);
    	$http.get(REST_SERVICE_URI_COMPTES+idClient)
		.then(
		function (response){
			deferred.resolve(response.data);
		},
		function (errResponse){
			console.error('Error while getting Account Data from server')
		})
		return deferred.promise;
    }
    
    
    function findCompteById(compte_id) {
    	var deferred = $q.defer();
    	$http.get(REST_SERVICE_URI_COMPTES+'byId/'+compte_id)
		.then(
		function (response){
			deferred.resolve(response.data);
		},
		function (errResponse){
			console.error('Error while getting Account Data from server')
		})
		return deferred.promise;
    } 
    
    
    function getAllComptesByCons(client_id) {
    	var deferred = $q.defer();
    	$http.get(REST_SERVICE_URI_COMPTES+'bycons/'+client_id)
		.then(
		function (response){
			deferred.resolve(response.data);
		},
		function (errResponse){
			console.error('Error while getting Account Data from server')
		})
		return deferred.promise;
    } 
  

}]);
