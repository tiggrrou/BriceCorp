'use strict';

App.factory('DemandeService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/GestBank/demande/';

    var factory = {
        fetchAllDemande: fetchAllDemande
    };

    var demande = {
    		clientID:'',compteID:'',decouvert:'',remunerateur:''
    		};

    return factory;
    
    /*recherche de tous les utilisateurs */
    function fetchAllDemandes() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Demandes');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    

}]);
