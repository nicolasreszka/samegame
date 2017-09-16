/**
 * L'interface <code>MouseController</code> g&egrave;re les evenements de clic de la classe <code>Grid</code>
 *  
 * @version 0.1
 * @author Nicolas Reszka et Guillaume Cau
 */

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseController implements MouseListener {
	
	/**
	* Ce constructeur herite de MouseListener
	*/
	public MouseController() {
		super();
	}


	/**
	* Appelle la fonction eponyme de la classe Grid
	* 
	* @param event: l'evenement de la souris
	*/
	@Override
	public void mousePressed(MouseEvent event){
		Grid grid = (Grid) event.getSource();
		grid.mousePressed(event.getX(),event.getY());
	}

	/**
	* ne fait rien
	* @param event: l'evenement de la souris
	*/
	@Override
	public void mouseClicked(MouseEvent event){}

	/**
	* ne fait rien
	* @param event: l'evenement de la souris
	*/
	@Override
	public void mouseReleased(MouseEvent event){} 

	/**
	* ne fait rien
	* @param event: l'evenement de la souris
	*/
	@Override
	public void mouseEntered(MouseEvent event){} 


	/**
	* ne fait rien
	* @param event: l'evenement de la souris
	*/
	@Override
	public void mouseExited(MouseEvent event){} 
}