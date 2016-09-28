<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Module d'affectation des nouveaux clients   Admin</title>
</head>
<body>
	<h1>Nouveaux Clients en attente d'affectation</h1>
		<thead>
		<tr>
			<th>Date</th>
			<th>Nom</th>
			<th>Prenom</th>
			<th width="20%"></th>
		</tr>
	</thead>
	<tr name="liste_newAaffect" ng-repeat="u in admin.inscription">
		<td><span ng-bind="u.date"></span></td>
		<td><span ng-bind="u.nom"></span></td>
		<td><span ng-bind="u.prenom"></span></td>
	</tr>
</body>
</html>