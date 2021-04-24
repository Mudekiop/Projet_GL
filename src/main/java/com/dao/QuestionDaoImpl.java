package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.beans.Modele;
import com.beans.Question;
import com.beans.TypeQuestion;

public class QuestionDaoImpl implements QuestionDao {

	private Connection connexion;
	private ResultSet resultat;
	private Statement st;

	public QuestionDaoImpl(Connection connexion) {
		super();
		this.connexion = connexion;
		this.resultat = null;
		this.st = null;
	}

	public List<Question> getQuestionByModele(Modele modele) {
		List<Question> liste = new ArrayList<>();
		Question q = null;
		Statement st2 = null;
		ResultSet resultat2 = null;
		try {
			this.st = this.connexion.createStatement();
			this.resultat = this.st
					.executeQuery("SELECT id_Question FROM contient WHERE id_Modele=" + modele.getId() + ";");
			while (this.resultat.next()) {
				st2 = this.connexion.createStatement();
				resultat2 = st2
						.executeQuery("SELECT * FROM question WHERE id=" + this.resultat.getString("id_Question"));
				while (resultat2.next()) {
					q = new Question(resultat2.getInt("id"),
							TypeQuestion.values()[resultat2.getInt("typeQuestion") - 1],
							resultat2.getString("contenu"));
					liste.add(q);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}

	public Question getQuestionById(int id) {
		Question q = null;
		try {
			this.st = this.connexion.createStatement();
			this.resultat = this.st.executeQuery("SELECT * FROM question WHERE id=" + id + ";");
			while (this.resultat.next()) {
				q = new Question(resultat.getInt("id"), TypeQuestion.values()[resultat.getInt("typeQuestion") - 1],
						resultat.getString("contenu"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return q;
	}
}
