import java.io.File;
import java.io.IOException;

public aspect VokabelImport {

	after() : call(Vokabeltrainer.new()){
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

	interface VokabelImport {
		public Vokabelliste importVokabelliste(File file) throws IOException;
	}
}