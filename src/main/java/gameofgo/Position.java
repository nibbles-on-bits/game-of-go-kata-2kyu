package gameofgo;

import java.util.ArrayList;
import java.util.Comparator;

public class Position implements Comparator <Position>{
	private int yCoord;
	private int xCoord;
	private String chrPos;	// Character representation : ie : A1 or H7
	
	public int getX() { return xCoord; }
	public int getY() { return yCoord; }
	
	public Position(int y_idx, int x_idx, int boardHeight) {
		yCoord = y_idx;
		xCoord = x_idx;
		String l = Character.toString("ABCDEFGHJKLMNOPQRST".charAt(x_idx));
		String n = Integer.toString(boardHeight-y_idx);
		chrPos = n + l ;
		
		return;
	}
	
	public int[] getIndexes() {
		int[] ret = {this.yCoord, this.xCoord};
		return ret;
	}
	
	public String getCharPos() {
		return chrPos;
	}

	public int compare(Position o1, Position o2) {
		return Math.abs(o1.getX() - o2.getX()) + Math.abs(o1.getY() - o2.getY());
	}
	
	public void print() {
		System.out.print(chrPos);
	}
	
	/**
	 * Determine if a list contains a position with the same coordinates as this
	 * @param pList The list of positions to check.
	 */
	public boolean isInPositionList(ArrayList<Position> pList) {
		boolean ret = false;
		
		for (Position p: pList) {
			if ((this.xCoord == p.xCoord) && (this.yCoord == p.yCoord)) {
				return true;
			}
		}
		
		return ret;
	}

}