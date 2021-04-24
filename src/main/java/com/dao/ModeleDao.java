package com.dao;

import java.util.List;

import com.beans.Modele;

public interface ModeleDao {

	Modele getModeleById(int id);

	public List<Modele> getListeModeles();
	
}
