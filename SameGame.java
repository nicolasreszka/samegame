/**
 * La classe <code>SameGame</code> est la classe principale du jeu
 *  
 * @version 0.1
 * @author Nicolas Reszka et Guillaume Cau
 */

public class SameGame {

	/**
	* Nombre de cellules dans une ligne
	*/
	public static final int gridWidth  = 15;
	/**
	* Nombre de cellules dans une colonne
	*/
	public static final int gridHeight = 10;
	/**
	* Largeur d'une cellule en pixel 
	*/
	public static final int cellWidth = 64;
	/**
	* Hauteur d'une cellule en pixel
	*/
	public static final int cellHeight = 64;
	/**
	* La class globale de la grille
	*/
	public static Grid grid;
	/**
	* La class globale de l'interface
	*/
	public static Menu menu;

	/**
	* La fonction main
	*
	* @param args: les arguments de la console
	*/
	public static void main(String[] args) {
		long seed = System.currentTimeMillis();
		System.out.println("seed : " + seed);
		grid = new Grid();
		MouseMotionController mouseMotion = new MouseMotionController();
		MouseController mouse = new MouseController();
		grid.addMouseListener(mouse);
		grid.addMouseMotionListener(mouseMotion);
		menu = new Menu();
	}
}