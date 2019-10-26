package gameofgo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Go {
	
	public static enum IllegalMoves{ SELFCAPTURING, OUTOFBOUNDS, SPACEOCCUPIED, KO};
	
	 // o-black stone  x -white stone
	private char 		currentTurn = 'o';	// black goes first
	private int 		xdim = 0;
	private int 		ydim = 0;
	private char[][] 	board;		// first dimension is up/down  second dimension is left right
									// Origin is at the bottom left corner of the game board
	
	
	public int getBoardHeight() { return ydim;}
	public int getBoardWidth() { return xdim;}
	
	
	
	/**
	 * Create a game in progress based off a visual representation of the game board
	 * 
	 * @param board A visual representation of the board, each row separated by \n
	 * ie :
	 * 
	 * String testBoard = 
				". x o o o o o . ." + "\n" +
				". x o o o o o x ." + "\n" +
				". . x o o o x . ." + "\n" +
				". . . x o o x . ." + "\n" +
				". . x . x x . . ." + "\n" +
				". . . . . . . o ." + "\n" +
				". . x . . . . . ." + "\n" +
				". . . x . . o . ." + "\n" +
				". . . . . . . . .";
				
	 * @param turn Who gets the next turn? o or x?
	 */
	public Go(String board, char turn) {
		this.currentTurn = turn;
		String[] sa = board.split("\n");
		
		// TODO : consider striping out any white spaces in each line
		ydim = sa.length;
		xdim = (sa[0].length()+1)/2;
		
		this.board = new char[ydim][xdim];
		for (int y = 0; y < ydim; y++) {
			for (int x = 0; x < xdim; x++) {
				this.board[y][x] = sa[ydim-1-y].charAt(x*2);
			}
		}
		
		
		
	}
	
	/**
	 * Determine if a particular move would capture an opponentGroup
	 * @param y
	 * @param x
	 * @param player
	 * @return
	 */
	public boolean wouldMoveCaptureGroup(int y, int x, char player) {
		boolean ret = false;
		char[][] nextBoard = new char[ydim][xdim];
		for (int yy = 0; yy < ydim; yy++) {	
			for (int xx = 0; xx < xdim; xx++) {
				nextBoard[yy][xx] = this.board[yy][xx];
			}
		}
		nextBoard[y][x] = player;
		
		
		// Now check all the adjacent positions around the desired move and 
		// if any of those belong to the opponent, see if they are a part of a group
		// that is captured.
		
		return ret;
		
	}
	
	
	/**
	 * Determine if a desired move would create a self capture situation.
	 * @param y
	 * @param x
	 * @param player
	 * @return
	 */
	public boolean wouldMoveCauseSelfCapture(int y, int x, char player) {
		boolean ret = false;
		char[][] nextBoard = new char[ydim][xdim];
		for (int yy = 0; yy < ydim; yy++) {	
			for (int xx = 0; xx < xdim; xx++) {
				nextBoard[yy][xx] = this.board[yy][xx];
			}
		}
		nextBoard[y][x] = player;
		
		return Group.findGroup(y, x, nextBoard).isCaptured(board);
	}
	
	public char getPositionValue(Position p) {
		return board[p.getY()][p.getX()];
	}

	/**
	 * required by codewars kata
	 * @return
	 */
	public String getSize() {
		return "{\"height\": " + ydim + ", \"width\": " + xdim + "}";
	}
	
	/**
	 * Place a series of stones on the board.  Used for initial setup, or game in progress.
	 * @param stones		A comma delimited string of positions 1Ao,2Ao...etc
	 */
	private void placeStones(String stones) {

		String[] sa = stones.split(",");
		for (String s : sa) {
			System.out.println(s);
			char piece = s.charAt(s.length()-1);
			int[] ia = Go.translateCoords(s.substring(0,s.length()-1));
			board[ia[0]][ia[1]] = piece;
		}
	}
	
	/**
	 * Skip your turn
	 */
	public void passTurn() {
		currentTurn = (currentTurn=='o') ? 'x' : 'o';
	}
	
	
	
	/**
	 * Clear the board and reset the player to black ('o')
	 */
	public void reset() {
		currentTurn = 'o';
		for (int y = 0; y < ydim; y++) {
			board[y] = new char[xdim];
			for (int x = 0; x < xdim; x++) {
				board[y][x] = '.';
			}
		}
	}
	
	/**
	 * Place handicap stone(s) on the board.  
	 * for 9x9 boards, up to 5 stones
	 * for 13x13 boards, up to 9 stones
	 * for 19x19 boards, up to 9 stones
	 * 9x9		G7,C3,G3,C7,E5
	 * 13x13	J10,D4,J4,D10,G7,D7,J7,G10
	 * 19x19	Q16,D4,Q4,D16,K10,D10,Q10,K16,K4
	 * 
	 * @param stones
	 */
	public void handicapStones(int stones) {
		if (stones < 1) return;
		
		if (!((xdim==9 && ydim==9) || (xdim==13 && ydim==13) || 
				(xdim==19 && ydim==19))) { return; }
		
		String[] sa;
		
		if (xdim==9 && ydim == 9) {
			if (stones > 5) { stones = 5; }
			sa = new String[] {"G7o","C3o","G3o","C7o","E5o"};
			for (int i = 0; i < stones; i++) {
				this.placeStones(sa[i]);
			}
		}
		
		if (xdim==13 && ydim == 13) {
			if (stones > 9) { stones = 9; }
			sa = new String[] {"K10o","D4o","K4o","D10o","G7o","D7o","K7o","G10o","G4o"};
			for (int i = 0; i < stones; i++) {
				this.placeStones(sa[i]);
			}
		}
		
		if (xdim==19 && ydim == 19) {
			if (stones > 9) { stones = 9; }
			sa = new String[] {"Q16o","D4o","Q4o","D16o","K10o","D10o","Q10o","K16o","K4o"};
			for (int i = 0; i < stones; i++) {
				this.placeStones(sa[i]);
			}
		}
	}
	
	/**
	 * Given y,x indices for the board, return the corresponding 
	 * @param y
	 * @param x
	 * @return
	 */
	public static String translateCoords(int y, int x) {
		String l = Character.toString("ABCDEFGHJKLMNOPQRST".charAt(x));
		String n = Integer.toString(y+1);
		return n + l;
	}
	
	/** 
	 * 
	 * @param cords ex: 1A or 15J
	 * @return array of y,x index positions on board.  
	 */
	public static int[] translateCoords(String cords) {
		int[] ret = new int[2]; 
		
		cords = cords.trim();
		String l = cords.substring(1);
		String n = cords.substring(0,1);

		ret[0] = Integer.parseInt(n)-1;
		ret[1] = "ABCDEFGHJKLMNOPQRST".indexOf(l);
		
		return ret;
	}
	
	/**
	 * Find groups
	 */
	public void findGroups() {
		//TODO : Stopped here
		String[] xGroups;
		String[] oGroups;
		
		// First let's find all connections
		for (int y = 0; y < ydim-1; y++) {
			// let's just start by finding connections
			// we just need to check right and up
			for (int x = 0; x < xdim-1; x++) {
				char thisChar = this.board[y][x];
				if (thisChar == '.') continue;
				
				// First see if this already belongs to a group, if not then create a new group
				
				char aboveChar = board[y+1][x];
				char rightChar = board[y][x+1];
				
				System.out.printf("y=%d  x=%d\n",y,x);
				if (thisChar == aboveChar) {
					// AddEdge(y,x,y+1,x)
					System.out.println(translateCoords(y,x) + " - " + translateCoords(y+1,x));
				}
				
				if (thisChar == rightChar) {
					System.out.println(translateCoords(y,x) + " - " + translateCoords(y,x+1));
				}
			}
			
			// now we have a bunch of pairings.  We want to search through the pairings to see 
			// what pairings can tie together.
		}
		
	}
	
	public String getTurn() {
		if (currentTurn == 'o') return "black";
		return "white";
	}
	

	/**
	 * Create a 
	 */
	
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
	 * Create a game in progress
	 * @param positions comma delimited string of occupied spaces ex "1Ax,1Bx,2Bx,6Co,6Do"...etc
	 * @param turn o-black or x-white
	 */
	public Go(String positions, int dimension, char turn) {
		
		this.currentTurn = turn;
		this.xdim = dimension;
		this.ydim = dimension;
		board = new char[this.ydim][];
		
		for (int y = 0; y < this.ydim; y++) {
			board[y] = new char[this.xdim];
			for (int x = 0; x < this.xdim; x++) {
				board[y][x] = '.';
			}
		}
		
		this.placeStones(positions);
			
		
		
	}
	
	/**
	 * Create a game in progress
	 * @param positions comma delimited string of occupied spaces ex "1Ax,1Bx,2Bx,6Co,6Do"...etc
	 * @param yDim height of the board
	 * @param xDim width of the board
	 */
	public Go(String positions, int yDim, int xDim, char turn) {
		
		this.currentTurn = turn;
		this.xdim = xDim;
		this.ydim = yDim;
		board = new char[yDim][];
		
		for (int y = 0; y < this.ydim; y++) {
			board[y] = new char[this.xdim];
			for (int x = 0; x < this.xdim; x++) {
				board[y][x] = '.';
			}
		}
		
		this.placeStones(positions);
	}
	
	/** 
	 *  Create a new game in progress
	 * @param board (all square should be contain [.,o,x]
	 * @param turn	o-black  x-white
	 */
	public Go(char[][] board, char turn) {
		// do we do a copy?
		this.board = board;
		currentTurn = turn;
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
		
		this.reset();
	}
	
	
	public void printBoard() {
		//System.out.print("--------GAME BOARD--------\n");
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
		
		//System.out.print("--------------------------\n");
		
		
	}
	
	
	public char[][] getBoard() {
		return board;
	}
	
	/**
	 * Have the current player make a move
	 * @param gameCoords
	 * @throws Exception
	 */
	public void move(String gameCoords) throws Exception{
		
		
		System.out.println("move() called.  gameCoords=" + gameCoords);
		gameCoords = gameCoords.toUpperCase().trim();
		
		// check to see if gameCoords are valid, it should be a 2-3 character string
		// in the format of (1-2digit number) + 1 cap character
		//  ie : 1A or 19A or 5J
		String letStr = gameCoords.substring(gameCoords.length()-1);
		String numStr = gameCoords.substring(0,gameCoords.length()-1);
		// convert to x/y coords
		int y = Integer.parseInt(numStr) - 1;
		
		// make sure that this move wont cause a self capture
		
		
		
		String header = "ABCDEFGHJKLMNOPQRST";
		int x = header.indexOf(letStr);
		
		if ((x < 0) || (x >= this.xdim)) {
			throw new Exception ("x must be between 0 and " + (this.xdim-1));
		} 
		
		if ((y < 0 ) || (y >= this.ydim)) {
			throw new Exception ("y must be between 0 and " + (this.ydim-1));
		}
		
		if (this.wouldMoveCauseSelfCapture(y, x, currentTurn)) {
			throw new IllegalArgumentException("Move would cause self capture.");
		}
		
		// detect captures here
		
		System.out.printf("move() letStr = %s  numStr = %s\n", letStr, numStr);
		System.out.printf("x = %d  y = %d \n\n", x, y);
		System.out.println("move() called.  gameCoords=" + gameCoords);
		
		this.board[y][x] = currentTurn;
		currentTurn = (currentTurn == 'o') ? 'x' : 'o';

	}
	

}
