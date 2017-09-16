/**
 * L'interface <code>ActionController</code> g&egrave;re les actions des bouttons de la classe <code>Menu</code>
 *  
 * @version 0.1
 * @author Nicolas Reszka et Guillaume Cau
 */

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ActionController implements ActionListener {

	/**
	* Ce constructeur herite de ActionListener
	*/
	public ActionController() {
		super();
	}

	/**
	* Gere les actions des boutons du menu
	* 
	* @param event: l'evenement de l'action
	*/
	@Override
	public void actionPerformed(ActionEvent event){
		JButton button = (JButton) event.getSource();
		String command = button.getActionCommand();

		if (command == "Generer") {
			SameGame.grid.generate();
			SameGame.menu.initializeGame();
		} else if (command == "Ouvrir") {
			JFileChooser chooser = new JFileChooser(".");
			chooser.showOpenDialog(null);
			File file = chooser.getSelectedFile();
			if (file != null){
				if (SameGame.grid.loadFromFile(file.getAbsolutePath())) {
					SameGame.menu.initializeGame();
				} else {
					JOptionPane.showMessageDialog(null, "Erreur lors de la lecture du fichier");
				}
			}
		} else if (command == "Retour au menu") {
			SameGame.menu.clearWindow();
			SameGame.menu.initializeMainMenu();
		}
	}
}