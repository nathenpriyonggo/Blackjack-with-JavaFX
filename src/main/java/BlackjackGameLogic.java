import java.util.ArrayList;

public class BlackjackGameLogic {

    /*
    This function should return either 'player' or 'dealer' or 'push'
    depending on who wins.
     */
    public String whoWon(ArrayList<Card> playerHand, ArrayList<Card> dealerHand) {

        int playerTotal = handTotal(playerHand);
        int dealerTotal = handTotal(dealerHand);

        // Player busts
        if (playerTotal > 21) {
            return "dealer";
        }
        // Dealer busts
        else if (dealerTotal > 21) {
            return "player";
        }
        // Draw
        else if (playerTotal == dealerTotal) {
            return "push";
        }
        // Player has more than dealer
        else if (playerTotal > dealerTotal) {
            return "player";
        }
        // Dealer has more than player
        else {
            return "dealer";
        }
    }

    /*
    This function should return an integer of the total value of
    all cards in the hand.
     */
    public int handTotal(ArrayList<Card> hand) {
        int total = 0;
        int numAces = 0;

        // Iterate through each card and calculate total
        for (Card card : hand) {

            total += card.getValue();
            // Check if card is an ace
            if (card.getValue() == 1) {
                numAces++;
            }
        }

        // Adjust for aces
        while (numAces > 0 && total + 10 <= 21) {
            total += 10;
            numAces--;
        }

        return total;
    }

    /*
    This function should return true if the dealer should draw another
    card if the value is 16 or less.
     */
    public boolean evaluateBankerDraw(ArrayList<Card> hand) {

        return handTotal(hand) <= 16;
    }
}
