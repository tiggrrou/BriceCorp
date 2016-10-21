<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>GestBank</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>

<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular.js"></script>
<script src="<c:url value='/static/js/app.js' />"></script>
<script src="<c:url value='/static/js/service/user_service.js' />"></script>
<script src="<c:url value='/static/js/controller/user_controller.js' />"></script>
<script
	src="<c:url value='/static/js/controller/compte_controller.js' />"></script>
<script src="<c:url value='/static/js/service/compte_service.js' />"></script>
<script src="<c:url value='/static/js/service/demande_service.js' />"></script>
<script
	src="<c:url value='/static/js/controller/demande_controller.js' />"></script>
<!-- Script pour routage -->
<script src="<c:url value='/static/js/service/langue_service.js' />"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-resource.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular-animate.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-route.js"></script>
<script src="<c:url value='/static/js/controller/ConfigRouter.js' />"></script>


<!-- Script pour Web Service -->
<script src="http://www.google.com/jsapi" type="text/javascript"></script>
<script src="static/gp/ajax_currency_converter.gp"
	type="text/javascript"></script>
<script src="static/gp/javascript.gp" type="text/javascript"></script>



</head>

<body ng-app="myApp" class="ng-cloak "
	ng-controller="UserController as ctrl"
	ng-init="ctrl.change_langue()">



	<!-- Affichage de la navbar -->

	<div id="navbar" class="navbar col-xs-5 col-sm-3 col-lg-2"
		ng-hide="nav_cache">
		<div id="contenu_navbar" class="contenu_navbar">
			<img id="logo" src="static/imgs/logo.png" ng-click="ctrl.populate_dummy()"/>
			<!-- Menu de connexion  -->
			<div class="formcontainer" ng-hide="{{connexion_cache}}">
				<form ng-submit="ctrl.connect()" name="formConnexion"
					class="form-horizontal">
					<input type="hidden" ng-model="ctrl.user.id" />
					<div class="column">
						<div class="form-group ">
							<label class="control-lable" for="file">{{translation.login}}</label>
							<input 
								type="text" 
								ng-model="ctrl.user.identifiant" 
								name="login"
								class="username form-control input-sm"
								placeholder="Entez votre login" 
								required />
							<div class="has-error" ng-show="formConnexion.login.$dirty">
								<span ng-show="formConnexion.login.$error.required">Champ obligatoire</span>
							</div>
						</div>
					</div>
					<div class="column">
						<div class="form-group ">
							<label class=" control-lable" for="file">{{translation.password}}</label>
							<input 
								type="password" 
								ng-model="ctrl.user.motDePasse"
								name="password" 
								class="password form-control input-sm"
								placeholder="Entrez votre mot de passe" 
								required />
							<div class="has-error" ng-show="formConnexion.password.$dirty">
								<span ng-show="formConnexion.password.$error.required">Champ obligatoire</span>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-actions center-block">
							<input 
								type="submit" 
								value="Connexion"
								class="btn btn-primary btn-sm"
								ng-disabled="formConnexion.$invalid">
						</div>
					</div>
				</form>
				
			</div>

			<!-- Menu du Conseiller -->
			<div class="container-fluid " ng-hide="{{conseiller_cache}}">
				<div class="list-group">
					<!-- Recherche client  -->
					<a href="#/cons/Cons_RechCli" class="list-group-item"
						ng-class="{active: $route.current.activetab == 'conseiller_rechercheclient'}"
						ng-click="rechercheClient_cache = !rechercheClient_cache ;rechercheDemandes_cache = false; rechercheIBAN_cache = false">
						<h4 class="list-group-item-heading">Recherche client</h4>
						<p class="list-group-item-text">Recherche de client par nom et prénom</p>
					</a>
					<div class="formcontainer" ng-hide="!rechercheClient_cache">
							<div class="form-group ">
								<label class=" control-lable" for="file">Prénom</label> <input
									type="text" 
									ng-model="searchClient.prenom" 
									name="prenom"
									class="form-control input-sm" 
									placeholder="Prenom du client" />
							</div>
							<div class="form-group ">
								<label class=" control-lable" for="file">Nom</label> <input
									type="text" 
									ng-model="searchClient.nom" 
									name="nom"
									class="form-control input-sm" 
									placeholder="Nom du client" />
							</div>
					</div>

					<!-- Recherche IBAN  -->
					<a href="#/cons/Cons_RechCpt" class="list-group-item"
						ng-class="{active: $route.current.activetab == 'conseiller_recherchecompte'}"
						ng-click="rechercheIBAN_cache = !rechercheIBAN_cache;rechercheDemandes_cache = false; rechercheClient_cache = false">
						<h4 class="list-group-item-heading">Recherche compte</h4>
						<p class="list-group-item-text">Recherche de comptes par IBAN</p>
					</a>
					<div class="formcontainer" ng-hide="!rechercheIBAN_cache">
							<div class="form-group ">
								<label class=" control-lable" for="file">IBAN</label> <input
									type="text" ng-model="searchCompteClient.id" name="iban"
									class="form-control input-sm" placeholder="IBAN à rechercher" />
							</div>
					</div>

					<!-- Tableau des demandes  -->
					<a href="#/cons/Cons_DemCli" class="list-group-item"
						ng-class="{active: $route.current.activetab == 'conseiller_demandesclient'}"
						ng-click="ctrl.getDemandes; rechercheDemandes_cache=!rechercheDemandes_cache; rechercheClient_cache = false; rechercheIBAN_cache = false">
						<h4 class="list-group-item-heading">Demandes</h4>
						<p class="list-group-item-text">Liste des demandes à traiter</p>
					</a>	
				</div>
			</div>


			<!-- Menu du Client -->
			<div class="container-fluid" ng-hide="{{client_cache}}">
				<div class="list-group">
				<!-- Synthese des comptes -->
					<a href="#/cli/Cli_ListeComptes" class="list-group-item"
						ng-class="{active: $route.current.activetab == 'client_compte'}">
						<h4 class="list-group-item-heading">Mes Comptes</h4>
						<p class="list-group-item-text">Synthèse de vos comptes</p> 
					
				<!-- Virements -->
					</a> <a href="#/cli/Cli_Virement" class="list-group-item"
						ng-class="{active: $route.current.activetab == 'client_virement'}"
						ng-click="compteCtrl.getComptes">
						<h4 class="list-group-item-heading">Virements</h4>
						<p class="list-group-item-text">Effectuer un virement</p> 
						
				<!-- Infos personnelles -->
					</a> <a href="#/cli/Cli_modifInfos" class="list-group-item"
						ng-class="{active: $route.current.activetab == 'client_info'}"
						ng-click="ctrl.getClient">
						<h4 class="list-group-item-heading">Informations Personnelles</h4>
						<p class="list-group-item-text">Consulter et modifier vos
							informations personnelles</p> 
					
				<!-- Notifications -->
					</a> <a href="#/cli/Cli_Notifications" class="list-group-item"
						ng-class="{active: $route.current.activetab == 'client_notif'}"
						ng-click="ctrl.getNotifs">
						<h4 class="list-group-item-heading">
							Notifications <span class="glyphicon glyphicon-envelope">
						</h4>
						<p class="list-group-item-text">Liste de vos notifications</p>
					</a>
				</div>
				
			</div>

			<!-- Menu de l'administrateur -->
			<div class="container-fluid" ng-hide="{{admin_cache}}">
				<div class="list-group">
					<!-- Tableau des demandes -->
					<a href="#/admin/Admin_AffectCli" class="list-group-item"
						ng-class="{active: $route.current.activetab == 'admin_ouverture'}"
						ng-click="ctrl.getDemandes; rechercheConseiller_cache = false">
						<h4 class="list-group-item-heading">Demandes d'ouvertures</h4>
						<p class="list-group-item-text">Afficher la liste des demande
							d'ouverture</p> 
					
					<!-- Recherche des conseillers -->
					</a> <a href="#/admin/Admin_RechCons" class="list-group-item"
						ng-class="{active: $route.current.activetab == 'admin_recherchecons'}"
						ng-click="rechercheConseiller_cache = !rechercheConseiller_cache">
						<h4 class="list-group-item-heading">Recherche des conseillers</h4>
						<p class="list-group-item-text">Recherche par nom et prénom</p>
					</a>
					<div class="formcontainer" ng-hide="!rechercheConseiller_cache">
							<div class="row">
								<div class="form-group col-xs-10">
									<label class="col-xl-2 control-lable" for="file">Prénom</label>
									<div class="col-xl-12">
										<input type="text" ng-model="searchCons.prenom" name="prenom"
											class="form-control input-sm"
											placeholder="Prénom du conseiller" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-10">
									<label class="col-xl-2 control-lable" for="file">Nom</label>
									<div class="col-xl-12">
										<input type="text" ng-model="searchCons.nom" name="nom"
											class="form-control input-sm" placeholder="Nom du conseiller" />
									</div>
								</div>
							</div>
					</div>

					<!-- nouveau conseiller -->
					<a href="#/admin/Admin_CreaCons" class="list-group-item"
						ng-class="{active: $route.current.activetab == 'admin_nouveaucons'}"
						ng-click="rechercheConseiller_cache = false">
						<h4 class="list-group-item-heading">Nouveau conseiller</h4>
						<p class="list-group-item-text">Ajout d'un nouveau conseiller</p>
					</a>
				</div>
			</div>
		</div>
		<div>
			<img class="flechemenu " src="static/imgs/fleche-bas.png"
				ng-click="ctrl.nav_cache_methode()" />
		</div>
		
	</div>


	<!-- banniere en fixe dans le header pour la deco -->

	<div id="banniere" class="col-xs-7 col-sm-9 col-lg-10">
		<div class="nav_fleche col-xs-1" ng-hide="!nav_cache">
			<img class="flechemenu " src="static/imgs/fleche-bas.png"
				ng-click="ctrl.nav_cache_methode()" />
		</div>
		<div class="session_name col-xs-8">
			<h1 ng-hide="{{connexion_cache}}">{{translation.bienvenue}}
				GestBank</h1>
			<h1 ng-hide="{{admin_cache}}">Espace Administrateur</h1>
			<h1 ng-hide="{{conseiller_cache}}">Espace Conseiller</h1>
			<h1 ng-hide="{{client_cache}}">
				Bienvenue <br /> {{ctrl.curentUser.prenom}} {{ctrl.curentUser.nom}}
			</h1>
		</div>
		<div class="langue_connect col-xs-2">
			<div>
				<img class="langue center-block" src="static/imgs/en.png"
					ng-hide="{{lang_cache}}" ng-click="ctrl.change_langue()" />
				<img class="langue center-block" src="static/imgs/fr.png"
					ng-hide="{{!lang_cache}}" ng-click="ctrl.change_langue()" />
			</div>
			<div>
				<input type="button" class="btn btn-danger center-block"
					value="Deconnexion" ng-click="ctrl.session_delete()"
					ng-hide="!{{connexion_cache}}" />
			</div>
		</div>
	</div>


	<div id="partie_commune" class="col-xs-7 col-sm-9 col-lg-10">

		<div class="generic-container">

			<div class="ng-view "></div>



		</div>
	</div>


	<script>
		gp_currencySymbols()
	</script>
	<!-- Responsive aÂ  faire
	<script>
	App.controller('cache_menu', function($scope) {
		
	
	window.addEventListener("resize", function(){
		var width = document.body.clientWidth;

		if(width < 400){
			$scope.nav_cache = true;
			$scope.resize_cache = true;
			}else{
		$scope.resize_cache = false;
}
console.log("resize" + $scope.resize_cache);
console.log("nav" + $scope.nav_cache);
	})
	})
	
	
	
	
	</script>
	
-->
</body>

</html>
