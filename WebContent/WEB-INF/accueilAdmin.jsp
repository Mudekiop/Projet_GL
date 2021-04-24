<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Mulish:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">

<title>Page d'accueil admin</title>
</head>
<body>
	<%@include file="menu.jsp"%>
	<div id="containerVuePrincipale">
		<span class="titre">Liste des utilisateurs</span>
		<div id="contenuPrincipale">
			<div id=titreParamètres>
				<div id=titre>
					<span class="titre">Ensemble des utilisateurs</span>
				</div>
				<div id=parametres>
					<div class=objetParametre>
						<span role='link' class="lienObjetParametre"> <img
							src="images/icones/tri.png" class="iconeParametre"
							alt="Trier par"> <span>Trier par</span>
						</span>
					</div>
					<div class=objetParametre>
						<span role='link' class="lienObjetParametre"> <img
							src="images/icones/filtre.png" class="iconeParametre"
							alt="Filtrer par"> <span>Filtrer par</span>
						</span>
					</div>
				</div>
			</div>
			<div id="tableau">
				<table>
					<caption></caption>
					<thead>
						<tr>
							<th scope="col">Nom</th>
							<th scope="col">Prenom</th>
							<th scope="col">Email</th>
							<th scope="col">Editer le role</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ utilisateurs }" var="utilisateurs" varStatus="status">
							<tr>
			    				<th scope="row"><c:out value="${ utilisateurs.nom }" /></th>
			    				<th scope="row"><c:out value="${ utilisateurs.prenom }" /></th>
			    				<th scope="row"><c:out value="${ utilisateurs.email }" /></th>
			    				<th scope="row"><a href="/Projet_GL_2/editerRole?id=${ utilisateurs.id }">Role</a></th>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>