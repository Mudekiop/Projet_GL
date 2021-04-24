package com.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.Utilisateur;
import com.dao.DaoFactory;
import com.dao.UtilisateurDao;
import com.forms.FormConnexion;

@WebServlet("/connexionController")
public class connexionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurDao utilisateurDao;

	public connexionController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DaoFactory daoFactory = DaoFactory.getInstance();

		try {
			this.utilisateurDao = daoFactory.getUtilisateurDao();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("personnes", this.utilisateurDao.getListUtilisateurs());

		if (request.getParameter("connect") != null) {
			FormConnexion form = null;
			try {
				form = new FormConnexion(request);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (form.getStatus().equals("good")) {
				Utilisateur uConnected = form.getConnectedUser();
				HttpSession session = request.getSession();
				session.setAttribute("id", uConnected.getId());
				session.setAttribute("nom", uConnected.getNom());
				session.setAttribute("prenom", uConnected.getPrenom());
				session.setAttribute("email", uConnected.getEmail());
				session.setAttribute("mdp", uConnected.getMdp());
				session.setAttribute("role", uConnected.getListeRoles());

				this.getServletContext().getRequestDispatcher("/WEB-INF/audits.jsp").forward(request, response);
			} else {
				request.setAttribute("errorMsg", form.getStatus());
				this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
			}
		} else {
			if (request.getParameter("deco") != null) {
				HttpSession session = request.getSession();
				if (session.getAttribute("email") != null) {
					session.invalidate();
				}
			} else {
				System.out.println("Déconnexion pas null");
			}
		}
	}

}
