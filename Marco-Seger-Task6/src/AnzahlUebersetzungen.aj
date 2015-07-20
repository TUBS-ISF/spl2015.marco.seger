
public aspect AnzahlUebersetzungen {
	after() : call(Vokabeltrainer.new()){
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
	
	
	interface AnzahlLoesung {

	public Vokabel openAddDialog();
	}
}