import java.util.Random;

public aspect ZufaelligeReihenfolge {

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