import java.util.ArrayList;

/**
 * <b>Player</b>
 * this is player.
 * each player is known by a number.
 * we store players cards here.
 *
 * @author Negar
 * @since 2020-04-18
 * @version 0.0
 */
public class Player {

    private Integer points;
    private ArrayList<Card> cards;
    private final char name;
    private static char num = '1';

    /**
     * name of player will be a number.
     * and other fields will be initialized.
     * and adding to players num for next players name.
     */
    public Player(){

        points = 0;
        cards = new ArrayList<Card>();
        name = num;
        num++;
    }

    /**
     * getting players points.
     * @return point field
     */
    public Integer getPoints() {
        return points;
    }

    /**
     * getting players name.
     * @return name field
     */
    public char getName() {
        return name;
    }

    /**
     * getting the players cards list.
     * @return cards field
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * adding the players cards points.
     * @param point each card point
     */
    public void addPoints(int point) {
        points += point;
    }

    /**
     * adding the card to players list of cards
     * @param card card for adding
     */
    public void addCard(Card card){
        cards.add(card);
    }

    /**
     * removing card from players card list.
     * @param card card for removing
     */
    public void remove(Card card){

        for (Card tempCard : cards){

            if (tempCard instanceof NumericCard && card instanceof NumericCard){
                if  ( ( (NumericCard)tempCard ).equals(card) ){
                    cards.remove(tempCard);
                    return;
                }

            }

            else if( tempCard instanceof WildColor && card instanceof WildColor ){
                if (( (WildColor)tempCard ).equals(card)){
                    cards.remove(tempCard);
                    return;
                }
            }

            else if ( tempCard instanceof Draw && card instanceof Draw){
                if (( (Draw)tempCard ).equals(card)){
                    cards.remove(tempCard);
                    return;
                }
            }

            else if (tempCard instanceof Reverse && card instanceof Reverse) {
                if (( (Reverse)tempCard ).equals(card)){
                    cards.remove(tempCard);
                    return;
                }
            }
            else if ( tempCard instanceof Skip && card instanceof Skip) {
                if (( (Skip)tempCard ).equals(card)){
                    cards.remove(tempCard);
                    return;
                }
            }
            else if (tempCard instanceof WildDraw && card instanceof WildDraw){
                if (( (WildDraw)tempCard ).equals(card)){
                    cards.remove(tempCard);
                    return;
                }
            }

        }
    }

    /**
     * Printing the players points.
     * @return a String a player name and its point
     */
    @Override
    public String toString() {
        return "Player " + name + " :\t" +
                "points = " + points ;
    }
}
