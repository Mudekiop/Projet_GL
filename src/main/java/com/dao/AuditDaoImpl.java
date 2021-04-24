package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.beans.Audit;
import com.beans.Modele;
import com.beans.Statut;
import com.beans.Utilisateur;

public class AuditDaoImpl implements AuditDao{
	private Connection connexion;
	private ResultSet resultat;
	private Statement st;

	private Audit audit;

	public AuditDaoImpl(Connection connexion) {
		super();
		this.connexion = connexion;
		this.resultat = null;
		this.st = null;
	}

	@Override
	public List<Audit> listerAudits() {

		List<Audit> listeAudits = new ArrayList<>();
		Statement statement = null;
		ResultSet resultat = null;

		try {
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM audit;");

			while (resultat.next()) {
				audit = new Audit(resultat.getInt("id"), resultat.getString("titre"),
						resultat.getDate("dateOuverture"), resultat.getDate("dateCloture"));

				listeAudits.add(audit);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeAudits;
	}

	
	@Override
	public List<Audit> listerMesAudits(Utilisateur utilisateur) {

		List<Audit> listeAudits = new ArrayList<>();

		Statement statement = null;
		ResultSet resultat = null;
		Statement statement2 = null;
		ResultSet resultat2 = null;

		try {
			statement = connexion.createStatement();
			resultat = statement.executeQuery(
					"SELECT a.id as id, a.titre as titre, a.dateOuverture as dateOuverture, a.dateCloture as dateCloture, a.id_Statut as id_Statut FROM utilisateur u, concerne c, audit a WHERE a.id = c.id_Audit AND c.id_Utilisateur = "
							+ utilisateur.getId() + " GROUP BY 1,2,3,4,5;");

			while (resultat.next()) {
				int id = resultat.getInt("id");
				String titre = resultat.getString("titre");
				Date dateOuverture = resultat.getDate("dateOuverture");
				Date dateCloture = resultat.getDate("dateCloture");

				// On recherche le statut de l'audit
				statement2 = connexion.createStatement();
				resultat2 = statement2
						.executeQuery("SELECT * FROM statut WHERE id = " + resultat.getString("id_Statut") + ";");
				while (resultat2.next()) {
					Statut statut = Statut.valueOf(resultat2.getString("typeStatut"));

					audit = new Audit(id, titre, statut, dateOuverture, dateCloture);

					listeAudits.add(audit);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeAudits;
	}


	@Override
	public boolean auditAlreadyExists(Audit audit) {
		boolean retour = false;
		try {
			PreparedStatement statement = connexion.prepareStatement("SELECT * FROM audit where id=?");
			statement.setString(1, String.valueOf(audit.getId()));
			this.resultat = statement.executeQuery();
			while (this.resultat.next()) {
				retour = true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return retour;
	}

	public boolean publierAudit(Audit audit) {
		boolean retour = false;
		try {
			PreparedStatement statement = this.connexion.prepareStatement(
					"INSERT INTO Audit (id, dateOuverture, dateCloture, titre, auteur, id_Statut, id_Modele) "
							+ "VALUES (NULL, ?, ?, ?, ?, ?, ?)");
			statement.setString(1, audit.getDateOuverture().toString());
			statement.setString(2, audit.getDateCloture().toString());
			statement.setString(3, audit.getTitre());
			statement.setString(4, String.valueOf(audit.getAuteur().getId()));
			statement.setInt(5, Statut.getIdByStatut(audit.getStatut()));
			statement.setString(6, String.valueOf(audit.getModele().getId()));
			statement.executeUpdate();

			statement = this.connexion.prepareStatement("SELECT id FROM Audit ORDER BY id DESC LIMIT 1");
			this.resultat = statement.executeQuery();
			while (this.resultat.next()) {
				audit.setId(Integer.valueOf(this.resultat.getString("id")));
			}

			for (Utilisateur utilisateur : audit.getListeConcerne()) {
				statement = this.connexion
						.prepareStatement("INSERT INTO concerne (id_Audit, id_Utilisateur) VALUES (?,?) ");
				statement.setString(1, String.valueOf(audit.getId()));
				statement.setString(2, String.valueOf(utilisateur.getId()));
				statement.executeUpdate();
			}
			retour = true;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return retour;
	}

	public List<Audit> getAuditByUtilisateur(Utilisateur user) {
		Audit audit = null;
		Modele modele = null;
		Statut statut = null;
		List<Audit> liste = new ArrayList<>();
		try {
			this.st = this.connexion.createStatement();
			this.resultat = this.st.executeQuery("	SELECT s.typeStatut FROM audit a, statut s WHERE a.auteur="
					+ user.getId() + " AND a.id_Statut=s.id;");
			while (this.resultat.next()) {
				statut = Statut.valueOf(this.resultat.getString("typeStatut"));
			}
			this.resultat = this.st
					.executeQuery("SELECT m.id, m.visibilite, m.titre FROM modele m, audit a WHERE a.auteur="
							+ +user.getId() + " AND a.id_Modele=m.id;");
			while (this.resultat.next()) {
				modele = new Modele(this.resultat.getInt("id"), this.resultat.getBoolean("visibilite"),
						resultat.getString("titre"));
			}
			this.resultat = this.st.executeQuery("SELECT * FROM audit a WHERE a.auteur=" + user.getId() + ";");
			while (this.resultat.next()) {
				audit = new Audit(this.resultat.getInt("id"), this.resultat.getString("titre"), user, statut, modele,
						this.resultat.getDate("dateOuverture"), this.resultat.getDate("dateCloture"), null);
				liste.add(audit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}

	public Audit getAuditById(int id, Utilisateur user) {
		Audit audit = null;
		Modele modele = null;
		Statut statut = null;

		try {
			this.st = this.connexion.createStatement();
			this.resultat = this.st.executeQuery("	SELECT s.typeStatut FROM audit a, statut s WHERE a.auteur="
					+ user.getId() + " AND a.id_Statut=s.id;");
			while (this.resultat.next()) {
				statut = Statut.valueOf(this.resultat.getString("typeStatut"));
			}
			this.resultat = this.st
					.executeQuery("SELECT m.id, m.visibilite, m.titre FROM modele m, audit a WHERE a.auteur="
							+ user.getId() + " AND a.id_Modele=m.id;");
			while (this.resultat.next()) {
				modele = new Modele(this.resultat.getInt("id"), this.resultat.getBoolean("visibilite"),
						this.resultat.getString("titre"));
			}
			this.resultat = this.st.executeQuery("SELECT * FROM audit a WHERE a.id=" + id + ";");
			while (resultat.next()) {
				audit = new Audit(this.resultat.getInt("id"), this.resultat.getString("titre"), user, statut, modele,
						this.resultat.getDate("dateOuverture"), this.resultat.getDate("dateCloture"), null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return audit;
	}

	public List<Audit> getAuditTermineByUtilisateur(int idUtilisateur, UtilisateurDao daoUser, ModeleDao daoModele) {
		Audit audit = null;
		List<Audit> liste = new ArrayList<>();

		try {
			PreparedStatement statement = connexion.prepareStatement(
					"SELECT * FROM audit JOIN concerne ON audit.id=concerne.id_Audit AND id_Utilisateur=? JOIN statut ON audit.id_Statut=statut.id AND statut.typeStatut='TERMINE'");
			statement.setInt(1, idUtilisateur);
			this.resultat = statement.executeQuery();
			while (this.resultat.next()) {
				audit = new Audit(this.resultat.getInt("id"), this.resultat.getString("titre"),
						daoUser.getUtilisateurById(this.resultat.getInt("auteur")), Statut.valueOf("TERMINE"),
						daoModele.getModeleById(this.resultat.getInt("id_Modele")),
						this.resultat.getDate("dateOuverture"), this.resultat.getDate("dateCloture"), null);
				liste.add(audit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}

	@Override
	public List<Modele> getListeModeles() {
		return null;
	}

}
