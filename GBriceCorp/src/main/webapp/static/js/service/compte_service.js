'use strict';

App.factory('UserService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI_COMPTES = 'http://localhost:8080/SpringAngularStartProject/compte/';

    var factory = {
     /*   fetchAllUsers: fetchAllUsers,
        createUser: createUser,
        updateUser:updateUser,
        deleteUser:deleteUser,
        connectUser:connectUser*/
    	getComptesClient:getComptesClient,
    	virement:virement //A FAIRE
    };
    
    return factory;
    
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
    } 
    
    /**
     * Demande au serveur la liste des comptes d'un client par son ID
     */
    function getComptesClient(idClient) {
    	var deferred = $q.defer();
    	$http.get(REST_SERVICE_URI+idClient)
		.then(
		function (response){
			deferred.resolve(response.data);
		},
		function (errResponse){
			console.error('Error while getting Account Data from server')
		})
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
