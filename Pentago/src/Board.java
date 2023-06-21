/**
 * <b>Board</b>
 *
 * This Class Shows our board game.
 * we have a board with 4 blocks.
 * each block has 3x3 place for us to place stone.
 * in total we have 36 place 6x6.
 * in this class we can have two moves.
 * one placing a Stone and two spinning a block.
 *
 * @author Negar
 * @since 2020-04-10
 * @version 0.0
 */
public class Board {
    private char[][] board;

    /**
     * Making a 6x6 board and initializing with a space.
     */
    public Board(){
        board = new char[6][6];

        for(int j = 0; j < 6 ; j++){

            for(int k = 0; k < 6 ; k++){
                board[j][k] = ' ';
            }
        }

        printBoard();
        System.out.println("Black Turn :");
    }

    /**
     * getting the game board.
     * @return board Array field
     */
    protected char[][] getBoard() {
        return board;
    }

    /**
     * Placing a Stone on the game board.
     *
     * @param row board row
     * @param column board column
     * @param turn player's turn
     * @return true if we are able to place the stone
     */
    public boolean addStone(int row, int column, char turn){
        if(( row <= 6 && row >=0 ) && ( column <= 6 && column >=0 )){

            if(board[row][column] == ' '){
                board[row][column] = turn;
                return true;
            }

            System.out.println("This place is already taken.");
            return false;
        }

        System.out.println("Wrong Number.");
        return false;
    }

    /**
     * Spinning the block by 90 degree.
     * 1 - Round Clock direction
     * 2 - Counter Clockwise direction
     *
     * @param block the block number
     * @param spin the direction we want to spin - Counter Clockwise or Round Clock
     * @return true if we are able to spin the block
     */
    public boolean spin(int block, int spin){
        int ii = 0, jj = 0;
        if(block == 2){
            ii = 3;
        } else if(block == 3){
                    jj = 3;
                } else if(block == 4){
                            ii = jj = 3;
                        }

        char[][] newBlock = new char[3][3];
        for(int i = ii , y = 0 ; y < 3 ; i++ , y++){

            for(int j = jj , x = 0 ; x < 3 ; j++ , x++){

                newBlock[y][x] = board[j][i];
            }
        }

        if(spin == 1){
            for(int i = ii , y = 2 ; i < 3+ii ; i++ , y--){

                for(int j = jj , x = 0 ; j < 3+jj ; j++ , x++){

                    board[j][i] = newBlock[x][y];
                }
            }

        } else if(spin == 2){
            for(int i = ii , y = 0 ; i < 3+ii ; i++ , y++){

                for(int j = jj , x = 2 ; j < 3+jj ; j++ , x--){

                    board[j][i] = newBlock[x][y];
                }
            }
        }

        return true;
    }

    /**
     * Printing Game Board.
     */
    public void printBoard(){

        System.out.println("\t Block 1\t\tBlock 2");

        System.out.print("     ");
        for(int k = 0; k < 6 ; k++){
            System.out.print(k + "   ");
        }
        System.out.println();

        System.out.print("    ");
        for(int k = 0; k < 6 ; k++){
            System.out.print("___ ");
        }
        System.out.println();

        for(int j = 0; j < 6 ; j++){

            System.out.print(j + "  |");
            for(int k = 0; k < 6 ; k++){
                if(k == 2)
                    System.out.print(" " + board[j][k] + " *");
                else
                System.out.print(" " + board[j][k] + " |");
            }
            System.out.println();

            if(j == 2){

                System.out.print("   |");
                for(int k = 0; k < 6 ; k++){
                    if(k == 2)
                        System.out.print("   *");
                    else
                        System.out.print("   |");
                }
                System.out.println();

                System.out.print("   *");
                for(int k = 0; k < 6 ; k++){
                    System.out.print(" * *");
                }
            } else {

                System.out.print("   |");
                for(int k = 0; k < 6 ; k++){
                    if(k == 2)
                        System.out.print("___*");
                    else
                        System.out.print("___|");
                }
            }
            System.out.println();
        }

        System.out.println("\n\t Block 3\t\tBlock 4\n");
    }

    /**
     * It will count how many stones are on the board.
     * @return stone Number
     */
    public int stoneNum(){
        int num = 0;

        for(char[] column : board){

            for(char stone : column){

                if(stone != ' ')
                    num++;
            }
        }

        return num;
    }
}
