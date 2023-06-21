/**
 * <b>Display Game</b>
 * here we display our cards.
 * since it's one player game you can only see
 * your cards when it's your turn.
 *
 * @author Negar
 * @since 2020-04-18
 * @version 0.0
 */
public class DisplayGame extends Game {

    /**
     * for creating the game.
     */
    public DisplayGame(){
        super();
    }

    /**
     * It will print all players cards using the
     * displayCard and displayPlayerVertical function.
     *
     * @param currentPlayer current Player turn
     */
    public void display(Player currentPlayer){

        if(isOrientation())
            System.out.println("\n« Clock Wise Orientation »\n");
        else
            System.out.println("\n« Anti Clock Wise Orientation »\n");

        Player player;

        for (int i = getPlayers().size()-1 ; i>=0 ; i-- ) {

            player = getPlayers().get( i );

            System.out.println("\nPlayer " + player.getName() + ": ");

            if(player.getName() != '1' || currentPlayer.getName() != '1')
                displayPlayerVertical(player.getCards().size());

            else {
                int iter = 1 ;
                for (Card card : player.getCards()){
                    System.out.println(iter + "- ");
                    displayCard(card);
                    System.out.println();
                    iter++;
                }
            }
        }

        String color;

        if(getCenterCard().getCOLOR() == 'r')
            color = "RED";
        else if (getCenterCard().getCOLOR() == 'b')
            color = "BLUE";
        else if (getCenterCard().getCOLOR() == 'g')
            color = "GREEN";
        else
            color = "YELLOW";

        System.out.println("\n\nCenter Card :");
        displayCard(getCenterCard());
        System.out.println(" " + color );
        displayPlayerVertical(1);
        System.out.println("    " + ( getCardsStorage().size() ) );
        System.out.println();
    }

    /**
     * display current player card.
     * @param card card we want to print
     */
    private void displayCard(Card card) {

        if(card instanceof NumericCard){

            coloring(((NumericCard) card).getCOLOR());

            System.out.println(" _______");

            System.out.println("|" + ((NumericCard) card).getNUMBER() + "      |");
            System.out.println("|       |");
            System.out.println("|   " + ((NumericCard) card).getNUMBER() + "   |");
            System.out.println("|       |");

            System.out.println("|______" + ((NumericCard) card).getNUMBER() + "|" + ConsoleColors.RESET);

        } else if(card instanceof Draw){

            coloring(((Draw) card).getCOLOR());

            System.out.println(" _______");

            System.out.println("| Draw  |");
            System.out.println("|       |");
            System.out.println("|   +2  |");
            System.out.println("|       |");

            System.out.println("|_______|" + ConsoleColors.RESET);

        } else if(card instanceof Skip){

            coloring(((Skip) card).getCOLOR());

            System.out.println(" _______");

            System.out.println("| Skip  |");
            System.out.println("|       |");
            System.out.println("|  \\/   |");
            System.out.println("|  /\\   |");

            System.out.println("|_______|" + ConsoleColors.RESET);

        } else if (card instanceof Reverse){

            coloring(((Reverse) card).getCOLOR());

            System.out.println(" _______");

            System.out.println("|Reverse|");
            System.out.println("|       |");
            System.out.println("|  -->  |");
            System.out.println("|  <--  |");

            System.out.println("|_______|" + ConsoleColors.RESET);

        } else if (card instanceof WildColor){

            System.out.println(ConsoleColors.WHITE + " _______");

            System.out.println("|Change |");
            System.out.println("|  Color|");
            System.out.println("|   Y   |");
            System.out.println("| R   B |");

            System.out.println("|___G___|" + ConsoleColors.RESET);

        } else if(card instanceof WildDraw){

            System.out.println(ConsoleColors.WHITE + " _______");

            System.out.println("|Wild   |");
            System.out.println("|   Draw|");
            System.out.println("|   Y   |");
            System.out.println("|R +4  B|");

            System.out.println("|___G___|" + ConsoleColors.RESET);

        }
    }

    /**
     * printing Vertical cards for players which is not their turn.
     * @param n number of the card we want to print
     */
    private void displayPlayerVertical(int n) {

        System.out.print(ConsoleColors.PURPLE);
        for (int i = 0; i < n; i++) {
            System.out.print(" _______" + "  ");
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.print("|       |" + " ");
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.print("|  * *  |" + " ");
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.print("|*  *  *|" + " ");
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.print("|  * *  |" + " ");
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.print("|_______|" + " ");
        }
        System.out.println(ConsoleColors.RESET);
    }

    /**
     * will change the printing color for cards.
     * @param color the cards color
     */
    private void coloring(char color){

        switch (color){

            case 'r' :
                System.out.print(ConsoleColors.RED);
                break;
            case 'y' :
                System.out.print(ConsoleColors.YELLOW);
                break;
            case 'g' :
                System.out.print(ConsoleColors.GREEN);
                break;
            case 'b' :
                System.out.print(ConsoleColors.BLUE);
                break;
        }
    }
}