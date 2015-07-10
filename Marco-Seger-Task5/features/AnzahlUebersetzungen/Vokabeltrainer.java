import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

/**
 * TODO description
 */
public class Vokabeltrainer {

	public Vokabeltrainer() {
		JMenuItem menuItemEditVokabelliste = new JMenuItem("Vokabel hinzufuegen");
		menuVokabelliste.add(menuItemEditVokabelliste);

		menuItemEditVokabelliste.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Vokabel vokabel = loesung.openAddDialog();
				if (vokabel != null) {
					vokabelliste.addVokabel(vokabel);
				}
			}
		});
	}
}