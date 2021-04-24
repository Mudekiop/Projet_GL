package com.beans;

public enum Statut {
	COMPLETE,
	EN_COURS,
	A_VENIR,
	TERMINE;
	
	public static int getIdByStatut(Statut statut) {
		switch(statut) {
		case COMPLETE:
			return 1;
		case EN_COURS:
			return 2;
		case A_VENIR:
			return 3;
		case TERMINE:
			return 4;
		}
		return -1;
	}
	
}
