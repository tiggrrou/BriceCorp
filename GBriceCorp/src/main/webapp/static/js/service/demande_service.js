'use strict';

App.factory('DemandeService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/GestBank/demande/';

    var factory = {
    	fetchDemandesWithType:fetchDemandesWithType,
    	createDemandeInscription:createDemandeInscription,
        writeDemDecou:writeDemDecou,
        attributionConseiller:attributionConseiller,
        findDemandeById:findDemandeById,
        validation_CreationCompteClient:validation_CreationCompteClient,
        demandeNouveauCompte:demandeNouveauCompte,
        modifEtat_Demande:modifEtat_Demande,
        savefile:savefile,
        reaffectation:reaffectation
    };
    return factory;
 
    function savefile(fd, idDemande, nom, prenom, typeJustificatif){  
    	
    	 var deferred = $q.defer();

    $http.post("demande/fileupload/"+idDemande+"&"+nom+"&"+prenom+"&"+typeJustificatif, fd, {
        withCredentials: true,
        headers: {'Content-Type': undefined },
        transformRequest: angular.identity})
        .then(
            function (response) {
                deferred.resolve();
            },
            function(errResponse){
                console.error('Error while save file');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    
    	
    }  
    
    function reaffectation(clientID, newConsID)
    {
    	var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+'reaffectation/' + clientID + "&" + newConsID)
            .then(
            function (response) {
                deferred.resolve();
            },
            function(errResponse){
                console.error('Error while reaffectation');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
  function demandeNouveauCompte(demande_NouveauCompte, idClient){
    	 var deferred = $q.defer();
         $http.post(REST_SERVICE_URI+'creationCompteBancaire/' + idClient,demande_NouveauCompte)
             .then(
             function (response) {
                 deferred.resolve();
             },
             function(errResponse){
                 console.error('Error while request of new compte');
                 deferred.reject(errResponse);
             }
         );
         return deferred.promise;
    }
    
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
   
    function fetchDemandesWithType(type, idUser)
    {
    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+ type + '/' + idUser)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Demandes of type ' + type );
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
