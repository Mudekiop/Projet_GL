package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.beans.Reponse;

public class ReponseDaoImpl implements ReponseDao {

	private Connection connexion;
	private ResultSet resultat;
	private Statement st;

	public ReponseDaoImpl(Connection connexion) {
		super();
		this.connexion = connexion;
		this.resultat = null;
		this.st = null;
	}

	public List<Reponse> getReponse(QuestionDao questionDao, AuditDao auditDao, UtilisateurDao userDao) {
		List<Reponse> liste = new ArrayList<>();
		Reponse reponse = null;
		try {
			this.st = this.connexion.createStatement();
			this.resultat = this.st.executeQuery("SELECT * FROM reponse ORDER BY id_Question;");
			while (this.resultat.next()) {
				reponse = new Reponse(resultat.getInt("id"), resultat.getString("contenuReponse"),
						auditDao.getAuditById(this.resultat.getInt("id_Audit"),
								userDao.getAuteurDeAuditById(this.resultat.getInt("id_Audit"))),
						userDao.getUtilisateurById(this.resultat.getInt("id_Utilisateur")),
						questionDao.getQuestionById(this.resultat.getInt("id_Question")));
				liste.add(reponse);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}

	public List<Reponse> getReponseByUtilisateurAndAudit(QuestionDao questionDao, AuditDao auditDao,
			UtilisateurDao userDao, int idAudit, int idUtilisateur) {
		List<Reponse> liste = new ArrayList<>();
		Reponse reponse = null;
		try {
			PreparedStatement statement = this.connexion.prepareStatement(
					"SELECT * FROM reponse WHERE id_Audit=? AND id_Utilisateur=? ORDER BY id_Question;");
			statement.setString(1, String.valueOf(idAudit));
			statement.setString(2, String.valueOf(idUtilisateur));
			this.resultat = statement.executeQuery();
			while (this.resultat.next()) {
				reponse = new Reponse(resultat.getInt("id"), resultat.getString("contenuReponse"),
						auditDao.getAuditById(idAudit, userDao.getAuteurDeAuditById(idAudit)),
						userDao.getUtilisateurById(idUtilisateur),
						questionDao.getQuestionById(this.resultat.getInt("id_Question")));
				liste.add(reponse);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}
}
