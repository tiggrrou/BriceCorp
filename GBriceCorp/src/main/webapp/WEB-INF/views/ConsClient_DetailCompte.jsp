<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page Detail et Histo d'un compte pour Client</title>
</head>
<body>
	<div>
		<h3> Historique de votre compte  </h3>
		<tr>
			<td> Nom : </td>
			<td> {{client.nom}} </td>
			<td> Libellé Compte </td>
			<td> {{compte.libellé}} </td>
		</tr>
		<tr>
			<td> Prenom : </td>
			<td> {{client.prebom}} </td>
			<td> Date d'ouverture </td>
			<td> {{compte.dateOuverture}} </td>
		</tr>
		<tr>
			<td> Date de naissance : </td>
			<td> {{client.dateNaiss}} </td>
			<td> Découvert autorisé </td>
			<td> {{compte.decouvert}} </td>
		</tr>
		<tr>
			<td> Adresse : </td>
			<td> {{client.adresse}} </td>
		</tr>
		<tr>
			<td> Numero de tel : </td>
			<td> {{client.telephone}} </td>
		</tr>
		
		
	</div>
	<thead>
		<tr>
			<th>Date</th>
			<th>Libellé</th>
			<th>Type</th>
			<th>Montant</th>
			<th width="20%"></th>
		</tr>
	</thead>
	<tr name="DetailHisto_comptes" ng-repeat="u in client.mouvCompte">
		<td><span ng-bind="u.date"></span></td>
		<td><span ng-bind="u.libellé"></span></td>
		<td><span ng-bind="u.type"></span></td>
		<td><span ng-bind="u.montant"></span></td>
	</tr>
</body>
</body>
</html>