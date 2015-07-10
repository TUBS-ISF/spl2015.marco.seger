import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButtonMenuItem;

/**
 * TODO description
 */
public class Vokabeltrainer {

	public Vokabeltrainer() {
		JRadioButtonMenuItem menuItemChronologisch = new JRadioButtonMenuItem(
				"Chronologisch");
		menuReihenfolge.add(menuItemChronologisch);
		group.add(menuItemChronologisch);
		menuItemChronologisch.setSelected(true);
		menuItemChronologisch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Vokabeltrainer.this.reihenfolge = new ChronologischeReihenfolge(
						vokabelliste);
			}
		});
		
		reihenfolge = new ChronologischeReihenfolge(vokabelliste);
	}

}