package de.tubs.spl.seger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Vokabel {

	private String vokabel;
	private List<String> uebersetzung;

	public Vokabel(String vokabel, String... uebersetzungen) {
		this.vokabel = vokabel;
		uebersetzung = new ArrayList<String>(Arrays.asList(uebersetzungen));
	}

	public Vokabel(String vokabel, List<String> uebersetzungen) {
		this.vokabel = vokabel;
		this.uebersetzung = uebersetzungen;
	}

	public String getVokabel() {
		return vokabel;
	}

	public void setVokabel(String vokabel) {
		this.vokabel = vokabel;
	}

	public List<String> getUebersetzung() {
		return uebersetzung;
	}

	public void setUebersetzung(List<String> uebersetzung) {
		this.uebersetzung = uebersetzung;
	}

	public void setUebersetzung(String... uebersetzungen) {
		this.uebersetzung = new ArrayList<String>(Arrays.asList(uebersetzungen));
	}

	public boolean verify(String eingabe) {
		return uebersetzung.contains(eingabe);
	}

}
