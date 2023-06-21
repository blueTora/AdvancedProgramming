import java.util.*;

/**
 * <b>Triangle</b>
 * Triangle shape
 * we can calculate Perimeter and Area of the Triangle.
 * and print it the shape and it's descriptions.
 * also we can determined if the Triangle is Equilateral.
 * It is Polygon's extended class.
 *
 * @author Negar
 * @since 2020-04-09
 * @version 0.0
 */
public class Triangle extends Polygon{

    /**
     * we use super class constructor here.
     * @param side1 Triangle's side
     * @param side2 Triangle's side
     * @param side3 Triangle's side
     */
    public Triangle(double side1, double side2, double side3){
        super(side1, side2, side3);
    }

    /**
     * It shows if the Triangle is Equilateral or not.
     * @return if the Triangle is Equilateral return true
     */
    public boolean isEquilateral(){

        if( (getSides().get(1) + getSides().get(2) > getSides().get(0)) && (getSides().get(1) + getSides().get(0) > getSides().get(2)) && (getSides().get(2) + getSides().get(0) > getSides().get(1)) ){
            if(getSides().get(1).equals(getSides().get(2)))
                if(getSides().get(1).equals(getSides().get(0)))
                    return true;

            if(getSides().get(0).equals(getSides().get(2)))
                if(getSides().get(0).equals(getSides().get(1)))
                    return true;

            if(getSides().get(0).equals(getSides().get(1)))
                if(getSides().get(0).equals(getSides().get(2)))
                    return true;

            return false;
        }

        System.out.println("This shape isn't a Triangle.");
        return false;
    }

    /**
     * Calculating Area of the Triangle.
     * @return the Area
     */
    public double calculateArea(){
        double s = calculatePerimeter()/2;

        return Math.sqrt(s * (s - getSides().get(0)) * (s - getSides().get(1)) * (s - getSides().get(2)));
    }

    /**
     * the Override toString method.
     * @return a String of Triangle's descriptions.
     */
    @Override
    public String toString() {
        return "Triangle:\t" + super.toString();
    }

    /**
     * printing Triangle with it's Perimeter and Area.
     */
    public void draw(){
        System.out.println("Triangle :\nPerimeter :  " + calculatePerimeter() + "\tArea :  " + calculateArea());
    }
}
