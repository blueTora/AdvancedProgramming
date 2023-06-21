import java.util.ArrayList;
import java.util.Objects;

/**
 * <b>Shape</b>
 * It is a abstract class
 * It has 6 abstract method which is
 * in common between our shapes.
 * It is the mother class in this program.
 *
 * @author Negar
 * @since 2020-04-09
 * @version 0.0
 */
public abstract class Shape {

    abstract double calculatePerimeter();

    abstract public double calculateArea();

    abstract public String toString();

    abstract public void draw();

    abstract public boolean equals(Object o);

    abstract public int hashCode();

}
