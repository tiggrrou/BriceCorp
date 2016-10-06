'use strict';

App.factory('UserService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/GestBank/user/';

    var factory = {
        fetchAllUsers: fetchAllUsers,
        createUser: createUser,
        updateUser:updateUser,
        deleteUser:deleteUser,
        connectUser:connectUser,
        getComptesClient:getComptesClient,
        recherche_userParType:recherche_userParType,

    };
    
    var user = {
    		id:null,nom:'',prenom:'',adresse:'',codepostal:'',mail:'',identifiant:'',motDePasse:'',typeUser:'',telephone:''
    		};

    return factory;
    
    /*recherche de tous les utilisateurs */
    function fetchAllUsers() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    

    
    /* Ajout d'un user puis refresh de la liste des users */
    function createUser(user) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, user)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    /* update un user puis refresh de la liste des users */
    function updateUser(user, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, user)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    /**
     *  tente la connexion à un espace personnel 
     */
    function connectUser(login, pwd) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+'connect/'+login+"&"+ pwd)
            .then(
            function (response) {
            	sessionStorage.setItem("currentUser",JSON.stringify(response.data));
                deferred.resolve(response.data);
                // je suis co receptionner session + envoyer message à routeur
                console.log('eh eh connexion réussie');
                
//                var typeUser = JSON.parse(sessionStorage.getItem("currentUser")).typeUser;
//                var truc = sessionStorage.getItem("currentUser");
//                user = JSON.parse(truc);
//                console.log(user.motDePasse);
            },
            function(errResponse){
                console.error('Error while logging User' + errResponse.value);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function getDemandes(idClient,idConseiller) {
    	var deferred = $q.defer();
    	$http.get(REST_SERVICE_URI+'demandes/'+idClient+"&"+idConseiller)
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
    
    /**
     * Demande au serveur un client par son ID
     */
    function getClient(idClient) {
    	var deferred = $q.defer();
    	$http.get(REST_SERVICE_URI+'client/'+idClient)
		.then(
		function (response){
			sessionStorage.setItem("Client",JOSN.stringify(response.data));
			deferred.resolve(response.data);
		},
		function (errResponse){
			console.error('Error while getting client');
		})
		return deferred.promise;
    }
      
    /* supprime un user puis refresh de la liste des users */
    function deleteUser(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function recherche_userParType(TypeUser) {
        var deferred = $q.defer();

        $http.get(REST_SERVICE_URI+'Conseiller/'+ TypeUser)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    
}]);
