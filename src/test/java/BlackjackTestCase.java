import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
class BlackjackTestCase {

	/*
	Test BlackjackGame
	 */
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

	/*
	Test BlackjackDealer
	 */
	@Test
	void emptyblackjackdealer() {
		BlackjackDealer dealer = new BlackjackDealer();
		assertEquals(52,  dealer.deckSize());
	}

	@Test
	void testGenerateDeck() {
		BlackjackDealer dealer = new BlackjackDealer();
		assertEquals(52, dealer.deckSize());
	}

	@Test
	void testShuffleDeck() {
		BlackjackDealer dealer = new BlackjackDealer();
		ArrayList<Card> originalDeck = new ArrayList<>(dealer.getDeck());
		dealer.shuffleDeck();
		assertNotEquals(originalDeck, dealer.getDeck());
	}

	@Test
	void testDealHand() {
		BlackjackDealer dealer = new BlackjackDealer();
		ArrayList<Card> hand = dealer.dealHand();
		assertEquals(2, hand.size());
		assertEquals(50, dealer.deckSize());
	}

	@Test
	void testDrawOne() {
		BlackjackDealer dealer = new BlackjackDealer();
		Card card = dealer.drawOne();
		assertNotNull(card);
		assertEquals(51, dealer.deckSize());
	}

	@Test
	void testDeckSize() {
		BlackjackDealer dealer = new BlackjackDealer();
		assertEquals(52, dealer.deckSize());
	}

	/*
	Test BlackjackGameLogic
	 */
	@Test
	void testWhoWon_PlayerWins() {
		BlackjackGameLogic gameLogic = new BlackjackGameLogic();
		ArrayList<Card> playerHand = new ArrayList<>();
		playerHand.add(new Card("heart", 10));
		ArrayList<Card> dealerHand = new ArrayList<>();
		dealerHand.add(new Card("spade", 7));
		assertEquals("player", gameLogic.whoWon(playerHand, dealerHand));
	}

	@Test
	void testWhoWon_DealerWins() {
		BlackjackGameLogic gameLogic = new BlackjackGameLogic();
		ArrayList<Card> playerHand = new ArrayList<>();
		playerHand.add(new Card("heart", 3));
		ArrayList<Card> dealerHand = new ArrayList<>();
		dealerHand.add(new Card("spade", 10));
		assertEquals("dealer", gameLogic.whoWon(playerHand, dealerHand));
	}

	@Test
	void testWhoWon_Draw() {
		BlackjackGameLogic gameLogic = new BlackjackGameLogic();
		ArrayList<Card> playerHand = new ArrayList<>();
		playerHand.add(new Card("heart", 10));
		ArrayList<Card> dealerHand = new ArrayList<>();
		dealerHand.add(new Card("spade", 10));
		assertEquals("push", gameLogic.whoWon(playerHand, dealerHand));
	}

	@Test
	void testHandTotal() {
		BlackjackGameLogic gameLogic = new BlackjackGameLogic();
		ArrayList<Card> hand = new ArrayList<>();
		hand.add(new Card("heart", 10));
		hand.add(new Card("spade", 3));
		assertEquals(13, gameLogic.handTotal(hand));
	}

	@Test
	void testEvaluateBankerDraw() {
		BlackjackGameLogic gameLogic = new BlackjackGameLogic();
		ArrayList<Card> hand1 = new ArrayList<>();
		hand1.add(new Card("heart", 10));
		assertTrue(gameLogic.evaluateBankerDraw(hand1));
		ArrayList<Card> hand2 = new ArrayList<>();
		hand2.add(new Card("spade", 6));
		assertTrue(gameLogic.evaluateBankerDraw(hand2));
	}

}
