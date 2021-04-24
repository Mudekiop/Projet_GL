package com.beans;

import java.sql.Date;
import java.util.List;

public class Audit {

	private String titre;
	private int id;
	private Utilisateur auteur;
	private Modele modele;
	private Statut statut;
	private Date dateOuverture;
	private Date dateCloture;
	private List<Utilisateur> listeConcerne;

	public Audit() {
		super();
	}
	
	public Audit(int id, String titre, Utilisateur auteur, Statut statut, Modele modele, Date dateOuverture, Date dateCloture, List<Utilisateur> listeConcerne) {
		this.id=id;
		this.titre=titre;
		this.auteur=auteur;
		this.statut=statut;
		this.modele=modele;
		this.dateOuverture=dateOuverture;
		this.dateCloture=dateCloture;
		this.listeConcerne=listeConcerne;
	}
	
	public Audit(int id, String titre, Statut statut, Date dateOuverture, Date dateCloture) {
		this.id=id;
		this.titre=titre;
		this.statut=statut;
		this.dateOuverture=dateOuverture;
		this.dateCloture=dateCloture;
	}
	
	public Audit(int id, String titre, Date dateOuverture, Date dateCloture) {
		this.id=id;
		this.titre=titre;
		this.dateOuverture=dateOuverture;
		this.dateCloture=dateCloture;
	}

	public void ajouterUtilisateurConcerne(Utilisateur utilisateur) {
		this.listeConcerne.add(utilisateur);
	}
	
	public void supprimerUtilisateurConcerne(Utilisateur utilisateur) {
		if(utilisateur!=null && this.listeConcerne.contains(utilisateur)) {
				this.listeConcerne.remove(utilisateur);
		}
	}
	
	public List<Utilisateur> getListeConcerne() {
		return listeConcerne;
	}

	public void setListeConcerne(List<Utilisateur> listeConcerne) {
		this.listeConcerne = listeConcerne;
	}

	public Date getDateOuverture() {
		return dateOuverture;
	}

	public void setDateOuverture(Date dateOuverture) {
		this.dateOuverture = dateOuverture;
	}

	public Date getDateCloture() {
		return dateCloture;
	}

	public void setDateCloture(Date dateCloture) {
		this.dateCloture = dateCloture;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Utilisateur getAuteur() {
		return auteur;
	}

	public void setAuteur(Utilisateur auteur) {
		this.auteur = auteur;
	}

	public Modele getModele() {
		return modele;
	}

	public void setModele(Modele modele) {
		this.modele = modele;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	
	public void setStatutCourrant() {
        if(this.dateOuverture!=null && this.dateCloture!=null && this.statut!=Statut.TERMINE) {
            long millisCourrant = System.currentTimeMillis();
            Date dateCourrant = new Date(millisCourrant);
            if(dateCourrant.before(dateOuverture))
                this.statut=Statut.A_VENIR;
            else if(dateCourrant.after(dateCloture))
                this.statut=Statut.COMPLETE;
            else
                this.statut=Statut.EN_COURS;

        }
    }
	
}