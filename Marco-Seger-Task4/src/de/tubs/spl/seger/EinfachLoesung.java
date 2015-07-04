package de.tubs.spl.seger;

import javax.swing.JOptionPane;

public class EinfachLoesung implements AnzahlLoesung {

	@Override
	public Vokabel openAddDialog() {
		Vokabel vokabel = null;
		String antwort = JOptionPane.showInputDialog(null, "Vokabel hinzufuegen (Vokabel:Uebersetzung)");
		if (antwort != null) {
			String[] token = antwort.split(":");
			if (token.length != 2) {
				JOptionPane.showMessageDialog(null, "Nutzen Sie folgende Syntax: Vokabel:Uebersetzung",
						"Falsche Syntax", JOptionPane.ERROR_MESSAGE);
			} else {
				vokabel = new Vokabel(token[0], token[1]);
			}
		}
		return vokabel;
	}
}
