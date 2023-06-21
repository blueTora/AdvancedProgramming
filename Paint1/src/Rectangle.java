import java.util.*;

/**
 * <b>Rectangle</b>
 * Rectangle shape
 * we can calculate Perimeter and Area of the Rectangle.
 * and print it the shape and it's descriptions.
 * also we can determined if the Rectangle is Square.
 *
 * @author Negar
 * @since 2020-04-09
 * @version 0.0
 */
public class Rectangle{
    private ArrayList<Double> sides;

    /**
     * since 2 out of 4 sides of the Rectangle is repetitive then
     * we only get the two sides that are different.
     * creating a ArrayList of sides which have 4 members which
     * two of them are repetitive.
     * @param side1 Rectangle's side
     * @param side2 Rectangle's side
     */
    public Rectangle(double side1, double side2){
        sides = new ArrayList<Double>();

        sides.add(side1);
        sides.add(side2);
        sides.add(side1);
        sides.add(side2);
    }

    /**
     * getting a list of sides.
     * @return sides ArrayList field
     */
    public ArrayList<Double> getSides() {
        return sides;
    }

    /**
     * It will show if the Rectangle is Square or not.
     * @return true if it is square
     */
    public boolean isSquare(){
        if(sides.get(1).equals(sides.get(0)))
            return true;

        return false;
    }

    /**
     * Calculating Perimeter of the Rectangle.
     * @return the Perimeter
     */
    public double calculatePerimeter(){
        return sides.get(0)*2 + sides.get(1)*2;
    }

    /**
     * Calculating Area of the Rectangle.
     * @return the Area
     */
    public double calculateArea(){
        return sides.get(1)*sides.get(0);
    }

    /**
     * printing Rectangle with it's Perimeter and Area.
     */
    public void draw(){
        System.out.println("Rectangle :\nPerimeter :  " + calculatePerimeter() + "\tArea :  " + calculateArea());
    }

    /**
     * the Override toString method.
     * @return a String of Rectangle's descriptions.
     */
    @Override
    public String toString() {
        return "Rectangle{" +
                "sides=" + sides +
                '}';
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
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Objects.equals(getSides(), rectangle.getSides());
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