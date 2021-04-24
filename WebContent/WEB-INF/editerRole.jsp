<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Mulish:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">

<title>Editer le profil</title>
</head>
<body>
	<%@include file="menu.jsp"%>
	<div id="containerVuePrincipale">
		<span class="titre">Utilisateur sélectionné</span>
		<div id="contenuPrincipale">
			<div id=titreParamètres>
				<div id=titre>
					<span class="titre">Rôle(s) de l'utilisateur sélectionné</span>
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
							<th scope="col">Role</th>
						</tr>
					</thead>
					<tbody>
						<tr>
			    			<th scope="row"><c:out value="${ utilisateur.nom }" /></th>
			    			<th scope="row"><c:out value="${ utilisateur.prenom }" /></th>
			    			<th scope="row"><c:out value="${ utilisateur.email }" /></th>
			    			<th scope="row"><c:out value="${ utilisateur.listeRoles }" /></th>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div id=titreParamètres>
				<div id=titre>
					<span class="titre">Ajouter un rôle à l'utilisateur :</span>
				</div>
			</div>
			<form action="editerRole?id=${ utilisateur.id }" method="post">
				<div class="formfield-select">
					<div class="formfield-select--container">
				        <select name="roleAjouter">
				            <c:forEach var="listeRoles" items="${roles}">
				                <option value="${listeRoles}" selected="selected"> ${listeRoles} </option>
				            </c:forEach>
				        </select>
		        	</div>
				</div> 
		        <input id="boutonAjouter" type="submit" value="Ajouter" name="ajouterUser">
		    </form>  
		    
		    <div id=titreParamètres>
				<div id=titre>
					<span class="titre">Supprimer un rôle à l'utilisateur :</span>
				</div>
			</div>
			<form action="editerRole?id=${ utilisateur.id }" method="post">
				<div class="formfield-select">
					<div class="formfield-select--container">
				        <select name="roleSupprimer">
				            <c:forEach var="listeRolesUtilisateur" items="${utilisateur.listeRoles}">
				                <option value="${listeRolesUtilisateur}" selected="selected"> ${listeRolesUtilisateur} </option>
				            </c:forEach>
				        </select>
				    </div>
				</div>
		        <input id="boutonSupprimer" type="submit" value="Supprimer" name="supprimerUser">
		    </form>
		    
		    <div id=titreParamètres>
		    	<div id=titre>
		    		<a href="/Projet_GL_2/accueilAdmin">Retour</a>
		    	</div>
		    </div>
    
    	</div>
	</div> 
</body>
</html>