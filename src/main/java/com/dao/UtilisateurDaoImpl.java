package com.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.beans.Role;
import com.beans.Utilisateur;
import com.forms.HashClass;

public class UtilisateurDaoImpl implements UtilisateurDao {

	private Connection connexion;

	public UtilisateurDaoImpl(Connection connexion) {
		super();
		this.connexion = connexion;
	}

	@Override
	public List<Utilisateur> getListUtilisateurs() {
		Statement statement = null;
		ResultSet resultat = null;
		List<Utilisateur> listeUtilisateurs = new ArrayList<>();
		try {
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM Utilisateur;");
			while (resultat.next()) {

				Utilisateur utilisateur = new Utilisateur(resultat.getInt("id"), resultat.getString("nom"),
						resultat.getString("prenom"), resultat.getString("email"), resultat.getString("mdp"));
				listeUtilisateurs.add(utilisateur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeUtilisateurs;
	}

	public Utilisateur getUtilisateurConnected(String email, String mdp) {
		Statement st = null;
		ResultSet resultat = null;
		ResultSet resultat2 = null;
		List<Role> role = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connexion
					.prepareStatement("SELECT * FROM Utilisateur WHERE email = ? AND mdp = ?;");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, mdp);
			resultat2 = preparedStatement.executeQuery();
			while (resultat2.next()) {
				st = this.connexion.createStatement();
				resultat = st.executeQuery(
						"SELECT r.type FROM listeroles l, role r WHERE l.id_Utilisateur =" + resultat2.getInt("id") + " AND r.id = l.id_Role ");
				while (resultat.next()) {
					role.add(Role.valueOf(resultat.getString("type")));
					System.out.println(role);
				}
				Utilisateur utilisateur = new Utilisateur(resultat2.getInt("id"), resultat2.getString("nom"),
						resultat2.getString("prenom"), resultat2.getString("email"), resultat2.getString("mdp"), role);
				return utilisateur;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean exists(Utilisateur utilisateur) {
		ResultSet resultat = null;
		try {
			PreparedStatement preparedStatement = connexion.prepareStatement(
					"SELECT COUNT(*) FROM Utilisateur WHERE LOWER(email) = LOWER(?) AND LOWER(mdp) = LOWER(?);");
			preparedStatement.setString(1, utilisateur.getEmail());
			preparedStatement.setString(2, utilisateur.getMdp());
			resultat = preparedStatement.executeQuery();
			if (resultat.next() && resultat.getInt(1) == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkPwd(Utilisateur utilisateur) {
		ResultSet resultat = null;
		String hash = "";
		try {
			PreparedStatement preparedStatement = connexion.prepareStatement(
					"SELECT email, mdp FROM Utilisateur WHERE LOWER(email) = LOWER(?) AND LOWER(mdp) = LOWER(?);");
			preparedStatement.setString(1, utilisateur.getEmail());
			preparedStatement.setString(2, utilisateur.getMdp());
			resultat = preparedStatement.executeQuery();
			if (resultat.next()) {
				hash = HashClass.hashIt(utilisateur.getMdp(), resultat.getString(1), 100);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String sha256(String base) {
		try {
			final MessageDigest digest = MessageDigest.getInstance("SHA-256");
			final byte[] hash = digest.digest(base.getBytes("UTF_8"));
			final StringBuilder hexString = new StringBuilder();
			for (int i = 0; i < hash.length; i++) {
				final String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Utilisateur getUtilisateurById(int id) {
		Utilisateur user = null;
		List<Role> role = new ArrayList<>();
		try {
			Statement st = this.connexion.createStatement();
			ResultSet resultat = st.executeQuery(
					"SELECT r.type FROM listeroles l, role r WHERE l.id_Utilisateur =" + id + " AND r.id = l.id_Role ");
			while (resultat.next()) {
				role.add(Role.valueOf(resultat.getString("type")));
			}
			resultat = st.executeQuery("SELECT * FROM utilisateur WHERE id=" + id + ";");
			while (resultat.next()) {
				user = new Utilisateur(resultat.getInt("id"), resultat.getString("nom"), resultat.getString("prenom"),
						resultat.getString("email"), resultat.getString("mdp"), role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}


	@Override
	public Utilisateur getAuteurDeAuditById(int id) {
		Utilisateur user = null;
		List<Role> role = new ArrayList<>();
		int idUtilisateur = 0;
		try {
			Statement st = connexion.createStatement();
			ResultSet resultat = st.executeQuery(
					"SELECT u.id FROM utilisateur u, audit WHERE audit.id=" + id + " AND u.id=audit.auteur");
			while (resultat.next()) {
				idUtilisateur = resultat.getInt("id");
			}
			st = connexion.createStatement();
			resultat = st.executeQuery("SELECT r.type FROM listeroles l, role r WHERE l.id_Utilisateur ="
					+ idUtilisateur + " AND r.id = l.id_Role ");
			while (resultat.next()) {
				role.add(Role.valueOf(resultat.getString("type")));
			}
			resultat = st.executeQuery("SELECT * FROM utilisateur WHERE id=" + idUtilisateur + ";");
			while (resultat.next()) {
				user = new Utilisateur(resultat.getInt("id"), resultat.getString("nom"), resultat.getString("prenom"),
						resultat.getString("email"), resultat.getString("mdp"), role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	
	@Override
	public Utilisateur ajouterRoleUtilisateur(int id, Role role) {
		Statement statement = null;
		ResultSet resultat = null;
		try {
			statement = connexion.createStatement();
        	resultat = statement.executeQuery("SELECT r.id FROM role r WHERE r.type='" + role + "'");
	    	while(resultat.next()) {
	    		PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO listeroles (id_Role, id_Utilisateur) VALUES (" + resultat.getString("id") + "," + id + ")");
	    		preparedStatement.executeUpdate();
	    	}
	    } catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Utilisateur supprimerRoleUtilisateur(int id, Role role) {
		Statement statement = null;
		ResultSet resultat = null;
		try {
			statement = connexion.createStatement();
        	resultat = statement.executeQuery("SELECT r.id FROM role r WHERE r.type='" + role + "'");
	    	while(resultat.next()) {
	    		PreparedStatement preparedStatement = connexion.prepareStatement("DELETE FROM listeroles WHERE id_Role = " + resultat.getString("id") + " AND id_Utilisateur = " + id + "");
	    		preparedStatement.executeUpdate();
	    	}
	    } catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Utilisateur> getListeUtilisateurByRole(Role role){
		List<Utilisateur> liste = new ArrayList<Utilisateur>();
		try {
			PreparedStatement statement = connexion.prepareStatement("SELECT "
					+ "u.*, type as role "
					+ "FROM "
					+ "utilisateur u "
					+ "inner join listeroles lr on lr.id_Utilisateur=u.id "
					+ "inner join role r on r.id=lr.id_Role "
					+ "where "
					+ "r.type=?");
			statement.setString(1, role.toString());
			ResultSet resultat = statement.executeQuery();
			while(resultat.next()) {
				Utilisateur utilisateur = this.getUtilisateurById(Integer.valueOf(resultat.getString("id")));
				liste.add(utilisateur);
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return liste;
	}

}
