package gameofgo;

public class Position {
	private int yCoord;
	private int xCoord;
	private String chrPos;	// Character representation : ie : A1 or H7
	
	
	public Position(int y_idx, int x_idx) {
		yCoord = y_idx;
		xCoord = x_idx;
		String l = Character.toString("ABCDEFGHJKLMNOPQRST".charAt(x_idx));
		String n = Integer.toString(y_idx-1);
		chrPos = l + n ;
		
		return;
	}
	
	public Position getLeft() { 
		if (x_idx == 0) return null;
		return new Position(this.yCoord, this.xCoord-1);
	}
	
	public Position getAbove() {
		
	}
	
	public Position getRight() {
		
	}
	
	
	public int[] getIndexes() {
		int[] ret = {this.yCoord, this.xCoord};
		return ret;
	}
	
	public String getCharPos() {
		return chrPos;
	}

}