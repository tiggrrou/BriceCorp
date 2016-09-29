<<<<<<< HEAD


App.config(['$routeProvider',function($routeProvider) {
	
	$routeProvider
	.when("/" , {
		templateUrl: "static/views/Admin_CreaCons.html",
		controller: 'UserController'
	})
	
	// Routage conseiller
		 
	.when("/cons/" , {
		templateUrl:"/Cons_DemCli.jsp",
		controller: 'UserController'		
	})
	.when("/cons/Cons_DemCli" , {
		templateUrl:"/Cons_DemCli.jsp",
		controller: 'UserController'		
	})
	.when("/cli/Cons_RechCli" , {
		templateUrl:"/Cons_RechCli.jsp",
		controller: 'UserController'		
	})
	.when("/cons/Cons_RechCpt" , {
		templateUrl:"/Cons_RechCpt.jsp",
		controller: 'UserController'		
	})
	.when("/cons/Cons_ValCheq" , {
		templateUrl:"/Cons_ValCheq.jsp",
		controller: 'UserController'		
	})
	.when("/cons/Cons_ValDecou" , {
		templateUrl:"/Cons_ValDecou.jsp",
		controller: 'UserController'		
	})
	.when("/cons/Cons_ValModif" , {
		templateUrl:"/Cons_ValModif.jsp",
		controller: 'UserController'		
	})
	.when("/cons/Cons_DetailCompte" , {
			templateUrl:"/ConsClient_DetailCompte.jsp",
			controller: 'UserController'		
		})
	
	// Routage Client
	
	.when("/cli/" , {
		templateUrl:"/Cli_postConnect.jsp",
		controller: 'UserController'		
	})
	.when("/cli/Cli_ListeComptes" , {
		templateUrl:"/Cli_postConnect.jsp",
		controller: 'UserController'		
	})
	.when("/cli/Cli_DemDecou" , {
		templateUrl:"/Cli_DemDecou.jsp",
		controller: 'UserController'		
	})
	.when("/cli/Cli_modifInfos" , {
		templateUrl:"/Cli_modifInfos.jsp",
		controller: 'UserController'		
	})
	.when("/cli/Cli_NouvCompte" , {
		templateUrl:"/Cli_NouvCompte.jsp",
		controller: 'UserController'		
	})
	.when("/cli/Cli_Virement" , {
		templateUrl:"/Cli_Virement.jsp",
		controller: 'UserController'		
	})
	.when("/cli/Cli_DetailCompte" , {
		templateUrl:"/ConsClient_DetailCompte.jsp",
		controller: 'UserController'		
	})
	
	
	// Routage Admin
	.when("/admin/" , {
		templateUrl:"Admin_AffectCli.html",
		controller: 'UserController'		
	})
	.when("/admin/Admin_AffectCli" , {
		templateUrl:"/Admin_AffectCli.jsp",
		controller: 'UserController'		
	})
	.when("/admin/Admin_RechCons" , {
		templateUrl:"/Admin_RechCons.jsp",
		controller: 'UserController'		
	})
	.when("/admin/Admin_EditCons" , {
		templateUrl:"/Admin_EditCon.jsp",
		controller: 'UserController'		
	})
	.when("/admin/Admin_CreaCons" , {
		templateUrl:"/Admin_CreaCons.jsp",
		controller: 'UserController'		
	})
}]);
=======


App.config(function($routeProvider) {
	
	$routeProvider
	.when("/" , {
		templateUrl: "./static/views/Admin_CreaCons.html",
		controller: 'user_controller'
	})
	
	// Routage conseiller
		 
	.when("/cons/" , {
		templateUrl:"/Cons_DemCli.jsp",
		controller: 'user_controller'		
	})
	.when("/cons/Cons_DemCli" , {
		templateUrl:"/Cons_DemCli.jsp",
		controller: 'user_controller'		
	})
	.when("/cli/Cons_RechCli" , {
		templateUrl:"/Cons_RechCli.jsp",
		controller: 'user_controller'		
	})
	.when("/cons/Cons_RechCpt" , {
		templateUrl:"/Cons_RechCpt.jsp",
		controller: 'user_controller'		
	})
	.when("/cons/Cons_ValCheq" , {
		templateUrl:"/Cons_ValCheq.jsp",
		controller: 'user_controller'		
	})
	.when("/cons/Cons_ValDecou" , {
		templateUrl:"/Cons_ValDecou.jsp",
		controller: 'user_controller'		
	})
	.when("/cons/Cons_ValModif" , {
		templateUrl:"/Cons_ValModif.jsp",
		controller: 'user_controller'		
	})
	.when("/cons/Cons_DetailCompte" , {
			templateUrl:"/ConsClient_DetailCompte.jsp",
			controller: 'user_controller'		
		})
	
	// Routage Client
	
	.when("/cli/" , {
		templateUrl:"/Cli_postConnect.jsp",
		controller: 'user_controller'		
	})
	.when("/cli/Cli_ListeComptes" , {
		templateUrl:"/Cli_postConnect.jsp",
		controller: 'user_controller'		
	})
	.when("/cli/Cli_DemDecou" , {
		templateUrl:"/Cli_DemDecou.jsp",
		controller: 'user_controller'		
	})
	.when("/cli/Cli_modifInfos" , {
		templateUrl:"/Cli_modifInfos.jsp",
		controller: 'user_controller'		
	})
	.when("/cli/Cli_NouvCompte" , {
		templateUrl:"/Cli_NouvCompte.jsp",
		controller: 'user_controller'		
	})
	.when("/cli/Cli_Virement" , {
		templateUrl:"/Cli_Virement.jsp",
		controller: 'user_controller'		
	})
	.when("/cli/Cli_DetailCompte" , {
		templateUrl:"/ConsClient_DetailCompte.jsp",
		controller: 'user_controller'		
	})
	
	
	// Routage Admin
	.when("/admin/" , {
		templateUrl:"Admin_AffectCli.html",
		controller: 'user_controller'		
	})
	.when("/admin/Admin_AffectCli" , {
		templateUrl:"/Admin_AffectCli.jsp",
		controller: 'user_controller'		
	})
	.when("/admin/Admin_RechCons" , {
		templateUrl:"/Admin_RechCons.jsp",
		controller: 'user_controller'		
	})
	.when("/admin/Admin_EditCons" , {
		templateUrl:"/Admin_EditCon.jsp",
		controller: 'user_controller'		
	})
	.when("/admin/Admin_CreaCons" , {
		templateUrl:"/Admin_CreaCons.jsp",
		controller: 'user_controller'		
	})
})
>>>>>>> branch 'master' of https://github.com/tiggrrou/BriceCorp.git
