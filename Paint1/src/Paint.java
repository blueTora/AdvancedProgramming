import java.util.*;

/**
 * <b>Paint</b>
 * This class provides the painting program tools.
 * we can have 3 kind of shape.
 * such as printing and drawing the shapes.
 *
 * @author Negar
 * @since 2020-04-09
 * @version 0.0
 */
public class Paint {
    private ArrayList<Circle> circles;
    private ArrayList<Triangle> triangles;
    private ArrayList<Rectangle> rectangles;

    /**
     * creating 3 list of circles, triangles and
     * rectangles wich contain shapes.
     */
    public Paint(){
        circles = new ArrayList<Circle>();
        triangles = new ArrayList<Triangle>();
        rectangles = new ArrayList<Rectangle>();
    }

    /**
     * adding a new triangle to the list.
     * @param triangle new triangle
     */
    public void addTriangle(Triangle triangle){
        triangles.add(triangle);
    }

    /**
     * adding a new circle to the list.
     * @param circle new circle
     */
    public void addCircle(Circle circle){
        circles.add(circle);
    }

    /**
     * adding a new rectangle to the list.
     * @param rectangle new rectangle
     */
    public void addRectangle(Rectangle rectangle){
        rectangles.add(rectangle);
    }

    /**
     * printing all of the shapes with their Perimeter and Area.
     */
    public void drawAll(){
        for(Circle circle : circles){
            circle.draw();
        }

        for(Triangle triangle : triangles){
            triangle.draw();
        }

        for(Rectangle rectangle : rectangles){
            rectangle.draw();
        }
    }

    /**
     * printing all of the shapes descriptions.
     */
    public void printAll(){
        for(Circle circle : circles){
            System.out.println(circle.toString());
        }

        for(Triangle triangle : triangles){
            System.out.println(triangle.toString());
        }

        for(Rectangle rectangle : rectangles){
            System.out.println(rectangle.toString());
        }
    }
}
