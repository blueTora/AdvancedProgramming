package GameObjects;

import java.awt.*;

/**
 * the Gifts
 *
 * @author Negar Karami
 * @since 2020-07-26
 * @version 0.0
 */
public class Gifts extends GameObject {

    private GiftId giftId;
    private Graphics graph;
    private boolean used;

    /**
     * Creating the gift
     * @param x the x location
     * @param y the y location
     * @param giftIndex a random number for as the gifts kind index
     */
    public Gifts(int x, int y, int giftIndex){
        super(x, y, ObjectId.gift);

        used = false;
        this.giftId = GiftId.values()[giftIndex];
        image = inputImage("images\\gift.png");

        setHeight(image.getHeight());
        setWidth(image.getWidth());
    }

    @Override
    public void rendering(Graphics g) {
        graph = g;
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(image,getX(),getY(),null);
        g2d.dispose();

        if (used){
            Graphics2D d = (Graphics2D) graph.create();
            String str = giftId.name();
            d.setColor(Color.WHITE);
            d.setFont(d.getFont().deriveFont(Font.ITALIC).deriveFont(16.0f));
            int strWidth = d.getFontMetrics().stringWidth(str);
            d.drawString(str, getX() - strWidth,getY() - 20);
            d.dispose();
        }
    }

    @Override
    public void update(){
    }

    /**
     * if the gift was picked up by a tank
     * @return true if it was picked
     */
    public boolean isUsed() {
        return used;
    }

    /**
     * setting true if it was picked
     * @param used if the gift was picked up by a tank
     */
    public void setUsed(boolean used) {
        this.used = used;
    }

    /**
     * getting the gift enum field
     * @return gift enum field
     */
    public GiftId getGiftId() {
        return giftId;
    }
}
