package com.beans;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur {

	private int id;
	private String nom;
	private String prenom;
	private String email;
	private String mdp;
	private List<Role> listeRoles;
	
	public Utilisateur() {
		this.listeRoles=new ArrayList<>();
	}

	public Utilisateur(int id, String nom, String prenom, String email, String mdp, List<Role> listeRoles) {
		this.id=id;
		this.nom=nom;
		this.prenom=prenom;
		this.email=email;
		this.mdp=mdp;
		this.listeRoles=listeRoles;
	}
	
	public Utilisateur(int id, String nom, String prenom, String email, String mdp) {
		this.id=id;
		this.nom=nom;
		this.prenom=prenom;
		this.email=email;
		this.mdp=mdp;
	}
	
	public Utilisateur(String email, String mdp) {
		this.email = email;
		this.mdp=mdp;
	}

	public void ajouterRole(Role role) {
		this.listeRoles.add(role);
	}
	
	public void supprimerRole(Role role) {
		if(role!=null && listeRoles.contains(role)) {
			listeRoles.remove(role);
		}
	}
	
	public List<Role> getListeRoles() {
		return listeRoles;
	}
	
	public void setListeRoles(List<Role> listeRoles) {
		this.listeRoles = listeRoles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;

		if (this.getClass() != o.getClass())
		    return false;
		
		return ((Utilisateur) o).id==this.id;
	}
	
	@Override
	public int hashCode() {
		return id;
	}
}
