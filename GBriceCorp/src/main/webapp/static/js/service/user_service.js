'use strict';

App.factory('UserService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/GestBank/user/';

    var factory = {
        fetchAllUsers: fetchAllUsers,
        updateUser:updateUser,
        deleteUser:deleteUser,
        connectUser:connectUser,
        recherche_userParType:recherche_userParType,
        creaCons2:creaCons2,
        getClients_Cons:getClients_Cons,
        getListeCons:getListeCons,
        getConsById:getConsById,
        updateCons:updateCons
    };

    var user = {
    		id:null,nom:'',prenom:'',adresse:'',mail:'',identifiant:'',motDePasse:'',typeUser:'',telephone:''
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
    
    function getListeCons() {
        var deferred = $q.defer();

        $http.get(REST_SERVICE_URI+'/Admin/Conseillers')
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
    
	
	 /* Ajout d'un conseiller puis refresh de la liste des users */
    function creaCons2(conseiller) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI +"ADMIN/creaCons", conseiller)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Conseiller');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    /* update un conseiller puis refresh de la liste des users/conseillers */
    function updateCons(cons, id) {
    	console.log('conseiller en update ' + cons);
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+"consEdit"+id, cons)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating Conseiller');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    /**
     * Demande au serveur un client par son ID
     */
    function getClients_Cons(idCons) {
    	var deferred = $q.defer();
    	$http.get(REST_SERVICE_URI+'Conseiller/'+idCons)
		.then(
		function (response){
			deferred.resolve(response.data);
		},
		function (errResponse){
			console.error('Error while getting client list du conseiller'+idCons);
		})
		return deferred.promise;
    }
    
    /**
     * Demande au serveur un conseiller par son ID
     */
    function getConsById(idCons) {
    	var deferred = $q.defer();
    	$http.get(REST_SERVICE_URI+'ADMIN/EditCons/'+idCons)
		.then(
		function (response){
			deferred.resolve(response.data);
		},
		function (errResponse){
			console.error('Error while getting conseiller by id'+idCons);
		})
		return deferred.promise;
    }
}]);
