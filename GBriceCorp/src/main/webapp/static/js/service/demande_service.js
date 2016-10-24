'use strict';

App.factory('DemandeService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/GestBank/demande/';

    var factory = {
    	fetchDemandesWithType:fetchDemandesWithType,
    	createDemandeInscription:createDemandeInscription,
        writeDemDecou:writeDemDecou,
        writeDemRemu:writeDemRemu,
        writeDemChequier:writeDemChequier,
        attributionConseiller:attributionConseiller,
        findDemandeById:findDemandeById,
        validation_CreationCompteClient:validation_CreationCompteClient,
        validation_Chequier:validation_Chequier,
        validation_ModificationCompte:validation_ModificationCompte,
        validation_ModificationInfoPerso:validation_ModificationInfoPerso,
        demandeNouveauCompte:demandeNouveauCompte,
        modifEtat_Demande:modifEtat_Demande,
        savefile:savefile,
        reaffectation:reaffectation,
        listeJustificatifs:listeJustificatifs,
        modifInfo:modifInfo,
        fetchAllDemandesInsAffectees:fetchAllDemandesInsAffectees,
        genererPassword:genererPassword
    };
    return factory;

    
    function fetchAllDemandesInsAffectees ()
    {
    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+'inscriptionsAffectees')
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetchAllDemandesInsAffectees');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function modifInfo(demande,clientID){
   	 var deferred = $q.defer();
        $http.post(REST_SERVICE_URI+'createmodifinfoperso/' + clientID,demande)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while request of new compte');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
   }
   
        function genererPassword(clientID){

    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+'motdepasse/' + clientID)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating nouveau mdp' );
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    	
    }
    
    function listeJustificatifs(id, clientOuDemande){

    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+'justificatif/' + id+"&"+clientOuDemande)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching listeJustificatifs' );
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    	
    }
    
    function savefile(fd, id, nom, prenom, typeJustificatif, clientOuDemande){  
    	 var deferred = $q.defer();

    $http.post("demande/fileupload/"+id+"&"+nom+"&"+prenom+"&"+typeJustificatif+"&"+clientOuDemande, fd, {
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
    
    
    

    function validation_Chequier(demande,id_conseiller){
    	  var deferred = $q.defer();
          $http.put(REST_SERVICE_URI+'validationchequier/'+id_conseiller,demande)
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
    function validation_ModificationCompte(demande,id_conseiller){
    	  var deferred = $q.defer();
          $http.put(REST_SERVICE_URI+'validationmodifcompte/'+id_conseiller,demande)
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
    function validation_ModificationInfoPerso(demande,id_conseiller){
    	console.log(demande)
    	  var deferred = $q.defer();
          $http.put(REST_SERVICE_URI+'validationmodifinfoperso/'+id_conseiller,demande)
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
    
    function writeDemDecou (userID, compteID, demDecouvert) {
    	var deferred = $q.defer();
    	$http.post(REST_SERVICE_URI+'decouvert/'+userID+"&"+compteID, demDecouvert)
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
    
    function writeDemChequier (userID, compteID) {
    	var deferred = $q.defer();
    	$http.post(REST_SERVICE_URI+'chequier/'+userID+"&"+compteID)
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
    
    function writeDemRemu (userID, compteID) {
    	var deferred = $q.defer();
    	$http.post(REST_SERVICE_URI+'remuneration/'+userID+"&"+compteID)
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
