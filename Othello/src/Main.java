import java.util.*;

/**
 * <h1>Othello Game</h1>
 * Othello is a strategy board game for two players (Black and White), played on an 8 by 8 board.
 * The game traditionally begins with four discs placed in the middle of the board as shown below.
 * Black moves first.
 * Players alternate taking turns. If a player does not have any valid moves,
 * play passes back to the other player.
 *
 *
 * @author Negar
 * @since 2020-04-05
 * @version 0.0
 */
public class Main {

    /**
     * with main method we can create a game and play it.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Game game = new Game();

        while(game.getBlackNum() + game.getWhiteNum() < 64 || game.getWhiteNum() == 0 || game.getBlackNum() == 0){

            if(game.checkMoves()){

                int row = scan.nextInt();
                char column = scan.next().charAt(0);

                if(!checkFormat(row, column)){
                    System.out.println("Wrong Address");
                    continue;
                }

                while( !game.makeMove(row, column) ){

                    System.out.println("Try Another Move.");
                    row = scan.nextInt();
                    column = scan.next().charAt(0);
                }
            } else
                System.out.println("Pass");

            if(game.getPlayer() == 'b'){
                game.setPlayer('w');
                System.out.println("\nThe White Player's Turn :");
            }
            else{
                game.setPlayer('b');
                System.out.println("\nThe Black Player's Turn :");
            }
        }

        System.out.println("\nGame is Over");

        if( game.getBlackNum() > game.getWhiteNum() )
            System.out.println("The Black Player is the Winner");
        else
            System.out.println("The white Player is the Winner");

    }

    /**
     * It will check if the scanned parameters are valid or not
     *
     * @param digit the int parameter
     * @param character the char parameter
     * @return if the parameters are not valid it will return false.
     */
    private static boolean checkFormat(int digit, char character){

        if(digit >= 1 && digit <= 8){

            if(character >= 'A' && character <= 'H')
                return true;
        }

        return false;
    }
}
