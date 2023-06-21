package GameObjects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Creating the game objects
 *
 * @author Negar Karami
 * @since 2020-07-26
 * @version 0.0
 */
public abstract class GameObject{

    private ObjectId objectId;
    private int x;
    private int y;
    protected BufferedImage image;
    private int height;
    private int width;
    private long startTime = 0;

    /**
     * Objects
     * @param x the x location
     * @param y the y location
     * @param objectId object kind
     */
    public GameObject(int x, int y, ObjectId objectId){
        startTime = System.currentTimeMillis();
        this.objectId = objectId;
        this.x = x;
        this.y = y;
        height = 32;
        width = 32;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * getting the object existing time duration
     * @return the existing time
     */
    public long getTime() {
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    /**
     * rendering the object
     * @param g the frame Graphics
     */
    public abstract void rendering(Graphics g);

    /**
     * updating the object
     */
    public abstract void update();

    /**
     * the bounds around the object
     * @return the rectangle of th object shape
     */
    public Rectangle getBounds() {
        return new Rectangle(getX(),getY(), getWidth() ,getHeight());
    }

    /**
     * reading image and returning it
     * @param address the image address
     * @return image buffer
     */
    protected BufferedImage inputImage(String address){
        BufferedImage image = null;
        try{
            image = ImageIO.read(new File(address));
        }
        catch(IOException e){
            System.out.println(e);
        }
        return image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(String imageAddress) {
        image = inputImage(imageAddress);
    }
}
