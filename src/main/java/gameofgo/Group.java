package gameofgo;

import java.util.ArrayList;
import java.util.List;

public class Group {
	
	private List<Position> Positions = new ArrayList<Position>();
	private char player;
	
	//private char[][] 	board;
	//public static Group findGroup()
	
	/**
	 * Build a group of positions that are part of a position
	 * @param y	y-coordinate of search starting location
	 * @param x x-coordinate of search starting location
	 */
	public static Group findGroup(int y_idx, int x_idx, char[][] board) {
		int ydim = board.length;
		int xdim = board[0].length;
			
		if (board[y_idx][x_idx] == '.') {
			System.out.printf("board[%d][%d] is empty\n", y_idx, x_idx);
			// TODO : throw an exception here
			return null;
		}
		
		int ptr = 0; 
		Group group = new Group(board[y_idx][x_idx]);
		group.addPosition(new Position(y_idx, x_idx));
		
		
	
		boolean done = false;
		while (!done) {
			y_idx = group.getPositionAt(ptr).getY();
			x_idx = group.getPositionAt(ptr).getX();
			
			// if there is a left neighbor and it has the same value
			if ((x_idx != 0) && board[y_idx][x_idx-1] == board[y_idx][x_idx]) {
				group.addPosition(y_idx, x_idx-1);
				//Go.addPositionToGroup(y_idx, x_idx-1, group);
			}
			
			// if there is a above neighbor and it has the same value
			if ((y_idx != ydim-1) && board[y_idx+1][x_idx] == board[y_idx][x_idx]) {
				group.addPosition(y_idx+1, x_idx);
				//Go.addPositionToGroup(y_idx+1, x_idx, group);
			}
			
			// if there is a right neighbor and it has the same value
			if ((x_idx != xdim-1) && board[y_idx][x_idx+1] == board[y_idx][x_idx]) {
				group.addPosition(y_idx,x_idx+1);
				//Go.addPositionToGroup(y_idx, x_idx+1, group);
			}
			
			// if there is a below neighbor and it has the same value
			if ((y_idx != 0) && board[y_idx-1][x_idx] == board[y_idx][x_idx]) {
				group.addPosition(y_idx-1, x_idx);
				//Go.addPositionToGroup(y_idx-1, x_idx, group);
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
	
	public void addPosition(int y_idx, int x_idx) {
		for (Position p: Positions) {
			if ((p.getX()==x_idx) && (p.getY() == y_idx)) {
				return;
			}
		}
		Positions.add(new Position(y_idx, x_idx));
	}
	
	
	/**
	 * See if this groups consists of a position
	 * @param position	in the form of A1
	 * @return true if position is part of this group
	 */
	public boolean containsPosition(String position) {
		int[] pos = Go.translateCoords(position);
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
