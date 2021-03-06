package gameofgo;

import java.util.ArrayList;
import java.util.List;

public class Group {
	
	private List<Position> Positions = new ArrayList<Position>();
	private char player;
	

	public void print() {
		System.out.print(" " + player + ": ");
		for (Position p : Positions) {
			p.print();
			System.out.print(" ");
		}
		System.out.println();
	}
	
	
	/**
	 * Determine if this group has been captured by the opponent
	 * @param game
	 * @return
	 */
	public boolean isCaptured(char[][] board) {
		
		char opponent = (player=='o' ? 'x' : 'o');
		List<Position> liberties = findGroupLiberties(board);
		
		// only captured when all liberties are occupied by the opponent
		for (Position p : liberties) {	
			if (board[p.getY()][p.getX()] != opponent) return false;
		}
		
		return true;
	}
	
	
	/**
	 * Clear any captured positions on the board.
	 * @param board
	 */
	public static void clearCapturedGroups(char[][] board) {
		ArrayList<Group> capturedGroups = new ArrayList<Group>();
		
		for (Group g : findAllGroups(board)) {
			if (g.isCaptured(board)) {
				capturedGroups.add(g);
			}
		}
		
		for (Group cg : capturedGroups) {
			for (Position p : cg.Positions) {
				board[p.getY()][p.getX()] = '.';
			}
		}
		
	}
	
	/**
	 * Search the entire board for any groups that have been captured.
	 * 
	 */
	public static List<Group> findCapturedGroups(char[][] board) {
		ArrayList<Group> capturedGroups = new ArrayList<Group>();
		
		for (Group g : findAllGroups(board)) {
			if (g.isCaptured(board)) {
				capturedGroups.add(g);
			}
		}
		
		return capturedGroups;
	}
	
	
	
	// TODO : build this.
	/**
	 *  Given the board dimensions, determine all the liberties of this current group
	 * @param y_dim	Height of the gameboard in play
	 * @param x_dim Width of the gameboard in play
	 * @return
	 */
	public List<Position> findGroupLiberties(char[][] board) {
		int boardHeight = board.length;
		int boardWidth = board[0].length;
		
		ArrayList<Position> liberties = new ArrayList<Position>();
		
		// if all neighbors are surrounded by player character, then this piece resides
		//  inside the group and can be ignored
		
		for (Position p : Positions) {
			
			//TODO : will want to prevent redundant liberties from being added to the list.
			
			// if all the neighbors are the player character, it can be ignored.  Since it resides
			//  inside the group
			// if the above to cases are
			int x = p.getX();
			int y = p.getY();
			
			// left neighbor
			if ((x != 0) && board[y][x-1] != player) {
				Position lp = new Position(y, x-1, boardHeight);
				if (!lp.isInPositionList(liberties)) 
					liberties.add(lp);
			}

			// above neighbor
			if ((y != 0) && board[y-1][x] != player) {
				Position lp = new Position(y-1, x, boardHeight);
				if (!lp.isInPositionList(liberties))
					liberties.add(lp);
			}
			
			// right neighbor
			if ((x != boardWidth-1) && board[y][x+1] != player) {
				Position lp = new Position(y, x+1, boardHeight);
				if (!lp.isInPositionList(liberties))
					liberties.add(lp);
			}
			
			// below neighbor
			if ((y != boardHeight-1) && board[y+1][x] != player) {
				Position lp = new Position(y+1, x, boardHeight);
				if (!lp.isInPositionList(liberties))
					liberties.add(lp);				
			}
		}
		
		for (Position lp : liberties) {
			lp.print();
			System.out.print(" ");
		}
		
		return liberties;
	}
	

	
	/**
	 * 
	 * @param board
	 * @return
	 */
	public static List<Group> findAllGroups(char[][] board) {
		int ydim = board.length;
		int xdim = board[0].length;
		
		List<Group> groups = new ArrayList<Group>();
		
		for (int y = 0; y < ydim; y++) {
			for (int x = 0; x < xdim; x++) {
				// First make sure this square isn't part of any groups
				boolean posInGroups = false;
				for (Group g : groups) {
					if (g == null) continue;
					if (g.containsPosition(y, x)){
						posInGroups = true;
					}
				}
				
				if (posInGroups == false) {
					Group g = findGroup(y,x,board);
					if (g != null) groups.add(g);
				}

			}
		}
		
		return groups;
	}
	
	
	
