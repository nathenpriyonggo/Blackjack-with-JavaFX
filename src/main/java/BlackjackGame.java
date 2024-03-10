import java.util.ArrayList;

public class BlackjackGame {

    ArrayList<Card> playerHand;
    ArrayList<Card> bankerHand;
    BlackjackDealer theDealer;
    BlackjackGameLogic gameLogic;
    double currentBet;
    double totalWinnings;

    // Default constructor
    public BlackjackGame() {

        playerHand = new ArrayList<>();
        bankerHand = new ArrayList<>();
        theDealer = new BlackjackDealer();
        gameLogic = new BlackjackGameLogic();
        currentBet = 0.0;
        totalWinnings = 0.0;
    }

    /*
    This function should determine if the user won or lost their bet
    and return the amount won or lost based on the value in
    currentBet.
     */
    public double evaluateWinnings() {

        String whoWon = gameLogic.whoWon(playerHand, bankerHand);

        // Player wins, return currentBet
        if (whoWon.equals("player")) {
            totalWinnings += currentBet;
            return currentBet;
        }
        // Player loses, return -currentBet
        else if (whoWon.equals("dealer")) {
            totalWinnings -= currentBet;
            return -currentBet;
        }
        // Push, return 0.0
        else {
            return 0.0;
        }
    }
}
