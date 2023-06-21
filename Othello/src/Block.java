/**
 * <b>Block Class</b>
 * This class is the block of the game board.
 * in total we will have 64 block.
 *
 * @author Negar
 * @since 2020-04-05
 * @version 0.0
 */
public class Block {

    private int row;
    private char column;
    private boolean full;
    private char state;

    /**
     * full field will declare if the block has any nut on it or not.
     * for the beginning we set the full as false which mean it is empty.
     * State field show if the block is full which player has it.
     *
     * @param row the row field
     * @param column the column field
     */
    public Block(int row, char column){
        full = false;
        state = ' ';
        this.column = column;
        this.row = row;
    }

    /**
     * getting the column field.
     * @return column field
     */
    public char getColumn() {
        return column;
    }

    /**
     * it shows if the block is empty or full.
     * @return full field
     */
    public boolean isFull() {
        return full;
    }

    /**
     * getting the row field.
     * @return row field
     */
    public int getRow() {
        return row;
    }

    /**
     * showing the which player has this block.
     * @return state field
     */
    public char getState() {
        return state;
    }

    /**
     * changing the state of the block.
     * @param state state field
     */
    public void setState(char state) {
        this.state = state;
        full = true;
    }
}