	/**
	 * Build a group of positions that are part of a position
	 * @param y	y-coordinate of search starting location
	 * @param x x-coordinate of search starting location
	 */
	public static Group findGroup(int y_idx, int x_idx, char[][] board) {
		int ydim = board.length;
		int xdim = board[0].length;
		char player = board[y_idx][x_idx];
		
		if (player == '.') {
			//System.out.printf("board[%d][%d] is empty\n", y_idx, x_idx);
			// TODO : throw an exception here
			return null;
		}
		
		
		int ptr = 0; 
		Group group = new Group(board[y_idx][x_idx]);
		group.addPosition(new Position(y_idx, x_idx, ydim));
		
		
	
		boolean done = false;
		while (!done) {
			y_idx = group.getPositionAt(ptr).getY();
			x_idx = group.getPositionAt(ptr).getX();
			
			// if there is a left neighbor and it has the same value
			if ((x_idx != 0) && board[y_idx][x_idx-1] == player) {
				group.addPosition(y_idx, x_idx-1, ydim);
			}
			
			// if there is a above neighbor and it has the same value
			if ((y_idx != 0) && board[y_idx-1][x_idx] == player) {
				group.addPosition(y_idx-1, x_idx, ydim);
			}
			
			
			// if there is a right neighbor and it has the same value
			if ((x_idx != xdim-1) && board[y_idx][x_idx+1] == player) {
				group.addPosition(y_idx,x_idx+1, ydim);
			}
			
			// if there is a below neighbor and it has the same value
			if ((y_idx != ydim-1) && board[y_idx+1][x_idx] == player) {
				group.addPosition(y_idx+1, x_idx, ydim);
				//Go.addPositionToGroup(y_idx+1, x_idx, group);
			}
			
			// we are done if the ptr is at the end of the list and all neighbors with same value have been added
			if (ptr == group.getSize()-1) { done = true; }
			// if we aren't done, advance the pointer on the list
			ptr++;
			
		}
		
		return group;	
	}
	
	public Group(char player) {
		this.player = player;
	}
	
	public Position getPositionAt(int idx) {
		return Positions.get(idx);
	}
	
	public int getSize() {
		return Positions.size();
	}
	
	public void addPosition(Position p) {
		for (Position pp : Positions) {
			if ((pp.getX() == p.getX()) && (pp.getY() == p.getY())) {
				return;
			}
		}
		Positions.add(p);
	}
	
	public void addPosition(int y_idx, int x_idx, int boardHeight) {
		for (Position p: Positions) {
			if ((p.getX()==x_idx) && (p.getY() == y_idx)) {
				return;
			}
		}
		Positions.add(new Position(y_idx, x_idx, boardHeight));
	}
	
	
	/**
	 * See if this groups consists of a position
	 * @param position	in the form of A1
	 * @return true if position is part of this group
	 */
	public boolean containsPosition(String position, int boardHeight) {
		int[] pos = Go.translateCoords(position, boardHeight);
		return this.containsPosition(pos[0], pos[1]);
		
	}
	
	
	public boolean containsPosition(int y_idx, int x_idx) {
		for (Position p: Positions) {
			if ((p.getX() == x_idx) && (p.getY() == y_idx)) {
				return true;
			}
			
		}
		return false;
	}
	
	public boolean containsPosition(Position p) {
		for (Position pp: Positions) {
			if ((pp.getX() == p.getX()) && (pp.getY() == p.getY())) {
				return true;
			}
		}
		return false;
	}

}
