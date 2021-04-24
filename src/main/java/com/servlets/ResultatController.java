package com.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Reponse;
import com.dao.AuditDao;
import com.dao.DaoFactory;
import com.dao.QuestionDao;
import com.dao.ReponseDao;
import com.dao.UtilisateurDao;

@WebServlet("/ResultatServlet")
public class ResultatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ResultatController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idAudit = Integer.parseInt(request.getParameter("id"));
		
		UtilisateurDao daoUtilisateur = null;
		AuditDao daoAudit = null;
		QuestionDao daoQuestion = null;
		ReponseDao daoReponse = null;

		try {
			daoUtilisateur = DaoFactory.getInstance().getUtilisateurDao();
			daoAudit = DaoFactory.getInstance().getAuditDao();
			daoQuestion = DaoFactory.getInstance().getQuestionDao();
			daoReponse = DaoFactory.getInstance().getReponseDao();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		List<Reponse> res = daoReponse.getReponseByUtilisateurAndAudit(daoQuestion, daoAudit, daoUtilisateur, idAudit, 3);

		request.setAttribute("reponse", res);

		this.getServletContext().getRequestDispatcher("/WEB-INF/resultat.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
