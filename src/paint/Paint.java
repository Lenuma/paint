package paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.border.LineBorder;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JFileChooser;

public class Paint extends JFrame {
	
	private JPanel outils;
	// CD- 3.1
	private surfaceDessin surface;
	// Gï¿½ - 2
	private Ecouteur ecSurface = new Ecouteur();
	private Ecouteur ecOutils = new Ecouteur();
	
	private Vector<Trait> traits = new Vector<Trait>();
	private int tailleDuTrait;
	
	private Color coul1;
	private Color coul2;
	private boolean surfaceInit = true;
	
	private JPanel couleurs;
	private JLabel magenta;
	private JLabel rose;
	private JLabel jaune;
	private JLabel rouge;
	private JLabel bleu;
	private JLabel orange;
	private JLabel vert;
	private JLabel cyan;
	private JLabel noir;
	private JLabel blanc;
	private JToggleButton couleur1;
	private JToggleButton couleur2;
	
	private JToggleButton triangle;
	private JToggleButton cercle;
	private JToggleButton rectangle;
	private JToggleButton crayon;
	private JToggleButton efface;
	private JToggleButton pipette;
	private JToggleButton potPeinture;
	private JButton enregistre;
	private JLabel labelTrait;
	private JTextField tailleTrait;
	private JLabel labelCouleur1;
	private JLabel labelCouleur2;

