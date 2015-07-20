package de.tubs.spl.seger;

import java.util.ArrayList;
import java.util.List;

public class Vokabelliste {

	private List<Vokabel> vokabeln = new ArrayList<Vokabel>();

	public Vokabelliste() {}
	
	public Vokabelliste(List<Vokabel> vokabeln) {
		this.vokabeln = vokabeln;
	}

	public List<Vokabel> getVokabeln() {
		return vokabeln;
	}

	public void setVokabeln(List<Vokabel> vokabeln) {
		this.vokabeln = vokabeln;
	}

	public void addVokabel(Vokabel vokabel) {
		vokabeln.add(vokabel);
	}

	public boolean isEmpty() {
		return vokabeln.isEmpty();
	}
}
