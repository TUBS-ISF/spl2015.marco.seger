package de.tubs.spl.seger;

public class ChronologischeReihenfolge implements Reihenfolge {

	private Vokabelliste vokabelliste;
	private int index = 0;

	public ChronologischeReihenfolge(Vokabelliste vokabelliste) {
		this.vokabelliste = vokabelliste;
	}
	
	@Override
	public Vokabel getNext() {
		index++;
		if (index >= vokabelliste.getVokabeln().size()) {
			index = 0;
		}
		return getAktuelleVokabel();
	}

	@Override
	public Vokabel getAktuelleVokabel() {
		return vokabelliste.getVokabeln().get(index);
	}

	@Override
	public void setVokabelliste(Vokabelliste vokabelliste) {
		this.vokabelliste = vokabelliste;
		index = 0;
	}
}
