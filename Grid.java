/**
 * La classe <code>Grid</code> est utilis&eacute;e pour dessiner et g&eacute;rer la grille
 *  
 * @version 0.1
 * @author Nicolas Reszka et Guillaume Cau
 */

import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import javax.swing.JComponent;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Grid extends JComponent {
	/**
	* La classe random
	*/
	private Random random;

	/**
	* Le tableau en 2d qui contient les données de de la grille
	*/
	private Block[][] blocks = new Block[SameGame.gridWidth][SameGame.gridHeight];

	/**
	* Couleur du fond
	*/
	private Color colorBackground = new Color(139, 16, 239);

	/**
	* Couleur d'une selection valide
	*/
	private Color colorSelected   = new Color(244, 233, 17);

	/**
	* Couleur d'une selection invalide
	*/
	private Color colorSelectedSingle = new Color(237, 14, 159);

	/**
	* Contient l'abcisse pr&eacute;cedente de la souris
	*/
	private int previousX;

	/**
	* Contient l'ordonn&eacute;e pr&eacute;cedente de la souris
	*/
	private int previousY;

	/**
	* Compteur de blocs
	*/
	private int count = 0;

	/**
	* Contient le score
	*/
	private int score = 0;

	/**
	* La police de caract&egrave;re utilis&eacute;e
	*/
	private Font font = new Font("Arial", Font.BOLD, 32);


	/**
	* Constructeur normal
	*/
	public Grid() {
		super();
		random = new Random();
	}

	/**
	* Constructeur de debug qui genere toujours la meme grille
	*
	* @param seed la graine de g&eacute;neration 
	*/
	public Grid(long seed) {
		super();
		random = new Random(seed);
	}

	/**
	* Charge une grille depuis un fichier
	*
	* @param filename le chemin du fichier 
	* @return true si le fichier a &eacute;t&eacute; charg&eacute; avec succ&egrave;s
	*/
	public boolean loadFromFile(String filename) {
		boolean success = true;

		try {
			File file = new File(filename);
			FileReader stream = new FileReader(file);
			BufferedReader reader = new BufferedReader(stream);
			try {
				char blockColor;
				for (int y = 0; y < SameGame.gridHeight; y++) {
					for (int x = 0; x < SameGame.gridWidth; x++) {
						blockColor = (char) reader.read();
						if (!(blockColor == 'R' || blockColor == 'V' || blockColor == 'B')) {
							success = false;
						} else {
							this.blocks[x][y] = new Block(blockColor);
						}
					}
					reader.skip(1);
				}
			} catch (IOException exception) {
				System.err.println("Erreur à la lecture");
				success = false;
			}
			try {
				reader.close();
				stream.close();
			} catch (IOException exception) {
				System.err.println("Erreur à la fermeture");
				success = false;
			}
		} catch (FileNotFoundException exception) {
			System.err.println("Erreur à l'ouverture");
			success = false;
		}

		return success;
	}

	/**
	* G&eacute;n&egrave;re une grille al&eacute;atoirement
	*
	*/
	public void generate() {
		int nextInt;
		for (int y = 0; y < SameGame.gridHeight; y++) {
			for (int x = 0; x < SameGame.gridWidth; x++) {
				nextInt = random.nextInt(3);
				if (nextInt == 0) {
					this.blocks[x][y] = new Block('R');
				} else if (nextInt == 1) {
					this.blocks[x][y] = new Block('V');
				} else {
					this.blocks[x][y] = new Block('B');
				}
			}
		}
		this.repaint();
	}	

	/**
	* Permet de changer le score
	*
	* @param score: le score a attribuer 
	*/
	public void setScore(int score) {
		this.score = score;
	} 

	/**
	* Permet de recuperer le score actuel
	* 
	* @return le score
	*/
	public int getScore() {
		return this.score;
	}

	/**
	* Deselectionne toutes les cases
	*/
	private void deselectAll() {
		Block temporaryBlock;
		for (int y = 0; y < SameGame.gridHeight; y++) {
			for (int x = 0; x < SameGame.gridWidth; x++) {
				temporaryBlock = this.blocks[x][y];
				if (temporaryBlock != null) {
					temporaryBlock.setSelected(false);
				}
			}
		}
	}

	/**
	* Selectionne les cases adjacentes recursivement
	*/
	private void select(int x, int y, char color) {
		Block block = this.blocks[x][y];
		if (block != null) {
			if (block.getColor() == color) {
				if (!block.getSelected()) {
					block.setSelected(true);
					this.count++;
					if (x > 0) {
						this.select(x-1,y,color);
					}
					if (x < SameGame.gridWidth-1) {
						this.select(x+1,y,color);
					}
					if (y > 0) {
						this.select(x,y-1,color);
					}
					if (y < SameGame.gridHeight-1) {
						this.select(x,y+1,color);
					}
				}
			}
		} 
	}

	/**
	* Verifie si il n'y a plus aucun groupe et affiche le menu de fin si il n'y en a aucun
	*
	* @return true si il n'y a plus aucun groupe
	*/
	public boolean checkGameOver() {
		boolean test = true;
		Block block;
		for (int y = 0; y < SameGame.gridHeight; y++) {
			for (int x = 0; x < SameGame.gridWidth; x++) {
				block = this.blocks[x][y];
				if (block != null) {
					this.count = 0;
					this.deselectAll();
					this.select(x,y,block.getColor());
					if (this.count > 1) {
						test = false;
					}
				}
			}
		}

		if (test) {
			System.out.println("Game Over");
			SameGame.menu.clearWindow();
			SameGame.menu.initializeEnding();
		}

		return test;
	}

	/**
	* Evenement activ&eacute; lorsque la souris est boug&eacute;e et selectionne les groupes
	*
	* @param mouseX l'abcisse de la souris
	* @param mouseY l'ordonnée de la souris
	*/
	public void mouseMoved(int mouseX, int mouseY) {
		int x = (mouseX/SameGame.cellWidth );
		int y = (mouseY/SameGame.cellHeight);
		
		if (x != this.previousX || y != this.previousY) {
			if (x >= 0 && x < SameGame.gridWidth 
			&&  y >= 0 && y < SameGame.gridHeight) {
				Block block = this.blocks[x][y];
				if (block != null) {
					if (!block.getSelected()) {
						this.count = 0;
						this.deselectAll();
						this.select(x,y,block.getColor());
						this.repaint();
					}
				} else {
					this.count = 0;
					this.deselectAll();
					this.repaint();
				}
			}
		}

		this.previousX = x;
		this.previousY = y;
	}

	/**
	* Evenement activ&eacute; lorsque la souris est cliqu&eacute;e et enl&egrave;ve 	
	* le groupe selectionn&eacute; et bouge tout les blocs
	*
	* @param mouseX l'abcisse de la souris
	* @param mouseY l'ordonnée de la souris
	*/
	public void mousePressed(int mouseX, int mouseY) {
		int x = (mouseX/SameGame.cellWidth );
		int y = (mouseY/SameGame.cellHeight);
		if (x >= 0 && x < SameGame.gridWidth 
		&&  y >= 0 && y < SameGame.gridHeight) {
			if (this.count > 1) {
				Block block;
				for (y = 0; y < SameGame.gridHeight; y++) {
					for (x = 0; x < SameGame.gridWidth; x++) {
						block = this.blocks[x][y];
						if (block != null) {
							if (block.getSelected()) {
								this.blocks[x][y] = null;
							}	
						}
					}
				}
				this.score += (this.count-2)*(this.count-2);

				System.out.println("Score : " + this.score);
				this.rearrange();
				this.repaint();
				this.checkGameOver();
				this.previousX = -1;
				this.previousY = -1;
				this.mouseMoved(mouseX,mouseY);
			}
		}				
	}

	/**
	* Verifie si une colonne est vide
	* @param x la colonne 	
	* @return true si la colonne selectionn&eacute;e est vide
	*/
	private boolean columnIsEmpty(int x) {
		boolean test = true;
		for (int y = 0; y < SameGame.gridHeight; y++) {
			if (this.blocks[x][y] != null) {
				test = false;
			}
		}
		return test;
	}

	/**
	* Verifie si il y une colonne vide entre deux colonnes
	*	
	* @return true si la condition est verifi&eacute;e
	*/
	private boolean hasEmptyColumnsInbetween() {
		boolean test = false;
		for (int x = 0; x < SameGame.gridWidth-1; x++) {
			if (this.columnIsEmpty(x) 
			&& !this.columnIsEmpty(x+1)) {
				test = true;
			}
		}
		return test;
	}

	/**
	* Verifie si une colonne a des espaces vides a la verticale
	*	
	* @return true si la condition est verifi&eacute;e
	*/
	private boolean columnHasHoles(int x) {
		boolean test = false;
		for (int y = SameGame.gridHeight-2; y >= 0; y--) {
			if (this.blocks[x][y] != null) {
				if (this.blocks[x][y+1] == null) {
					test = true;
				}
			}
		}
		return test;
	}

	/**
	* Bouge les cellules
	*/
	private void rearrange() {
		while (this.hasEmptyColumnsInbetween()) {
			for (int x = 0; x < SameGame.gridWidth-1; x++) {
				if (this.columnIsEmpty(x)) {
					for (int y = 0; y < SameGame.gridHeight; y++) {
						this.blocks[x][y] = this.blocks[x+1][y];
			 			this.blocks[x+1][y] = null;
					}
				}
			}
		}

		for (int x = 0; x < SameGame.gridWidth; x++) {
			if (!this.columnIsEmpty(x)) {
				while (this.columnHasHoles(x)) {
					for (int y = SameGame.gridHeight-2; y >= 0; y--) {
						if (this.blocks[x][y+1] == null) {
							this.blocks[x][y+1] = this.blocks[x][y];
			 				this.blocks[x][y] = null;
						}
					}
				}
			}
		}
	}


	/**
	* Dessine la grille et le score 
	*/
	@Override
	public void paintComponent(Graphics globalBrush) {
		Graphics brush = globalBrush.create();
		if (this.isOpaque()) {
			brush.setColor(this.getBackground());
			brush.fillRect(0, 0, this.getWidth(), this.getHeight());
		}

		brush.setColor(this.colorBackground);
		brush.fillRect(
			0,0, 
			SameGame.gridWidth  * SameGame.cellWidth, 
			SameGame.gridHeight * SameGame.cellHeight
		);
		
		Block temporaryBlock;
		int xPosition;
		int yPosition;
		for (int y = 0; y < SameGame.gridHeight; y++) {
			for (int x = 0; x < SameGame.gridWidth; x++) {
				temporaryBlock = this.blocks[x][y];
				if (temporaryBlock != null) {
					xPosition = x * SameGame.cellWidth;
					yPosition = y * SameGame.cellHeight;
					if (temporaryBlock.getSelected()) {
						if (this.count > 1) {
							brush.setColor(this.colorSelected);
						} else {
							brush.setColor(this.colorSelectedSingle);
						}
						brush.fillRect(xPosition, yPosition, SameGame.cellWidth, SameGame.cellHeight);
					} 
					brush.drawImage(temporaryBlock.getSprite(), xPosition, yPosition, this);
				}
			}
		}
		brush.setColor(Color.BLACK);
		brush.setFont(this.font);
		brush.drawString("Score : " + this.score,0,SameGame.gridHeight*SameGame.cellHeight+32);
	}

}