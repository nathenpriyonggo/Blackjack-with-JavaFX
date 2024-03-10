public class Card {
    String suit;
    int value;
    Card(String theSuit, int theValue) {

        suit = theSuit;
        value = theValue;
    }

    // Jack, Queen, and King have a value of 10
    public int getValue() {
        if (value > 10) {
            return 10;
        }
        else {
            return value;
        }
    }
}
