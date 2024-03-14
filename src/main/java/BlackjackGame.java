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

    /*
    Helper functions
     */

    // Returns an int that contains the total in the player's hand
    public int playerHandTotal() {return gameLogic.handTotal(playerHand);}

    // Returns an int that contains the total in dealer's visible hand
    public int dealerFirstCardTotal() {return dealerHand().get(0).getValue();}

    // Return an int that contains the total in dealer's hand
    public int dealerHandTotal() {return gameLogic.handTotal(bankerHand);}

    public ArrayList<Card> playerHand() {return playerHand;};
    public ArrayList<Card> dealerHand() {return bankerHand;};

    // Start Game
    public void startGame() {
        playerHand = theDealer.dealHand();
        bankerHand = theDealer.dealHand();
    }


    public void setBet(int newBet) {currentBet = newBet;};

    public Card hit() {
        Card hitCard = theDealer.drawOne();
        playerHand.add(hitCard);
        return hitCard;
    };

    public void dealerHit() {
        bankerHand.add(theDealer.drawOne());
    }
    public boolean evalBankerDraw() {return gameLogic.evaluateBankerDraw(bankerHand);}
    public boolean playerBusted() {return gameLogic.handTotal(playerHand) > 21;};
    public String whoWonWithArgs() {return gameLogic.whoWon(playerHand, bankerHand);};
    public boolean isBlackjack(ArrayList<Card> hand) {
        if (hand.get(0).getValue() == 1 && hand.get(1).getValue() == 10) {
            return true;
        }
        else return hand.get(0).getValue() == 10 && hand.get(1).getValue() == 1;
    }
}
