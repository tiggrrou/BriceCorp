'use strict';

App.controller('CompteController', ['$scope','$location', '$route', '$routeParams', 'CompteService', function($scope,$location,$route,$routeParams, CompteService) {
    var self = this;
    $scope.compte = {};
    $scope.comptes = [];

    $scope.virement;

    self.user;
    

    
    self.notifications;
    
    self.edit = edit;
    self.remove = remove;
    self.getComptes=getComptes;
    self.goToNouveauCompte = goToNouveauCompte;
    $scope.virement = virement;
    $scope.compte_id = $routeParams.compte_id;
    self.getAllComptesByCons =getAllComptesByCons;
    self.detailCompte=detailCompte;
    self.findComptesByClientId=findComptesByClientId;
    self.findCompteById=findCompteById;
    function detailCompte(compte_id){
	  	 $location.path("/cons/DetailClient/" + compte_id);
	   }   
    

    function getAllComptesByCons(){

      	CompteService.getAllComptesByCons(JSON.parse(sessionStorage.getItem("currentUser")).id)
    		    	.then(
    		    			function(data){
    		    				console.log(data);
    		    				$scope.comptes = data;
    		    			},
    		    			function (errResponse){
    		    				console.error('Error while getting an account from an customer ID')
    		    			});
    		    	
    		    };
    		    
	    
	    function getComptes(){

	    	 if($scope.client_id>0){
			    	console.log("demande.client")
			    	CompteService.getComptesClient($scope.client_id)
			    	.then(
			    			function(data){
			    				console.log(data);
			    				$scope.comptes = data;
			    			},
			    			function (errResponse){
			    				console.error('Error while getting an account from an customer ID')
			    			});
		 }else if($scope.demande_id){
			    	console.log("Creation")
$scope.comptes=[];
			    	
	}else  if($scope.compte_id){
			    	console.log("compte_id")
			    	CompteService.findCompteById($scope.compte_id)
			    	.then(
			    			function(data){
			    				console.log(data);
			    				$scope.comptes = [data];
			    			},
			    			function (errResponse){
			    				console.error('Error while getting an account from an customer ID')
			    			});	
				}
	    	
	    	
	    	
	    	
	   	 

	    	
	    };
	 
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
    
    self.findComptesByDemandeId=findComptesByDemandeId;
    function findComptesByDemandeId(demande_id){
    	// je recupere les comptes d'un client en fonction de son ID
    	
    	
    	CompteService.getComptesClient(demande_id)
    	.then(
    			function(data){
    				console.log(data);
    				$scope.comptes = data;
    			},
    			function (errResponse){
    				console.error('Error while getting an account from an customer ID')
    			});
    	
    };
    
 
    function findComptesByClientId(){
    	// je recupere les comptes d'un client en fonction de son ID

    	CompteService.getComptesClient(JSON.parse(sessionStorage.getItem("currentUser")).id)
    	.then(
    			function(data){
    				console.log(data);
    				$scope.comptes = data;
    			},
    			function (errResponse){
    				console.error('Error while getting an account from an customer ID')
    			});
    	
    };
    

    function findCompteById(compte_id){
    	// je recupere les comptes d'un client en fonction de son ID

    	CompteService.findCompteById(compte_id)
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
