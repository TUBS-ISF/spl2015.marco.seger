import java.io.File;
import java.io.IOException;

public interface VokabelExport {

	public void exportVokabelliste(Vokabelliste vokabelliste, File file) throws IOException;
}
