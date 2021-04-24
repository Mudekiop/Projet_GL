<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Accueil Resultat</title>
<link rel="stylesheet" href="style.css">
<link rel="preconnect" href="https://fonts.gstatic.com/%22%3E">
<link
	href="https://fonts.googleapis.com/css2?family=Mulish:wght@300;400;500;600;700;800&display=swap"
	rel="stylesheet">
<style>
a {
	text-decoration: none;
	color: #000000;
}
</style>
</head>
<body>
	<%@include file="menu.jsp"%>
	<div id="containerVuePrincipale">
		<span class="titre">Résultats</span>
		<div id="contenuPrincipale">
			<div id=titreParamètres>
				<div id=titre>
					<span class="titre">Résultats pour mes audits</span>
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
					<tr>
						<th scope="col">Numéro audit</th>
						<th scope="col">Titre</th>
						<th scope="col">Clôturé le</th>
					</tr>
					<c:forEach var="i" begin="0" end="${resultat.size() -1}">
						<tr>
							<th scope="row"><c:out
									value=" <a href='Resultat?id=${resultat.get(i).getId()}'> ${resultat.get(i).getId() }"
									escapeXml="false" /></th>
							<th scope="row"><c:out value="${resultat.get(i).getTitre()}" /></th>
							<th scope="row"><c:out
									value="${resultat.get(i).getDateCloture()}" /></th>
						</tr>
					</c:forEach>

				</table>

			</div>
		</div>
	</div>
</body>
</html>