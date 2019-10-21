package gameofgo;
import java.util.HashMap;
import java.util.Map;

public class Go {
	
	 // o-black stone  x -white stone
	
	private char 		currentTurn = 'o';	// black goes first
	private int 		xdim = 0;
	private int 		ydim = 0;
	private char[][] 	board;		// first dimension is up/down  second dimension is left right
									// Origin is at the bottom left corner of the game board
	
	
	/**
	 * Create a new square game board
	 * @param gameCoords
	 */
	public Go(int dimension) {
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
	public Go(int ydim, int xdim) {
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
	
	public void move(String gameCoords) {
		String ylookup = "ABCDEFGHJKLMNOPQRST";
		
		
	}
	

}
