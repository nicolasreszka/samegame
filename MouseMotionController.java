/**
 * La classe <code>MouseMotionListener</code> g&egrave;re les evenements de hover de la classe <code>Grid</code>
 *  
 * @version 0.1
 * @author Nicolas Reszka et Guillaume Cau
 */

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMotionController implements MouseMotionListener {

	/**
	* Ce constructeur herite de MouseMotionListener
	*/
	public MouseMotionController() {
		super();
	}

	/**
	* Appelle la fonction eponyme de la classe Grid
	* 
	* @param event: l'evenement de la souris
	*/
	@Override
	public void mouseMoved(MouseEvent event) {
		Grid grid = (Grid) event.getSource();
		grid.mouseMoved(event.getX(),event.getY());
	}

	/**
	* Appelle la fonction mouseMoved de la classe Grid
	* 
	* @param event: l'evenement de la souris
	*/
	@Override
	public void mouseDragged(MouseEvent event) {
		Grid grid = (Grid) event.getSource();
		grid.mouseMoved(event.getX(),event.getY());
    }

}