package com.dao;

import java.util.List;
import com.beans.Role;
import com.beans.Utilisateur;

public interface UtilisateurDao {

	Utilisateur getAuteurDeAuditById(int id);

	Utilisateur getUtilisateurById(int id);
	
	Utilisateur ajouterRoleUtilisateur(int id, Role role);
	
	Utilisateur supprimerRoleUtilisateur(int id, Role role);

	public List<Utilisateur> getListeUtilisateurByRole(Role role);

	Utilisateur getUtilisateurConnected(String email, String mdp);

	List<Utilisateur> getListUtilisateurs();

	boolean exists(Utilisateur p);

	boolean checkPwd(Utilisateur p);
	
	String sha256(String base);

}
