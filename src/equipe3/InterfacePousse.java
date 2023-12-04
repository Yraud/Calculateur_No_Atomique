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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.border.LineBorder;
import javax.swing.JLayeredPane;
import javax.swing.JList;

public class InterfacePousse {

	private JFrame frmCalcul;
	private JLabel titre;
	private JTextField zoneTexte;
	private JButton btnConfirmer;
	private JButton btnConfirmerIons;
	private JTextArea zoneConsigne;
	private static double nombreElectron;
	private static double nombreIon;
	private static int electronSoustrait;
	private JLabel affichageReponse;
	private JLabel instruction;
	private JInternalFrame internalFrame;
	private JLabel titreIon;
	private JTextField zoneTexteAtome1;
	private JTextField indiceAtome1;
	private JTextArea ConsigneAtome1;
	private JTextField zoneTexteAtome2;
	private JTextField indiceAtome2;
	private JTextArea ConsigneAtome2;
	private JTextField zoneTexteCharge;
	private JTextField ConsigneCharge;
	private JButton btnValiderIon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacePousse window = new InterfacePousse();
					window.frmCalcul.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public InterfacePousse() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCalcul = new JFrame();
		frmCalcul.setTitle("IONS XO3-");
		frmCalcul.setBounds(100, 100, 1000, 700);
		frmCalcul.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCalcul.getContentPane().setLayout(null);

		titre = new JLabel("Bienvenue!");
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setFont(new Font("Tahoma", Font.BOLD, 30));
		titre.setBounds(243, 44, 486, 85);
		frmCalcul.getContentPane().add(titre);

