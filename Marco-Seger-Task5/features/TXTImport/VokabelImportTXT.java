import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VokabelImportTXT implements VokabelImport {

	@Override
	public Vokabelliste importVokabelliste(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;
		Vokabelliste vokabelliste = new Vokabelliste();
		
		while ((line = br.readLine()) != null) {
			String[] token = line.split(":");
			List<String> uebersetzungen = new ArrayList<String>(
					Arrays.asList(token));
			uebersetzungen.remove(0);
			Vokabel vokabel = new Vokabel(token[0], uebersetzungen);
			vokabelliste.addVokabel(vokabel);
		}
		br.close();
		return vokabelliste;
	}

}
