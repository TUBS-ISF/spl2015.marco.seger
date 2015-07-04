package de.tubs.spl.seger;

import java.io.File;
import java.io.IOException;

public interface VokabelImport {

	public Vokabelliste importVokabelliste(File file) throws IOException;
}
