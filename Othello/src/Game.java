import java.util.*;

/**
 * <b>Game Class</b>
 * player must place a black disc on the board, in such a way that there is at least
 * one straight (horizontal, vertical, or diagonal) occupied line between the
 * new disc and another disc that belong to the player with one or more
 * contiguous opponent pieces between them.
 *
 * @author Negar
 * @since 2020-04-05
 * @version 0.0
 */
public class Game {

    private ArrayList<Block> blocks;
    private int whiteNum, blackNum;
    private char player;

    /**
     * We set the default board.
     * Creating 8x8 board with the Block Class.
     * setting the 4 start nut in the center of the board.
     */
    public Game(){
        whiteNum = blackNum = 2;
        blocks = new ArrayList<Block>();
        player = 'b';

        for (int i = 1; i <= 8; i++) {
            for(char j = 'A'; j <= 'H'; j++){

                Block newBlock = new Block(i, j);
                blocks.add(newBlock);

                if(i == 4){

                    if(j == 'D')
                        newBlock.setState('w');

                    if(j == 'E')
                        newBlock.setState('b');
                }
                if(i == 5){

                    if(j == 'D')
                        newBlock.setState('b');

                    if(j == 'E')
                        newBlock.setState('w');
                }
            }
        }

        printState();
        printBoard();
        System.out.println("The Black Player's Turn :");
    }

    /**
     * getting the number of the black nuts on the board.
     * @return blackNum field
     */
    public int getBlackNum() {
        return blackNum;
    }

    /**
     * getting the number of the white nuts on the board.
     * @return whiteNum field
     */
    public int getWhiteNum() {
        return whiteNum;
    }

    /**
     * getting which player turn is right now.
     * @return player field
     */
    public char getPlayer() {
        return player;
    }

    /**
     * setting the players turn.
     * @param player player filed
     */
    public void setPlayer(char player) {
        this.player = player;
    }

    /**
     * Printing the Game Board.
     */
    private void printBoard(){

        int index = 0;

        System.out.print("    ");
        for (char c = 'A'; c <= 'H' ; c++) {
            System.out.print(" " + c + "  ");
        }
        System.out.println();

        System.out.print("    ");
        for (int i = 0; i <8 ; i++) {
            System.out.print("___ ");
        }
        System.out.println();

        for (int i=1; i<=8 ; i++)
        {
            System.out.print(i + "  | ");
            for (int j = 0; j < 8; j++) {

                System.out.print( blocks.get(index).getState() + " | ");
                index++;
            }
            System.out.println();

            System.out.print("   |");
            for (int k = 0; k <8 ; k++) {
                System.out.print("___|");
            }
            System.out.println();
        }
    }

    /**
     * Printing the State of Game and how many block
     * belong to each player.
     */
    private void printState(){

        System.out.println();
        System.out.println("White :\t" + whiteNum);
        System.out.println("Black :\t" + blackNum + "\n");
    }

    /**
     * It check if it is possible to make the move and if it was
     * It will Call the nextMove method to make the move.
     *
     * @param row the row field
     * @param column the column field
     * @return if it is possible to make the move it will return true.
     */
    public boolean makeMove(int row, char column){

        for(Block block : blocks){

            if(block.getRow() == row && block.getColumn() == column){

                if(!block.isFull()){

                    int index = blocks.indexOf(block);

                    if( move(row, column, index) ){

                        if(player == 'w')
                            whiteNum++;
                        else
                            blackNum++;

                        block.setState(player);
                        printState();
                        printBoard();
                        return true;
                    } else
                        return false;

                }else {

                    System.out.println("This Block is not Empty.");
                    return false;
                }
            }
        }
        return false;

    }

