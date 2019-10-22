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
	 * @param cords ex: A1 or J15
	 * @return array of y,x index positions on board.  
	 */
	public static int[] translateCoords(String cords) {
		int[] ret = new int[2]; 
		
		cords = cords.trim();
		String l = cords.substring(cords.length()-1);
		String n = cords.substring(0,cords.length()-1);

		ret[0] = Integer.parseInt(n)-1;
		ret[1] = "ABCDEFGHJKLMNOPQRST".indexOf(l);
		
		return ret;
	}
	
	public void findGroup(String position) {
		int y = Go.translateCoords(position)[0];
		int x = Go.translateCoords(position)[1];
		findGroup(y,x);
	}
	
	
	/**
	 * Build a group of positions that are part of a position
	 * @param y	y-coordinate of search starting location
	 * @param x x-coordinate of search starting location
	 */
	public void findGroup(int y_idx, int x_idx) {
		
		List<Position> group = new ArrayList<Position>();
		group.add(new Position(y_idx, x_idx));
		
		int ptr = 0;  
		
		if (board[y_idx][x_idx] == '.') {
			System.out.printf("board[%d][%d] is empty\n", y_idx, x_idx);
			// TODO : throw an exception here
			return;
		}
		
		
	
		boolean done = false;
		while (!done) {
			// if there is a left neighbor and it has the same value
			if ((x_idx != 0) && board[y_idx][x_idx-1] == board[y_idx][x_idx]) {
				// TODO : make sure the group doesn't already have this position
				group.add(new Position(y_idx, x_idx-1));
			}
			
			// if there is a above neighbor and it has the same value
			if ((y_idx != this.ydim-1) && board[y_idx+1][x_idx] == board[y_idx][x_idx]) {
				// TODO : make sure the group doesn't already have this position
				group.add(new Position(y_idx+1, x_idx));
			}
			
			// if there is a right neighbor and it has the same value
			if ((x_idx != this.xdim-1) && board[y_idx][x_idx+1] == board[y_idx][x_idx]) {
				// TODO : make sure the group doesn't already have this position
				group.add(new Position(y_idx, x_idx+1));
			}
			
			// if there is a below neighbor and it has the same value
			if ((y_idx != 0) && board[y_idx-1][x_idx] == board[y_idx][x_idx]) {
				// TODO : make sure the group doesn't already have this position
				group.add(new Position(y_idx-1, x_idx));
			}
			
			
			// we are done if the ptr is at the end of the list and all neighbors with same value have been added
			if (ptr == group.size()-1) { done = true; }
			// if we aren't done, advance the pointer on the list
			
		}
		
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
	 * Create a game in progress
	 * @param positions comma delimited string of occupied spaces ex "1Ax,1Bx,2Bx,6Co,6Do"...etc
	 * @param turn o-black or x-white
	 */
	public Go(String positions, char turn, int dimension) {
		
		
	}
	
	/**
	 * Create a game in progress
	 * @param positions comma delimited string of occupied spaces ex "1Ax,1Bx,2Bx,6Co,6Do"...etc
	 * @param yDim height of the board
	 * @param xDim width of the board
	 */
	public Go(String positions, int yDim, int xDim) {
		this.xdim = xDim;
		this.ydim = yDim;
		board = new char[yDim][];
		for (int y = 0; y < yDim; y++) {
			board[y] = new char[xDim];
			for (int x = 0; x < xDim; x++) {
				board[y][x] = '.';
			}
		}
		
				
		String[] sa = positions.split(",");
		for (String s : sa) {
			System.out.println(s);
			char piece = s.charAt(s.length()-1);
			int[] ia = Go.translateCoords(s.substring(0,s.length()-1));
			board[ia[0]][ia[1]] = piece;
		}
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