	/*******************************************
	 *                  Main                   *
	 *******************************************/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Paint frame = new Paint();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**********************************************
	 *                Constructeur                *
	 **********************************************/
	public Paint() {
		setTitle("Paint");
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		//Panels
		outils = new JPanel();
		outils.setBounds(10, 11, 784, 82);
		getContentPane().add(outils);
		outils.setLayout(null);
		
		couleurs = new JPanel();
		couleurs.setBounds(552, 5, 226, 71);
		outils.add(couleurs);
		couleurs.setLayout(new GridLayout(2, 5, 1, 1));
		
		//Jlabels des couleurs
		rose = new JLabel("");
		rose.setBorder(new LineBorder(new Color(0, 0, 0)));
		rose.setOpaque(true);
		rose.setBackground(Color.PINK);
		couleurs.add(rose);
		
		jaune = new JLabel("");
		jaune.setBorder(new LineBorder(new Color(0, 0, 0)));
		jaune.setOpaque(true);
		jaune.setBackground(Color.YELLOW);
		couleurs.add(jaune);
		
		magenta = new JLabel("");
		magenta.setBorder(new LineBorder(new Color(0, 0, 0)));
		magenta.setOpaque(true);
		magenta.setBackground(Color.MAGENTA);
		couleurs.add(magenta);
		
		bleu = new JLabel("");
		bleu.setBorder(new LineBorder(new Color(0, 0, 0)));
		bleu.setOpaque(true);
		bleu.setBackground(Color.BLUE);
		couleurs.add(bleu);
		
		rouge = new JLabel("");
		rouge.setBorder(new LineBorder(new Color(0, 0, 0)));
		rouge.setOpaque(true);
		rouge.setBackground(Color.RED);
		couleurs.add(rouge);
		
		orange = new JLabel("");
		orange.setBorder(new LineBorder(new Color(0, 0, 0)));
		orange.setBackground(Color.ORANGE);
		orange.setOpaque(true);
		couleurs.add(orange);
		
		vert = new JLabel("");
		vert.setBorder(new LineBorder(new Color(0, 0, 0)));
		vert.setOpaque(true);
		vert.setBackground(Color.GREEN);
		couleurs.add(vert);
		
		cyan = new JLabel("");
		cyan.setBorder(new LineBorder(new Color(0, 0, 0)));
		cyan.setBackground(Color.CYAN);
		cyan.setOpaque(true);
		couleurs.add(cyan);
		
		noir = new JLabel("");
		noir.setBorder(new LineBorder(new Color(0, 0, 0)));
		noir.setOpaque(true);
		noir.setBackground(Color.BLACK);
		couleurs.add(noir);
		
		blanc = new JLabel("");
		blanc.setBorder(new LineBorder(new Color(0, 0, 0)));
		blanc.setBackground(Color.WHITE);
		blanc.setOpaque(true);
		couleurs.add(blanc);
		
		// Toggle Buttons Couleurs 1 et 2
		couleur1 = new JToggleButton("Couleur 1");
		couleur1.setSelected(true); //Initialise la fenÃªtre avec ce bouton de sÃ©lectionnÃ©
		couleur1.setLayout(new FlowLayout()); //Important pour ajouter un JLabel au bouton
		couleur1.setFont(new Font("Tahoma", Font.PLAIN, 7));
		couleur1.setVerticalAlignment(SwingConstants.BOTTOM);
		couleur1.setBounds(375, 11, 82, 65);
		outils.add(couleur1);
		
		couleur2 = new JToggleButton("Couleur 2");
		couleur2.setSelected(false);
		couleur2.setLayout(new FlowLayout()); //Important pour ajouter un JLabel au bouton
		couleur2.setFont(new Font("Tahoma", Font.PLAIN, 7));
		couleur2.setVerticalAlignment(SwingConstants.BOTTOM);
		couleur2.setBounds(458, 11, 82, 65);
		outils.add(couleur2);
		
		//Crï¿½ation d'un toggle group et ajout des boutons
		ButtonGroup couleur1Et2 = new ButtonGroup();
		couleur1Et2.add(couleur1);
		couleur1Et2.add(couleur2);
		
		//Ajout de JLabel aux boutons "couleur1" et "couleur2"
		labelCouleur1 = new JLabel("");
		labelCouleur1.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelCouleur1.setPreferredSize(new Dimension(50, 30));
		labelCouleur1.setOpaque(true);
		labelCouleur1.setBackground(Color.BLACK);
		labelCouleur1.setBounds(388, 18, 61, 39);
		couleur1.add(labelCouleur1);
		
		labelCouleur2 = new JLabel("");
		labelCouleur2.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelCouleur2.setBackground(Color.WHITE);
		labelCouleur2.setPreferredSize(new Dimension(50, 30));
		labelCouleur2.setOpaque(true);
		labelCouleur2.setBounds(469, 22, 61, 39);
		couleur2.add(labelCouleur2);
		
		// Toggle buttons de formes
		triangle = new JToggleButton("");
		triangle.setIcon(new ImageIcon(".\\icones\\triangle.gif"));
		triangle.setBounds(328, 28, 35, 29);
		outils.add(triangle);
		
		cercle = new JToggleButton("");
		cercle.setIcon(new ImageIcon(".\\icones\\cercle.gif"));
		cercle.setBounds(281, 28, 35, 29);
		outils.add(cercle);
		
		rectangle = new JToggleButton("");
		rectangle.setIcon(new ImageIcon(".\\icones\\rectangle.gif"));
		rectangle.setBounds(234, 28, 35, 29);
		outils.add(rectangle);
		
		// Toggle buttons d'outils de dessin
		crayon = new JToggleButton("");
		crayon.setIcon(new ImageIcon(".\\icones\\crayon.gif"));
		crayon.setSelected(true);
		crayon.setBounds(6, 5, 28, 22);
		outils.add(crayon);
		
		efface = new JToggleButton("");
		efface.setIcon(new ImageIcon(".\\icones\\efface.gif"));
		efface.setBounds(46, 5, 28, 22);
		outils.add(efface);
		
		pipette = new JToggleButton("");
		pipette.setIcon(new ImageIcon(".\\icones\\pipette.gif"));
		pipette.setBounds(86, 5, 28, 22);
		outils.add(pipette);
		
		potPeinture = new JToggleButton("");
		potPeinture.setIcon(new ImageIcon(".\\icones\\remplissage.gif"));
		potPeinture.setBounds(126, 5, 28, 22);
		outils.add(potPeinture);
		
		ButtonGroup outilsDessin = new ButtonGroup();
		outilsDessin.add(crayon);
		outilsDessin.add(efface);
		outilsDessin.add(pipette);
		outilsDessin.add(potPeinture);
		outilsDessin.add(triangle);
		outilsDessin.add(cercle);
		outilsDessin.add(rectangle);
		
		// Bouton pour enregistrer
		enregistre = new JButton("");
		enregistre.setIcon(new ImageIcon(".\\icones\\save.gif"));
		enregistre.setBounds(188, 5, 28, 22);
		outils.add(enregistre);
		
		// DÃ©fnition de la taille du trait
		labelTrait = new JLabel("Taille du trait: ");
		labelTrait.setBounds(16, 39, 92, 16);
		outils.add(labelTrait);
		
		tailleTrait = new JTextField();
		tailleTrait.setText("1");
		tailleTrait.setBounds(106, 35, 35, 22);
		outils.add(tailleTrait);
		tailleTrait.setColumns(10);
		
		// CD 3.2
		surface = new surfaceDessin();
		surface.setBounds(10,100,779,450);
		getContentPane().add(surface);
		
		// GÉ - 3.1
		// Listener de la zone de dessin
		surface.addMouseListener(ecSurface);
		surface.addMouseMotionListener(ecSurface);
		
		// Listeners de la zone d'outils 
		//   Listeners des toggle buttons
		Component [] tabToggle = outils.getComponents();
		for (Component c : tabToggle) {
			if(c instanceof JToggleButton) {
				JToggleButton temp = (JToggleButton) c;
				temp.addActionListener(ecOutils);
			}
		}
		
		// 	Listeners des couleurs
		Component [] tabCouleur = couleurs.getComponents();
		for (Component c : tabCouleur) {
			if(c instanceof JLabel) {
				JLabel temp = (JLabel) c;
				temp.addMouseListener(ecOutils);
			}
		}
		
		// 	Listeners du JTextfield "tailleTrait" et du JButton "enregistre"
		tailleTrait.addActionListener(ecOutils);
		enregistre.addActionListener(ecOutils);
			
	}
	
