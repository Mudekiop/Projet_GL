package com.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Audit;
import com.dao.AuditDao;
import com.dao.DaoFactory;
import com.dao.ModeleDao;
import com.dao.UtilisateurDao;

@WebServlet("/PageResultatController")
public class PageResultatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PageResultatController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UtilisateurDao daoUtilisateur = null;
		AuditDao daoAudit = null;
		ModeleDao daoModele = null;

		try {
			daoUtilisateur = DaoFactory.getInstance().getUtilisateurDao();
			daoAudit = DaoFactory.getInstance().getAuditDao();
			daoModele = DaoFactory.getInstance().getModeleDao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<Audit> res = daoAudit.getAuditTermineByUtilisateur(3, daoUtilisateur, daoModele);

		request.setAttribute("resultat", res);

		this.getServletContext().getRequestDispatcher("/WEB-INF/pageResultat.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
