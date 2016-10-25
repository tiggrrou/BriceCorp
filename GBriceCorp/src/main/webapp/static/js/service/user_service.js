'use strict';

App.factory('UserService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://192.168.200.50:8080/GestBank/user/';

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
        updateCons:updateCons,
        populate_dummy:populate_dummy,
        refreshUser:refreshUser,
        deleteCons:deleteCons,
        getClient:getClient,
        getClientByCompteId:getClientByCompteId,
        checkCreaCons:checkCreaCons,
        switchNotif:switchNotif,
        deleteNotif:deleteNotif,
        getNotifs:getNotifs
    };

    var user = {
    		id:null,nom:'',prenom:'',adresse:'',mail:'',identifiant:'',motDePasse:'',typeUser:'',telephone:''
    		};

    return factory;
    
    
    function getNotifs(userID)
    {
    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+ "getnotif/" + userID)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching notifs');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    
    function switchNotif(userID, notifSwitched)
    {
    	var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+ "switch/" + userID, notifSwitched)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while switching notifs');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteNotif(userID, notifSwitched)
    {
    	var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+ "deleteNotif/" + userID, notifSwitched)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Notif');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function checkCreaCons(consID)
    {
    	 var deferred = $q.defer();
         $http.post(REST_SERVICE_URI+ "check/" + consID)
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
    
    

    
    
    function refreshUser( id) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while refreshing CurrentUser');
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
    

      
    /**
     * Demande au serveur un client par son ID
     */
    function getClientByCompteId(compte_id) {
    	var deferred = $q.defer();
    	$http.get(REST_SERVICE_URI+'compte/'+compte_id)
		.then(
		function (response){
			deferred.resolve(response.data);
		},
		function (errResponse){
			console.error('Error while getting client'+compte_id);
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
			deferred.resolve(response.data);
		},
		function (errResponse){
			console.error('Error while getting client'+idClient);
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
    deleteCons

    function deleteCons(consID) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+"delCons" + consID)
            .then(
            function (response) {
                deferred.resolve();
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
    function updateCons(cons) {
    	console.log('conseiller en update ' + cons);
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+"consEdit", cons)
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
    	$http.get(REST_SERVICE_URI+'ADMIN/Cons/'+idCons)
		.then(
		function (response){
			deferred.resolve(response.data);
		},
		function (errResponse){
			console.error('Error while getting conseiller by id'+idCons);
		})
		return deferred.promise;
    }
    
    
    
    /* Ajout d'un conseiller puis refresh de la liste des users */
    function populate_dummy() {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI +"Dummy")
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while populate Dummy');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
}]);
