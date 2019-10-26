package gameofgo;

import java.util.List;

import org.junit.Test;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		////////////// test rollback
		Go game = new Go(9);
		game.move("1A");
		game.move("3C");
		game.move("5D");
		game.printBoard();
		game.rollBack(1);
		game.printBoard();
		
		
		
		////////////// test KO
		/*String testBoard = 
				". . o . o x . . ." + "\n" +
				". . . o x . . . ." + "\n" +
				". . . . . . . . ." + "\n" +
				". . . . . . . . ." + "\n" +
				". . . . . . . . ." + "\n" +
				". . . . . . . . ." + "\n" +
				". . . . . . . . ." + "\n" +
				". . . . . . . . ." + "\n" +
				". . . . . . . . .";
		Go game = new Go(testBoard,'x');
		game.printBoard();
		game.move("9D");
		System.out.println();*/
		
		
		/*
		String testBoard = 
				". x o o o o o . ." + "\n" +
				". x o o o o o x ." + "\n" +
				". . x o o o x . ." + "\n" +
				". . . x o o x . ." + "\n" +
				". . x . x x . . ." + "\n" +
				". . . . . . . o ." + "\n" +
				". . x . . . . . ." + "\n" +
				". . . x . . o . ." + "\n" +
				". . . . . . . . .";
		
		Go game = new Go(testBoard,'x');
		game.printBoard();
		game.move("9H");
		System.out.println();
		System.out.println();
		game.printBoard();
		Group.clearCapturedGroups(game.getBoard());
		game.printBoard();
		*/
		
		
		
		
		
		///////////////////// Test findCapturedGroups ////////////////////////
		/*Go game = null;
		String positions = "2Ax,1Bx,1Ao";		// captured o
		game = new Go(positions, 8, 8, 'x');
		List<Group> capturedGroups = Group.findCapturedGroups(game.getBoard());
		
		System.out.println("\n\nCaptured Groups");
		for (Group cg : capturedGroups) {
			cg.print();
		}
		
		System.out.println("Before clearing captured groups");
		game.printBoard();
		
		Group.clearCapturedGroups(game.getBoard());
		
		System.out.println("\n\nAfter clearing captured groups");
		game.printBoard();
		*/
		
		
		
		
		/////////////////// Test for capture ////////////////////////////////
		/*Go game = null;
		String positions = "2Ax,1Ao";
		game = new Go(positions, 8, 8, 'o');
		try {
			game.move("1B");
		} catch (IllegalArgumentException iae) {
			System.out.println("IllegalArgumentException : " + iae.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
		//////////////////////Test for self capture //////////////////
		/*Go game = null;
		String positions = "2Ax,1Bx";
		game = new Go(positions, 8, 8, 'o');
		try {
			game.move("1A");
		} catch (IllegalArgumentException iae) {
			System.out.println("IllegalArgumentException : " + iae.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//////////////////////// Test for self capture //////////////////
		/*Go game = null;
		String positions = "2Ax,1Bx";
		game = new Go(positions,8,8,'o');
		if (game.wouldMoveCauseSelfCapture(0, 0, 'o')) {
			System.out.println("WOULD CAUSE SELF CAPTURE!");
		} else {
			System.out.println("Would not cause self capture");
		}*/
		
		//wouldMoveCauseSelfCapture()
		
		
		
		
		/////////////////////////// Find all groups //////////////////////
		/*
		Go game = null;
		String positions = "1Ax,1Bx,1Fo,2Bo,2Co,2Dx,3Ax,3Bo,3Dx,3Ex,4Bx,4Do,4Ex,4Fo,5Bx,5Eo";
		//String positions = "1Ax,1Bx,1Fo,2Bo";
		game = new Go(positions,8,8,'o');
		game.printBoard();
		System.out.println();
		System.out.println("All Groups: ");
		List<Group> groups = Group.findAllGroups(game.getBoard());
		for (Group g : groups) {
			g.print();
		}
		*/
		
		
		/////////////////// captured test 1 /////////////////////
		/*
		Go game = null;
		String positions = "1Ax,2Ao,1Bo";
		game = new Go(positions,8,8, 'o');
		game.printBoard();
		
		
		//Group[] groups = Group.findAllGroups(game.getBoard());
	
		Group g = Group.findGroup(0, 0, game.getBoard());
		g.print();
		g.findGroupLiberties(game);

		System.out.println();
		if (g.isCaptured(game)) {
			System.out.println("\nGroup CAPTURED");
		} else {
			System.out.println("\nGroup NOT CAPTURED");
		}*/
		
		
		///////////////////////// captured test 2 ////////////////////////////////////////////
		
		/*Go game = null;
		//String positions = "1Ax,1Bx,1Fo,2Bo,2Co,2Dx,3Ax,3Bo,3Dx,3Ex,4Bx,4Do,4Ex,4Fo,5Bx,5Eo";
		//String positions = "1Ax,1Cx,1Dx,2Ax,2Cx,2Dx,2Hx,3Ax,3Bx,3Cx,4Bx,5Bx,6Bx,7Cx,7Dx,8Ax,1Ho,2Ho,4Eo,4Fo,4Go,5Do,5Eo,5Go,6Eo,6Fo,6Go,7Eo,7Fo,7Ho,8Co,8Do,8Eo,4Co";	//3Co
		String positions = "1Ax,1Cx,1Dx,2Ax,2Cx,2Dx,2Hx,3Ax,3Bx,3Cx,4Bx,5Bx,6Bx,7Cx,7Dx,8Ax,1Ho,2Ho,4Eo,4Fo,4Go,5Do,5Eo,5Go,6Eo,6Fo,6Go,7Eo,7Fo,7Ho,8Co,8Do,8Eo,4Co,1Bo,2Bo,4Ao,3Co,4Co,5Ao,5Co,6Ao,7Bo,6Co,3Do,1Eo,2Eo";	//3Co
		game = new Go(positions,8,8, 'o');
		game.printBoard();
		
		
		//Group[] groups = Group.findAllGroups(game.getBoard());
	
		Group g = Group.findGroup(0, 0, game.getBoard());
		g.print();
		g.findGroupLiberties(game);
		System.out.println();
		if (g.isCaptured(game)) {
			System.out.println("Group CAPTURED");
		} else {
			System.out.println("Group NOT CAPTURED");
		}*/
		
		
		
		//System.out.println(g.containsPosition("1A"));
		//System.out.println(g.containsPosition("1B"));
		
		
		
		////////////////handicapStones////////////////
		/*
		Go game = null;
		try {
			game = new Go(19);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		game.handicapStones(9);  //TODO : 7 doesn't work, figure this out.
		game.printBoard();
		*/
		
		
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
