package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {


	private String url;
	private String username;
	private String password;

	DaoFactory(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public static DaoFactory getInstance() {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
            	e.printStackTrace();
            }

            return new DaoFactory("jdbc:mysql://localhost/Projet_GL", "root", "root");
    }
    

	// Récupération du Dao
	public UtilisateurDao getUtilisateurDao() throws SQLException {
		return new UtilisateurDaoImpl(this.getConnection());
	}

	public ModeleDao getModeleDao() throws SQLException {
		return new ModeleDaoImpl(this.getConnection());
	}

	public AuditDao getAuditDao() throws SQLException {
		return new AuditDaoImpl(this.getConnection());
	}

	public QuestionDao getQuestionDao() throws SQLException {
		return new QuestionDaoImpl(this.getConnection());
	}

	public ReponseDao getReponseDao() throws SQLException {
		return new ReponseDaoImpl(this.getConnection());
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

}
