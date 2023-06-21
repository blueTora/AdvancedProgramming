import java.util.Objects;

/**
 * <b>Card</b>
 * This is the base class for all the cards in game.
 * a abstract class for all cards.
 *
 * @author Negar
 * @since 2020-04-18
 * @version 0.0
 */
abstract public class Card{

    private char COLOR;
    private final int point;

    /**
     * creating a card.
     * @param color cards color
     * @param point cards point
     */
    public Card(char color, int point){
        COLOR = color;
        this.point = point;
    }

    /**
     * for wild cards which their color will be
     * determined in the game process.
     * @param point cards point
     */
    public Card(int point){
        this.point = point;
    }

    /**
     * getting the cards color.
     * @return color field
     */
    public char getCOLOR() {
        return COLOR;
    }

    /**
     * for changing the color in wild cards
     * @param COLOR card color
     */
    public void setCOLOR(char COLOR) {
        this.COLOR = COLOR;
    }

    /**
     * getting the card point.
     * @return card point
     */
    public int getPoint() {
        return point;
    }

    /**
     * comparing this object with another one for being equal or not.
     * @param o the object we want to compare
     * @return if they are equal will return true
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return getCOLOR() == card.getCOLOR();
    }

    /**
     * @return object hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(getCOLOR());
    }
}
