import static org.junit.Assert.*;

import org.junit.Test;

public class TennisGameTest {

// Here is the format of the scores: "player1Score - player2Score"
// "love - love"
// "15 - 15"
// "30 - 30"
// "deuce"
// "15 - love", "love - 15"
// "30 - love", "love - 30"
// "40 - love", "love - 40"
// "30 - 15", "15 - 30"
// "40 - 15", "15 - 40"
// "player1 has advantage"
// "player2 has advantage"
// "player1 wins"
// "player2 wins"
	@Test
	public void testTennisGame_Start() {
		// Arrange
		TennisGame game = new TennisGame();
		// Act
		String score = game.getScore();
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);
	}

	@Test
	public void testTennisGame_EachPlayerWin4Points_Score_Deuce() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();

		game.player1Scored();
		game.player1Scored();
		game.player1Scored();

		game.player2Scored();
		game.player2Scored();
		game.player2Scored();

		game.player1Scored();
		game.player2Scored();
		// Act
		String score = game.getScore();
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);
	}

	@Test(expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();
		// Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		// Act
		// This statement should cause an exception
		game.player2Scored();
	}

	@Test(expected = TennisGameException.class)
	public void testTennisGame_Player2WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();
		// Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		// Act
		// This statement should cause an exception
		game.player1Scored();
	}

	@Test
	public void testTennisGame_Player1Wins() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();

		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		// Act
		String score = game.getScore();
		// Assert
		assertEquals("Player win incorrect", "player1 wins", score);
	}

	@Test
	public void testTennisGame_Player2Wins() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();

		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		// Act
		String score = game.getScore();
		// Assert
		assertEquals("Player win incorrect", "player2 wins", score);
	}

	@Test
	public void testTennisGame_Player1Advantage() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();

		game.player1Scored();
		game.player1Scored();
		game.player1Scored();

		game.player2Scored();
		game.player2Scored();
		game.player2Scored();

		game.player1Scored();
		// Act
		String score = game.getScore();
		// Assert
		assertEquals("Advantage incorrect", "player1 has advantage", score);

		game.player2Scored();
		game.player1Scored();
		// Act
		String score2 = game.getScore();
		// Assert
		assertEquals("Advantage incorrect", "player1 has advantage", score2);
	}

	@Test
	public void testTennisGame_Player2Advantage() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();

		game.player2Scored();
		game.player2Scored();
		game.player2Scored();

		game.player1Scored();
		game.player1Scored();
		game.player1Scored();

		game.player2Scored();
		// Act
		String score = game.getScore();
		// Assert
		assertEquals("Advantage incorrect", "player2 has advantage", score);

		game.player1Scored();
		game.player2Scored();
		// Act
		String score2 = game.getScore();
		// Assert
		assertEquals("Advantage incorrect", "player2 has advantage", score2);
	}

	@Test
	public void testTennisGame_DifferentScores() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();

		game.player1Scored();
		// Act
		String score = game.getScore();
		// Assert
		assertEquals("15 - love score incorrect", "love - 15", score);

		game.player2Scored();
		game.player2Scored();
		// Act
		String score2 = game.getScore();
		// Assert
		assertEquals("15 - 30 score incorrect", "30 - 15", score2);

		game.player1Scored();
		// Act
		String score3 = game.getScore();
		// Assert
		assertEquals("30 - 30 score incorrect", "30 - 30", score3);

		game.player2Scored();
		game.player2Scored();
		// Act
		String score4 = game.getScore();
		// Assert
		assertEquals("player2 win incorrect", "player2 wins", score4);
	}
}