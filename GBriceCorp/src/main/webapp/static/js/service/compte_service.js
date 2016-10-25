'use strict';

App.factory('CompteService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI_COMPTES = 'http://192.168.200.50:8080/GestBank/compte/';

    var factory = {
    	getComptesClient:getComptesClient,
    	virement:virement,
    	getAllComptesByCons:getAllComptesByCons,
    	findCompteById:findCompteById,
    	filtreListeMouvement:filtreListeMouvement
    };
    
    return factory;
    
   
    
    function filtreListeMouvement(compte_id, datelimite){

    	var deferred = $q.defer();
    	$http.get(REST_SERVICE_URI_COMPTES+'mouvements/'+compte_id+"&"+datelimite)
    	.then(
    			function (response){
    				deferred.resolve(response.data);
    			},
    			function (errResponse){
    				console.error('Error while fetching mouvements')
    			})
    			return deferred.promise;
    }
    
    
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