		zoneTexte = new JTextField();
		zoneTexte.setBackground(Color.WHITE);
		zoneTexte.setFocusable(true);
		zoneTexte.setFocusTraversalKeysEnabled(false);
		zoneTexte.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				zoneTexte.setText(null);
				zoneTexte.setFont(new Font("Tahoma",Font.PLAIN,20));
				
			}
		});
		zoneTexte.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				int touche = e.getKeyCode();
				if (touche == KeyEvent.VK_ENTER) {
					if (btnConfirmer.isEnabled()) {
						btnConfirmer.doClick();
					} else {
						btnConfirmerIons.doClick();
					}
							
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					zoneTexte.setText(null);
					zoneTexte.setFont(new Font("Tahoma",Font.PLAIN,20));
				}

				
			}
		});
		zoneTexte.setFont(new Font("Tahoma", Font.ITALIC, 20));
		zoneTexte.setHorizontalAlignment(SwingConstants.CENTER);
		zoneTexte.setText("Veuillez écrire ici...");
		zoneTexte.setBounds(243, 228, 486, 69);
		zoneTexte.setVisible(false);
		frmCalcul.getContentPane().add(zoneTexte);

		zoneConsigne = new JTextArea();
		zoneConsigne.setRows(2);
		zoneConsigne.setFont(new Font("Tahoma", Font.PLAIN, 15));
		zoneConsigne.setBackground(new java.awt.Color(240,240,240));
		zoneConsigne.setEditable(false);
		zoneConsigne.setText("      Écrivez le nombre d'électrons puis appuyez sur \"Entrez\".\n     Dans le cas d'une notation scientifique, veuillez écrire un \"E\" \n                        entre la mantisse et l'exposant");
		zoneConsigne.setBounds(253, 139, 486, 74);
		zoneConsigne.setVisible(false);
		frmCalcul.getContentPane().add(zoneConsigne);

		btnConfirmer = new JButton("Entrez");
		btnConfirmer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = zoneTexte.getText().toUpperCase();
				try {
					nombreElectron = notationScientifique(message);
					affichageReponse.setText("Parfait! " + nombreElectron + " électrons");
					affichageReponse.setVisible(true);
					zoneTexte.setText("Veuillez écrire ici...");
					instruction.setVisible(true);
					btnConfirmer.removeActionListener(this);
					btnConfirmerIons.setVisible(true);
					btnConfirmer.setEnabled(false);
					btnConfirmer.setVisible(false);
				} catch (Exception excep) {
					JOptionPane.showMessageDialog(frmCalcul, "Veuillez vous assurer d'écrire des chiffres et un \"E\" entre la mantisse et l'exposant");
				}
			}

		});

		btnConfirmer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnConfirmer.setBounds(401, 341, 186, 48);
		btnConfirmer.setVisible(false);
		frmCalcul.getContentPane().add(btnConfirmer);

		btnConfirmerIons = new JButton("Entrez");
		btnConfirmerIons.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = zoneTexte.getText().toUpperCase();
				try {
					nombreIon = notationScientifique(message);
					btnConfirmerIons.removeActionListener(this);
					btnConfirmerIons.setVisible(false);
					affichageReponse.setText("Parfait! " + nombreIon + " ions");
					instruction.setText("Le numéro atomique est donc " + numeroAtomique());
				} catch (Exception excep) {
					JOptionPane.showMessageDialog(frmCalcul, "Veuillez vous assurer d'écrire des chiffres et un \"E\" entre la mantisse et l'exposant");
				}
			}
		});
		btnConfirmerIons.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnConfirmerIons.setBounds(401, 341, 186, 48);
		btnConfirmerIons.setVisible(false);
		frmCalcul.getContentPane().add(btnConfirmerIons);
		
		affichageReponse = new JLabel();
		affichageReponse.setBounds(243, 440, 486, 48);
		affichageReponse.setHorizontalAlignment(SwingConstants.CENTER);
		affichageReponse.setFont(new Font("Tahoma", Font.BOLD, 30));
		affichageReponse.setVisible(false);
		frmCalcul.getContentPane().add(affichageReponse);
		
		instruction = new JLabel("Entrez maintenant le nombre d'ions");
		instruction.setFont(new Font("Tahoma", Font.BOLD, 26));
		instruction.setHorizontalAlignment(SwingConstants.CENTER);
		instruction.setBounds(253, 498, 476, 64);
		instruction.setVisible(false);
		frmCalcul.getContentPane().add(instruction);
		
		internalFrame = new JInternalFrame("Définir votre ion");
		internalFrame.setBounds(243, 103, 486, 385);
		internalFrame.getContentPane().setLayout(null);
		internalFrame.setOpaque(true);
		frmCalcul.getContentPane().add(internalFrame);
		
		
		titreIon = new JLabel("Veuillez définir votre ion");
		titreIon.setLabelFor(internalFrame);
		titreIon.setForeground(new Color(0, 0, 0));
		titreIon.setHorizontalAlignment(SwingConstants.CENTER);
		titreIon.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titreIon.setBounds(118, 11, 235, 30);
		internalFrame.getContentPane().add(titreIon);
		
		zoneTexteAtome1 = new JTextField();
		zoneTexteAtome1.setHorizontalAlignment(SwingConstants.CENTER);
		zoneTexteAtome1.setBounds(38, 142, 86, 20);
		internalFrame.getContentPane().add(zoneTexteAtome1);
		zoneTexteAtome1.setColumns(10);
		
		indiceAtome1 = new JTextField();
		indiceAtome1.setHorizontalAlignment(SwingConstants.CENTER);
		indiceAtome1.setBounds(129, 142, 18, 20);
		internalFrame.getContentPane().add(indiceAtome1);
		indiceAtome1.setColumns(10);
		
		ConsigneAtome1 = new JTextArea();
		ConsigneAtome1.setText("Écrire le symbole de \n    l'atome et son\n  indice au dessus.");
		ConsigneAtome1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ConsigneAtome1.setBounds(45, 170, 108, 42);
		ConsigneAtome1.setAlignmentX(57);
		internalFrame.getContentPane().add(ConsigneAtome1);
		internalFrame.setVisible(true);
		
		zoneTexteAtome2 = new JTextField();
		zoneTexteAtome2.setHorizontalAlignment(SwingConstants.CENTER);
		zoneTexteAtome2.setBounds(155, 142, 86, 20);
		internalFrame.getContentPane().add(zoneTexteAtome2);
		zoneTexteAtome2.setColumns(10);
		
		indiceAtome2 = new JTextField();
		indiceAtome2.setHorizontalAlignment(SwingConstants.CENTER);
		indiceAtome2.setBounds(246, 142, 18, 20);
		internalFrame.getContentPane().add(indiceAtome2);
		indiceAtome2.setColumns(10);
		
		ConsigneAtome2 = new JTextArea();
		ConsigneAtome2.setText("Écrire le symbole de \n    l'atome 2 et son\n  indice au dessus.");
		ConsigneAtome2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ConsigneAtome2.setBounds(160, 170, 114, 42);
		ConsigneAtome2.setAlignmentX(57);
		internalFrame.getContentPane().add(ConsigneAtome2);
		
		zoneTexteCharge = new JTextField();
		zoneTexteCharge.setFont(new Font("Tahoma", Font.PLAIN, 15));
		zoneTexteCharge.setHorizontalAlignment(SwingConstants.CENTER);
		zoneTexteCharge.setBounds(345, 131, 48, 42);
		internalFrame.getContentPane().add(zoneTexteCharge);
		zoneTexteCharge.setColumns(10);
		
		ConsigneCharge = new JTextField();
		ConsigneCharge.setEditable(false);
		ConsigneCharge.setBorder(null);
		ConsigneCharge.setText("Entrez la charge au dessus");
		ConsigneCharge.setBounds(303, 184, 157, 20);
		internalFrame.getContentPane().add(ConsigneCharge);
		ConsigneCharge.setColumns(10);
		
		btnValiderIon = new JButton("Valider l'ion");
		btnValiderIon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int electronAtome1 = Elements.getElement(zoneTexteAtome1.getText()).getNombreElectrons()*Integer.parseInt(indiceAtome1.getText());
				int electronAtome2 = Elements.getElement(zoneTexteAtome2.getText()).getNombreElectrons()*Integer.parseInt(indiceAtome2.getText());
				electronSoustrait = electronAtome1 + electronAtome2;			}
		});
		btnValiderIon.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnValiderIon.setBounds(152, 281, 185, 51);
		internalFrame.getContentPane().add(btnValiderIon);
		internalFrame.setVisible(true);
		
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
		
		numeroAtomique  = (int)(nombreElectron/nombreIon)-electronSoustrait;
		
		return numeroAtomique;
	}
}
