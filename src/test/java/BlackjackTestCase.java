import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BlackjackTestCase {

	@Test
	void emptyBlackjackGame() {
		BlackjackGame blackjackGame = new BlackjackGame();
		assertEquals(0.0, blackjackGame.currentBet);
		assertEquals(0.0, blackjackGame.totalWinnings);
	}

	@Test
	void testPlayerHandTotal() {
		BlackjackGame blackjackGame = new BlackjackGame();
		blackjackGame.startGame();
		assertTrue(blackjackGame.playerHandTotal() > 0);
	}

	@Test
	void testDealerFirstCardTotal() {
		BlackjackGame blackjackGame = new BlackjackGame();
		blackjackGame.startGame();
		assertTrue(blackjackGame.dealerFirstCardTotal() > 0);
	}

	@Test
	void testPlayerBusted() {
		BlackjackGame blackjackGame = new BlackjackGame();
		blackjackGame.startGame();

		assertFalse(blackjackGame.playerBusted());
		while(blackjackGame.playerHandTotal() <= 21) {
			blackjackGame.hit();
		}

		assertTrue(blackjackGame.playerBusted());
	}

}
