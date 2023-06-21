import java.util.Objects;

/**
 * <b>Circle</b>
 * Circle shape
 * we can calculate Perimeter and Area of the Circle.
 * and print it the shape and it's descriptions.
 * It is shape's extended class.
 *
 * @author Negar
 * @since 2020-04-09
 * @version 0.0
 */
public class Circle extends Shape{
    private double radius;

    /**
     * @param radius radius of the circle
     */
    public Circle(double radius){
        this.radius = radius;
    }

    /**
     * getting the radius of the circle.
     * @return radius field
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Calculating Perimeter of the Circle.
     * @return the Perimeter
     */
    public double calculatePerimeter(){
        return radius*2*(Math.PI);
    }

    /**
     * Calculating Area of the Circle.
     * @return the Area
     */
    public double calculateArea(){
        return (Math.PI)*radius*radius;
    }

    /**
     * printing Circle with it's Perimeter and Area.
     */
    public void draw(){
        System.out.println("Circle :\nPerimeter :  " + calculatePerimeter() + "\tArea :  " + calculateArea());
    }

    /**
     * the Override toString method.
     * @return a String of Circle's descriptions.
     */
    @Override
    public String toString() {
        return "Circle:\t" +
                "radius=" + radius;
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
        Circle circle = (Circle) o;
        return Double.compare(circle.getRadius(), getRadius()) == 0;
    }

    /**
     * the equals hashCode method.
     * @return the hash code fo object
     */
    @Override
    public int hashCode() {
        return Objects.hash(getRadius());
    }
}
