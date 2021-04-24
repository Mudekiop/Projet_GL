package com.dao;

import java.util.List;

import com.beans.Reponse;

public interface ReponseDao {

	List<Reponse> getReponse(QuestionDao questionDao, AuditDao auditDao, UtilisateurDao userDao);
	
	List<Reponse> getReponseByUtilisateurAndAudit(QuestionDao questionDao, AuditDao auditDao, UtilisateurDao utilisateurDao, int idAudit, int idUtilisateur);

}
