package de.tubs.spl.seger;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		for (String arg : args) {
			if (arg.equals("-av")) {
				Config.addVokabeln = true;
			} else if (arg.equals("-co")) {
				Config.chronologicalOrder = true;
			} else if (arg.equals("-ro")) {
				Config.randomOrder = true;
			} else if (arg.equals("-help")) {
				System.out.println("-av, Vokabel-Anlegen");
				System.out.println("-co, Chronologische Reihenfolge");
				System.out.println("-ro, Zufällige Reihenfolge");
				System.exit(0);
			}
		}

		if (!Config.chronologicalOrder && !Config.randomOrder) {
			System.out.println("Mind. eine Reihenfolge muss gewählt werden.");
			System.exit(0);
		}

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

		if (Config.addVokabeln) {
			JMenu menuVokabelliste = new JMenu("Vokabelliste");
			menuBar.add(menuVokabelliste);
			JMenuItem menuItemEditVokabelliste = new JMenuItem(
					"Vokabel hinzufügen");
			menuVokabelliste.add(menuItemEditVokabelliste);

			menuItemEditVokabelliste.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					String antwort = JOptionPane.showInputDialog(
							Vokabeltrainer.this,
							"Vokabel hinzufügen (Vokabel:Übersetzung)");
					if (antwort != null) {
						String[] token = antwort.split(":");
						if (token.length != 2) {
							JOptionPane
									.showMessageDialog(
											Vokabeltrainer.this,
											"Nutzen Sie folgende Syntax: Vokabel:Übersetzung",
											"Falsche Syntax",
											JOptionPane.ERROR_MESSAGE);
						} else {
							Vokabel vokabel = new Vokabel(token[0], token[1]);
							Vokabeltrainer.this.vokabelliste
									.addVokabel(vokabel);
						}
					}
				}
			});
		}

		JMenu menuReihenfolge = new JMenu("Reihenfolge");
		menuBar.add(menuReihenfolge);
		ButtonGroup group = new ButtonGroup();
		if (Config.chronologicalOrder) {
			JRadioButtonMenuItem menuItemChronologisch = new JRadioButtonMenuItem(
					"Chronologisch");
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
		}

		if (Config.randomOrder) {
			JRadioButtonMenuItem menuItemZufällig = new JRadioButtonMenuItem(
					"Zufällig");
			menuReihenfolge.add(menuItemZufällig);
			group.add(menuItemZufällig);
			Vokabeltrainer.this.vokabelliste
					.setReihenfolge(Reihenfolge.ZUFÄLLIG);
			menuItemZufällig.setSelected(true);
			menuItemZufällig.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Vokabeltrainer.this.vokabelliste
							.setReihenfolge(Reihenfolge.ZUFÄLLIG);

				}
			});
		}

		this.add(panel);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c;

		c = new GridBagConstraints(0, 0, 1, 1, 1, 0.1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
		panel.add(new JLabel("Vokabel"), c);
		
		c = new GridBagConstraints(1, 0, 1, 1, 1, 0.1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
		panel.add(new JLabel("Übersetzung"), c);
		
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

						// nächste Vokabel
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

		JButton nextButton = new JButton("Nächste Vokabel");
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
