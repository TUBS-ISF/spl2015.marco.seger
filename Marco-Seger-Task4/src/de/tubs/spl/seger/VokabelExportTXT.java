package de.tubs.spl.seger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class VokabelExportTXT implements VokabelExport {

	@Override
	public void exportVokabelliste(Vokabelliste vokabelliste, File file) throws IOException {
		
		FileWriter fw = new FileWriter(file);
	    BufferedWriter bw = new BufferedWriter(fw);

	    List<Vokabel> vokabeln = vokabelliste.getVokabeln();
	    for (Vokabel v : vokabeln) {
	    	StringBuilder sb = new StringBuilder();
	    	sb.append(v.getVokabel());
	    	for (String uebersetzung : v.getUebersetzung()) {
	    		sb.append(":" + uebersetzung);
	    	}
	    	bw.write(sb.toString());
	    	bw.newLine();
	    }
	    bw.close();
	}

	
}
