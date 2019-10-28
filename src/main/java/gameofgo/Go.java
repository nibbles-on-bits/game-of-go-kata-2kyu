package gameofgo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Go {
	
	public static enum IllegalMoves{ SELFCAPTURING, OUTOFBOUNDS, SPACEOCCUPIED, KO};
	
	 // x-black stone  o-white stone
	private char 		currentTurn = 'x';	// black goes first
	private int 		xdim = 0;
	private int 		ydim = 0;
	private char[][] 	board;		// first dimension is up/down  second dimension is left right
									// Origin is at the bottom left corner of the game board
	
	private char[][]		previousBoard;	// What did the board look like previously?
	private List<char[][]> 	boardStack = new ArrayList<char[][]>();
	
	public int getBoardHeight() { return ydim;}
	public int getBoardWidth() { return xdim;}
	
	/**
	 * Return the game to a previous state
	 * @param i
	 * @throws Exception 
	 */
	public void rollBack(int i) throws Exception {
		// TODO : make exception more descriptive
		if (i > boardStack.size() || i < 1) throw new Exception ("can't roll back that far");

		if (i % 2 == 1) { currentTurn = currentTurn=='o' ? 'x' : 'o'; }
		int idx = boardStack.size() -i;
		this.board = boardStack.get(idx);
		
		for (int x = 0; x < i; x++) {
			boardStack.remove(boardStack.size()-1);
		}
		
		
	}
	
	/**
	 * Make a snapshot of the current board, used to detect KO's
	 */
	private void saveBoardToPrevious() {
		if (previousBoard == null) {
			previousBoard = new char[this.ydim][this.xdim];
		}
		
		for (int y = 0; y < ydim; y++) {
			for (int x = 0; x < xdim; x++) {
				this.previousBoard[y][x] = this.board[y][x];
			}
		}
	}
	
	/**
	 * Create a new char[][] object to hold a copy of the current board and add it
	 *  to boardStack.
	 * @return 
	 */
	private void copyBoardToStack() {
		char[][] boardCopy = new char[ydim][xdim];
		
		for (int y = 0; y < ydim; y++) {
			for (int x = 0; x < xdim; x++) {
				boardCopy[y][x] = this.board[y][x];
			}
		}
		this.boardStack.add(boardCopy);
	}
	
	private static boolean boardsAreSame(char[][] board_a, char[][]board_b) {
		if (board_a == null || board_b == null) return false;
		int ydim_a = board_a.length;
		int ydim_b = board_b.length;
		int xdim_a = board_a[0].length;
		int xdim_b = board_a[0].length;
		if ((ydim_a != ydim_b) || (xdim_a != xdim_b)) { return false; }
		for (int y = 0; y < ydim_a; y++) {
			for (int x = 0; x < xdim_a; x++) {
				if (board_a[y][x] != board_b[y][x]) return false;
			}
		}
		return true;
	}
	
	
	
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
	 * Determine if a move would cause OK
	 * @param y
	 * @param x
	 * @param player
	 * @return
	 */
	public boolean wouldMoveCauseKO(int y, int x, char player) {
		char[][] nextBoard = new char[ydim][xdim];
		for (int yy = 0; yy < ydim; yy++) {	
			for (int xx = 0; xx < xdim; xx++) {
				nextBoard[yy][xx] = this.board[yy][xx];
			}
		}
		nextBoard[y][x] = player;
		
		return this.boardsAreSame(nextBoard, previousBoard);
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
	
	/**
	 * Required by codewars kata
	 * @return returns the value at a given coordinate in the 1A format
	 */
	public char getPosition(String p) {
		int[] ret = new int[2]; 
	
		p = p.trim();
		String l = p.substring(1);
		String n = (p.substring(0,1));

		int y = this.ydim-Integer.parseInt(n);
		int x = "ABCDEFGHJKLMNOPQRST".indexOf(l);
		
		return board[y][x];
	}
	
	public char getPositionValue(Position p) {
		return board[p.getY()][p.getX()];
	}

	/**
	 * required by codewars kata
	 * @return
	 */
	public HashMap<String, Integer> getSize() {
		HashMap<String,Integer> ret = new HashMap<String, Integer>();
		ret.put("height", ydim);
		ret.put("width", xdim);
		return ret;
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
			int[] ia = Go.translateCoords(s.substring(0,s.length()-1), this.ydim);
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
		currentTurn = 'x';
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
	 * 9x9		7G,3C,3G,7C,5E
	 * 13x13	10J,4D,4J,10D,7G,7D,7J,10G
	 * 19x19	16Q,4D,4Q,16D,10K,10D,10Q,16K,4K
	 * 
	 * @param stones	The number of handicap stones to place
	 */
	public void handicapStones(int stones) {
		if (stones < 1) return;
		
		if (!((xdim==9 && ydim==9) || (xdim==13 && ydim==13) || 
				(xdim==19 && ydim==19))) { return; }
		
		String[] sa;
		
		if (xdim==9 && ydim == 9) {
			if (stones > 5) { stones = 5; }
			sa = new String[] {"7Gx","3Cx","3Gx","7Cx","5Ex"};
			for (int i = 0; i < stones; i++) {
				this.placeStones(sa[i]);
			}
		}
		
		if (xdim==13 && ydim == 13) {
			if (stones > 9) { stones = 9; }
			sa = new String[] {"10Kx","4Dx","4Kx","10Dx","7Gx","7Dx","7Kx","10Gx","4Gx"};
			for (int i = 0; i < stones; i++) {
				this.placeStones(sa[i]);
			}
		}
		
		if (xdim==19 && ydim == 19) {
			if (stones > 9) { stones = 9; }
			sa = new String[] {"16Qx","4Dx","4Qx","16Dx","10Kx","10Dx","10Qx","16Kx","4Kx"};
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
	public static String translateCoords(int y, int x, int boardHeight) {
		String l = Character.toString("ABCDEFGHJKLMNOPQRST".charAt(boardHeight-1-x));
		String n = Integer.toString(+1);
		return n + l;
	}
	
	/** 
	 * 
	 * @param cords ex: 1A or 15J
	 * @return array of y,x index positions on board.  
	 */
	public static int[] translateCoords(String cords, int boardHeight) {
		int[] ret = new int[2]; 
		
 		cords = cords.trim();
		String l = cords.substring(1);
		String n = cords.substring(0,1);

		ret[0] = boardHeight - Integer.parseInt(n);
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
					System.out.println(translateCoords(y, x, this.ydim) + " - " + translateCoords(y+1, x, this.ydim));
				}
				
				if (thisChar == rightChar) {
					System.out.println(translateCoords(y, x, this.ydim) + " - " + translateCoords(y, x+1, this.ydim));
				}
			}
			
			// now we have a bunch of pairings.  We want to search through the pairings to see 
			// what pairings can tie together.
		}
		
	}
	
	public String getTurn() {
		if (currentTurn == 'x') return "black";
		return "white";
	}
	
	
	/**
	 * Create a new square game board
	 * @param gameCoords
	 * @throws Exception 
	 */
	public Go(int dimension) throws IllegalArgumentException {
		if (dimension <= 4) {
			throw new IllegalArgumentException("board dimension must be > 4");
		}
		
		if (dimension > 19) {
			throw new IllegalArgumentException("board dimension must be < 19");
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
	 * Create a rectangular game board.  Origin (0,0) is top left of board
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
		String header = "ABCDEFGHJKLMNOPQRST";
		System.out.print("    ");
		for (int x = 0; x < this.xdim; x++) {
			System.out.print(header.charAt(x) + " ");
		}
		System.out.println();
		
		for (int y = 0; y < ydim; y++ ) {
			System.out.printf("%2d ",this.ydim-y);
			for (int x = 0; x < this.xdim; x++) {
				System.out.print(" " + board[y][x]);
			}
			System.out.println();
		}
	}
	
	public char[][] getBoard() {
		return board;
	}
	
	
	/**
	 * Make a series of moves
	 * required by Codewars Kata
	 * @param moves
	 */
	public void move(String... moves) throws Exception {
		for (String move : moves) {
			move(move);
		}
		
	}
	
	/**
	 * Have the current player make a move 
	 * @param gameCoords
	 * @throws Exception
	 */
	public void move(String gameCoords) throws IllegalArgumentException {
		//System.out.println("move() called.  gameCoords=" + gameCoords);
		gameCoords = gameCoords.toUpperCase().trim();
		
		// check to see if gameCoords are valid, it should be a 2-3 character string
		// in the format of (1-2digit number) + 1 cap character
		//  ie : 1A or 19A or 5J
		String letStr = gameCoords.substring(gameCoords.length()-1);
		String numStr = gameCoords.substring(0,gameCoords.length()-1);
		
		// convert to x/y coordinates
		int y = 0;
		try {
			y = this.ydim-Integer.parseInt(numStr);
		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentException("Invalid game coordinates");
		}
		
		if (y < 0 || y >= ydim) {
			throw new IllegalArgumentException("Invalid game coordinates");
		}
		
		// make sure that this move wont cause a self capture
		String header = "ABCDEFGHJKLMNOPQRST".substring(0,this.xdim);
		if (!header.contains(letStr)) { 
			throw new IllegalArgumentException("Invalid game coordinates");
		}
		
		int x = header.indexOf(letStr);
		
		
		if ((x < 0) || (x >= this.xdim)) {
			throw new IllegalArgumentException("Invalid game coordinates");
		} 
		
		if ((y < 0 ) || (y >= this.ydim)) {
			throw new IllegalArgumentException("Invalid game coordinates");
		}
		
		if (this.wouldMoveCauseKO(y, x, currentTurn)) {
			throw new IllegalArgumentException("Move would cause KO.");
		}
		
		if (this.wouldMoveCauseSelfCapture(y, x, currentTurn)) {
			throw new IllegalArgumentException("Move would cause self capture.");
		}
		
		saveBoardToPrevious();		// take a snapshot
		copyBoardToStack();
		this.board[y][x] = currentTurn;
		currentTurn = (currentTurn == 'o') ? 'x' : 'o';

	}
	private Exception IllegalArgumentException(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
