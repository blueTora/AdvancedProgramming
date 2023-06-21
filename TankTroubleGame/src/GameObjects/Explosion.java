package GameObjects;

import java.awt.*;

/**
 * Explosion for the killed tanks
 *
 * @author Negar Karami
 * @since 2020-07-26
 * @version 0.0
 */
public class Explosion extends GameObject{

    /**
     * creating the Explosion
     * @param x the x location
     * @param y the y location
     */
    public Explosion(int x, int y){
        super(x, y, ObjectId.explosion);

        image = inputImage("images\\explosion.png");

        setHeight(image.getHeight());
        setWidth(image.getWidth());
    }

    @Override
    public void rendering(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(image,getX(),getY(),null);
        g2d.dispose();
    }

    @Override
    public void update(){
    }
}