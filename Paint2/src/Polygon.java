import java.util.ArrayList;
import java.util.Objects;

/**
 * <b>Polygon</b>
 * It is shape's extended class and also a abstract class.
 * It has 1 abstract method which has inherited from shape.
 * It is the mother class of Triangle and Rectangle.
 *
 * @author Negar
 * @since 2020-04-09
 * @version 0.0
 */
abstract public class Polygon extends Shape{
    private ArrayList<Double> sides;

    /**
     *
     * @param sides a list of sides
     */
    public Polygon(double... sides){
        this.sides = new ArrayList<Double>();

        for(double side : sides){
            this.sides.add(side);
        }
    }

    /**
     * getting a list of sides.
     * @return sides ArrayList field
     */
    public ArrayList<Double> getSides() {
        return sides;
    }

    /**
     * Calculating Perimeter of the Polygon.
     * @return the Perimeter
     */
    public double calculatePerimeter(){
        double p = 0;
        for(double side : sides){
            p += side;
        }

        return p;
    }

    /**
     * the Override toString method.
     * @return a String of Polygon's descriptions.
     */
    @Override
    public String toString() {
        return "sides=" + sides;
    }

    /**
     * the Override equals method.
     * It shows if two objects are equal or not.
     * @param o the object that we want to compare
     * @return if the two objects are equal it returns true
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Polygon)) return false;
        Polygon polygon = (Polygon) o;
        return getSides().equals(polygon.getSides());
    }

    /**
     * the equals hashCode method.
     * @return the hash code fo object
     */
    @Override
    public int hashCode() {
        return Objects.hash(getSides());
    }
}
