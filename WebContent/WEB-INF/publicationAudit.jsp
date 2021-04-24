<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Publier Audit</title>
<%@include file="bibliotheques.jsp"%>

</head>
<body>
	<div>Titre du modèle : ${ modele.getTitre() }</div>

	<div>Visibilité : ${ modele.isVisibilite() }</div>

	<div>
		<c:forEach items="${ modele.getListeQuestion() }" var="question">
			<p>
				Type de question :
				<c:out value="${ question.getTypeQuestion() }" />
				Question :
				<c:out value="${ question.getContenu() }" />
			</p>
		</c:forEach>
	</div>

	<form method="post" action="publierAudit">
		<input hidden="hidden" value="${ modele.getId() }" name="idModele" id="idModele">
		<div>
			<label for="titre">Titre de l'audit</label> <input type="text"
				name="titre" id="titre" required="required"/>
		</div>

		<!-- Jquery -->
		<div>
			<label for="dateOuverture">Date d'ouverture de l'audit</label>
			<input type="text" name="dateOuverture" id="dateOuverture" required="required" autocomplete="off"/>
		</div>

		<div>
			<label for="dateFermeture">Date de fermeture de l'audit</label>
			<input type="text" name="dateFermeture" id="dateFermeture" required="required" autocomplete="off"/>
		</div>
		
		<div>
			Cet audit concerne :
			<div>
				Responsables d'option :
				<select style="width: 200px;" multiple="multiple" name="listeRespOption">
					<c:forEach items="${ listeResponsableOption }" var="respOption">
						<option value="${ respOption.getId() }"><c:out value="${ respOption.getPrenom() }" /> <c:out value="${ respOption.getNom() }" /></option>
					</c:forEach>
				</select>
			</div>
			<div>
				Responsables d'UE :
				<select style="width: 200px;" multiple="multiple" name="listeRespUE">
					<c:forEach items="${ listeResponsableUE }" var="respUE">
						<option value="${ respUE.getId() }"><c:out value="${ respUE.getPrenom() }" /> <c:out value="${ respUE.getNom() }" /></option>
					</c:forEach>
				</select>
			</div>
			<div>
				Encadrant matière :
				<select style="width: 200px;" multiple="multiple" name="listeEncadrantMatiere">
					<c:forEach items="${ listeEncadrantMatiere }" var="encadrantMatiere">
						<option value="${ encadrantMatiere.getId() }"><c:out value="${ encadrantMatiere.getPrenom() }" /> <c:out value="${ encadrantMatiere.getNom() }" /></option>
					</c:forEach>
				</select>
			</div>
			<div>
				Eleves :
				<select style="width: 200px;" multiple="multiple" name="listeEleve">
					<c:forEach items="${ listeEleve }" var="eleve">
						<option value="${ eleve.getId() }"><c:out value="${ eleve.getPrenom() }" /> <c:out value="${ eleve.getNom() }" /></option>
					</c:forEach>
				</select>
			</div>
		</div>
		
		<div>
			<input type="submit" name="btn_publierAudit" value="Publier l'audit" onclick="return verifTitre()"/>		
		</div>

	</form>

<script type="text/javascript">
	$.datepicker.setDefaults({
		altField : "#datepicker",
		closeText : 'Fermer',
		prevText : 'Précédent',
		nextText : 'Suivant',
		currentText : 'Aujourd\'hui',
		monthNames : [ 'Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin',
				'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre',
				'Décembre' ],
		monthNamesShort : [ 'Janv.', 'Févr.', 'Mars', 'Avril', 'Mai', 'Juin',
				'Juil.', 'Août', 'Sept.', 'Oct.', 'Nov.', 'Déc.' ],
		dayNames : [ 'Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi',
				'Vendredi', 'Samedi' ],
		dayNamesShort : [ 'Dim.', 'Lun.', 'Mar.', 'Mer.', 'Jeu.', 'Ven.',
				'Sam.' ],
		dayNamesMin : [ 'D', 'L', 'M', 'M', 'J', 'V', 'S' ],
		weekHeader : 'Sem.',
		dateFormat : 'yy-mm-dd'
	});

	$(function() {
		$("#dateOuverture").datepicker({
			minDate:0
		});
	});
	
	$(function() {
		$("#dateFermeture").datepicker({
			minDate:0
		});
	});
	
	$('#dateOuverture').change(function() {
		var datestart=$("#dateOuverture").datepicker("getDate");
		$("#dateFermeture").datepicker('option', 'minDate', datestart);
	})
	
	$('#dateFermeture').change(function() {
		var datestart=$("#dateFermeture").datepicker("getDate");
		$("#dateOuverture").datepicker('option', 'maxDate', datestart);
	})
	
	$("#dateOuverture").on('keydown paste focus', function(e){
        if(e.keyCode != 9) // ignore tab
            e.preventDefault();
    });
	
	$("#dateFermeture").on('keydown paste focus', function(e){
        if(e.keyCode != 9) // ignore tab
            e.preventDefault();
    });

	function verifTitre(){
		var titre = document.getElementById("titre").value;
		if(titre.trim()==""){
			alert("Entrez un titre valide");
			return false;
		}
	}
	
</script>

</body>
</html>