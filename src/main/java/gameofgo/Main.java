package gameofgo;

import org.junit.Test;

public class Main {
	
	public static void main(String[] args) {

		
		Go game = null;
		//String positions = "1Ax,1Bx,1Fo,2Bo,2Co,2Dx,3Ax,3Bo,3Dx,3Ex,4Bx,4Do,4Ex,4Fo,5Bx,5Eo";
		String positions = "1Ax,1Cx,1Dx,2Ax,2Cx,2Dx,2Hx,3Ax,3Bx,3Cx,4Bx,5Bx,6Bx,7Cx,7Dx,8Ax,1Ho,2Ho,4Eo,4Fo,4Go,5Do,5Eo,5Go,6Eo,6Fo,6Go,7Eo,7Fo,7Ho,8Co,8Do,8Eo";
		game = new Go(positions,8,8);
		game.printBoard();
		Group g = Group.findGroup(0, 0, game.getBoard());
		System.out.println(g.containsPosition("1A"));
		System.out.println(g.containsPosition("1B"));
		//game.findGroup(0,0);
		
		// Test Group.containsPosition(int, int)
		
		//game.findGroups();
		
		
		
		/*try {
			game = new Go(6,5);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		game.printBoard();
		System.out.println(game.getTurn() + "'s turn");
		//System.out.println("game.getSize()=" + game.getSize());
		//System.out.println("game.getTurn()=" + game.getTurn());
		
		try {
			game.move("2A");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		game.printBoard();
		System.out.println(game.getTurn() + "'s turn");
		
		try {
			game.move("6E");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		game.printBoard();
		System.out.println(game.getTurn() + "'s turn");
		
		////////////////////////////
		
		try {
			game.move("1B");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		game.printBoard();
		System.out.println(game.getTurn() + "'s turn");
		
		///////////////////////////
		 *
		 */
		
		

	}

}
