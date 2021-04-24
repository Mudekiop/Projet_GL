<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Page de connexion</title>
<link rel="stylesheet" type="text/css" href="./connexion.css">
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:300,500,600"
	rel="stylesheet">
	
<style>
#error {
	color: #FF0000;
}
</style>
</head>
<body>
	<div id="arrierePlan">
		<div id="contenantDuLogin">
			<img id="logoEseo" src="./images/logo/logo_ESEO 1.svg" alt="LogoEseo">
			<form method="post" action="audits">
				<div>
					<input id="email" type="text" name="email"
						placeholder="IDENTIFIANT" autocomplete="off"/>
				</div>
				<div>
					<input id="mdp" type="password" name="mdp"
						placeholder="MOT DE PASSE" />
				</div>
				
				<div>
					<input id="boutonSeConnecter" type="submit" value="SE CONNECTER"
						name="connect" />
				</div>
				

			</form>
			<form method="post" action="home">
				<input id="boutonRetour" type="submit" value="RETOUR" />
			</form>
			<span id="motDePasseOublie">Mot de passe oublié ?</span>
			<p id="error">${errorMsg=="wrongPwd"?"Mauvais mot de passe":errorMsg=="notFound"?"Utilisateur inconnu":""}</p>

		</div>

	</div>

</body>
</html>