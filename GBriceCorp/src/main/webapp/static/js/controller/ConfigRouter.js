'use strict';

angular.module.config(function($routeProvider) {
	
	$routeProvider
	.when("/" && user.type == "1", {
		templateUrl: "./homePage.html",
		controller: 'user_controller'
	})
	
	// Routage conseiller
	 
	.when("/cons/:id/", {
		templateUrl:"/Cons_DemCli.jsp",
		controller: 'user_controller'		
	})
	.when("/cons/:id/Cons_DemCli", {
		templateUrl:"/Cons_DemCli.jsp",
		controller: 'user_controller'		
	})
	.when("/cli/:id/Cons_RechCli", {
		templateUrl:"/Cons_RechCli.jsp",
		controller: 'user_controller'		
	})
	.when("/cons/:id/Cons_RechCpt", {
		templateUrl:"/Cons_RechCpt.jsp",
		controller: 'user_controller'		
	})
	.when("/cons/:id/Cons_ValCheq", {
		templateUrl:"/Cons_ValCheq.jsp",
		controller: 'user_controller'		
	})
	.when("/cons/:id/Cons_ValDecou", {
		templateUrl:"/Cons_ValDecou.jsp",
		controller: 'user_controller'		
	})
	.when("/cons/:id/Cons_ValModif", {
		templateUrl:"/Cons_ValModif.jsp",
		controller: 'user_controller'		
	})
	.when("/cons/:id/Cons_DetailCompte", {
			templateUrl:"/ConsClient_DetailCompte.jsp",
			controller: 'user_controller'		
		})
	
	// Routage Client
	
	.when("/cli/:id/", {
		templateUrl:"/Cli_postConnect.jsp",
		controller: 'user_controller'		
	})
	.when("/cli/:id/Cli_ListeComptes", {
		templateUrl:"/Cli_postConnect.jsp",
		controller: 'user_controller'		
	})
	.when("/cli/:id/Cli_DemDecou", {
		templateUrl:"/Cli_DemDecou.jsp",
		controller: 'user_controller'		
	})
	.when("/cli/:id/Cli_modifInfos", {
		templateUrl:"/Cli_modifInfos.jsp",
		controller: 'user_controller'		
	})
	.when("/cli/:id/Cli_NouvCompte", {
		templateUrl:"/Cli_NouvCompte.jsp",
		controller: 'user_controller'		
	})
	.when("/cli/:id/Cli_Virement", {
		templateUrl:"/Cli_Virement.jsp",
		controller: 'user_controller'		
	})
	.when("/cli/:id/Cli_DetailCompte", {
		templateUrl:"/ConsClient_DetailCompte.jsp",
		controller: 'user_controller'		
	})
	
	
	// Routage Admin
	
	.when("/admin/", {
		templateUrl:"/Admin_AffectCli.jsp",
		controller: 'user_controller'		
	})
	.when("/admin/Admin_AffectCli", {
		templateUrl:"/Admin_AffectCli.jsp",
		controller: 'user_controller'		
	})
	.when("/admin/Admin_RechCons", {
		templateUrl:"/Admin_RechCons.jsp",
		controller: 'user_controller'		
	})
	.when("/admin/Admin_EditCons", {
		templateUrl:"/Admin_EditCon.jsp",
		controller: 'user_controller'		
	})
	.when("/admin/Admin_CreaCons", {
		templateUrl:"/Admin_CreaCons.jsp",
		controller: 'user_controller'		
	})
})
