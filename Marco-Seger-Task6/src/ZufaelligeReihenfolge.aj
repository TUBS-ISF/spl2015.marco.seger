import java.util.Random;

public aspect ZufaelligeReihenfolge {

	after() : call(Vokabeltrainer.new()){
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
	
class ZufaelligeReihenfolge implements Reihenfolge {

	private Vokabelliste vokabelliste;
	private Random random = new Random();
	private Vokabel aktuelleVokabel;
	
	public ZufaelligeReihenfolge(Vokabelliste vokabelliste) {
		this.vokabelliste = vokabelliste;
	}
	
	@Override
	public Vokabel getNext() {
	    int randomNum = random.nextInt(vokabelliste.getVokabeln().size());
	    aktuelleVokabel = vokabelliste.getVokabeln().get(randomNum);
		return aktuelleVokabel;
	}

	@Override
	public Vokabel getAktuelleVokabel() {
		if (aktuelleVokabel == null) {
			aktuelleVokabel = getNext();
		}
		return aktuelleVokabel;
	}

	@Override
	public void setVokabelliste(Vokabelliste vokabelliste) {
		this.vokabelliste = vokabelliste;
	}

}
}