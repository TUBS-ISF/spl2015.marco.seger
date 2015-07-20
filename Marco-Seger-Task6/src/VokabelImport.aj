import java.io.File;
import java.io.IOException;

public aspect VokabelImport {

interface VokabelImport {

	public Vokabelliste importVokabelliste(File file) throws IOException;
}
}