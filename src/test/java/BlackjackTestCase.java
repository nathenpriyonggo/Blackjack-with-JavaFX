import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BlackjackTestCase {

	@Test
	void emptyBlackjackGame() {
		BlackjackGame blackjackGame = new BlackjackGame();
		assertEquals(0.0, blackjackGame.currentBet);
		assertEquals(0.0, blackjackGame.totalWinnings);
	}

}