    /**
     * It will check if the move we want to make is by the rules of game or not.
     * It will check all the 8 different directions of the block.
     * if there was not any possible move to make it will return false.
     *
     * @param row the row field
     * @param column the column field
     * @param index the index of the block that player has chose.
     * @return if it is possible to make the move it will return true.
     */
    private boolean move(int row, char column, int index){
        int test = 0;

//        test += fillip(block, row, column, index-8, 8);
//        test += fillip(block, row, column, index+8, 8);
//
//        test += fillip(block, row, column, index-1, 8);
//        test += fillip(block, row, column, index+1, 8);
//
//        test += fillip(block, row, column, index-8+1, 7);
//        test += fillip(block, row, column, index-8-1, 9);
//        test += fillip(block, row, column, index+8-1, 7);
//        test += fillip(block, row, column, index+8+1, 9);

        test += fillip( index, -8);
        test += fillip( index, 8);

        test += fillip( index, -1);
        test += fillip( index, 1);

        test += fillip( index, -7);
        test += fillip( index, -9);
        test += fillip( index, 7);
        test += fillip( index, 9);

        if(test != 0)
            return true;
        else
            return false;
    }

    /**
     * This method will handle the logic of a single turn in the Othello game.
     * It will flip the opponents nuts wherever they are surrounded in a line
     * by the latest nut played and the first nut of the player's same colour in that line.
     *
     * @param index the block index in blocks list.
     * @param difference the difference between next index
     * @return one or zero digit which one shows true and zero shows false
     */
    private int fillip(int index, int difference) {

        int tempIndex = index + difference;
        if ( tempIndex > 64 || tempIndex < 0 )
            return 0;

        Block currentBlock = blocks.get(tempIndex);

        while (currentBlock.getState() != ' ') {

            if (currentBlock.getState() == player) {

                tempIndex -= difference;
                currentBlock = blocks.get(tempIndex);

                if(tempIndex != index){
                    while (tempIndex != index) {

                        if(currentBlock.getState() != player){

                            if(player == 'w'){
                                whiteNum++;
                                blackNum--;

                            } else{
                                blackNum++;
                                whiteNum--;
                            }

                            currentBlock.setState(player);
                        }

                        tempIndex -= difference;
                        currentBlock = blocks.get(tempIndex);
                    }

                    return 1;

                } else
                    return 0;

            } else {

                tempIndex += difference;
                if ( tempIndex > 64 || tempIndex < 0 )
                    return 0;

                currentBlock = blocks.get(tempIndex);
            }
        }

        return 0;
    }

    /**
     * It will check all the 8 different directions of the block.
     * if there was not any possible move to make it will return false.
     *
     * @param row block row
     * @param column block column
     * @param index block index
     * @return if there is a possible move for that block will return true.
     */
    private boolean check(int row, char column, int index){

        int test = 0;

        test += isPossible( index, -8);
        test += isPossible( index, 8);

        test += isPossible( index, -1);
        test += isPossible( index, 1);

        test += isPossible( index, -7);
        test += isPossible( index, -9);
        test += isPossible( index, 7);
        test += isPossible( index, 9);

        if(test != 0)
            return true;
        else
            return false;
    }

    /**
     * it will check if in that direction there is a possible move or not.
     *
     * @param index block index
     * @param difference the difference between next index
     * @return one or zero digit which one shows true and zero shows false
     */
    private int isPossible( int index, int difference){
        int tempIndex = index + difference;
        if ( tempIndex > 64 || tempIndex < 0 )
            return 0;

        Block currentBlock = blocks.get(tempIndex);

        while (currentBlock.getState() != ' ') {

            if (currentBlock.getState() == player) {

                tempIndex -= difference;

                if(tempIndex != index){
                    while (tempIndex != index) {

                        tempIndex -= difference;
                    }

                    return 1;

                } else
                    return 0;

            } else {

                tempIndex += difference;
                if ( tempIndex > 64 || tempIndex < 0 )
                    return 0;

                currentBlock = blocks.get(tempIndex);
            }
        }

        return 0;
    }

    /**
     * It checks if player can make any moves or not.
     * if they can't then their turn will pass.
     *
     * @return if there isn't any move for the player to make it will return false
     */
    public boolean checkMoves(){

        for(Block block : blocks){

            if(block.getState() == ' '){

                if(!check(block.getRow(), block.getColumn(), blocks.indexOf(block)))
                    return true;
            }
        }

        return false;
    }

}
