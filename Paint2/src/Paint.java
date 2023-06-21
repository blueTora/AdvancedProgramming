import java.util.*;

/**
 * <b>Paint</b>
 * This class provides the painting program tools.
 * we can have 3 kind of shape.
 * such as printing and drawing the shapes.
 * we can also determine the shapes with equal sides.
 *
 * @author Negar
 * @since 2020-04-09
 * @version 0.0
 */
public class Paint {
    private ArrayList<Shape> shapes;

    /**
     *
     */
    public Paint(){
        shapes = new ArrayList<Shape>();
    }

    /**
     * adding a shape to the shapes list.
     * @param shape the shape we want to add
     */
    public void addShape(Shape shape){
        shapes.add(shape);
    }

    /**
     * printing all of the shapes with their Perimeter and Area.
     */
    public void drawAll(){
        for(Shape shape : shapes){
            shape.draw();
        }
    }

    /**
     * printing all of the shapes descriptions.
     */
    public void printAll(){
        for(Shape shape : shapes){
            System.out.println(shape.toString());
        }
    }

    /**
     * We determine the shapes with equal sides.
     * Rectangles that are Square and Triangle's that are
     */
    public void describeEqualSides(){
        for (Shape shape : shapes){

            if(shape instanceof Triangle){

                if(((Triangle) shape).isEquilateral()){
                    System.out.println(shape.toString());
                }
            }

            if(shape instanceof Rectangle){

                if(((Rectangle) shape).isSquare()){
                    System.out.println(shape.toString());
                }
            }
        }
    }
}
