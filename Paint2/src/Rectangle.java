import java.util.*;

/**
 * <b>Rectangle</b>
 * Rectangle shape
 * we can calculate Perimeter and Area of the Rectangle.
 * and print it the shape and it's descriptions.
 * also we can determined if the Rectangle is Square.
 * It is Polygon's extended class.
 *
 * @author Negar
 * @since 2020-04-09
 * @version 0.0
 */
public class Rectangle extends Polygon{

    /**
     * we use super class constructor here.
     * @param side1 Rectangle's side
     * @param side2 Rectangle's side
     * @param side3 Rectangle's side
     * @param side4 Rectangle's side
     */
    public Rectangle(double side1, double side2, double side3, double side4){
        super(side1, side2, side3, side4);
    }

    /**
     * It will show if the Rectangle is Square or not.
     * @return true if it is square
     */
    public boolean isSquare(){

        if( getSides().get(0).equals(getSides().get(2)) && getSides().get(1).equals(getSides().get(3)) ){
            if(getSides().get(1).equals(getSides().get(0)))
                return true;
            return false;
        }

        System.out.println("This shape isn't a Rectangle.");
        return false;
    }

    /**
     * Calculating Area of the Rectangle.
     * @return the Area
     */
    public double calculateArea(){
        return getSides().get(1)*getSides().get(0);
    }

    /**
     * the Override toString method.
     * @return a String of Rectangle's descriptions.
     */
    @Override
    public String toString() {
        return "Rectangle:\t" + super.toString();
    }

    /**
     * printing Rectangle with it's Perimeter and Area.
     */
    public void draw(){
        System.out.println("Rectangle :\nPerimeter :  " + calculatePerimeter() + "\tArea :  " + calculateArea());
    }
}