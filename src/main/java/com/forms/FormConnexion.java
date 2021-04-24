package com.forms;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.beans.Utilisateur;
import com.dao.DaoFactory;
import com.dao.UtilisateurDao;

public class FormConnexion {
	private Utilisateur connectedUtilisateur;
	private String status;
	private UtilisateurDao utilisateurDao;

	public FormConnexion(HttpServletRequest r) throws SQLException {

		DaoFactory daoFactory = DaoFactory.getInstance();

		this.utilisateurDao = daoFactory.getUtilisateurDao();

		String email = r.getParameter("email");
		String mdp = r.getParameter("mdp");

		Utilisateur tmpUtilisateur = new Utilisateur(email, mdp);

		if (this.utilisateurDao.exists(tmpUtilisateur)) {
			if (this.utilisateurDao.checkPwd(tmpUtilisateur)) {
				this.connectedUtilisateur = this.utilisateurDao.getUtilisateurConnected(tmpUtilisateur.getEmail(),
						tmpUtilisateur.getMdp());
				status = "good";
			} else
				status = "wrongPwd";
		} else
			status = "notFound";
	}

	public Utilisateur getConnectedUser() {
		return this.connectedUtilisateur;
	}

	public String getStatus() {
		return this.status;
	}
}
