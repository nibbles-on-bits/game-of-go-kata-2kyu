package gameofgo;

import java.util.Comparator;

public class Position implements Comparator <Position>{
	private int yCoord;
	private int xCoord;
	private String chrPos;	// Character representation : ie : A1 or H7
	
	public int getX() { return xCoord; }
	public int getY() { return yCoord; }
	
	public Position(int y_idx, int x_idx) {
		yCoord = y_idx;
		xCoord = x_idx;
		String l = Character.toString("ABCDEFGHJKLMNOPQRST".charAt(x_idx));
		String n = Integer.toString(y_idx-1);
		chrPos = l + n ;
		
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

}