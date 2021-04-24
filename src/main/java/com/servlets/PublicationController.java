package com.servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JSpinner.ListEditor;

import com.beans.Audit;
import com.beans.Modele;
import com.beans.Role;
import com.beans.Utilisateur;
import com.dao.AuditDao;
import com.dao.DaoFactory;
import com.dao.ModeleDao;
import com.dao.UtilisateurDao;

/**
 * Servlet implementation class publicationController
 */
@WebServlet("/publicationController")
public class PublicationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ModeleDao modeleDao;
	private UtilisateurDao utilisateurDao;
	private AuditDao auditDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicationController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		try {
			this.modeleDao = daoFactory.getModeleDao();
			this.utilisateurDao = daoFactory.getUtilisateurDao();
			this.auditDao = daoFactory.getAuditDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Si on choisit un audit
		if (request.getParameter("id") != null) {
			String id = request.getParameter("id");
			Modele modele = modeleDao.getModeleById(Integer.valueOf(id));
			List<Utilisateur> listeResponsableOption = utilisateurDao.getListeUtilisateurByRole(Role.RESPONSABLE_OPTION);
			List<Utilisateur> listeResponsableUE = utilisateurDao.getListeUtilisateurByRole(Role.RESPONSABLE_UE);
			List<Utilisateur> listeEncadrantMatiere = utilisateurDao.getListeUtilisateurByRole(Role.ENCADRANT_MATIERE);
			List<Utilisateur> listeEleve = utilisateurDao.getListeUtilisateurByRole(Role.ELEVE);
			request.setAttribute("modele", modele);
			request.setAttribute("listeResponsableOption", listeResponsableOption);
			request.setAttribute("listeResponsableUE", listeResponsableUE);
			request.setAttribute("listeEncadrantMatiere", listeEncadrantMatiere);
			request.setAttribute("listeEleve", listeEleve);
			this.getServletContext().getRequestDispatcher("/WEB-INF/publicationAudit.jsp").forward(request, response);
		}
		// Sinon on affiche tous les modeles
		else {
			List<Modele> liste = modeleDao.getListeModeles();
			request.setAttribute("listeModeles", liste);
			this.getServletContext().getRequestDispatcher("/WEB-INF/listeModeles.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idModele = Integer.valueOf(request.getParameter("idModele"));
		Modele modele = modeleDao.getModeleById(idModele);
		Audit audit = new Audit();
		audit.setAuteur(utilisateurDao.getUtilisateurById(2)); // TODO Changer l'utilisateur en fonction de la personne
																// connectée
		audit.setModele(modele);
		audit.setTitre(request.getParameter("titre"));
		Date dateOuverture = Date.valueOf(request.getParameter("dateOuverture"));
		audit.setDateOuverture(dateOuverture);
		Date dateFermeture = Date.valueOf(request.getParameter("dateFermeture"));
		audit.setDateCloture(dateFermeture);
		List<String> listeIdConcernes = new ArrayList<String>();
		if(request.getParameterValues("listeRespOption")!=null)
			listeIdConcernes.addAll(Arrays.asList(request.getParameterValues("listeRespOption")));
		if(request.getParameterValues("listeRespUE")!=null)
			listeIdConcernes.addAll(Arrays.asList(request.getParameterValues("listeRespUE")));
		if(request.getParameterValues("listeEncadrantMatiere")!=null)
			listeIdConcernes.addAll(Arrays.asList(request.getParameterValues("listeEncadrantMatiere")));
		if(request.getParameterValues("listeEleve")!=null)
			listeIdConcernes.addAll(Arrays.asList(request.getParameterValues("listeEleve")));
		
		
		List<Utilisateur> listeUtilisateursConcernes = new ArrayList<Utilisateur>();
		for(String id : listeIdConcernes) {
			listeUtilisateursConcernes.add(utilisateurDao.getUtilisateurById(Integer.valueOf(id)));
		}
		audit.setListeConcerne(listeUtilisateursConcernes);

		audit.setStatutCourrant();
		boolean resultat = auditDao.publierAudit(audit);

		if(resultat)
			this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response); //appeler l'url accueil
		else
			this.getServletContext().getRequestDispatcher("/WEB-INF/erreurPublicationAudit.jsp").forward(request, response); //afficher page erreur
		

	}

}
