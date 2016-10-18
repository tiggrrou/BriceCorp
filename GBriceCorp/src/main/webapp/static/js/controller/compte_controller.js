'use strict';

App.controller('CompteController', ['$scope','$location', '$route', '$routeParams', 'CompteService', function($scope,$location,$route,$routeParams, CompteService) {
    var self = this;
    self.compte;
    self.comptes;
    $scope.virement;

    self.user;
    

    
    self.notifications;
    
    self.edit = edit;
    self.remove = remove;

    self.goToNouveauCompte = goToNouveauCompte;
    self.getComptes = getComptes;
    $scope.virement = virement;

	 if($routeParams.compte_id != null){	
			for (var i = 0; i < JSON.parse(sessionStorage.getItem("currentUser")).comptes.length ; i++) {
			   if(JSON.parse(sessionStorage.getItem("currentUser")).comptes[i].id == $routeParams.compte_id ){
				  $scope.comptes =  [JSON.parse(sessionStorage.getItem("currentUser")).comptes[i]]; 
				  console.log($scope.comptes)
			   }
			}
	 }
	 
	 
    function goToNouveauCompte()
    {
    	$location.path("/cli/Cli_NouvCompte");
    }
    
    function fetchAllComptes(){
        CompteService.fetchAllComptes()
            .then(
            function(d) {
                self.comptes = d;
            },
            function(errResponse){
                console.error('Error while fetching Comptes');
            }
        );
    }

    function createCompte(compte){
        CompteService.createCompte(compte)
            .then(
            fetchAllComptes,
            function(errResponse){
                console.error('Error while creating Compte');
            }
        );
    }

    function updateCompte(compte, id){
        CompteService.updateCompte(compte, id)
            .then(
            fetchAllComptes,
            function(errResponse){
                console.error('Error while updating Compte' + errResponse);
            }
        );
    }

    function deleteCompte(id){
        CompteService.deleteCompte(id)
            .then(
            fetchAllComptes,
            function(errResponse){
                console.error('Error while deleting Compte');
            }
        );
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.comptes.length; i++){
            if(self.comptes[i].id === id) {
                self.compte = angular.copy(self.comptes[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.compte.id === id) {//clean form if the compte to be deleted is shown there.
            reset();
        }
        deleteCompte(id);
    }
    

    
    function getComptes(){
    	// je recupere les comptes d'un client en fonction de son ID
    	var idCurrent = JSON.parse(sessionStorage.getItem("currentUser")).id;
    	CompteService.getComptesClient(idCurrent)
    	.then(
    			function(data){
    				console.log(data);
    				$scope.comptes = data;
    			},
    			function (errResponse){
    				console.error('Error while getting an account from an customer ID')
    			});
    	
    };
    


    function virement()
    {
    	CompteService.virement(	$scope.virement.compteDebiteur, 
    							($scope.virement.compteCredite == 'Autre')? $scope.virement.compteAutre :$scope.virement.compteCredite, 
    							$scope.virement.montant)
    		.then(
    				function(){
    					alert("Votre virement a bien ete effectue, retour a la synthese des comptes");
    					$location.path("cli/");
    				},
    				function(messageErreurFromService){
    					alert("Une erreur est survenue pendant le traitement de votre opération")
    				})
    				}
}]);
