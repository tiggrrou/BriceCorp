'use strict';

App.controller('UserController', ['$scope','$location', 'UserService', function($scope,$location, UserService) {
    var self = this;
    self.user={id:null,nom:'',adresse:'',mail:'',identifiant:'',motDePasse:''};
    self.users=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    self.connect = connect;

    
  
    

  
    if(sessionStorage.getItem("currentUser") == null){
console.log("pas de session");
    	$scope.connexion_cache=false;
    	$scope.admin_cache=true;
    	$scope.conseiller_cache=true;
    	$scope.client_cache=true;
    }else {
		console.log("une session existe");
		var utilisateur = JSON.parse(sessionStorage.getItem("currentUser"));
		console.log("utilisateur.typeUser" +utilisateur.typeUser);
		console.log("utilisateur.nom" +utilisateur.nom);
    
	if(utilisateur.typeUser == 1){
    	$scope.connexion_cache=true;
    	$scope.admin_cache=false;
    	$scope.conseiller_cache=true;
    	$scope.client_cache=true;
	}else if(utilisateur.typeUser == 2){
		$scope.connexion_cache=true;
		$scope.admin_cache=true;
		$scope.conseiller_cache=false;
		$scope.client_cache=true;
	}else if(utilisateur.typeUser == 3){
		$scope.connexion_cache=true;
		$scope.admin_cache=true;
		$scope.conseiller_cache=true;
		$scope.client_cache=false;
	} 

    }
    
    $scope.session_delete = function() {
    	//delete sessionStorage.getItem("currentUser");
    	sessionStorage.removeItem('currentUser');
        location.reload();
    	console.log("delete session");
    };
    

    fetchAllUsers();

    function connexion(login, pwd){
    	UserService.connectUser(login, pwd)
    		.then(
    		function(d) {
    			var user = JSON.parse(sessionStorage.getItem("currentUser"));
    			var userType = (user == null)? 0 : user.typeUser;
    			switch(userType){
    			case 0 : 
    				$location.path("/");
    				break;
    			case 1 : 
    				$location.path("/admin/");
    				break;
    			case 2 : 
    				$location.path("/cons/");
    				break;    			
    			case 3 : 
    				$location.path("/cli/");
    				break;    			
    			}
    			$location.path("/")
    			console.log(d);
    			console.log("mon d est juste au dessus")
    	        location.reload();
    		}, 
    		function (errResponse){
    			console.error('Error while connection');
    		}
    	);

    }
    
    function fetchAllUsers(){
        UserService.fetchAllUsers()
            .then(
            function(d) {
                self.users = d;
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }

    function createUser(user){
        UserService.createUser(user)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while creating User');
            }
        );
    }

    function updateUser(user, id){
        UserService.updateUser(user, id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while updating User' + errResponse);
            }
        );
    }

    function deleteUser(id){
        UserService.deleteUser(id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while deleting User');
            }
        );
    }

    function submit() {
        if(self.user.id===null){
            console.log('Saving New User', self.user);
            createUser(self.user);
        }else{
            updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.user.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.users.length; i++){
            if(self.users[i].id === id) {
                self.user = angular.copy(self.users[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.user.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteUser(id);
    }


    function reset(){
        self.user={id:null,nom:'',adresse:'',mail:'',identifiant:'',motDePasse:''};
        $scope.myForm.$setPristine(); //reset Form
    }

    function connect() {
    	connexion(self.user.identifiant, self.user.motDePasse);
    }
}]);
