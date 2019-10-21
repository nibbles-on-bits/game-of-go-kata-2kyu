package gameofgo;

import org.junit.Test;

public class Main {
	
	public static void main(String[] args) {
		Go game = null;
		try {
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
		
		

	}

}
