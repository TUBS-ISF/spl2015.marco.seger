
public aspect Reihenfolge {
	
	interface Reihenfolge {
		public void setVokabelliste(Vokabelliste vokabelliste);
		public Vokabel getNext();
		public Vokabel getAktuelleVokabel();
	}
}