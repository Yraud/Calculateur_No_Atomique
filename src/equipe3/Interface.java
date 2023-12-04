package equipe3;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class Interface {

	private JFrame frmIonsXo;
	private JLabel titre;
	private JTextField zoneTexte;
	private JButton btnConfirmer;
	private JButton btnConfirmerIons;
	private JTextArea zoneConsigne;
	private String message;
	private static double nombreElectron;
	private static double nombreIon;
	private JLabel affichageReponse;
	private JLabel instruction;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) { 			//Sert a démarrer l'interface
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frmIonsXo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIonsXo = new JFrame();
		frmIonsXo.setTitle("IONS XO3-");
		frmIonsXo.setBounds(100, 100, 1000, 700);
		frmIonsXo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIonsXo.getContentPane().setLayout(null);

		titre = new JLabel("Bienvenue!");
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setFont(new Font("Tahoma", Font.BOLD, 30));
		titre.setBounds(243, 44, 486, 85);
		frmIonsXo.getContentPane().add(titre);

		zoneTexte = new JTextField();
		zoneTexte.setBackground(Color.WHITE);
		zoneTexte.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				zoneTexte.setText(null);
				zoneTexte.setFont(new Font("Tahoma",Font.PLAIN,20));
			}
		});
		zoneTexte.setFont(new Font("Tahoma", Font.ITALIC, 20));
		zoneTexte.setHorizontalAlignment(SwingConstants.CENTER);
		zoneTexte.setText("Veuillez écrire ici...");
		zoneTexte.setBounds(243, 228, 486, 69);
		frmIonsXo.getContentPane().add(zoneTexte);

		zoneConsigne = new JTextArea();
		zoneConsigne.setRows(2);
		zoneConsigne.setFont(new Font("Tahoma", Font.PLAIN, 15));
		zoneConsigne.setBackground(new java.awt.Color(240,240,240));
		zoneConsigne.setEditable(false);
		zoneConsigne.setText("      Écrivez le nombre d'électrons puis appuyez sur \"Entrez\".\n     Dans le cas d'une notation scientifique, veuillez écrire un \"E\" \n                        entre la mantisse et l'exposant");
		zoneConsigne.setBounds(253, 139, 486, 74);
		frmIonsXo.getContentPane().add(zoneConsigne);

		btnConfirmer = new JButton("Entrez");
		btnConfirmer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				message = zoneTexte.getText().toUpperCase();
				try {
					nombreElectron = notationScientifique(message);
					affichageReponse.setText("Parfait! " + nombreElectron + " électrons");
					affichageReponse.setVisible(true);
					zoneTexte.setText("Veuillez écrire ici...");
					instruction.setVisible(true);
					btnConfirmer.removeActionListener(this);
					btnConfirmerIons.setVisible(true);
					btnConfirmer.setVisible(false);
				} catch (Exception excep) {
					JOptionPane.showMessageDialog(frmIonsXo, "Veuillez vous assurer d'écrire des chiffres et un \"E\" entre la mantisse et l'exposant");
				}
			}

		});

		btnConfirmer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnConfirmer.setBounds(401, 341, 186, 48);
		frmIonsXo.getContentPane().add(btnConfirmer);

		btnConfirmerIons = new JButton("Entrez");
		btnConfirmerIons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				message = zoneTexte.getText().toUpperCase();
				try {
					nombreIon = notationScientifique(message);
					btnConfirmerIons.removeActionListener(this);
					btnConfirmerIons.setVisible(false);
					affichageReponse.setText("Parfait! " + nombreIon + " ions");
					instruction.setText("Le numéro atomique est donc " + numeroAtomique());
				} catch (Exception excep) {
					JOptionPane.showMessageDialog(frmIonsXo, "Veuillez vous assurer d'écrire des chiffres et un \"E\" entre la mantisse et l'exposant");
				}
			}
		});
		btnConfirmerIons.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnConfirmerIons.setBounds(401, 341, 186, 48);
		btnConfirmerIons.setVisible(false);
		frmIonsXo.getContentPane().add(btnConfirmerIons);
		
		affichageReponse = new JLabel();
		affichageReponse.setBounds(243, 440, 486, 48);
		affichageReponse.setHorizontalAlignment(SwingConstants.CENTER);
		affichageReponse.setFont(new Font("Tahoma", Font.BOLD, 30));
		affichageReponse.setVisible(false);
		frmIonsXo.getContentPane().add(affichageReponse);
		
		instruction = new JLabel("Entrez maintenant le nombre d'ions");
		instruction.setFont(new Font("Tahoma", Font.BOLD, 26));
		instruction.setHorizontalAlignment(SwingConstants.CENTER);
		instruction.setBounds(253, 498, 476, 64);
		instruction.setVisible(false);
		frmIonsXo.getContentPane().add(instruction);
	}
	public static double notationScientifique(String entree) {
		double mantisse,exposant,notationScientifique;
		int positionE;

		if (entree.contains("E")) {
			positionE = entree.indexOf("E");
			mantisse = Double.parseDouble(entree.substring(0,positionE));
			exposant = Double.parseDouble(entree.substring(positionE+1));

			notationScientifique = mantisse*Math.pow(10, exposant);
		} else {
			notationScientifique = Double.parseDouble(entree);
		}

		return notationScientifique;
	}
	public static int numeroAtomique() {
		int numeroAtomique;
		
		numeroAtomique  = (int)(nombreElectron/nombreIon)-25;
		
		return numeroAtomique;
	}
}
