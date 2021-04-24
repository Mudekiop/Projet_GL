package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/accueilController")
public class accueilController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public accueilController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("deco") != null) {
			HttpSession session = request.getSession();
			if (session.getAttribute("email") != null) {
				session.invalidate();
			}
		} else {
			System.out.println("Déconnexion pas null");
		}
		this.doGet(request, response);
	}

}
