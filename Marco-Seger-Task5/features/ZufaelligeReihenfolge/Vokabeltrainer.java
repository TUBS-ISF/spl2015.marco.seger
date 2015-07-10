import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButtonMenuItem;

/**
 * TODO description
 */
public class Vokabeltrainer {

	public Vokabeltrainer() {
		JRadioButtonMenuItem menuItemZufaellig = new JRadioButtonMenuItem(
				"Zufaellig");
		menuReihenfolge.add(menuItemZufaellig);
		group.add(menuItemZufaellig);
		menuItemZufaellig.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Vokabeltrainer.this.reihenfolge = new ZufaelligeReihenfolge(vokabelliste);
			}
		});
		
		reihenfolge = new ZufaelligeReihenfolge(vokabelliste);
	}
}