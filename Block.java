/**
 * L'interface <code>Block</code> contient les informations d'un bloc présent dans une cellue
 *  
 * @version 0.1
 * @author Nicolas Reszka et Guillaume Cau
 */

import java.awt.Toolkit;
import java.awt.Image;

public class Block {

	/**
	* le sprite a afficher
	*/
	private Image sprite;

	/**
	* true si le bloc est selectionn&eacute;
	*/
	private boolean selected;

	/**
	* La couleur du bloc
	*/
	private char color;

	/**
	* Ce constructeur attribue une couleur et un sprite au bloc
	*
	* @param color: la couleur du bloc
	*/
	public Block(char color) {
		this.color = color;
		if (color == 'R') {
			this.sprite = Toolkit.getDefaultToolkit().getImage("sprites/red.png");
		} else if (color == 'V') {
			this.sprite = Toolkit.getDefaultToolkit().getImage("sprites/green.png");
		} else if (color == 'B') {
			this.sprite = Toolkit.getDefaultToolkit().getImage("sprites/blue.png");
		} 
		this.selected = false;
	}

	/**
	* Change le flag decrivant si le bloc a été selectionné ou non 
	*
	* @param selected: le statut du bloc
	*/
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	* Donne le flag decrivant si le bloc a été selectionné ou non 
	*
	* @return selected: le statut du bloc
	*/
	public boolean getSelected() {
		return this.selected;
	}	

	/**
	* Donne la couleur du bloc 
	*
	* @return color: la couleur du bloc (R, V ou B)
	*/
	public char getColor() {
		return this.color;
	}

	/**
	* Donne le sprite du bloc 
	*
	* @return sprite: le sprite du bloc 
	*/
	public Image getSprite() {
		return this.sprite;
	}
}
