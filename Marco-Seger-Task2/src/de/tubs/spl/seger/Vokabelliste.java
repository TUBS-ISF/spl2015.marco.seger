package de.tubs.spl.seger;

import java.util.ArrayList;
import java.util.List;

public class Vokabelliste {

	private List<Vokabel> vokabeln = new ArrayList<Vokabel>();
	private Vokabel aktuelleVokabel;
	private int count = 0;

	public enum Reihenfolge {
		CHRONOLOGISCH, ZUFÄLLIG
	}

	private Reihenfolge reihenfolge;

	public Reihenfolge getReihenfolge() {
		return reihenfolge;
	}

	public void setReihenfolge(Reihenfolge reihenfolge) {
		this.reihenfolge = reihenfolge;
	}

	public Vokabelliste() {
	}

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

	public Vokabel getAktuelleVokabel() {
//		if (aktuelleVokabel == null && vokabeln.size() > 0) {
//			aktuelleVokabel = vokabeln.get(0);
//		}
		return aktuelleVokabel;
	}

	public void setAktuelleVokabel(Vokabel aktuelleVokabel) {
		this.aktuelleVokabel = aktuelleVokabel;
	}

	public Vokabel next() {
		Vokabel vokabel = null;
		if (reihenfolge == Reihenfolge.CHRONOLOGISCH) {
			count++;
			if (count < vokabeln.size()) {
				vokabel = vokabeln.get(count);
			} else {
				count = 0;
				vokabel = vokabeln.get(0);
			}
		}
		
		if (reihenfolge == Reihenfolge.ZUFÄLLIG) {
			vokabel = vokabeln.get((int) Math.abs(Math.random() * vokabeln.size()));
		}
		aktuelleVokabel = vokabel;
		return vokabel;
	}
	
	public boolean isEmpty() {
		return vokabeln.isEmpty();
	}
}
