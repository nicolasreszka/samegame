/**
 * La classe <code>Menu</code> g&eacute;n&egrave;re une fenetre initialise les differents menus 
 *  
 * @version 0.1
 * @author Nicolas Reszka et Guillaume Cau
 */

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.CardLayout;


public class Menu {
	
	/**
	* La fenetre principale
	*/
	private JFrame window;

	/**
	* Genere une fenetre et initialise le menu principal
	*/
	public Menu() {
		this.window = new JFrame();
		this.window.setSize(SameGame.gridWidth*64+16,SameGame.gridHeight*64+96);
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initializeMainMenu();
		this.window.setVisible(true);
	}

	/**
	* Enleve tout les composants de la fenetre
	*/
	public void clearWindow() {
		this.window.getContentPane().removeAll();
		this.window.getContentPane().revalidate();
		this.window.getContentPane().repaint();
	}

	/**
	* Initialise le menu principal 
	*/
	public void initializeMainMenu() {
		ActionController actionController = new ActionController();
		JButton buttonGenerate = new JButton("Generer");
		JButton buttonOpenFile = new JButton("Ouvrir");
		JPanel buttonPanel = new JPanel();
		buttonGenerate.addActionListener(actionController);
		buttonOpenFile.addActionListener(actionController);
		buttonPanel.add(buttonGenerate);
		buttonPanel.add(buttonOpenFile);
		this.window.add(buttonPanel);
	}

	/**
	* Initialise la fenetre du jeu 
	*/
	public void initializeGame() {
		this.clearWindow();
		SameGame.grid.setScore(0);
		this.window.add(SameGame.grid);
	}

	/**
	* Initialise la fenetre de fin qui affiche le score
	*/
	public void initializeEnding() {
		this.clearWindow();
		ActionController actionController = new ActionController();
		JButton buttonBackToMenu = new JButton("Retour au menu");
		JPanel buttonPanel = new JPanel();
		JLabel score = new JLabel("Score : " + SameGame.grid.getScore());
		buttonPanel.add(score);
		buttonBackToMenu.addActionListener(actionController);
		buttonPanel.add(buttonBackToMenu);
		this.window.add(buttonPanel);
	}
}