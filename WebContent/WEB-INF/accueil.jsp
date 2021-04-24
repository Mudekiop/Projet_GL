<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="style.css">
<title>Page d'accueil</title>
</head>
<body>
	<h1>Page d'accueil</h1>

	<a href="audits">Mes audits</a>
	
	
	<!--  FORMULAIRE DE CONNEXION -->
	<c:choose>
		<c:when test="${ empty sessionScope.email || sessionScope.mdp }">
			<a href="connexion"><input type="button" value="Connexion"></a>	
		</c:when>
		
		<c:when test="${ !empty sessionScope.email || sessionScope.mdp }">
			<p>Connecté en tant que ${sessionScope.prenom} ${ sessionScope.nom } !</p>
			
			<form method="post" action="home">
				<input type="submit" value="Deconnexion" name="deco" id="deco"/>
			</form>
		</c:when>
	</c:choose>
</body>
</html>