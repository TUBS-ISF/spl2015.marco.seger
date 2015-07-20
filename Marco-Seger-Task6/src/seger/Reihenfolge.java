package de.tubs.spl.seger;

public interface Reihenfolge {

	public void setVokabelliste(Vokabelliste vokabelliste);
	public Vokabel getNext();
	public Vokabel getAktuelleVokabel();
}
