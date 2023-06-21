package GameObjects;

import java.awt.*;

/**
 * Blocks for collision
 *
 * @author Negar Karami
 * @since 2020-07-26
 * @version 0.0
 */
public class Block extends GameObject{

    /**
     * creating the block
     * @param x the x location
     * @param y the y location
     */
    public Block(int x, int y){
        super(x, y, ObjectId.block);
        setHeight(40);
        setWidth(40);
    }

    @Override
    public void rendering(Graphics g) {
    }

    @Override
    public void update() {
    }
}
