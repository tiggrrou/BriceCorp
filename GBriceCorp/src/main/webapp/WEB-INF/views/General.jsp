
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>GestBank</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>

</head>

<body ng-app="myApp" class="ng-cloak" layout="row" ng-controller="UserController as ctrl">


	<!-- Affichage de la navbar -->

	<div class="navbar col-xs-3" flex  >
		<div id="contenu_navbar" class="contenu_navbar" ng-hide="nav_cache">
			<img id="logo" src="static/imgs/logo.png" />
			<!-- Menu de connexion  -->
			<div class="formcontainer" ng-hide="{{connexion_cache}}" >
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
								<input type="password" ng-model="ctrl.user.motDePasse" name="password"
									class="password form-control input-sm"
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
								class="btn btn-primary btn-sm" 
								
								>
						</div>
					</div>
				</form>
			</div>

			<!-- Menu du Conseiller -->
			<div class="container-fluid" ng-hide="{{conseiller_cache}}" >
				<div class="list-group">
					<a href="#" class="list-group-item"
						ng-click="rechercheClient_cache = !rechercheClient_cache ; rechercheIBAN_cache = false">
						<h4 class="list-group-item-heading">Recherche client</h4>
						<p class="list-group-item-text">Rechercher un client</p>
					</a>

					<!-- Recherche client  -->
					<div class="formcontainer" ng-hide="!rechercheClient_cache">
						<form ng-submit="ctrl.connect(prenom.value, nom.value)"
							name="formConnexion" class="form-horizontal">
							<input type="hidden" ng-model="ctrl.user.id" />
							<div class="row">
								<div class="form-group col-xs-10">
									<label class="col-xl-2 control-lable" for="file">Prenom</label>
									<div class="col-xl-10">
										<input type="text" ng-model="ctrl.user.login" name="prenom"
											class="prenom form-control input-sm"
											placeholder="Entez votre prenom" required />
										<div class="has-error" ng-show="formConnexion.$dirty">
											<span ng-show="formConnexion.login.$error.required">Champ
												obligatoire</span>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-10">
									<label class="col-xl-2 control-lable" for="file">nom</label>
									<div class="col-xl-10">
										<input type="text" ng-model="ctrl.user.nom" name="password"
											class="password form-control input-sm"
											placeholder="Entrez votre nom" required />
										<div class="has-error" ng-show="formConnexion.$dirty">
											<span ng-show="formConnexion.password.$error.required">Champ
												obligatoire</span>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-actions floatRight">
									<input type="submit" value="Recherche"
										class="btn btn-primary btn-sm"
										ng-disabled="formConnexion.$invalid">
								</div>
							</div>
						</form>
					</div>

					<a href="#" class="list-group-item" active="true"
						ng-click="rechercheIBAN_cache = !rechercheIBAN_cache; rechercheClient_cache = false">
						<h4 class="list-group-item-heading">Recherche IBAN</h4>
						<p class="list-group-item-text">Rechercher un compte par
							l'IBAN</p>
					</a>

					<!-- Recherche IBAN  -->
					<div class="formcontainer" ng-hide="!rechercheIBAN_cache">
						<form ng-submit="ctrl.connect(iban.value)" name="formConnexion"
							class="form-horizontal">
							<input type="hidden" ng-model="ctrl.user.id" />
							<div class="row">
								<div class="form-group col-xs-10">
									<label class="col-xl-2 control-lable" for="file">iban</label>
									<div class="col-xl-10">
										<input type="text" ng-model="ctrl.user.login" name="iban"
											class="prenom form-control input-sm"
											placeholder="Entez l'iban" required />
										<div class="has-error" ng-show="formConnexion.$dirty">
											<span ng-show="formConnexion.login.$error.required">Champ
												obligatoire</span>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-actions floatRight">
									<input type="submit" value="Recherche"
										class="btn btn-primary btn-sm"
										ng-disabled="formConnexion.$invalid">
								</div>
							</div>
						</form>
					</div>

					<a href="#" class="list-group-item">
						<h4 class="list-group-item-heading">Demandes</h4>
						<p class="list-group-item-text">Liste des demandes à traiter</p>
					</a>
				</div>
			</div>


			<!-- Menu du Client -->
			<div class="container-fluid" ng-hide="{{client_cache}}">
				<div class="list-group">
					<a href="#" class="list-group-item active">
						<h4 class="list-group-item-heading">Mes Comptes</h4>
						<p class="list-group-item-text">Synthèse de vos comptes</p>
					</a> <a href="#" class="list-group-item">
						<h4 class="list-group-item-heading">Virements</h4>
						<p class="list-group-item-text">Effectuer un virement</p>
					</a> <a href="#" class="list-group-item">
						<h4 class="list-group-item-heading">Informations Personnelles</h4>
						<p class="list-group-item-text">Consulter et modifier vos
							informations personnelles</p>
					</a> <a href="#" class="list-group-item">
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
					<a href="#" class="list-group-item active">
						<h4 class="list-group-item-heading">Demandes d'ouvertures</h4>
						<p class="list-group-item-text">Afficher la liste des demande
							d'ouverture</p>
					</a> <a href="#" class="list-group-item"
						ng-click="rechercheConseiller_cache = !rechercheConseiller_cache">
						<h4 class="list-group-item-heading">Recherche des conseillers</h4>
						<p class="list-group-item-text">List Group Item Text</p>
					</a>


					<!-- Recherche conseiller  -->
					<div class="formcontainer" ng-hide="!rechercheConseiller_cache">
						<form ng-submit="ctrl.connect(adresse.value, nom.value)"
							name="formConnexion" class="form-horizontal">
							<input type="hidden" ng-model="ctrl.user.id" />
							<div class="row">
								<div class="form-group col-xs-10">
									<label class="col-xl-2 control-lable" for="file">adresse</label>
									<div class="col-xl-10">
										<input type="text" ng-model="ctrl.user.login" name="adresse"
											class="prenom form-control input-sm"
											placeholder="Entez l'adresse" required />
										<div class="has-error" ng-show="formConnexion.$dirty">
											<span ng-show="formConnexion.login.$error.required">Champ
												obligatoire</span>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-10">
									<label class="col-xl-2 control-lable" for="file">nom</label>
									<div class="col-xl-10">
										<input type="text" ng-model="ctrl.user.nom" name="password"
											class="password form-control input-sm"
											placeholder="Entrez votre nom" required />
										<div class="has-error" ng-show="formConnexion.$dirty">
											<span ng-show="formConnexion.password.$error.required">Champ
												obligatoire</span>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-actions floatRight">
									<input type="submit" value="Recherche"
										class="btn btn-primary btn-sm"
										ng-disabled="formConnexion.$invalid">
								</div>
							</div>
						</form>
					</div>

					<a href="#" class="list-group-item">
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

