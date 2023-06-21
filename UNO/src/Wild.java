import java.util.Random;
import java.util.Scanner;

/**
 * <b>Wild</b>
 * this is a abstract class for Wild cards.
 * Wild Draw and Wild Color.
 *
 * @author Negar
 * @since 2020-04-18
 * @version 0.0
 */
abstract public class Wild extends Card {

    /**
     * each wild card has 50 points.
     * the color of the card will be determined later in the game.
     */
    public Wild(){
        super(50);
    }

    /**
     * changing center card color.
     * if the player is computer it will chose a mrandom color beside the current color.
     *
     * @param player the current player
     * @param color the current color of center card
     */
    public void changeColor(char player, char color){

        Scanner scan = new Scanner(System.in);
        int colorNum;

        if (player == '1'){

            System.out.println("Chose the color :\n1) Red\n2) Yellow\n3) Blue\n4) Green");
            colorNum = scan.nextInt();

            while (colorNum < 1 || colorNum > 4){

                System.out.println("Wrong color Number.\nTry Again!");
                colorNum = scan.nextInt();
            }
        } else {

            char c;
            do {

                Random rand = new Random();
                colorNum = rand.nextInt(5)+1;

                if (colorNum == 1)
                    c = 'r';
                else if (colorNum == 2)
                    c = 'y';
                else if (colorNum == 3)
                    c = 'b';
                else
                    c = 'g';

            }while (color != c);
        }

        if (colorNum == 1)
            setCOLOR('r');
        else if (colorNum == 2)
            setCOLOR('y');
        else if (colorNum == 3)
            setCOLOR('b');
        else
            setCOLOR('g');
    }
}
