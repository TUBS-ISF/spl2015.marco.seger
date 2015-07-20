
public aspect VokabelExport {
	
	public interface VokabelExport {
		public void exportVokabelliste(Vokabelliste vokabelliste, File file) throws IOException;
	}
}