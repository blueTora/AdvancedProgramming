/**
 * <h1>Main</h1>
 * This is a painting program.
 * we can have circles, triangles and rectangles.
 * we can draw and print them here.
 *
 * @author Negar
 * @since 2020-04-09
 * @version 0.0
 */
public class Main {

    /**
     * With main we can use the Paint Class
     * @param args args Unused.
     */
    public static void main(String[] args) {

        Circle circle1 = new Circle(19);
        Circle circle2 = new Circle(3);

        Rectangle rect1 = new Rectangle(1,4,1,4);
        Rectangle rect2 = new Rectangle(8,5,8,5);
        Rectangle rect3 = new Rectangle(6,6,6,6);

        System.out.println("Are they Equal? " + rect1.equals(rect3));

        Triangle tri1 = new Triangle(2,2,2);
        Triangle tri2 = new Triangle(3,4,5);

        Shape tri3 = new Triangle(4,4,4);
        Shape tri4 = new Triangle(2,2,2);

        System.out.println("Are they Equal? " + tri4.equals(tri1));

        Paint paint = new Paint();

        paint.addShape(circle1);
        paint.addShape(circle2);

        paint.addShape(rect1);
        paint.addShape(rect2);
        paint.addShape(rect3);

        paint.addShape(tri1);
        paint.addShape(tri2);
        paint.addShape(tri3);
        paint.addShape(tri4);

        rect1 = rect3;
        System.out.println("Are they Equal? " + rect1.equals(rect3));
        paint.addShape(rect1);

        System.out.println("Draw All..............................................");
        paint.drawAll();

        System.out.println("Print All.............................................");
        paint.printAll();

        System.out.println("Equal Sides...........................................");
        paint.describeEqualSides();

        System.out.println("......................................................");
        if(rect3.isSquare())
            System.out.println("rect3 is Square");
        else
            System.out.println("rect3 isn't Square");

        if(rect2.isSquare())
            System.out.println("rect2 is Square");
        else
            System.out.println("rect2 isn't Square");

        if(tri2.isEquilateral())
            System.out.println("tri2 is Equilateral");
        else
            System.out.println("tri2 isn't Equilateral");
    }
}