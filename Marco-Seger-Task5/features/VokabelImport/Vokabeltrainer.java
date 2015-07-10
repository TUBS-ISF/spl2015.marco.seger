import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

/**
 * TODO description
 */
public class Vokabeltrainer {

	public Vokabeltrainer() {
		JMenuItem menuItemImportVokabelliste = new JMenuItem("Vokabelliste importieren");
		menuVokabelliste.add(menuItemImportVokabelliste);
		menuItemImportVokabelliste.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Vokabelliste vokabelliste = null;
				JFileChooser fileChooser = new JFileChooser();
				 
				int userSelection = fileChooser.showOpenDialog(null);
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    File file = fileChooser.getSelectedFile();
				    VokabelImport vokabelImport = new VokabelImportTXT();
				    try {
				    	vokabelliste = vokabelImport.importVokabelliste(file);
					} catch (IOException e) {
						e.printStackTrace();
					}
				    if (vokabelliste != null) {
				    	Vokabeltrainer.this.vokabelliste = vokabelliste;
				    	Vokabeltrainer.this.reihenfolge.setVokabelliste(vokabelliste);
				    }
				}
			}
		});
	}
}