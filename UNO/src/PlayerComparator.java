import java.util.Comparator;

/**
 * <b>Player Comparator</b>
 * a Comparator for sorting the players point array list.
 * It will be used in ArrayList sort function.
 *
 * @author Negar
 * @since 2020-04-18
 * @version 0.0
 */
public class PlayerComparator implements Comparator<Player>{

    /**
     * It will compare each two players with each other based on their points value.
     * @param player1 player one
     * @param player2 player two
     * @return 1 or 0 or -1 based on compering the two players
     */
    public int compare(Player player1, Player player2){
        return player1.getPoints().compareTo( player2.getPoints() );
    }

}
