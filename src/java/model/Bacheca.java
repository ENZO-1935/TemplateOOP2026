package model;

import java.util.ArrayList;

public class Bacheca {
	private ArrayList<ToDo> toDo;
	private String titolo;
	private String descrizione;

	public Bacheca(String titolo, String descrizione) {
		toDo = new ArrayList<>();
		this.titolo=titolo;
		this.descrizione=descrizione;

	}


}
