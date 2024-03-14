import java.util.ArrayList;
import java.util.Collections;

public class BlackjackDealer {

    private ArrayList<Card> deck;
    public BlackjackDealer() {
        deck = new ArrayList<>();
        generateDeck();
        shuffleDeck();
    }

    /*
    This function should generate 52 cards, one for each
    of 13 faces and 4 suits.
     */
    public void generateDeck() {

        String[] suits = {"spade", "heart", "diamond", "club"};

        // Iterate through each card and add to deck
        for (String suit : suits) {
            for (int face = 1; face <= 13; face++) {

                deck.add(new Card(suit, face));
            }
        }
    }

    /*
    This function should return an Arraylist of two cards and
    leave the remainder of the deck able to be drawn later.
     */
    public ArrayList<Card> dealHand() {

        ArrayList<Card> hand = new ArrayList<>();
        hand.add(drawOne());
        hand.add(drawOne());
        return hand;
    }

    /*
    The function should return the next card on top of the deck.
     */
    public Card drawOne() {

        // Check if deck has cards
        if (deckSize() > 0) {
            return deck.remove(0);
        }
        // Empty deck, create and shuffle deck
        else {
            generateDeck();
            shuffleDeck();
            return drawOne();
        }
    }

    /*
    The function should return all 52 cards to the deck and
    shuffle their order.
     */
    public void shuffleDeck() {Collections.shuffle(deck);}

    /*
    The function should return the number of cards left in the deck.
    After a call to shuffleDeck(), this should be 52.
     */
    public int deckSize() {return deck.size();}

    public ArrayList<Card> getDeck() {
        return deck;
    }
}
