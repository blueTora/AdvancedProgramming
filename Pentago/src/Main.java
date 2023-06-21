import java.util.Scanner;

/**
 * <h1>Pentago</h1>
 *
 * <b>Main</b>
 *
 * This Program is a two player board game.
 * We have Black and Red Stones.
 * Black Player starts the Game.
 *
 * How to Play:
 * 1) Place a stone by Saying it's row and column.
 * 2) Spin a quadrant by Saying block's number and
 * choosing Round Clock or Counter Clockwise direction to spin by 90 degrees.
 * 3) First player to get 5 in a row wins.
 *
 * @author Negar
 * @since 2020-04-10
 * @version 0.0
 */
public class Main {

    /**
     *
     * @param args args Unused.
     */
    public static void main(String[] args) {

        Game game = new Game();

        Scanner scan = new Scanner(System.in);

        char player = 'B';
        char winner = ' ';

        while( game.stoneNum() != 36 ){

            System.out.println("Write Row and Column you want to place the Stone :");
            int row = scan.nextInt();
            int column = scan.nextInt();

            if(game.addStone(row, column, player)){
                game.printBoard();
                winner = game.checkGameOver(player, 1);
                if( winner != ' ' )
                    break;
            }
            else {
                System.out.println("Try Again.");
                continue;
            }

            System.out.println("Chose the Block you want to Spin :");
            int block2 = scan.nextInt();

            while (block2 < 1 || block2 > 4){

                System.out.println("Wrong Block Number.");
                System.out.println("Try Again.");
                System.out.println("Chose the Block you want to Spin :");
                block2 = scan.nextInt();
            }

            System.out.println("1) Round Clock\n2) Counter Clockwise");
            int spin = scan.nextInt();

            while (!(spin == 2 || spin == 1)){

                System.out.println("Wrong Spin Number.");
                System.out.println("Try Again.");
                System.out.println("1) Round Clock\n2) Counter Clockwise");
                spin = scan.nextInt();
            }

            if(game.spin(block2, spin)){
                game.printBoard();
                winner = game.checkGameOver(player, 2);
                if( winner != ' ' )
                    break;
            }

            if(player == 'B'){
                player = 'R';
                System.out.println("Red Turn :");
            } else {
                player = 'B';
                System.out.println("Black Turn :");
            }

        }

        if( winner != ' '){

            System.out.println("Game Over");

            if(game.stoneNum() == 36 || winner == 'D'){
                System.out.println("This is a Draw.");
            } else {

                if( winner == 'B')
                    System.out.println("Black player is the Winner.");
                else
                    System.out.println("Red Player is the Winner");
            }
        } else
            System.out.println("Error");

    }
}
