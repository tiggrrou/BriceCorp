<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page post connexion client</title>
</head>
<body>
	<tr name="liste_comptes" ng-repeat="u in client.comptes">
		<td><span ng-bind="u.libellé"></span></td>
		<td><span ng-bind="u.iban"></span></td>
		<td><span ng-bind="u.solde"></span></td>
	</tr>
</body>
</html>