
public aspect VokabelExport {
	
	after() : call(Vokabeltrainer.new()){
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
	
	interface VokabelExport {
		public void exportVokabelliste(Vokabelliste vokabelliste, File file) throws IOException;
	}
}