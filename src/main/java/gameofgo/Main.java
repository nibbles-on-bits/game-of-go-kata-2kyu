package gameofgo;

import org.junit.Test;

public class Main {
	
	public static void main(String[] args) {

		
		Go game = null;
		String positions = "1Ax,1Bx,1Fo,2Bo,2Co,2Dx,3Ax,3Bo,3Dx,3Ex,4Bx,4Do,4Ex,4Fo,5Bx,5Eo";
		game = new Go(positions,5,6);
		game.printBoard();
		
		game.findGroups();
		
		
		
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
