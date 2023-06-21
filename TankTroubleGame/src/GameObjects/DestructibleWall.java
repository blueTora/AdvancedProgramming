package GameObjects;

import GameHandler.GameState;

import java.awt.*;

public class DestructibleWall extends GameObject{

    //ofoghi
    private boolean horizontal;
    private int life;

    /**
     * creating the Destructible Wall
     * @param x the x location
     * @param y the y location
     * @param horizontal the wall direction
     */
    public DestructibleWall(int x, int y, boolean horizontal){
        super(x, y, ObjectId.destructibleWall);
        this.horizontal = horizontal;
        this.life = GameState.getWallLife();

        if (horizontal)
            image = inputImage("images\\wall\\destructible1.png");
        else
            image = inputImage("images\\wall\\destructible2.png");

        setHeight(image.getHeight());
        setWidth(image.getWidth());
    }

    public int getLife() {
        return life;
    }

    /**
     * reducing life
     * @param damage the bullet power
     */
    public void reduceLife(int damage) {
        life -= damage;
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
}