	//CD - 1
	private class surfaceDessin extends JPanel {
		@Override
		//CD - 2
		protected void paintComponent(Graphics g) {
			//Important!
			super.paintComponent(g);
			if (!surfaceInit) {
				if (potPeinture.isSelected() && couleur1.isSelected()) {
					this.setBackground(coul1);
					for (int i = 0; i < traits.size(); i++) {
						if (traits.elementAt(i) instanceof Efface) {
							traits.elementAt(i).setColor(coul1);
						}
					}
				}	
				else if (potPeinture.isSelected() && couleur2.isSelected()){
					this.setBackground(coul2);
					for (int i = 0; i < traits.size(); i++) {
						if (traits.elementAt(i) instanceof Efface) {
							traits.elementAt(i).setColor(coul2);
						}
					}
				}
			}
			else {
				this.setBackground(Color.white);
				surfaceInit = false;
			}
			
			
			// Graphics2D nÃ©cessaire pour utiliser "BasicSroke()"
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			if (traits.size() > 0) {
				for (int i = 0; i < traits.size(); i++) {
					
					g2.setStroke(new BasicStroke(traits.elementAt(i).getTaille()));
					g.setColor(traits.elementAt(i).getColor());
					
					traits.elementAt(i).dessiner(g);
					
				}
			}
		}
	}
	
	//Fonction pour la pipette
	public BufferedImage recupererImage ( JPanel surface ) {
    	Dimension size = surface.getSize();
    	BufferedImage image = new BufferedImage( size.width, size.height, BufferedImage.TYPE_INT_RGB);
    	Graphics2D g2 = image.createGraphics();
    	surface.paint(g2);
    	
    	return image;
     }

	
	//GÉ - 1
	private class Ecouteur implements MouseListener, MouseMotionListener, ActionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			if (e.getSource() == surface) {
				if (crayon.isSelected() || efface.isSelected() || rectangle.isSelected() || cercle.isSelected() || triangle.isSelected()) {
					traits.elementAt(traits.size()-1).getVectPoints().add(new Point(e.getX(),e.getY()));
				}
			}
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			Color ColorSelected = null;
			
			if (e.getSource() == rose) {
				ColorSelected = Color.pink;
			}
			else if(e.getSource() == jaune) {
				ColorSelected = Color.yellow;
			}
			else if(e.getSource() == magenta) {
				ColorSelected = Color.magenta;
			}
			else if(e.getSource() == bleu) {
				ColorSelected = Color.blue;
			}
			else if(e.getSource() == rouge) {
				ColorSelected = Color.red;
			}
			else if(e.getSource() == orange) {
				ColorSelected = Color.orange;
			}
			else if(e.getSource() == vert) {
				ColorSelected = Color.green;
			}
			else if(e.getSource() == cyan) {
				ColorSelected = Color.cyan;
			}
			else if(e.getSource() == noir) {
				ColorSelected = Color.black;
			}
			else if(e.getSource() == blanc) {
				ColorSelected = Color.white;
			}
			
