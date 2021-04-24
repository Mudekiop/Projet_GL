package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.beans.Modele;
import com.beans.Question;
import com.beans.TypeQuestion;

public class ModeleDaoImpl implements ModeleDao {

	private Connection connexion;

	public ModeleDaoImpl(Connection connexion) {
		this.connexion=connexion;
	}
	
	@Override
	public Modele getModeleById(int id) {
		
		Connection connexion = null;
		Modele modele = new Modele();
		try {
			connexion=this.connexion;
			PreparedStatement statement = connexion.prepareStatement("SELECT "
					+ "qu.*, tq.type "
					+ "FROM question qu "
					+ "inner join contient c on c.id_Question=qu.id "
					+ "inner join typequestion tq on tq.id=qu.typeQuestion "

					+ "where c.id_Modele=?");
			statement.setString(1, String.valueOf(id));
			ResultSet resultat = statement.executeQuery();
			while(resultat.next()) {
				modele.ajouterQuestion(new Question(Integer.valueOf(resultat.getString("id")),TypeQuestion.valueOf(resultat.getString("type")),resultat.getString("contenu")));
			}
			statement = connexion.prepareStatement("SELECT "
					+ "* "
					+ "from modele "
					+ "where id=?");
			statement.setString(1, String.valueOf(id));
			resultat = statement.executeQuery();
			while(resultat.next()) {
				modele.setId(id);
				modele.setTitre(resultat.getString("titre"));
				modele.setVisibilite("1".equals(resultat.getString("visibilite"))); //Les valeurs de ce champ prennent '1' ou '0' en BDD				
			}
			
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return modele;
	}
	
	@Override
	public List<Modele> getListeModeles(){
		Connection connexion = null;
		List<Modele> liste = new ArrayList<>();
		try {
			connexion=this.connexion;
			PreparedStatement statement = connexion.prepareStatement("SELECT * FROM modele");
			ResultSet resultat = statement.executeQuery();
			while(resultat.next()) {
				Modele modele = new Modele();
				modele.setId(resultat.getInt("id"));
				modele.setTitre(resultat.getString("titre"));
				modele.setVisibilite("1".equals(resultat.getString("visibilite")));
				PreparedStatement statement2 = connexion.prepareStatement("SELECT "
						+ "qu.*, tq.type as type "
						+ "FROM question qu "
						+ "inner join contient c on c.id_Question=qu.id "
						+ "inner join typequestion tq on tq.id=qu.typeQuestion "
						+ "where c.id_Modele=?");
				statement2.setInt(1, modele.getId());
				ResultSet resultat2 = statement2.executeQuery();
				while(resultat2.next()) {
					modele.ajouterQuestion(new Question(Integer.valueOf(resultat2.getString("id")),TypeQuestion.valueOf(resultat2.getString("type")),resultat2.getString("contenu")));
				}				
				liste.add(modele);
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return liste;
	}
	
}
