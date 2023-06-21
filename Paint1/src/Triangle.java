import java.util.*;

/**
 * <b>Triangle</b>
 * Triangle shape
 * we can calculate Perimeter and Area of the Triangle.
 * and print it the shape and it's descriptions.
 * also we can determined if the Triangle is Equilateral.
 *
 *
 * @author Negar
 * @since 2020-04-09
 * @version 0.0
 */
public class Triangle{
    private ArrayList<Double> sides;

    /**
     * creating a ArrayList of sides which have 3 members.
     * It will check if the 3 sides can make a Triangle or not.
     * @param side1 Triangle's side
     * @param side2 Triangle's side
     * @param side3 Triangle's side
     */
    public Triangle(double side1, double side2, double side3){

        if( (side1 + side2 > side3) && (side1 + side3 > side2) && (side2 + side3 > side1) ){

            sides = new ArrayList<Double>();

            sides.add(side1);
            sides.add(side2);
            sides.add(side3);
        }
    }

    /**
     * getting the list of Triangle's sides.
     * @return field sides ArrayList
     */
    public ArrayList<Double> getSides() {
        return sides;
    }

    /**
     * It shows if the Triangle is Equilateral or not.
     * @return if the Triangle is Equilateral return true
     */
    public boolean isEquilateral(){
        if(sides.get(1).equals(sides.get(2)))
            if(sides.get(1).equals(sides.get(0)))
                return true;

        if(sides.get(0).equals(sides.get(2)))
            if(sides.get(0).equals(sides.get(1)))
                return true;

        if(sides.get(0).equals(sides.get(1)))
            if(sides.get(0).equals(sides.get(2)))
                return true;

        return false;
    }

    /**
     * Calculating Perimeter of the Triangle.
     * @return the Perimeter
     */
    public double calculatePerimeter(){
        return sides.get(0)+sides.get(1)+sides.get(2);
    }

    /**
     * Calculating Area of the Triangle.
     * @return the Area
     */
    public double calculateArea(){
        double s = calculatePerimeter()/2;

        return Math.sqrt(s * (s - sides.get(0)) * (s - sides.get(1)) * (s - sides.get(2)));
    }

    /**
     * printing Triangle with it's Perimeter and Area.
     */
    public void draw(){
        System.out.println("Triangle :\nPerimeter :  " + calculatePerimeter() + "\tArea :  " + calculateArea());
    }

    /**
     * the Override toString method.
     * @return a String of Triangle's descriptions.
     */
    @Override
    public String toString() {
        return "Triangle{" +
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
        Triangle triangle = (Triangle) o;
        return getSides().equals(triangle.getSides());
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
