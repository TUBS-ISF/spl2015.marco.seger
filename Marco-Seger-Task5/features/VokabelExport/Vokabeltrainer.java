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
		JMenuItem menuItemExportVokabelliste = new JMenuItem("Vokabelliste exportieren");
		menuVokabelliste.add(menuItemExportVokabelliste);
		menuItemExportVokabelliste.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				 
				int userSelection = fileChooser.showSaveDialog(null);
				 
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    File file = fileChooser.getSelectedFile();
				    VokabelExport vokabelExport = new VokabelExportTXT();
				    try {
						vokabelExport.exportVokabelliste(vokabelliste, file);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
}