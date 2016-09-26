<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>AngularJS $http Exemple</title>
<style>
.username.ng-valid {
	background-color: lightgreen;
}

.username.ng-dirty.ng-invalid-required {
	background-color: red;
}

.username.ng-dirty.ng-invalid-minlength {
	background-color: yellow;
}

.email.ng-valid {
	background-color: lightgreen;
}

.email.ng-dirty.ng-invalid-required {
	background-color: red;
}

.email.ng-dirty.ng-invalid-email {
	background-color: yellow;
}
</style>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>

</head>
<body ng-app="myApp" class="ng-cloak" layout="row" layout-sm="column">
<!-- banniere en fixe dans le header pour la deco -->
	<header>
		<img id="banniere" src="static/imgs/banniere.jpg" />
	</header>
	
<!-- Affichage de la fleche sur petit ecran -->
	
	<div class="menucache col-sm-1 visible-xs"
		ng-controller="UserController as ctrl">	
			<img id="flechemenu" src="static/imgs/fd.jpg" />
	</div>		

<!-- Affichage de la navbar sur grand ecran -->			
	<div class="navbar col-sm-4 hidden-xs"
		ng-controller="UserController as ctrl">

		<img id="logo" src="static/imgs/logo.png" />


		<!-- Menu de connexion  -->
		<div class="formcontainer " ng-hide="false">
			<form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
				<input type="hidden" ng-model="ctrl.user.id" />


				<div class="form-group col-xl-12">
					<label class="col-xl-2 control-lable" for="file">Nom</label>
					<div class="col-xl-7">
						<input type="text" ng-model="ctrl.user.username" name="uname"
							class="username form-control input-sm"
							placeholder="Entez votre nom" required ng-minlength="3" />
						<div class="has-error" ng-show="myForm.$dirty">
							<span ng-show="myForm.uname.$error.required">Champ
								Obligatoire</span> <span ng-show="myForm.uname.$error.minlength">longueur
								minimum requise est de 3</span> <span ng-show="myForm.uname.$invalid">Ce
								champ est invalide </span>
						</div>
					</div>
				</div>
				<div class="form-group col-xl-12">
					<label class="col-xl-2 control-lable" for="file">Mot de
						passe</label>
					<div class="col-xl-7">
						<input type="mdp" ng-model="ctrl.user.mdp" name="mdp"
							class="email form-control input-sm"
							placeholder="Entrez votre MDP" required />
						<div class="has-error" ng-show="myForm.$dirty">
							<span ng-show="myForm.mdp.$error.required">Ce champ est
								obligatoire</span> <span ng-show="myForm.mdp.$invalid">Ce champ
								est invalide</span>
						</div>
					</div>
				</div>
				<div class="form-group form-actions floatRight">
					<input type="submit"
						value="{{!ctrl.user.id ? 'Ajouter' : 'Modifier'}}"
						class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
					<button type="button" ng-click="ctrl.reset()"
						class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Mdp
						oublié</button>
				</div>
			</form>
		</div>


		<!-- Menu du Conseiller -->
		<div class="container-fluid" ng-hide="true">
			<div class="list-group">
				<a href="#" class="list-group-item active">
					<h4 class="list-group-item-heading">Recherche client</h4>
					<p class="list-group-item-text">Rechercher un client</p>
				</a> <a href="#" class="list-group-item">
					<h4 class="list-group-item-heading">Recherche IBAN</h4>
					<p class="list-group-item-text">Rechercher un compte par l'IBAN</p>
				</a> <a href="#" class="list-group-item">
					<h4 class="list-group-item-heading">Demandes</h4>
					<p class="list-group-item-text">Liste des demandes à traiter</p>
				</a>
			</div>
		</div>

		<!-- Menu du Client -->
		<div class="container-fluid" ng-hide="true">
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
		<div class="container-fluid" ng-hide="true">
			<div class="list-group">
				<a href="#" class="list-group-item active">
					<h4 class="list-group-item-heading">Demandes d'ouvertures</h4>
					<p class="list-group-item-text">Afficher la liste des demande
						d'ouverture</p>
				</a> <a href="#" class="list-group-item">
					<h4 class="list-group-item-heading">Recherche des conseillers</h4>
					<p class="list-group-item-text">List Group Item Text</p>
				</a> <a href="#" class="list-group-item">
					<h4 class="list-group-item-heading">Nouveau conseiller</h4>
					<p class="list-group-item-text">List Group Item Text</p>
				</a>
			</div>
		</div>



	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
	<script src="<c:url value='/static/js/app.js' />"></script>
	<script src="<c:url value='/static/js/service/user_service.js' />"></script>
	<script
		src="<c:url value='/static/js/controller/user_controller.js' />"></script>
</body>
</html>