			if (couleur1.isSelected() && e.getSource() instanceof JLabel) {
				coul1 = ColorSelected;
				System.out.println(coul1);
				labelCouleur1.setBackground(ColorSelected);
			}
			else if (couleur2.isSelected() && e.getSource() instanceof JLabel){
				coul2 = ColorSelected;
				System.out.println(coul2);
				labelCouleur2.setBackground(ColorSelected);
			}
			
			if (potPeinture.isSelected() && e.getSource() == surface) {
				repaint();
			}
			else if (pipette.isSelected() && e.getSource() == surface) {
				BufferedImage image = recupererImage(surface);
				int pixel = image.getRGB(e.getX(), e.getY());
				Color couleurPixel = new Color(pixel);
				if (couleur1.isSelected()) {
					coul1 = couleurPixel;
					labelCouleur1.setBackground(coul1);
				}
				else if (couleur2.isSelected()) {
					coul2 = couleurPixel;
					labelCouleur2.setBackground(coul2);
				}
				crayon.setSelected(true);
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// 1- Vérifier quel JToggleButton d'outils de dessin est sélectionné
			if (crayon.isSelected()) {
				
				Trait traitDessine = new Trait();
				Point pointDessine = new Point(e.getX(),e.getY());
				traitDessine.setPoint(pointDessine);
				
				System.out.println("trait");
				
				if (tailleDuTrait != 0) {
					traitDessine.setTaille(tailleDuTrait);
				}
				if (e.getButton() == 1) {
					traitDessine.setColor(coul1);
				}
				else if (e.getButton() == 3){
					traitDessine.setColor(coul2);
				} 
				
				traits.add(traitDessine);
			}
			else if (efface.isSelected()) {
				
				Efface traitEfface = new Efface(10);
				Point pointDessine = new Point(e.getX(),e.getY());
				traitEfface.setPoint(pointDessine);
				System.out.println("efface");
				
				traitEfface.setColor(coul2);	
				traits.add(traitEfface);
			}
			else if (rectangle.isSelected()) {
				
				Rectangle traitRectangle = new Rectangle();
				Point pointDessine = new Point(e.getX(),e.getY());
				traitRectangle.setPoint(pointDessine);
				
				if (tailleDuTrait != 0) {
					traitRectangle.setTaille(tailleDuTrait);
				}
				traitRectangle.setColor(coul1);
				traits.add(traitRectangle);
	
			}
			else if (cercle.isSelected()) {
				Cercle traitCercle = new Cercle();
				Point pointDessine = new Point(e.getX(),e.getY());
				traitCercle.setPoint(pointDessine);
				
				if (tailleDuTrait != 0) {
					traitCercle.setTaille(tailleDuTrait);
				}
				traitCercle.setColor(coul1);
				traits.add(traitCercle);
			}
			else if (triangle.isSelected()) {
				Triangle traitTriangle = new Triangle();
				Point pointDessine = new Point(e.getX(),e.getY());
				traitTriangle.setPoint(pointDessine);
				
				if (tailleDuTrait != 0) {
					traitTriangle.setTaille(tailleDuTrait);
				}
				traitTriangle.setColor(coul1);
				traits.add(traitTriangle);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			Trait traitDessine = new Trait();
			Trait traitEfface = new Trait();
			Trait traitRectangle = new Trait();
			Trait traitCercle = new Trait();
			Trait traitTriangle = new Trait();
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == tailleTrait) {
				tailleDuTrait = Integer.parseInt(tailleTrait.getText());
			}
			else if (e.getSource() == efface) {
				coul2 = surface.getBackground();
				labelCouleur2.setBackground(coul2);
				couleur2.setSelected(true);
			}
			else if (e.getSource() == enregistre) {
				File fichier;
				JFileChooser c = new JFileChooser();
				
				c.setDialogTitle("JustAnotherPaintProject");
				int selectVal = c.showSaveDialog(Paint.this);
				
				if (selectVal == JFileChooser.APPROVE_OPTION) {
					fichier = c.getSelectedFile();
					try {
						ImageIO.write(recupererImage(surface), "png", new File(fichier.getAbsolutePath() + ".png"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
}
