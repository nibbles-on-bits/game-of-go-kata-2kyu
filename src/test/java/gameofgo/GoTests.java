package gameofgo;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GoTests {

	private static String STR_FAIL_GENERATE_BOARD = "Should generate a valid %dx%d board",
			STR_SHOULD_THROW_CREATE = "Should throw an IllegalArgumentException for a board of size %dx%d";

	private Random rand = new Random();

	private static char[][] board_9x9 = { { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.' }, { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.' }, { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.' }, { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.' }, { '.', '.', '.', '.', '.', '.', '.', '.', '.' } };

	private static char[][] board_13x13 = { { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' } };

	private static char[][] board_19x19 = {
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' } };

	@Test
	public void test_CreatingGoBoards_OneArg() throws Exception {
		int[] sizes = { 9, 13, 19 };
		char[][][] boards = { board_9x9, board_13x13, board_19x19 };
		int i = -1;
		for (int size : sizes) {
			i++;
			Go g = new Go(size);
			assertArrayEquals(String.format(STR_FAIL_GENERATE_BOARD, size, size), boards[i], g.getBoard());
		}
	}

	@Test
	public void test_CreatingGoBoards_OneArg_Invalid() {

		int[] sizes = { -1, 32 };
		for (int size : sizes) {
			try {
				Go g = new Go(size);
				fail(String.format(STR_SHOULD_THROW_CREATE, size, size));
			} catch (IllegalArgumentException e) {
			} catch (Exception e) {
				fail(String.format(STR_SHOULD_THROW_CREATE, size, size));
			}
		}
	}

	@Test
	public void test_PlacingStones_OneArg() {

		Go g = new Go(9);

		String coord = "3D", coord2 = "4D";

		g.move(coord);
		assertEquals("Should have placed a black stone at the beginning", "x", "" + g.getPosition(coord));

		g.move(coord2);
		assertEquals("Should have placed a white stone at the second turn", "o", "" + g.getPosition(coord2));
	}

	@Test
	public void test_PlacingStones_ManyArgs() {

		Go g = new Go(9);
		String[] coords = { "4A", "5A", "6A" };
		g.move(coords);
		for (String pos : coords)
			assertEquals(pos.equals(coords[1]) ? "o" : "x", "" + g.getPosition(pos));
	}

	@Test
	public void test_PlacingStones_StonesReproduction() {

		Go g = new Go(9);
		String[] coords = { "3D", "4D" };

		g.move(coords);
		for (String pos : coords) {
			String msg = "Cannot place a stone on an existing stone. Should throw an IllegalArgumentExcpetion: " + pos
					+ " is an invalid move";
			try {
				g.move(pos);
				fail(msg);
			} catch (IllegalArgumentException ae) {
			} catch (Exception e) {
				fail(msg + "But was " + e.getMessage());
			}
		}
	}

	@Test
	public void test_PlacingStones_OutOfBounds_Coordinates() {

		Go g = new Go(9);
		String[] coords = { "3Z", "66" };
		for (String pos : coords) {
			String msg = pos + " should be an invalid move";
			try {
				g.move(pos);
				fail(msg);
			} catch (IllegalArgumentException ae) {
			} catch (Exception e) {
				fail(msg + "But was " + e.getMessage());
			}
		}
	}

	private static Map<String, String[][]> VALID_CAPTURE_DATAS = new HashMap<String, String[][]>() {
		{
// Board of size 9 for all the tests.
// Key = title ; Value = {String[] moves, String[] captured}
			put("Black captures single white stone",
					new String[][] { { "4D", "3D", "4H", "5D", "3H", "4C", "5B", "4E" }, { "4D" } });

			put("Black captures multiple white stones", new String[][] {
					{ "6D", "7E", "6E", "6F", "4D", "5E", "5D", "7D", "5C", "6C", "7H", "3D", "4E", "4F", "3E", "2E",
							"3F", "3G", "2F", "1F", "2G", "2H", "1G", "1H", "4C", "3C", "6H", "4B", "5H", "5B" },
					{ "6D", "6E", "4D", "5D", "5C", "4E", "3E", "3F", "2F", "2G", "1G", "4C" } });

			put("Corner capture", new String[][] { { "9A", "8A", "8B", "9B" }, { "9A" } });

			put("Multiple captures", new String[][] {
					{ "5D", "5E", "4E", "6E", "7D", "4F", "7E", "3E", "5F", "4D", "6F", "6D", "6C", "7F", "4E", "5E" },
					{ "4E", "6D", "6E" } });

			put("Snapback", new String[][] { { "5A", "1E", "5B", "2D", "5C", "2C", "3A", "1C", "2A", "3D", "2B", "3E",
					"4D", "4B", "4E", "4A", "3C", "3B", "1A", "4C", "3C" }, { "4A", "4B", "4C", "3B" } });
		}
	};

	@Test
	public void test_CapturingStones() {

		for (String title : VALID_CAPTURE_DATAS.keySet()) {
			Go g = new Go(9);
			g.move(VALID_CAPTURE_DATAS.get(title)[0]);
			for (String capt : VALID_CAPTURE_DATAS.get(title)[1])
				assertEquals(title, ".", "" + g.getPosition(capt));
		}
	}

	@Test
	public void test_SelfCapturingNotAllowed() {

		Go g = null;
		String msg = "Self capturing moves are illegal; should throw an IllegalArgumentException";
		try {
			g = new Go(9);
			g.move("4H", "8A", "8B", "9B", "9A");
			fail(msg);
		} catch (IllegalArgumentException ae) {
		} catch (Exception e) {
			fail(msg + "But was " + e.getMessage());
		}

		assertEquals("Illegal stone should be removed", ".", "" + g.getPosition("9A"));

		g.move("3B");
		assertEquals("Black should have another try", "x", "" + g.getPosition("3B"));
	}

	@Test
	public void test_KO_Rule_Illegal_KO_by_white() {

		Go g = null;
		String msg = "Illegal KO move. Should throw an IllegalArgumentException.";
		try {
			g = new Go(5);
			g.move("5C", "5B", "4D", "4A", "3C", "3B", "2D", "2C", "4B", "4C", "4B");
			fail(msg);
		} catch (IllegalArgumentException ae) {
		} catch (Exception e) {
			fail(msg + "But was " + e.getMessage());
		}

		g.move("2B");
		assertEquals("Black should have another try to place their stone", "x", "" + g.getPosition("2B"));
		assertEquals("Should rollback game before illegal move was made", ".", "" + g.getPosition("4B"));
	}

	@Test
	public void test_HandicapStones() throws Exception {

		Go g = new Go(9);
		g.handicapStones(3);
		char[][] board = { { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.' }, { '.', '.', '.', '.', '.', '.', 'x', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.' }, { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.' }, { '.', '.', 'x', '.', '.', '.', 'x', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.' }, { '.', '.', '.', '.', '.', '.', '.', '.', '.' } };
		assertArrayEquals(board, g.getBoard());
	}

	@Test
	public void test_getSize() {
		int h = 9, w = 16;
		Go g = new Go(h, w);
		assertEquals("Should be able to get the size of the correct board", new HashMap<String, Integer>() {
			{
				put("height", h);
				put("width", w);
			}
		}, g.getSize());
	}

	@Test
	public void test_getColorTurn() {
		Go g = new Go(9);
		g.move("3B");
		assertEquals("Should be able to get the color of the current turn", "white", g.getTurn());
		g.move("4B");
		assertEquals("Should be able to get the color of the current turn", "black", g.getTurn());
	}

	@Test
	public void test_rollBack() {
		Go g = new Go(9);
		g.move("3B", "2B", "1B");
		char[][] board = { { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.' }, { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.' }, { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.' }, { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.' }, { '.', '.', '.', '.', '.', '.', '.', '.', '.' } };

		g.rollBack(3);
		assertArrayEquals("Should be able to rollback 3 turns", board, g.getBoard());
		assertEquals("black", g.getTurn());
	}

	@Test
	public void test_PassTurn() {

		Go g = new Go(9);
		g.passTurn();
		assertEquals("white", g.getTurn());
	}

	@Test
	public void test_ResetBoard() {
		Go g = new Go(9);
		g.move("3B", "2B", "1B");
		g.reset();
		assertArrayEquals(board_9x9, g.getBoard());
		assertEquals("black", g.getTurn());
	}
}