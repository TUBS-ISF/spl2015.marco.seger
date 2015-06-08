package de.tubs.spl.seger;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.tubs.spl.seger.Vokabelliste.Reihenfolge;

public class Vokabeltrainer extends JFrame {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		new Vokabeltrainer();
	}

	private Vokabelliste vokabelliste = new Vokabelliste();

	private JLabel vokabelLabel;
	private JTextField eingabeFeld;
	private JButton okButton;

	public Vokabeltrainer() {
		super("Vokabeltrainer");

		JPanel panel = new JPanel();
		JMenuBar menuBar = new JMenuBar();
		this.add(menuBar, BorderLayout.NORTH);

		// #ifdef VokabelAnlegen
		JMenu menuVokabelliste = new JMenu("Vokabelliste");
		menuBar.add(menuVokabelliste);
		JMenuItem menuItemEditVokabelliste = new JMenuItem(
				"Vokabel hinzufuegen");
		menuVokabelliste.add(menuItemEditVokabelliste);

		menuItemEditVokabelliste.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String antwort = null;
				// #ifdef Einfachloesung
//@
//@				antwort = JOptionPane.showInputDialog(
//@						Vokabeltrainer.this,
//@						"Vokabel hinzufuegen (Vokabel:Uebersetzung)");
//@				if (antwort != null) {
//@					String[] token = antwort.split(":");
//@					if (token.length != 2) {
//@						JOptionPane
//@								.showMessageDialog(
//@										Vokabeltrainer.this,
//@										"Nutzen Sie folgende Syntax: Vokabel:Uebersetzung",
//@										"Falsche Syntax",
//@										JOptionPane.ERROR_MESSAGE);
//@					} else {
//@						Vokabel vokabel = new Vokabel(token[0], token[1]);
//@						Vokabeltrainer.this.vokabelliste.addVokabel(vokabel);
//@					}
//@				}
				//#endif
				
				// #ifdef Mehrfachloesung
				antwort = JOptionPane.showInputDialog(
						Vokabeltrainer.this,
						"Vokabel hinzufuegen (Vokabel:Uebersetzung1:Uebersetzung2:...)");
				if (antwort != null) {
					String[] token = antwort.split(":");
					if (token.length < 2) {
						JOptionPane
								.showMessageDialog(
										Vokabeltrainer.this,
										"Nutzen Sie folgende Syntax: Vokabel:Uebersetzung1[:Uebersetzung2:...]",
										"Falsche Syntax",
										JOptionPane.ERROR_MESSAGE);
					} else {
						List<String> uebersetzungen = new ArrayList<String>(Arrays.asList(token));
						uebersetzungen.remove(0);
						Vokabel vokabel = new Vokabel(token[0], uebersetzungen);
						Vokabeltrainer.this.vokabelliste.addVokabel(vokabel);
					}
				}
				//#endif
			}
		});
		// #endif

		JMenu menuReihenfolge = new JMenu("Reihenfolge");
		menuBar.add(menuReihenfolge);
		ButtonGroup group = new ButtonGroup();

		// #ifdef ChronologischeReihenfolge
		 JRadioButtonMenuItem menuItemChronologisch = new JRadioButtonMenuItem("Chronologisch");
		 menuReihenfolge.add(menuItemChronologisch);
		 group.add(menuItemChronologisch);
		 Vokabeltrainer.this.vokabelliste
		 .setReihenfolge(Reihenfolge.CHRONOLOGISCH);
		 menuItemChronologisch.setSelected(true);
		 menuItemChronologisch.addActionListener(new ActionListener() {
		
		 @Override
		 public void actionPerformed(ActionEvent e) {
		 Vokabeltrainer.this.vokabelliste
		 .setReihenfolge(Reihenfolge.CHRONOLOGISCH);
		
		 }
		 });
		// #endif

		// #ifdef ZufaelligeReihenfolge
		JRadioButtonMenuItem menuItemZufaellig = new JRadioButtonMenuItem(
				"Zufaellig");
		menuReihenfolge.add(menuItemZufaellig);
		group.add(menuItemZufaellig);
		Vokabeltrainer.this.vokabelliste.setReihenfolge(Reihenfolge.ZUFAELLIG);
		menuItemZufaellig.setSelected(true);
		menuItemZufaellig.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Vokabeltrainer.this.vokabelliste
						.setReihenfolge(Reihenfolge.ZUFAELLIG);

			}
		});
		// #endif

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
					if (vokabelliste.getAktuelleVokabel() != null) {
						String eingabe = Vokabeltrainer.this.eingabeFeld
								.getText();
						boolean richtigeAntwort = vokabelliste
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
						Vokabeltrainer.this.vokabelLabel.setText(vokabelliste
								.next().getVokabel());
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
					Vokabeltrainer.this.vokabelLabel.setText(vokabelliste
							.next().getVokabel());
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
