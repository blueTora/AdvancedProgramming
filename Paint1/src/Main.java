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

        Rectangle rect1 = new Rectangle(1,4);
        Rectangle rect2 = new Rectangle(8,5);
        Rectangle rect3 = new Rectangle(6,6);

        Triangle tri1 = new Triangle(2,2,2);
        Triangle tri2 = new Triangle(3,3,4);

        Paint paint = new Paint();

        paint.addCircle(circle1);
        paint.addCircle(circle2);

        paint.addRectangle(rect1);
        paint.addRectangle(rect2);
        paint.addRectangle(rect3);

        paint.addTriangle(tri1);
        paint.addTriangle(tri2);

        paint.drawAll();

        paint.printAll();

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

        if(tri1.isEquilateral())
            System.out.println("tri1 is Equilateral");
        else
            System.out.println("tri1 isn't Equilateral");
    }
}