import java.util.Objects;

/**
 * <b>Numeric Card</b>
 * It is a card with number.
 *
 * @author Negar
 * @since 2020-04-18
 * @version 0.0
 */
public class NumericCard extends Card {

    private final int NUMBER;

    /**
     * It will call the Card class constructor.
     * each card has point equal with it's number.
     * @param num card number
     * @param color card color
     */
    public NumericCard(int num, char color) {
        super(color, num);
        NUMBER = num;
    }

    /**
     * getting the card number.
     * @return number field
     */
    public int getNUMBER() {
        return NUMBER;
    }

    /**
     * comparing this object with another one for being equal or not.
     * @param o the object we want to compare
     * @return if they are equal will return true
     */
    @Override
    public boolean equals(Object o) {
        if(!super.equals(o)) return false;

        if (this == o) return true;
        if (!(o instanceof NumericCard)) return false;
        NumericCard that = (NumericCard) o;
        return getNUMBER() == that.getNUMBER();
    }

    /**
     * @return object hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(getNUMBER());
    }
}
