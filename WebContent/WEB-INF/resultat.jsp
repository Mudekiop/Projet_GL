<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Résultat de l'audit</title>
<link rel="stylesheet" href="style.css">
<link rel="preconnect" href="https://fonts.gstatic.com/%22%3E">
<link
	href="https://fonts.googleapis.com/css2?family=Mulish:wght@300;400;500;600;700;800&display=swap"
	rel="stylesheet">
<style>
a {
	text-decoration: none;
	color: #A4A6B3;
}
</style>
</head>

<body>
	<%@include file="menu.jsp"%>

	<!-- Page content -->
	<div id="containerVuePrincipale">
		<span class="titre">Résultats</span>
		<div id="contenuPrincipale">
			<div id=titreParamètres>
				<div id=titre>
					<span class="titre">Résultats de l'audit :
						${reponse.get(0).getAudit().getTitre() }</span>
				</div>
			</div>
			<div id=tableau>
				<p>
					<c:out value="1)" />
					<c:out value="${ reponse.get(0).getQuestion().getContenu() }" />
					<br />
					<c:out
						value="Réponse de : ${reponse.get(0).getUtilisateur().getPrenom()} ${reponse.get(0).getUtilisateur().getNom()}" />
					<br />
					<c:out value="${ reponse.get(0).getContenuReponse() }" />
				</p>
				<c:forEach var="i" begin="1" end="${reponse.size() - 1}">
					<c:choose>
						<c:when
							test="${ !reponse.get(i).getQuestion().getContenu().equals(reponse.get(i-1).getQuestion().getContenu())}">
							<p>
								<c:out value="${i+1}) " />
								<c:out value="${ reponse.get(i).getQuestion().getContenu() }" />
								<br />
								<c:out
									value="Réponse de : ${reponse.get(i).getUtilisateur().getPrenom()} ${reponse.get(i).getUtilisateur().getNom()}" />
								<br />
								<c:out value="${ reponse.get(i).getContenuReponse() }" />
							</p>
						</c:when>
						<c:when
							test="${ reponse.get(i).getQuestion().getContenu().equals(reponse.get(i-1).getQuestion().getContenu())}">
							<p>
								<c:out
									value="Réponse de : ${reponse.get(i).getUtilisateur().getPrenom()} ${reponse.get(i).getUtilisateur().getNom()}" />
								<br />
								<c:out value="${ reponse.get(i).getContenuReponse() }" />
							</p>
						</c:when>
					</c:choose>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>