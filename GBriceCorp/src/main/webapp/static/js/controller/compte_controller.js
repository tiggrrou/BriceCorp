'use strict';

App.controller('CompteController', ['$scope','$location', '$route', 'CompteService', function($scope,$location,$route, CompteService) {
    var self = this;
    self.compte;
    self.comptes;
    $scope.virement;

    self.user;
    
    self.demandes;
    
    self.notifications;
    
   self.edit = edit;
    self.remove = remove;
    $scope.getDemandes = getDemandes;
    $scope.getComptes = getComptes;
    $scope.virement = virement;
    $scope.writeDemDecou = writeDemDecou;
    
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
    
    function getDemandes(){
    	// je demande les demandes en fournissant l'id client et l'id conseiller
    	// ces valeurs peuvent être nulles dans ce cas on récupère les demandes d'admissions des nouveaux clients
    	CompteService.getDemandes(self.client.id,self.conseiller.id)
    	.then(
    			function(d){
    				//je place les demandes récupérées dans demandes
    				demandes = JSON.parse(sessionStorage.getItem("Demandes"));	
    			},
    			function (errResponse){
    				console.error('Error while getting Clients request')
    			});
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
    
    function writeDemDecou() {
    	CompteService.writeDemDecou(JSON.parse(sessionStorage.getItem("User").id), compte.id, decouvert)
    			.then(
    					function(){
    						// cas normal, pop up ?
    						alert("Votre demande a bien été transmise")
    					},
    					function(messageErreurFromService){
    						alert("Une erreur est survenue pendant la création de votre demande\n" + messageErreurFromService )
    					})
    }

    function virement()
    {
//    	var idCompteDebiteur = $scope.compteDebiteur.split("-")[1].trim();
//    	var idCompteCrediteur = $scope.compteCredite.split("-")[1].trim();
//    	CompteService.virement(idCompteDebiteur, idCompteCrediteur, $scope.montant)
    	CompteService.virement(	$scope.virement.compteDebiteur, 
    							$scope.virement.compteCredite, 
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
