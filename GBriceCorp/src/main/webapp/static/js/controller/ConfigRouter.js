


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
	
	// Routage conseiller
		 
	.when("/cons/" , {
				templateUrl:"static/views/Cons_DemCli.html",
		controller: 'UserController'		
	})
	.when("/cons/Cons_DemCli" , {
		templateUrl:"static/views/Cons_DemCli.html",
		controller: 'UserController'		
	})
	.when("/cons/Cons_RechCli" , {
		templateUrl:"static/views/Cons_RechCli.html",
		controller: 'UserController'		
	})
	.when("/cons/Cons_RechCpt" , {
		templateUrl:"static/views/Cons_RechCpt.html",
		controller: 'UserController'		
	})
	.when("/cons/Cons_ValCheq" , {
		templateUrl:"static/views/Cons_ValCheq.html",
		controller: 'UserController'		
	})
	.when("/cons/Cons_ValDecou" , {
		templateUrl:"static/views/Cons_ValDecou.html",
		controller: 'UserController'		
	})
	.when("/cons/Cons_ValModif" , {
		templateUrl:"static/views/Cons_ValModif.html",
		controller: 'UserController'		
	})
	.when("/cons/Cons_DetailCompte" , {
			templateUrl:"static/views/ConsClient_DetailCompte.html",
			controller: 'UserController'		
		})
	
	// Routage Client
	
	.when("/cli/" , {
		templateUrl:"static/views/Cli_ListeComptes.html",
		controller: 'UserController'		
	})
	.when("/cli/Cli_ListeComptes" , {
		templateUrl:"static/views/Cli_ListeComptes.html",
		controller: 'UserController'		
	})
	.when("/cli/Cli_DemDecou" , {
		templateUrl:"static/views/Cli_DemDecou.html",
		controller: 'UserController'		
	})
	.when("/cli/Cli_modifInfos" , {
		templateUrl:"static/views/Cli_ModifInfos.html",
		controller: 'UserController'		
	})
	.when("/cli/Cli_NouvCompte" , {
		templateUrl:"static/views/Cli_NouvCompte.html",
		controller: 'UserController'		
	})
	.when("/cli/Cli_Virement" , {
		templateUrl:"static/views/Cli_Virement.html",
		controller: 'UserController'		
	})
	.when("/cli/Cli_Notifications" , {
		templateUrl:"static/views/Cli_Notifications.html",
		controller: 'UserController'		
	})
	.when("/cli/Cli_DetailCompte" , {
		templateUrl:"static/views/ConsClient_DetailCompte.html",
		controller: 'UserController'		
	})
	
	
	// Routage Admin
	.when("/admin/" , {
		templateUrl:"static/views/Admin_AffectCli.html",
		controller: 'UserController'		
	})
	.when("/admin/Admin_AffectCli" , {
		templateUrl:"static/views/Admin_AffectCli.html",
		controller: 'UserController'		
	})
	.when("/admin/Admin_RechCons" , {
		templateUrl:"static/views/Admin_RechCons.html",
		controller: 'UserController'		
	})
	.when("/admin/Admin_EditCons" , {
		templateUrl:"static/views/Admin_EditCon.html",
		controller: 'UserController'		
	})
	.when("/admin/Admin_CreaCons" , {
		templateUrl:"static/views/Admin_CreaCons.html",
		controller: 'UserController'		
	})
}]);
