
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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


<!-- Carroussel 
  <meta name="viewport" content=" initial-scale=4">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
  .carousel-inner > .item > img,
  .carousel-inner > .item > a > img {
      height: 30%;
      width:100%;

  }

  </style>
  -->
</head>

<body ng-app="myApp" class="ng-cloak"
	ng-controller="UserController as ctrl">

	<div class="navbar2 col-xs-3" ng-hide="!nav_cache">
		<img class="flechemenu " src="static/imgs/fd.png"
			ng-click="nav_cache = !nav_cache" />
	</div>
	<!-- Affichage de la navbar -->

	<div class="navbar col-xs-3" ng-hide="nav_cache">
		<div class="contenu_navbar">
			<img id="logo" src="static/imgs/logo.png" />
			<!-- Menu de connexion  -->
			<div class="formcontainer" ng-hide="{{connexion_cache}}">
				<form ng-submit="ctrl.connect()" name="formConnexion"
					class="form-horizontal">
					<input type="hidden" ng-model="ctrl.user.id" />
					<div class="column">
						<div class="form-group flex">
							<label class="control-lable" for="file">{{translation.login}}</label>
							<input type="text" ng-model="ctrl.user.identifiant" name="login"
								class="username form-control input-sm"
								placeholder="Entez votre login" required />
							<div class="has-error" ng-show="formConnexion.$dirty">
								<span ng-show="formConnexion.login.$error.required">Champ
									obligatoire</span>
							</div>
						</div>
					</div>
					<div class="column">
						<div class="form-group ">
							<label class=" control-lable" for="file">{{translation.password}}</label>
							<input type="password" ng-model="ctrl.user.motDePasse"
								name="password" class="password form-control input-sm"
								placeholder="Entrez votre mot de passe" required />
							<div class="has-error" ng-show="formConnexion.$dirty">
								<span ng-show="formConnexion.password.$error.required">Champ
									obligatoire</span>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-actions center-block">
							<input type="submit" value="Connexion"
								class="btn btn-primary btn-sm">
						</div>
					</div>
				</form>
			</div>

			<!-- Menu du Conseiller -->
			<div class="container-fluid col-xs-12" ng-hide="{{conseiller_cache}}">
				<div class="list-group">
					<!-- Recherche client  -->
					<a href="#/cons/Cons_RechCli" class="list-group-item"
						ng-class="{active: $route.current.activetab == 'conseiller_rechercheclient'}"
						ng-click="rechercheClient_cache = !rechercheClient_cache ; rechercheIBAN_cache = false">
						<h4 class="list-group-item-heading">Recherche client</h4>
						<p class="list-group-item-text">Rechercher un client</p>
					</a>
					<div class="formcontainer" ng-hide="!rechercheClient_cache">
						<form ng-submit="ctrl.searchClients" name="formConnexion"
							class="form-horizontal">

							<div class="form-group ">
								<label class=" control-lable" for="file">Prenom</label> <input
									type="text" ng-model="search.Prenom" name="prenom"
									class="form-control input-sm" placeholder="Prenom du client" />

							</div>


							<div class="form-group ">
								<label class=" control-lable" for="file">Nom</label> <input
									type="text" ng-model="search.Nom" name="nom"
									class="form-control input-sm" placeholder="Nom du client" />

							</div>

						</form>
					</div>

					<!-- Recherche IBAN  -->
					<a href="#/cons/Cons_RechCpt" class="list-group-item"
						ng-class="{active: $route.current.activetab == 'conseiller_recherchecompte'}"
						ng-click="rechercheIBAN_cache = !rechercheIBAN_cache; rechercheClient_cache = false">
						<h4 class="list-group-item-heading">Recherche compte</h4>
						<p class="list-group-item-text">Rechercher un compte par IBAN</p>
					</a>



					<div class="formcontainer" ng-hide="!rechercheIBAN_cache">
						<form class="form-horizontal">

							<div class="form-group ">
								<label class=" control-lable" for="file">IBAN</label> <input
									type="text" ng-model="search.Iban" name="iban"
									class="form-control input-sm" placeholder="IBAN a  rechercher" />


							</div>
						</form>
					</div>

					<!-- Tableau des demandes  -->
					<a href="#/cons/Cons_DemCli" class="list-group-item"
						ng-class="{active: $route.current.activetab == 'conseiller_demandesclient'}"
						ng-click="ctrl.getDemandes; rechercheClient_cache = false; rechercheIBAN_cache = false">
						<h4 class="list-group-item-heading">Demandes</h4>
						<p class="list-group-item-text">Liste des demandes a  traiter</p>
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
						<p class="list-group-item-text">Synthese de vos comptes</p> <!-- Virements -->
					</a> <a href="#/cli/Cli_Virement" class="list-group-item"
						ng-class="{active: $route.current.activetab == 'client_virement'}"
						ng-click="compteCtrl.getComptes">
						<h4 class="list-group-item-heading">Virements</h4>
						<p class="list-group-item-text">Effectuer un virement</p> <!-- Infos personnelles -->
					</a> <a href="#/cli/Cli_modifInfos" class="list-group-item"
						ng-class="{active: $route.current.activetab == 'client_info'}"
						ng-click="ctrl.getClient">
						<h4 class="list-group-item-heading">Informations Personnelles</h4>
						<p class="list-group-item-text">Consulter et modifier vos
							informations personnelles</p> <!-- Notifications -->
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
							d'ouverture</p> <!-- Recherche des conseillers -->
					</a> <a href="#/admin/Admin_RechCons" class="list-group-item"
						ng-class="{active: $route.current.activetab == 'admin_recherchecons'}"
						ng-click="rechercheConseiller_cache = !rechercheConseiller_cache">
						<h4 class="list-group-item-heading">Recherche des conseillers</h4>
						<p class="list-group-item-text">List Group Item Text</p>
					</a>
					<div class="formcontainer" ng-hide="!rechercheConseiller_cache">
						<form class="form-horizontal" ng-hide="!rechercheConseiller_cache">
							<div class="row">
								<div class="form-group col-xs-10">
									<label class="col-xl-2 control-lable" for="file">Prenom</label>
									<div class="col-xl-12">
										<input type="text" ng-model="search.prenom" name="prenom"
											class="form-control input-sm"
											placeholder="Prenom du conseiller" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-10">
									<label class="col-xl-2 control-lable" for="file">Nom</label>
									<div class="col-xl-12">
										<input type="text" ng-model="search.nom" name="nom"
											class="form-control input-sm" placeholder="Nom du conseiller" />
									</div>
								</div>
							</div>
						</form>
					</div>

					<!-- nouveau conseiller -->
					<a href="#/admin/Admin_CreaCons" class="list-group-item"
						ng-class="{active: $route.current.activetab == 'admin_nouveaucons'}"
						ng-click="rechercheConseiller_cache = false">
						<h4 class="list-group-item-heading">Nouveau conseiller</h4>
						<p class="list-group-item-text">List Group Item Text</p>
					</a>
				</div>
			</div>
		</div>


		<img class="flechemenu " src="static/imgs/fg.png"
			ng-click="nav_cache = !nav_cache" />
	</div>


	<div class="partie_commune ">

		<!-- banniere en fixe dans le header pour la deco -->

		<div class="banniere col-xs-12">
			<div class="session_name col-xs-9">
				<h1 ng-hide="{{connexion_cache}}">{{translation.bienvenue}}
					GestBank</h1>
				<h1 ng-hide="{{admin_cache}}">Espace Administrateur</h1>
				<h1 ng-hide="{{conseiller_cache}}">Espace Conseiller</h1>
				<h1 ng-hide="{{client_cache}}">Bienvenue {{ctrl.client.nom}}</h1>
			</div>
			<div class="langue_connect col-xs-3">
				<div>
					<img class="langue center-block" src="static/imgs/en.png"
						ng-hide="{{lang_cache}}" ng-click="ctrl.change_langue('fr')" /> <img
						class="langue center-block" src="static/imgs/fr.png"
						ng-hide="!{{lang_cache}}" ng-click="ctrl.change_langue('en')" />
				</div>
				<div>
					<input type="button" class="btn btn-danger center-block"
						value="Deconnexion" ng-click="ctrl.session_delete()"
						ng-hide="!{{connexion_cache}}" />
				</div>
			</div>
		</div>




		<div class="generic-container ">


			<div class="col-xs-12">
				<ng-view></ng-view>

			</div>

			<!-- Web Service de conversion -->

			<div class="webservice" ng-hide="{{connexion_cache}}">
				<div class="panel panel-info">
					<!-- Default panel contents -->
					<div class="panel-heading">
						<span class="lead">Service de conversion</span>
					</div>
					<div class="panel-body col-lg-12">
						<div class="col-xs-12 column ">

							<div class="col-sm-6 ">
								<label class="control-lable" for="file">Saisir le
									montant a convertir :</label>
							</div>
							<div class="form-group  col-sm-6">

								<input type="text" id="gp_amount" class="form-control input-sm"
									placeholder="1234,56" />

							</div>
						</div>
						<div class="col-xs-12 ">

							<div class="col-sm-6">
								<label class="control-lable" for="file">Montant converti
									:</label>
							</div class="col-sm-6">
							<div class="form-group flex" id="gp_converted" text-align: center>



							</div>
						</div>
						<div class="col-xs-12">
							<div class="class="col-sm-6"">
								<label class="control-lable" for="file">Devise de depart
									:</label>
							</div>
							<div class="class="col-sm-6"">
								<select class="form-control" id="gp_from"></select>
							</div>

						</div>
						<div class="col-xs-12 ">


							<div class="class="col-sm-6"">
								<label class="control-lable" for="file">Devise d'arrivee
									:</label>
							</div>
							<div class="class="col-sm-6"">
								<select class="form-control" id="gp_to"></select>
							</div>
						</div>
						<div class="col-xs-12">

							<input class="btn btn-primary btn-sm center-block" type='button'
								onClick='gp_convertIt()'
								onMouseOver="PlaySound('static/Sound/Wololo.mp3')"
								value='Convertir! (Wololo)' />
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	</div>

	<script>
		gp_currencySymbols()
	</script>
	<!-- Responsive a  faire
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
