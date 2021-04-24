<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Mulish:wght@300;400;500;600;700;800&display=swap"
	rel="stylesheet">
<title>Mon Profil</title>
</head>
<body>
	<%@include file="menu.jsp"%>
	<div id="containerVuePrincipale">
		<span class="titre">Mon profil</span>
		<div id="contenuPrincipale">
			<div id=titreParamètres>
				<div id=titre>
					<span class="titre">Informations détaillées</span>
				</div>
			</div>
			<hr>
			<section id="monProfil">
				<c:if test="${ !empty sessionScope.email }">
					<div>
						<div class="left">
							<p>Prénom :</p>
							<p>Nom :</p>
							<p>Adresse mail :</p>
							<p>Rôle :</p>
							<p>Avatar :</p>
						</div>
						<div class="right">
							<p>${sessionScope.prenom}</p>
							<p>${sessionScope.nom}</p>
							<p>${sessionScope.email}</p>
							<p>${sessionScope.role}</p>
							<img src="images/icones/photoProfil.png" alt="PhotoProfil">
						</div>
					</div>
				</c:if>
				<c:if test="${ empty sessionScope.email }">
					<p>Vous n'avez pas d'audits</p>
				</c:if>
			</section>

			<form method="post" action="home">
				<input id="boutonRetour" type="submit" value="RETOUR" />
			</form>
		</div>
	</div>

</body>
</html>