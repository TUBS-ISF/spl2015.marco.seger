import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;

public class Vokabeltrainer extends JFrame {

	private Vokabelliste vokabelliste = new Vokabelliste();
	private Reihenfolge reihenfolge = new ChronologischeReihenfolge(vokabelliste);
	private AnzahlLoesung loesung = new EinfachLoesung();

	private JLabel vokabelLabel;
	private JTextField eingabeFeld;
	private JButton okButton;

	public Vokabeltrainer() {
		super("Vokabeltrainer");

		JMenuBar menuBar = new JMenuBar();
		this.add(menuBar, BorderLayout.NORTH);

		JMenu menuVokabelliste = new JMenu("Vokabelliste");
		menuBar.add(menuVokabelliste);
		JMenuItem menuItemEditVokabelliste = new JMenuItem("Vokabel hinzufuegen");
		menuVokabelliste.add(menuItemEditVokabelliste);

		menuItemEditVokabelliste.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Vokabel vokabel = loesung.openAddDialog();
				if (vokabel != null) {
					vokabelliste.addVokabel(vokabel);
				}
			}
		});
		
		JMenuItem menuItemExportVokabelliste = new JMenuItem("Vokabelliste exportieren");
		menuVokabelliste.add(menuItemExportVokabelliste);
		menuItemExportVokabelliste.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				 
				int userSelection = fileChooser.showSaveDialog(null);
				 
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    File file = fileChooser.getSelectedFile();
				    VokabelExport vokabelExport = new VokabelExportTXT();
				    try {
						vokabelExport.exportVokabelliste(vokabelliste, file);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		JMenuItem menuItemImportVokabelliste = new JMenuItem("Vokabelliste importieren");
		menuVokabelliste.add(menuItemImportVokabelliste);
		menuItemImportVokabelliste.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Vokabelliste vokabelliste = null;
				JFileChooser fileChooser = new JFileChooser();
				 
				int userSelection = fileChooser.showOpenDialog(null);
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    File file = fileChooser.getSelectedFile();
				    VokabelImport vokabelImport = new VokabelImportTXT();
				    try {
				    	vokabelliste = vokabelImport.importVokabelliste(file);
					} catch (IOException e) {
						e.printStackTrace();
					}
				    if (vokabelliste != null) {
				    	Vokabeltrainer.this.vokabelliste = vokabelliste;
				    	Vokabeltrainer.this.reihenfolge.setVokabelliste(vokabelliste);
				    }
				}
			}
		});

		JMenu menuReihenfolge = new JMenu("Reihenfolge");
		menuBar.add(menuReihenfolge);
		ButtonGroup group = new ButtonGroup();

		 JRadioButtonMenuItem menuItemChronologisch = new JRadioButtonMenuItem("Chronologisch");
		 menuReihenfolge.add(menuItemChronologisch);
		 group.add(menuItemChronologisch);
		 menuItemChronologisch.setSelected(true);
		 menuItemChronologisch.addActionListener(new ActionListener() {
		
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 Vokabeltrainer.this.reihenfolge = new ChronologischeReihenfolge(vokabelliste);
		 }
		 });

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

		
		
		JPanel panel = new JPanel();
		this.add(panel);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c;

		c = new GridBagConstraints(0, 0, 1, 1, 1, 0.1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						0, 0, 0, 0), 0, 0);
		panel.add(new JLabel("Vokabel"), c);

		c = new GridBagConstraints(1, 0, 1, 1, 1, 0.1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						0, 0, 0, 0), 0, 0);
		panel.add(new JLabel("Uebersetzung"), c);

		vokabelLabel = new JLabel();
		c = new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
		panel.add(vokabelLabel, c);

		eingabeFeld = new JTextField();
		c = new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		panel.add(eingabeFeld, c);

		okButton = new JButton("Vergleichen");
		c = new GridBagConstraints(1, 2, 1, 1, 1, 0.2,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0);
		panel.add(okButton, c);

		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Vokabel testen
				if (!vokabelliste.isEmpty()) {
					if (reihenfolge.getAktuelleVokabel() != null) {
						String eingabe = Vokabeltrainer.this.eingabeFeld
								.getText();
						boolean richtigeAntwort = reihenfolge
								.getAktuelleVokabel().verify(eingabe);
						String antwort;
						if (richtigeAntwort) {
							antwort = "Richtig!";
						} else {
							antwort = "Falsch!";
						}
						JOptionPane.showMessageDialog(Vokabeltrainer.this,
								antwort);

						// naechste Vokabel
						Vokabeltrainer.this.eingabeFeld.setText("");
						Vokabeltrainer.this.vokabelLabel.setText(reihenfolge.getNext().getVokabel());
					}

				} else {
					JOptionPane.showMessageDialog(Vokabeltrainer.this,
							"Keine Vokabeln vorhanden");
				}

			}
		});

		JButton nextButton = new JButton("Naechste Vokabel");
		c = new GridBagConstraints(0, 2, 1, 1, 1, 0.2,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0);
		panel.add(nextButton, c);

		nextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!vokabelliste.isEmpty()) {
					Vokabeltrainer.this.vokabelLabel.setText(reihenfolge
							.getNext().getVokabel());
				} else {
					JOptionPane.showMessageDialog(Vokabeltrainer.this,
							"Keine Vokabeln vorhanden.");
				}

			}
		});

		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
