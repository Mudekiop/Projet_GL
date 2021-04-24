package com.beans;

public class Question {

	private int id;
	private String contenu;
	private TypeQuestion typeQuestion;

	public Question(int id, TypeQuestion typeQuestion, String contenu) {
		this.id=id;
		this.typeQuestion=typeQuestion;
		this.contenu=contenu;
	}

	public Question() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public TypeQuestion getTypeQuestion() {
		return typeQuestion;
	}

	public void setTypeQuestion(TypeQuestion typeQuestion) {
		this.typeQuestion = typeQuestion;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;

		if (this.getClass() != o.getClass())
		    return false;
		
		return ((Question) o).id==this.id;
	}
	
	@Override
	public int hashCode() {
		return id;
	}

}
