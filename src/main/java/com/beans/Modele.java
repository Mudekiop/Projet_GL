package com.beans;

import java.util.ArrayList;
import java.util.List;

public class Modele {

	private int id;
	private boolean visibilite;
	private List<Question> listeQuestion;
	private String titre;

	public Modele() {
		this.listeQuestion=new ArrayList<>();
	}
	
	public Modele(int id, boolean visibilite, String titre, List<Question> listeQuestion) {
		this.id=id;
		this.visibilite=visibilite;
		this.listeQuestion=listeQuestion;
		this.titre=titre;
		this.listeQuestion=new ArrayList<>();
	}

	public Modele(int id, boolean visibilite, String titre) {
		this.id=id;
		this.visibilite=visibilite;		
		this.titre=titre;
		this.listeQuestion=new ArrayList<>();
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public void ajouterQuestion(Question question) {
		this.listeQuestion.add(question);
	}
	
	public void supprimerQuestion(Question question) {
		if(question!=null && listeQuestion.contains(question)) {
				listeQuestion.remove(question);
		}
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isVisibilite() {
		return visibilite;
	}

	public void setVisibilite(boolean visibilite) {
		this.visibilite = visibilite;
	}

	public List<Question> getListeQuestion() {
		return listeQuestion;
	}

	public void setListeQuestion(List<Question> listeQuestion) {
		this.listeQuestion = listeQuestion;
	}
	
	public String getListeQuestionString() {
		String liste="";
		for(Question question : this.getListeQuestion()) {
			liste+=question.getContenu()+"\n";
		}
		return liste;
	}
	
}
