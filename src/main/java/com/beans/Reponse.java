package com.beans;

public class Reponse {

	private int id;
	private String contenuReponse;
	private Utilisateur utilisateur;
	private Audit audit;
	private Question question;

	public Reponse() {
		
	}
	
	public Reponse(int id, String contenuReponse, Audit audit, Utilisateur utilisateur, Question question) {
		this.id=id;
		this.contenuReponse=contenuReponse;
		this.audit=audit;
		this.utilisateur=utilisateur;
		this.question=question;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContenuReponse() {
		return contenuReponse;
	}

	public void setContenuReponse(String contenuReponse) {
		this.contenuReponse = contenuReponse;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Audit getAudit() {
		return audit;
	}

	public void setAudit(Audit audit) {
		this.audit = audit;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
}
