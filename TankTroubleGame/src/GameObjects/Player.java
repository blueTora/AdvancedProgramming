package GameObjects;

import GameHandler.GameFrame;
import GameHandler.GameState;
import GameHandler.Main;

import java.awt.*;

/**
 * the Player
 *
 * @author Negar Karami
 * @since 2020-07-26
 * @version 0.0
 */
public class Player extends GameObject{

    private boolean UP, DOWN, turnRIGHT, turnLEFT;
    private int speed;
    private int life;
    private int rotateDegree;
    private boolean hasGift;
    private String name;
    private boolean guardUP;
    private ShotId shotId;
    private long startTime;
    private GiftId giftId;
    private boolean win;

    private PlayerData data;

    private long shotTime;
    private int shotCount;

    private boolean computer;
    private int preX, preY;

    /**
     * creating the player
     * @param x the x location
     * @param y the y location
     * @param name player name
     */
    public Player(int x, int y, String name){
        super(x, y, ObjectId.player);

        image = inputImage("images\\tank\\tank_blue.png");
        setHeight(image.getHeight());
        setWidth(image.getWidth());

        speed = GameState.getTankSpeed();
        life = GameState.getTankLife();

        this.name = name;
        guardUP = false;
        shotId = ShotId.shot;
        UP = false;
        DOWN = false;
        turnRIGHT = false;
        turnLEFT = false;
        hasGift = false;
        win = true;
        rotateDegree = 0;

        shotCount = 0;
        giftId = GiftId.None;
        computer = false;
        preX = preY = 0;
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
        Graphics2D d = (Graphics2D) g.create();

        //TODO: name and life bar
        //String str = "";
        //        d.setColor(Color.WHITE);
        //        d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(64.0f));
        //        int strWidth = g2d.getFontMetrics().stringWidth(str);
        //        d.drawString(str, (GAME_WIDTH - strWidth) / 2, GAME_HEIGHT / 2);

        d.dispose();


        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(rotateDegree), getX() + 28, getY() + 28);
        g2d.drawImage(image, getX(), getY(), null);
        g2d.dispose();
    }

    @Override
    public void update() {

        if (UP){
            setX(getX() - (int) (Math.sin(rotateDegree * (Math.PI/180)) * speed));
            setY(getY() - (int) (Math.cos(rotateDegree * (Math.PI/180)) * -speed));
        }
        if (DOWN){
            setX(getX() + (int) (Math.sin(rotateDegree * (Math.PI/180)) * speed));
            setY(getY() + (int) (Math.cos(rotateDegree * (Math.PI/180)) * -speed));
        }
        if (turnRIGHT)
            rotateDegree -= 7;
        if (turnLEFT)
            rotateDegree += 7;

        if (computer){
            try {
                nextMove();
            } catch (Exception e) {
            }
            finally {
                preX = getX();
                preY = getY();
            }

            if (getShotTime()/1000 < 1){
                if (getShotCount() < 2){
                    GameState.addObject(new Shot(getX(), getY(), getShotId(),
                            getRotateDegree() , getWidth(), getHeight()));
                    addShotCount();
                }
            } else
                resetShotCounts();
        }

        setY(Math.max(getY(), Main.y0));
        setY(Math.min(getY(), GameFrame.GAME_HEIGHT - getHeight()));
        setX(Math.max(getX(), 0));
        setX(Math.min(getX(), GameFrame.GAME_WIDTH - getWidth()));

        collision();

        if (hasGift){
            long t = System.currentTimeMillis();
            if ((t - startTime)/1000 > 15){
                switch (giftId){
                    case guardGift:
                        guardUP = false;
                        break;
                    case additionalLifeGift:
                        break;
                    case laserShotGift:
                    case multipleShotPowerGiftX2:
                    case multipleShotPowerGiftX3:
                        shotId = ShotId.shot;
                        break;
                }
                hasGift = false;
                giftId = GiftId.None;
            }
        }
    }

    /**
     * setting the collisions for player moves and receiving the gifts
     */
    public void collision() {
        for (GameObject object :  GameState.getObjects()){

            if (object.getObjectId() == ObjectId.block || object.getObjectId() == ObjectId.destructibleWall || object.getObjectId() == ObjectId.indestructibleWall || object.getObjectId() == ObjectId.player){

                if (getTopBound().intersects(object.getBounds())){
                    setY(object.getY() + object.getHeight());
                }
                if (getDownBound().intersects(object.getBounds())){
                    setY(object.getY() - getHeight());
                }
                if (getLeftBound().intersects(object.getBounds())){
                    setX(object.getX() + object.getWidth());
                }
                if (getRightBound().intersects(object.getBounds())){
                    setX(object.getX() - getWidth());
                }

            } else if (object.getObjectId() == ObjectId.gift){
                if (getBounds().intersects(object.getBounds())){
                    if (!hasGift){
                        hasGift = true;
                        ((Gifts) object).setUsed(true);
                        startTime = System.currentTimeMillis();
                        giftId = ((Gifts) object).getGiftId();
                        System.out.println(((Gifts) object).getGiftId().name());

                        switch (giftId){
                            case laserShotGift:
                                shotId = ShotId.laserShot;
                                break;
                            case guardGift:
                                guardUP = true;
                                break;
                            case additionalLifeGift:
                                int additionalLife =  GameState.getTankLife()/10;
                                life += additionalLife;
                                break;
                            case multipleShotPowerGiftX2:
                                shotId = ShotId.shotX2;
                                break;
                            case multipleShotPowerGiftX3:
                                shotId = ShotId.shotX3;
                                break;
                        }
                    }
                }
            }
        }
    }

    /**
     * for computer moves
     * @throws Exception
     */
    private void nextMove() throws Exception{
        int[][] map = GameState.getInputMap();
        int block = Main.blockSize;

        int i = 2 * (getY()-Main.y0)/block + 1;
        int j = 2 * getX()/block + 1;

        setUP(true);

        int a = (getX() - block/4)%block;
        int b = (getY()-Main.y0 - block/4 )%block;

        //TODO: fix a
        if ( a < 10 && b < 10) {
            switch (getRotationArea()){
                case 1:
                    if (map[i+1][j] == 0){
                    } else
                    if (map[i][j-1] == 0){
                        rotateDegree = getRotateDegree() + 90;
                    } else if (map[i][j+1] == 0){
                        rotateDegree = getRotateDegree() - 90;
                    } else if (map[i-1][j] == 0){
                        rotateDegree = getRotateDegree() + 180;
                    }
                    break;
                case 2:
                    if (map[i][j+1] == 0){
                    } else
                    if (map[i+1][j] == 0){
                        rotateDegree = getRotateDegree() + 90;
                    } else if (map[i-1][j] == 0){
                        rotateDegree = getRotateDegree() - 90;
                    } else if (map[i][j-1] == 0){
                        rotateDegree = getRotateDegree() + 180;
                    }
                    break;
                case 3:
                    if (map[i-1][j] == 0){
                    } else
                    if (map[i][j-1] == 0){
                        rotateDegree = getRotateDegree() + 90;
                    } else if (map[i][j+1] == 0){
                        rotateDegree = getRotateDegree() - 90;
                    } else if (map[i+1][j] == 0){

                    }
                    break;

                case 4:
                    if (map[i][j-1] == 0) {
                    } else
                    if (map[i+1][j] == 0){
                        rotateDegree = getRotateDegree() + 90;
                    } else if (map[i-1][j] == 0){
                        rotateDegree = getRotateDegree() - 90;
                    } else if (map[i][j+1] == 0){
                        rotateDegree = getRotateDegree() + 180;
                    }
                    break;
            }
        }

        if (preY == getY() && preX == getX()){
            rotateDegree = getRotateDegree() + 180;
        }
    }

    /**
     * getting the tank direction for computer moves
     * @return the area
     */
    private int getRotationArea(){
        int area = 0;
        double sin = Math.sin(Math.toRadians(getRotateDegree()));
        double cos = Math.cos(Math.toRadians(getRotateDegree()));

        if(sin > -0.1 && sin < 0.1){
            if (cos-1 > -0.1 && cos-1 < 0.1){
                area = 1;
            }
            if (cos+1 > -0.1 && cos+1 < 0.1){
                area = 3;
            }
        } else if (sin-1 > -0.1 && sin-1 < 0.1){
            if (cos > -0.1 && cos < 0.1){
                area = 4;
            }
        } else if (sin+1 > -0.1 && sin+1 < 0.1){
            if (cos > -0.1 && cos < 0.1){
                area = 2;
            }
        }

        return area;
    }

    public Rectangle getBounds() {
        return new Rectangle(getX(),getY(), getWidth() ,getWidth());
    }

    public int getRotateDegree() {
        return rotateDegree;
    }

    public void setUP(boolean UP) {
        this.UP = UP;
    }

    public void setDOWN(boolean DOWN) {
        this.DOWN = DOWN;
    }

    public void setTurnRIGHT(boolean turnRIGHT) {
        this.turnRIGHT = turnRIGHT;
    }

    public void setTurnLEFT(boolean turnLEFT) {
        this.turnLEFT = turnLEFT;
    }

    /**
     * getting the top bound of the player
     * @return the bound
     */
    public Rectangle getTopBound() {
        return new Rectangle(getX() + 10 , getY(), getWidth() - 20 ,getHeight()/2 - 5);
    }

    /**
     * getting the down bound of the player
     * @return the bound
     */
    public Rectangle getDownBound() {
        return new Rectangle(getX() + 10,getY() + getHeight()/2 + 5, getWidth() - 20 ,getHeight()/2 - 5);
    }

    /**
     * getting the left bound of the player
     * @return the bound
     */
    public Rectangle getLeftBound() {
        return new Rectangle(getX(),getY() + 10, 5 ,getHeight() - 20);
    }

    /**
     * getting the right bound of the player
     * @return the bound
     */
    public Rectangle getRightBound() {
        return new Rectangle(getX() + getWidth() - 5,getY() + 10, 5 ,getHeight() - 20);
    }

    public boolean isGuardUP() {
        return guardUP;
    }

    public ShotId getShotId() {
        return shotId;
    }

    public void addShotCount(){
        shotCount++;
    }

    public void resetShotCounts(){
        shotCount = 0;
        shotTime = System.currentTimeMillis();
    }

    /**
     * getting the player shooting time from last time.
     * @return time duration
     */
    public long getShotTime() {
        long t = (System.currentTimeMillis() - shotTime);
        return t;
    }

    public int getShotCount() {
        return shotCount;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public PlayerData getData() {
        return data;
    }

    public void setData(PlayerData data) {
        this.data = data;
    }

    public void setComputer(boolean computer) {
        this.computer = computer;
    }

    public String getName() {
        return name;
    }

    public GiftId getGiftId() {
        return giftId;
    }
}
