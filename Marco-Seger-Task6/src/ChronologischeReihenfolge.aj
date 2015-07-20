
public aspect ChronologischeReihenfolge {
	after() : call(Vokabeltrainer.new()){
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
	
	class ChronologischeReihenfolge implements Reihenfolge {

	private Vokabelliste vokabelliste;
	private int index = 0;

	public ChronologischeReihenfolge(Vokabelliste vokabelliste) {
		this.vokabelliste = vokabelliste;
	}
	
	@Override
	public Vokabel getNext() {
		index++;
		if (index >= vokabelliste.getVokabeln().size()) {
			index = 0;
		}
		return getAktuelleVokabel();
	}

	@Override
	public Vokabel getAktuelleVokabel() {
		return vokabelliste.getVokabeln().get(index);
	}

	@Override
	public void setVokabelliste(Vokabelliste vokabelliste) {
		this.vokabelliste = vokabelliste;
		index = 0;
	}
}
}