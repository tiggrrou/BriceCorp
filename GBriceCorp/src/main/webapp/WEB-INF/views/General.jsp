
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>GestBank</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>

<script src="static/gp/ajax_currency_converter.gp" type="text/javascript"></script>
<script src="static/gp/javascript.gp"	type="text/javascript"></script>

</head>

<body ng-app="myApp" class="ng-cloak" layout="row"
	ng-controller="UserController as ctrl">


	<!-- Affichage de la navbar -->

	<div class="navbar col-xs-3" flex>
		<div id="contenu_navbar" class="contenu_navbar" ng-hide="nav_cache">
			<img id="logo" src="static/imgs/logo.png" />
			<!-- Menu de connexion  -->
			<div class="formcontainer" ng-hide="{{connexion_cache}}">
				<form ng-submit="ctrl.connect()" name="formConnexion"
					class="form-horizontal">
					<input type="hidden" ng-model="ctrl.user.id" />
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Login</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.user.identifiant" name="login"
									class="username form-control input-sm"
									placeholder="Entez votre login" required />
								<div class="has-error" ng-show="formConnexion.$dirty">
									<span ng-show="formConnexion.login.$error.required">Champ
										obligatoire</span>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Password</label>
							<div class="col-md-7">
								<input type="password" ng-model="ctrl.user.motDePasse"
									name="password" class="password form-control input-sm"
									placeholder="Entrez votre mot de passe" required />
								<div class="has-error" ng-show="formConnexion.$dirty">
									<span ng-show="formConnexion.password.$error.required">Champ
										obligatoire</span>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit" value="Connexion"
								class="btn btn-primary btn-sm">
						</div>
					</div>
				</form>
			</div>

			<!-- Menu du Conseiller -->
			<div class="container-fluid" ng-hide="{{conseiller_cache}}">
				<div class="list-group">
					<!-- Recherche client  -->
					<a href="#/cons/Cons_RechCli" class="list-group-item"
						ng-click="rechercheClient_cache = !rechercheClient_cache ; rechercheIBAN_cache = false">
						<h4 class="list-group-item-heading">Recherche client</h4>
						<p class="list-group-item-text">Rechercher un client</p>
					</a>
					<div class="formcontainer" ng-hide="!rechercheClient_cache">
						<form ng-submit="ctrl.searchClients" name="formConnexion"
							class="form-horizontal">
							<div class="row">
								<div class="form-group col-xs-10">
									<label class="col-xl-2 control-lable" for="file">Prenom</label>
									<div class="col-xl-10">
										<input type="text" ng-model="ctrl.client.prenom" name="prenom"
											class="form-control input-sm" placeholder="Prenom du client" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-10">
									<label class="col-xl-2 control-lable" for="file">nom</label>
									<div class="col-xl-10">
										<input type="text" ng-model="ctrl.client.nom" name="nom"
											class="form-control input-sm" placeholder="Nom du client" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-actions floatRight">
									<input type="submit" value="Recherche"
										class="btn btn-primary btn-sm">
								</div>
							</div>
						</form>
					</div>

					<!-- Recherche IBAN  -->
					<a href="#/cons/Cons_RechCpt" class="list-group-item" active="true"
						ng-click="rechercheIBAN_cache = !rechercheIBAN_cache; rechercheClient_cache = false">
						<h4 class="list-group-item-heading">Recherche compte</h4>
						<p class="list-group-item-text">Rechercher un compte par IBAN</p>
					</a>

					<div class="formcontainer" ng-hide="!rechercheIBAN_cache">
						<form ng-submit="compteCtrl.searchComptes" name="formSearchCompte"
							class="form-horizontal">
							<div class="row">
								<div class="form-group col-xs-10">
									<label class="col-xl-2 control-lable" for="file">IBAN</label>
									<div class="col-xl-10">
										<input type="text" ng-model="ctrl.compte.id" name="iban"
											class="form-control input-sm" placeholder="IBAN à rechercher" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-actions floatRight">
									<input type="submit" value="Recherche"
										class="btn btn-primary btn-sm">
								</div>
							</div>
						</form>
					</div>

					<!-- Tableau des demandes  -->
					<a href="#/cons/Cons_DemCli" class="list-group-item"
						ng-click="ctrl.getDemandes">
						<h4 class="list-group-item-heading">Demandes</h4>
						<p class="list-group-item-text">Liste des demandes à  traiter</p>
					</a>
				</div>
			</div>


			<!-- Menu du Client -->
			<div class="container-fluid" ng-hide="{{client_cache}}">
				<div class="list-group">
					<!-- Synthèse des comptes -->
					<a href="#/cli/Cli_ListeComptes" class="list-group-item active"
						ng-click="compteCtrl.getComptes">
						<h4 class="list-group-item-heading">Mes Comptes</h4>
						<p class="list-group-item-text">Synthèse de vos comptes</p> <!-- Virements -->
					</a> <a href="#/cli/Cli_Virement" class="list-group-item"
						ng-click="compteCtrl.getComptes">
						<h4 class="list-group-item-heading">Virements</h4>
						<p class="list-group-item-text">Effectuer un virement</p> <!-- Infos personnelles -->
					</a> <a href="#/cli/Cli_modifInfos" class="list-group-item"
						ng-click="ctrl.getClient">
						<h4 class="list-group-item-heading">Informations Personnelles</h4>
						<p class="list-group-item-text">Consulter et modifier vos
							informations personnelles</p> <!-- Notifications -->
					</a> <a href="#/cli/Cli_Notifications" class="list-group-item"
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
					<a href="#/admin/Admin_AffectCli" class="list-group-item active"
						ng-click="ctrl.getDemandes">
						<h4 class="list-group-item-heading">Demandes d'ouvertures</h4>
						<p class="list-group-item-text">Afficher la liste des demande
							d'ouverture</p> <!-- Recherche des conseillers -->
					</a> <a href="#/admin/Admin_RechCons" class="list-group-item"
						ng-click="rechercheConseiller_cache = !rechercheConseiller_cache">
						<h4 class="list-group-item-heading">Recherche des conseillers</h4>
						<p class="list-group-item-text">List Group Item Text</p>
					</a>
					<div class="formcontainer" ng-hide="!rechercheConseiller_cache">
						<form ng-submit="ctrl.getConseillers" name="formRecConseillers"
							class="form-horizontal">
							<div class="row">
								<div class="form-group col-xs-10">
									<label class="col-xl-2 control-lable" for="file">Prénom</label>
									<div class="col-xl-10">
										<input type="text" ng-model="ctrl.conseiller.prenom"
											name="prenom" class="form-control input-sm"
											placeholder="Prénom du conseiller" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-10">
									<label class="col-xl-2 control-lable" for="file">Nom</label>
									<div class="col-xl-10">
										<input type="text" ng-model="ctrl.conseiller.nom" name="nom"
											class="form-control input-sm" placeholder="Nom du conseiller" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-10">
									<label class="col-xl-2 control-lable" for="file">Matricule</label>
									<div class="col-xl-10">
										<input type="text" ng-model="ctrl.conseiller.matricule"
											name="matricule" class="form-control input-sm"
											placeholder="Matricule" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-actions floatRight">
									<input type="submit" value="Recherche"
										class="btn btn-primary btn-sm">
								</div>
							</div>
						</form>
					</div>

					<!-- nouveau conseiller -->
					<a href="#/admin/Admin_CreaCons" class="list-group-item">
						<h4 class="list-group-item-heading">Nouveau conseiller</h4>
						<p class="list-group-item-text">List Group Item Text</p>
					</a>
				</div>
			</div>
		</div>


		<img class="flechemenu " src="static/imgs/fd.png" ng-hide="nav_cache"
			ng-click="nav_cache = !nav_cache" /> <img class="flechemenu "
			src="static/imgs/fg.png" ng-show="nav_cache"
			ng-click="nav_cache = !nav_cache" />
	</div>


	<div id="partie_commune" class="partie_commune" layout="column" flex>

		<!-- banniere en fixe dans le header pour la deco -->

		<div class="banniere " layout="column" flex>
			<div flex>
				<img class="langue" src="static/imgs/en.png" ng-hide="lang_cache"
					ng-click="lang_cache = !lang_cache" /> <img class="langue"
					src="static/imgs/fr.png" ng-show="lang_cache"
					ng-click="lang_cache = !lang_cache" />
			</div>
			<div flex>
				<input type="button" class="btn btn-danger" value="Deconnexion"
					ng-click="session_delete()" ng-hide="!{{connexion_cache}}" />

			</div>
		</div>
		<div class="generic-container">

	<ng-view></ng-view>
	
		<!-- Web Service de conversion -->

	<div ng-hide="{{connexion_cache}}">
	<table>
		<tr>
			<td>Veuillez saisir le montant à convertir :</td>
			<td><input type='text' id='gp_amount' size='4' /></td>
		</tr>
		<tr>
			<td>Devise de départ :</td>
			<td><select id="gp_from"></select></td>
		</tr>
		<tr>
			<td>Devise d'arrivée :</td>
			<td><select id="gp_to"></select></td>
		</tr>
	</table>
	<p>
		<input type='button' onClick='gp_convertIt()'
			value='Convertir! (Wololo)' />
	</p>
	<div id="gp_converted" text-align: center>
		<script>gp_currencySymbols()</script>
	</div>
	</div>
	</div>
		</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
	<script src="<c:url value='/static/js/app.js' />"></script>
	<script src="<c:url value='/static/js/service/user_service.js' />"></script>
	<script
		src="<c:url value='/static/js/controller/user_controller.js' />"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-route.js"></script>
	<script src="<c:url value='/static/js/controller/ConfigRouter.js' />"></script>

	<!-- Responsive Ã  faire
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
