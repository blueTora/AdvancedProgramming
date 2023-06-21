package GameObjects;

import GameHandler.GameState;

import java.awt.*;

/**
 * the tanks bullets
 *
 * @author Negar Karami
 * @since 2020-07-26
 * @version 0.0
 */
public class Shot extends GameObject{

    private int power;
    private int speed;
    private int rotateDegree;
    private int w,h;
    private ShotId id;
    private boolean destroyed;
    private int a, b;
    private double alpha;
    private int preX, preY, beta;
    private ShotId shotId;

    /**
     * creating the shot
     * @param x the x location
     * @param y the y location
     * @param id the shot kind
     * @param rotateDegree the player direction
     * @param w player width
     * @param h player height
     */
    public Shot(int x, int y, ShotId id, int rotateDegree, int w, int h){
        super(x, y, ObjectId.shot);

        destroyed = false;
        this.power = GameState.getShotPower();
        speed = GameState.getShotSpeed();
        this.rotateDegree = rotateDegree;
        shotId = id;
        this.w = w;
        this.h = h;
        a = b = 1;
        alpha = 0;

        switch (id){
            case shot:
                image = inputImage("images\\shot.png");
                break;
            case shotX2:
                image = inputImage("images\\shotX2.png");
                power *= 2;
                break;
            case shotX3:
                image = inputImage("images\\shotX3.png");
                power *= 3;
                break;
            case laserShot:
                image = inputImage("images\\laser.png");
                this.speed = speed * 3;
                //power = 4 * GameState.getShotPower();
                break;
        }

        setHeight(image.getHeight());
        setWidth(image.getWidth());

        setX((int) (getX() + Math.cos(rotateDegree * (Math.PI/180)) + w/2));
        setY((int) (getY() + Math.sin(rotateDegree * (Math.PI/180)) + h/2));
    }

    @Override
    public void rendering(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(rotateDegree) + alpha * beta * 1.2, getX() , getY());
        if (alpha == 0)
            g2d.translate(0 ,h/2 + 12);
        //TODO: fix it
//        else
//            g2d.translate(betaX * (w/2) , betaY * (h/2 + 3));

        g2d.drawImage(image, getX(), getY(), null);
        g2d.dispose();

//        Graphics2D d = (Graphics2D) g.create();
//        d.draw(getBounds());
//        d.dispose();
    }

    @Override
    public void update() {
        preX = getX();
        preY = getY();
        setX(getX() - a * ((int) (Math.sin(rotateDegree * (Math.PI/180)) * speed)));
        setY(getY() - b * ((int) (Math.cos(rotateDegree * (Math.PI/180)) * -speed)));

        collision();
    }

    private void collision(){
        for (GameObject object :  GameState.getObjects()){

            if(object.getObjectId() == ObjectId.player){
                if (getBounds().intersects(object.getBounds())){
                    if (!((Player) object).isGuardUP())
                        ((Player) object).reduceLife(power);

                    if (shotId != ShotId.laserShot)
                        destroyed = true;
                }

            } else if(object.getObjectId() == ObjectId.destructibleWall){
                if (getBounds().intersects(object.getBounds())){
                    ((DestructibleWall) object).reduceLife(power);

                    if (shotId != ShotId.laserShot)
                        destroyed = true;
                }

            } else if(object.getObjectId() == ObjectId.indestructibleWall){
                if (getBounds().intersects(object.getBounds())){
                    if (((IndestructibleWall) object).isHorizontal()) {
                        b = -1;
                        alpha = 2 * Math.atan((getY() * 1.0)/getX());
                        if (preY != getY())
                            beta = (getY() - preY)/Math.abs(preY - getY());
                    } else {
                        a = -1;
                        alpha = 2 * Math.atan((getX() * 1.0)/getY());
                        if (preX != getX())
                            beta = (getX() - preX)/Math.abs(preX - getX());
                    }
//                    betaX = (getX() - preX)/Math.abs(preX - getX());
//                    betaY = (getY() - preY)/Math.abs(preY - getY());
                }
            }
        }
    }

    public ShotId getId() {
        return id;
    }

    public Rectangle getBounds() {
        int x = (int) (getX() + Math.cos((rotateDegree * (Math.PI/180)) + Math.PI/2) * 2*w/3);
        int y = (int) (getY() + Math.sin((rotateDegree * (Math.PI/180)) + Math.PI/2) * 2*h/3);
        return new Rectangle(x, y, 8 ,8);
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}
