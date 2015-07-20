import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

public aspect Mehrfachloesung {

	after() : call(Vokabeltrainer.new()){
		private AnzahlLoesung loesung = new MehrfachLoesung();
	}
	

class MehrfachLoesung implements AnzahlLoesung {

	@Override
	public Vokabel openAddDialog() {
		Vokabel vokabel = null;
		String antwort = JOptionPane
				.showInputDialog(null,
						"Vokabel hinzufuegen (Vokabel:Uebersetzung1:Uebersetzung2:...)");
		if (antwort != null) {
			String[] token = antwort.split(":");
			if (token.length < 2) {
				JOptionPane
						.showMessageDialog(
								null,
								"Nutzen Sie folgende Syntax: Vokabel:Uebersetzung1[:Uebersetzung2:...]",
								"Falsche Syntax", JOptionPane.ERROR_MESSAGE);
			} else {
				List<String> uebersetzungen = new ArrayList<String>(
						Arrays.asList(token));
				uebersetzungen.remove(0);
				vokabel = new Vokabel(token[0], uebersetzungen);
			}
		}
		return vokabel;
	}
}
}