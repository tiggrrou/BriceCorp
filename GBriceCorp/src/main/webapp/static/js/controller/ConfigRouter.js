


App.config(['$routeProvider',function($routeProvider) {
	
	$routeProvider
	
	// Routage Guest
	.when("/" , {
		templateUrl:"static/views/Guest_WebService.html",
		controller: 'UserController'
	})
	.when("/inscription" , {
		templateUrl:"static/views/Guest_Inscription.html",
		controller: 'UserController'
	})
	.when("/Fin_Inscription/:demande_id&:nom&:prenom" , {
		templateUrl:"static/views/Fin_Inscription.html",
		controller: 'DemandeController'
	})
	
	
	// Routage conseiller
		 
	.when("/cons/" , {
		templateUrl:"static/views/Cons_DemCli.html",
		controller: 'UserController',	
	    activetab: 'conseiller_demandesclient'
	})
	.when("/cons/Cons_DemCli" , {
		templateUrl:"static/views/Cons_DemCli.html",
		controller: 'DemandeController',	
	    activetab: 'conseiller_demandesclient'		
	})
	.when("/cons/Cons_RechCli" , {
		templateUrl:"static/views/Cons_RechCli.html",
		controller: 'UserController',	
	    activetab: 'conseiller_rechercheclient'		
	})
	.when("/cons/Cons_RechCpt" , {
		templateUrl:"static/views/Cons_RechCpt.html",
		controller: 'CompteController',	
	    activetab: 'conseiller_recherchecompte'		
	})
	.when("/cons/ConsClient_DetailCompte/:client_id" , {
			templateUrl:"static/views/ConsClient_DetailCompte.html",
			controller: 'UserController',	
		    activetab: 'conseiller_recherchecompte'			
		})
	.when("/cons/DetailClient/:client_id" , {
			templateUrl:"static/views/ConsClient_DetailCompte.html",
			controller: 'DemandeController',	
		    activetab: 'conseiller_rechercheclient'			
		})		
	.when("/cons/Cons_DetailCompte/:demande_id&:client_id" , {
			templateUrl:"static/views/ConsClient_DetailCompte.html",
			controller: 'DemandeController',	
		    activetab: 'conseiller_recherchecompte'			
		})
		
	// Routage Client
	
	.when("/cli/" , {
		templateUrl:"static/views/Cli_ListeComptes.html",
		controller: 'CompteController',	
	    activetab: 'client_compte'			
	})
	.when("/cli/Cli_ListeComptes" , {
		templateUrl:"static/views/Cli_ListeComptes.html",
		controller: 'CompteController',	
	    activetab: 'client_compte'			
	})
	.when("/cli/Cli_DemDecou" , {
		templateUrl:"static/views/Cli_DemDecou.html",
		controller: 'CompteController',	
	    activetab: 'conseiller_demandesclient'			
	})
	.when("/cli/Cli_modifInfos" , {
		templateUrl:"static/views/Cli_ModifInfos.html",
		controller: 'CompteController',	
	    activetab: 'client_info'			
	})
	.when("/cli/Cli_NouvCompte" , {
		templateUrl:"static/views/Cli_NouvCompte.html",
		controller: 'CompteController',	
	    activetab: 'client_compte'			
	})
	.when("/cli/Cli_Virement" , {
		templateUrl:"static/views/Cli_Virement.html",
		controller: 'CompteController',	
	    activetab: 'client_virement'			
	})
	.when("/cli/Cli_Notifications" , {
		templateUrl:"static/views/Cli_Notifications.html",
		controller: 'UserController',	
	    activetab: 'client_notif'			
	})
	.when("/cli/Cli_DetailCompte/:compte_id" , {
		templateUrl:"static/views/ConsClient_DetailCompte.html",
		controller: 'CompteController',	
	    activetab: 'conseiller_demandesclient'			
	})

	
	// Routage Admin
	.when("/admin/" , {
		templateUrl:"static/views/Admin_AffectCli.html",
		controller: 'UserController',	
	    activetab: 'admin_ouverture'	
	})
	.when("/admin/Admin_AffectCli" , {
		templateUrl:"static/views/Admin_AffectCli.html",
		controller: 'UserController',	
	    activetab: 'admin_ouverture'		
	})
	.when("/admin/Admin_RechCons" , {
		templateUrl:"static/views/Admin_RechCons.html",
		controller: 'UserController',	
	    activetab: 'admin_recherchecons'		
	})
	.when("/admin/Admin_EditCons/:consId" , {
		templateUrl:"static/views/Admin_EditCons.html",
		controller: 'UserController',	
	    activetab: 'admin_recherchecons'		
	})
	.when("/admin/Admin_CreaCons" , {
		templateUrl:"static/views/Admin_CreaCons.html",
		controller: 'UserController',	
	    activetab: 'admin_nouveaucons'		
	})
}]);
