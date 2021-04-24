package com.forms;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.beans.Role;
import com.beans.Utilisateur;
import com.dao.DaoFactory;
import com.dao.UtilisateurDao;

public class FormAjouterRoleUtilisateur {

	private UtilisateurDao utilisateurDB;
	
	public FormAjouterRoleUtilisateur (HttpServletRequest r) {
		DaoFactory daoFactory = DaoFactory.getInstance();

		try {
			this.utilisateurDB = daoFactory.getUtilisateurDao();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		int id = Integer.parseInt(r.getParameter("id"));
		String roleAjouter = r.getParameter("roleAjouter");
		
		Utilisateur utilisateur = utilisateurDB.getUtilisateurById(id);
		List<Role> role = utilisateur.getListeRoles();
		
		Role rolePlus = Role.valueOf(roleAjouter);
		utilisateurDB.ajouterRoleUtilisateur(id, rolePlus);
		utilisateur.ajouterRole(rolePlus);
		role = utilisateur.getListeRoles();
	}
}
