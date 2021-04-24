package com.dao;

import java.util.List;

import com.beans.Audit;
import com.beans.Modele;
import com.beans.Utilisateur;

public interface AuditDao {

	boolean publierAudit(Audit audit);

	boolean auditAlreadyExists(Audit audit);

	List<Audit> getAuditByUtilisateur(Utilisateur user);

	Audit getAuditById(int id, Utilisateur user);

	List<Audit> getAuditTermineByUtilisateur(int idUtilisateur, UtilisateurDao daoUser, ModeleDao daoModele);

	public List<Modele> getListeModeles();

	List<Audit> listerMesAudits(Utilisateur utilisateur);

	List<Audit> listerAudits();
}
