package GameObjects;

import java.awt.*;
import java.util.*;

public class IndestructibleWall extends GameObject{

    //ofoghi
    private boolean horizontal;

    /**
     * creating the Indestructible Wall
     * @param x the x location
     * @param y the y location
     * @param horizontal the wall direction
     */
    public IndestructibleWall(int x, int y, boolean horizontal){
        super(x, y, ObjectId.indestructibleWall);
        this.horizontal = horizontal;

        if (horizontal)
            image = inputImage("images\\wall\\indestructible1.png");
        else
            image = inputImage("images\\wall\\indestructible2.png");

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
    public void update() {

    }

    public boolean isHorizontal() {
        return horizontal;
    }
}
