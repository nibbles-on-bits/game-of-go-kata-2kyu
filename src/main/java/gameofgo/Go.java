package gameofgo;
import java.util.HashMap;
import java.util.Map;

public class Go {
	
	public static enum IllegalMoves{ SELFCAPTURING, OUTOFBOUNDS, SPACEOCCUPIED, KO};
	
	 // o-black stone  x -white stone
	private char 		currentTurn = 'o';	// black goes first
	private int 		xdim = 0;
	private int 		ydim = 0;
	private char[][] 	board;		// first dimension is up/down  second dimension is left right
									// Origin is at the bottom left corner of the game board
	
	public String getTurn() {
		if (currentTurn == 'o') return "black";
		return "white";
	}
	
	public String getSize() {
		return "{\"height\": " + ydim + ", \"width\": " + xdim + "}";
	}
	
	/**
	 * Create a new square game board
	 * @param gameCoords
	 * @throws Exception 
	 */
	public Go(int dimension) throws Exception {
		if (dimension <= 4) {
			throw new Exception("board dimension must be > 4");
		}
		this.ydim = dimension;
		this.xdim = dimension;
		board = new char[dimension][];
		for (int y = 0; y < dimension; y++) {
			board[y] = new char[dimension];
			for (int x = 0; x < dimension; x++) {
				board[y][x] = '.';
			}
		}
		
	}
	
	/**
	 * Create a rectangular game board.  Origin is bottom left of board
	 * @param ydim
	 * @param xdim
	 */
	public Go(int ydim, int xdim) throws Exception {
		if (ydim <= 4) {
			throw new Exception("ydim must be > 4");
		}
		
		if (xdim <= 4) {
			throw new Exception("xdim must be > 4");
		}
		
		this.ydim = ydim;
		this.xdim = xdim;
		
		board = new char[ydim][];
		
		for (int y = 0; y < ydim; y++) {
			board[y] = new char[xdim];
			for (int x = 0; x < xdim; x++) {
				board[y][x] = '.';
			}
		}
	}
	
	
	public void printBoard() {
		System.out.print("--------GAME BOARD--------\n");
		String header = "ABCDEFGHJKLMNOPQRST";
		System.out.print("    ");
		for (int x = 0; x < this.xdim; x++) {
			System.out.print(header.charAt(x) + " ");
		}
		System.out.println();
		
		for (int y = ydim-1; y >= 0; y-- ) {
			System.out.printf("%2d ",y+1);
			for (int x = 0; x < this.xdim; x++) {
				System.out.print(" " + board[y][x]);
			}
			System.out.println();
		}
		
		System.out.print("--------------------------\n");
		
		
	}
	
	
	public char[][] getBoard() {
		return board;
	}
	
	public void move(String gameCoords) throws Exception{
		System.out.println("move() called.  gameCoords=" + gameCoords);
		gameCoords = gameCoords.toUpperCase().trim();
		
		//TODO : add some checking of gameCoords here
		
		
		// check to see if gameCoords are valid, it should be a 2-3 character string
		// in the format of (1-2digit number) + 1 cap character
		//  ie : 1A or 19A or 5J
		String letStr = gameCoords.substring(gameCoords.length()-1);
		String numStr = gameCoords.substring(0,gameCoords.length()-1);
		// convert to x/y coords
		int y = Integer.parseInt(numStr) - 1;
		
		String header = "ABCDEFGHJKLMNOPQRST";
		int x = header.indexOf(letStr);
		
		if ((x < 0) || (x >= this.xdim)) {
			throw new Exception ("x must be between 0 and " + (this.xdim-1));
		} 
		
		if ((y < 0 ) || (y >= this.ydim)) {
			throw new Exception ("y must be between 0 and " + (this.ydim-1));
		}
		
		System.out.printf("move() letStr = %s  numStr = %s\n", letStr, numStr);
		System.out.printf("x = %d  y = %d \n\n", x, y);
		System.out.println("move() called.  gameCoords=" + gameCoords);
		
		this.board[y][x] = currentTurn;
		currentTurn = (currentTurn == 'o') ? 'x' : 'o';

	}
	

}
