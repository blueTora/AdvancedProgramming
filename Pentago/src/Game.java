/**
 * <b>Game</b>
 *
 * Board's subclass
 * This class job is to check the winning possibilities and ending the Game.
 * The board condition will be checked once after placing a stone and
 * once after spinning a block.
 *
 * @author Negar
 * @since 2020-04-10
 * @version 0.0
 */
public class Game extends Board{

    /**
     * This Method check all of Vertical, Horizontal and Diagonal directions
     * to find if there are 5 stones in a row or not.
     * @param turn player that we are checking
     * @return true if there are 5 stones in a row
     */
    private boolean checkDirections(char turn){

        if( checkWinner(0 , 0, 0, 1, turn) )
            return true;

        if( checkWinner(1, 0, 0, 1, turn) )
            return true;

        if( checkWinner(2, 0, 0, 1, turn) )
            return true;

        if( checkWinner(3, 0, 0, 1, turn) )
            return true;

        if( checkWinner(4, 0, 0, 1, turn) )
            return true;

        if( checkWinner(5, 0, 0, 1, turn) )
            return true;

        if( checkWinner(0, 0, 1, 0, turn) )
            return true;

        if( checkWinner(0, 1, 1, 0, turn) )
            return true;

        if( checkWinner(0, 2, 1, 0, turn) )
            return true;

        if( checkWinner(0, 3, 1, 0, turn) )
            return true;

        if( checkWinner(0, 4, 1, 0, turn) )
            return true;

        if( checkWinner(0, 5, 1, 0, turn) )
            return true;

        if( checkWinner(0, 0, 1, 1, turn) )
            return true;

        if( checkWinner(0, 1, 1, 1, turn) )
            return true;

        if( checkWinner(1, 0, 1, 1, turn) )
            return true;

        if( checkWinner(5, 0, -1, 1, turn) )
            return true;

        if( checkWinner(4, 0, -1, 1, turn) )
            return true;

        if( checkWinner(5, 1, -1, 1, turn) )
            return true;

        return false;
    }

    /**
     * This method start from a point and check if in the given direction
     * There are 5 stones in a row or not.
     *
     * @param row start point row
     * @param column start point column
     * @param x The difference between current place and next place in vector x direction
     * @param y The difference between current place and next place in vector y direction
     * @param turn  player that we are checking
     * @return true if there are 5 stones in a row
     */
    private boolean checkWinner(int row, int column, int x, int y, char turn){
        int inRow = 0;

        for(int i = row , j = column ; i < 6 && j < 6 && i >= 0 ; i += x , j += y){

            if( getBoard()[i][j] == turn){
                inRow++;
            } else
                inRow = 0;
        }

        if(inRow >= 5)
            return true;
        else
            return false;
    }

    /**
     * It will check if the game has winner or not and if it had
     * then return the winner char and if there is still no winner then return a space.
     *
     * R = Red
     * B = Black
     * D = Draw - Both Players won at the same time
     *
     * @param player the current player
     * @param both it means that if we must check our two player for a winner or just the current player
     *             it happens when we spin a block which there is a possibility for a Draw.
     * @return return the winner char or space if there is still no winner.
     */
    public char checkGameOver(char player, int both){
        char turn = player;

        if(both == 1){

            if( checkDirections(player) )
                return player;

        } else {
            if ( checkDirections(player) ){

                if(turn == 'B')
                    turn = 'R';
                else
                    turn = 'B';

                if(checkDirections(turn))
                    return 'D';
                else
                    return player;
            }

        }

        return ' ';
    }
}