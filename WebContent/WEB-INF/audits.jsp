<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Mulish:wght@300;400;500;600;700;800&display=swap"
	rel="stylesheet">
<title>Audits</title>
</head>
<body>
	<%@include file="menu.jsp"%>
	<div id="containerVuePrincipale">
		<span class="titre">Mes audits</span>
		<div id="contenuPrincipale">
			<div id=titreParamètres>
				<div id=titre>
					<span class="titre">Ensemble de mes audits</span>
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
				<c:if test="${ !empty sessionScope.email }">
					<table>
						<caption></caption>
						<tr>
							<th scope="col">Audit n°</th>
							<th scope="col">Sujet</th>
							<th scope="col">Date d'ouverture</th>
							<th scope="col">Date de fermeture</th>
							<th scope="col">Etat</th>
						</tr>
						<c:forEach items="${listeAudits}" var="current" varStatus="status">
							<tr>
								<th scope="row">${current.getId()}</th>
								<th scope="row">${current.getTitre()}</th>
								<th scope="row">${current.getDateOuverture()}</th>
								<th scope="row">${current.getDateCloture()}</th>
								<th scope="row">${current.getStatut()}</th>
							</tr>
						</c:forEach>
					</table>
				</c:if>
				<c:if test="${ empty sessionScope.email }">
					<p>Vous n'avez pas d'audits</p>
				</c:if>
			</div>

			<form method="post" action="home">
				<input id="boutonRetour" type="submit" value="RETOUR" />
			</form>
		</div>
	</div>
</body>
</html>