<div class="banniere "  layout="column" flex>
	<div flex>	
		<img class="langue" src="static/imgs/en.png" ng-hide="lang_cache" ng-click="lang_cache = !lang_cache" />
		<img class="langue" src="static/imgs/fr.png" ng-show="lang_cache" ng-click="lang_cache = !lang_cache" />
		</div>
		<div flex>	
	<input type="button" class="btn btn-danger" value="Deconnexion" ng-click="session_delete()" ng-hide="!{{connexion_cache}}"/>
	
	</div>

</div>

   
	 



	<!-- Tableau d'affichage des utilisateurs -->


	<div class="generic-container" ng-controller="UserController as ctrl" >
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">
			<span class="lead">Liste des utilisateurs </span>
		</div>
		<div class="tablecontainer">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Login</th>
						<th>MPD</th>	
																	<th>type</th>
						<th>Nom</th>
						<th>Adresse</th>
						<th>Email</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="u in ctrl.users">
						<td><span ng-bind="u.identifiant"></span></td>
						<td><span ng-bind="u.motDePasse"></span></td>	
																	<td><span ng-bind="u.typeUser"></span></td>
						<td><span ng-bind="u.nom"></span></td>
						<td><span ng-bind="u.adresse"></span></td>
						<td><span ng-bind="u.mail"></span></td>

					</tr>
				</tbody>
			</table>
		</div>







</div>
</div>
	<ng-view></ng-view>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
	<script src="<c:url value='/static/js/app.js' />"></script>
	<script src="<c:url value='/static/js/service/user_service.js' />"></script>
	<script
		src="<c:url value='/static/js/controller/user_controller.js' />"></script>
<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-route.js"></script>
	<script
		src="<c:url value='/static/js/controller/ConfigRouter.js' />"></script>

	<!-- Responsive à faire
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
