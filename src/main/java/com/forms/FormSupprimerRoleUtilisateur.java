package com.forms;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.beans.Role;
import com.beans.Utilisateur;
import com.dao.DaoFactory;
import com.dao.UtilisateurDao;

public class FormSupprimerRoleUtilisateur {
	
	private UtilisateurDao utilisateurDB;

	public FormSupprimerRoleUtilisateur (HttpServletRequest r) {
		DaoFactory daoFactory = DaoFactory.getInstance();

		try {
			this.utilisateurDB = daoFactory.getUtilisateurDao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int id = Integer.parseInt(r.getParameter("id"));
		String roleSupprimer = r.getParameter("roleSupprimer");
		
		Utilisateur utilisateur = utilisateurDB.getUtilisateurById(id);
		List<Role> role = utilisateur.getListeRoles();
		
		Role roleMoins = Role.valueOf(roleSupprimer);
		utilisateurDB.supprimerRoleUtilisateur(id, roleMoins);
		utilisateur.supprimerRole(roleMoins);
		role = utilisateur.getListeRoles();
		if(role.isEmpty()) {
			String roleAjouter = "INVITE";
			Role rolePlus = Role.valueOf(roleAjouter);
			utilisateurDB.ajouterRoleUtilisateur(id, rolePlus);
		}
	}
}
