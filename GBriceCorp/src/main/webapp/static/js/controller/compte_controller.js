'use strict';

App.controller('CompteController', ['$scope','$location', '$route', '$routeParams', 'CompteService','DemandeService', function($scope,$location,$route,$routeParams, CompteService,DemandeService) {
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
    self.trier_par=trier_par;
    self.compteDebiteur=compteDebiteur;
    self.compteExiste=compteExiste;
	self.writeDemDecou=writeDemDecou;
    
    
    
    function compteDebiteur(compte){
   		$scope.comptedebiteur = JSON.parse(compte);
    }  
    
    function trier_par(tri){
    	//en cours d affinage
    	if ($scope.tripar = tri){
    		$scope.sens = !$scope.sens;		
    	}
   		$scope.tripar = tri;
    }

    
    
    function detailCompte(compte_id){
	  	 $location.path("/cons/DetailCompte/" + compte_id);
	   }   
    
    
    function compteExiste(compte_id){
    	$scope.compte_id_existe = "aucun";
      	CompteService.findCompteById(compte_id)
    		    	.then(
    		    			function(data){
    		    				console.log(data)
    		    				if(data){
        		    				console.log(data.id);
        		    				$scope.compte_id_existe = data.id;	    					
    		    				}


    		    			},
    		    			function (errResponse){
    		    				console.error('Error while getting an account from an compte ID')
    		    			});
    		    	
    		    };
    		    
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
    	console.log("coucous")
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
    				$scope.compte = data;
    			},
    			function (errResponse){
    				console.error('Error while getting an account from an customer ID')
    			});
    	
    };  


    function virement()
    {
    	var compteDebiter = JSON.parse($scope.virement.compteDebiteur).id;
    	var compteCrediter = ($scope.virement.compteCredite == 'Autre')? $scope.virement.compteAutre :$scope.virement.compteCredite;
    	if(compteDebiter != compteCrediter){
        	CompteService.virement(compteDebiter,compteCrediter,$scope.virement.montant)
    		.then(
    				function(){
    					alert("Votre virement a bien ete effectue, retour a la synthese des comptes");
    					$location.path("cli/");
    				},
    				function(messageErreurFromService){
    					alert("Une erreur est survenue pendant le traitement de votre opération")
    				})	
    		
    	}else{
    		alert("Vous ne pouvez pas debiter et crediter le meme compte");
    		
    	}

    				}
    
    function writeDemDecou(compteId) {
    	DemandeService.writeDemDecou(JSON.parse(sessionStorage.getItem("currentUser")).id, compteId, $scope.demDecouvert)
    			.then(
    					function(){
    						// cas normal, pop up ?
    						alert("Votre demande de découvert a bien été transmise")
    					},
    					function(messageErreurFromService){
    						alert("Une erreur est survenue pendant la création de votre demande de Decouvert \n" + messageErreurFromService )
    					})
    }; 
    
    function writeDemRemu(compteId) {
    	DemandeService.writeDemRemu(JSON.parse(sessionStorage.getItem("currentUser")).id, compteId, $scope.demRemu)
    			.then(
    					function(){
    						// cas normal, pop up ?
    						alert("Votre demande de Temunération sur compte a bien été transmise")
    					},
    					function(messageErreurFromService){
    						alert("Une erreur est survenue pendant la création de votre demande de Remuneration \n" + messageErreurFromService )
    					})
    };
    
    function writeDemChequier(compteId) {
    	DemandeService.writeDemChequier(JSON.parse(sessionStorage.getItem("currentUser")).id, compteId , $scope.demChequier)
    			.then(
    					function(){
    						// cas normal, pop up ?
    						alert("Votre demande de chéquier a bien été transmise")
    					},
    					function(messageErreurFromService){
    						alert("Une erreur est survenue pendant la création de votre demande de chéquier \n" + messageErreurFromService )
    					})
    };
    
    
}]);