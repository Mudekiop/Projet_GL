package com.dao;

import java.util.List;

import com.beans.Modele;
import com.beans.Question;

public interface QuestionDao {

	List<Question> getQuestionByModele(Modele modele);
	
	Question getQuestionById(int id);
}
