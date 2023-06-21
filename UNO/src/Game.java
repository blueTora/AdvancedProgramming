import java.util.*;

/**
 * <b>Game</b>
 * in this class we have the games base.
 * our total cards and players are stored here.
 *
 * @author Negar
 * @since 2020-04-18
 * @version 0.0
 */
public class Game {

    private ArrayList<Card> cardsStorage;
    private ArrayList<Player> players;
    private Card centerCard;
    //true - clockwise
    //false - anticlockwise
    private boolean orientation;

    /**
     * our beginning orientation is clockwise.
     * creating player and storage lists.
     */
    public Game(){

        cardsStorage = new ArrayList<Card>();
        players = new ArrayList<Player>();
        orientation = true;
    }

    /**
     * Starting the game by creating the 108 games card.
     */
    public void startGame(){

        for (int i = 0; i < 10; i++) {

            cardsStorage.add(new NumericCard(i,'r'));
        }

        for (int i = 0; i < 10; i++) {

            cardsStorage.add(new NumericCard(i,'y'));
        }

        for (int i = 0; i < 10; i++) {

            cardsStorage.add(new NumericCard(i,'g'));
        }

        for (int i = 0; i < 10; i++) {

            cardsStorage.add(new NumericCard(i,'b'));
        }


        for (int i = 1; i < 10; i++) {

            cardsStorage.add(new NumericCard(i,'r'));
        }

        for (int i = 1; i < 10; i++) {

            cardsStorage.add(new NumericCard(i,'y'));
        }

        for (int i = 1; i < 10; i++) {

            cardsStorage.add(new NumericCard(i,'g'));
        }

        for (int i = 1; i < 10; i++) {

            cardsStorage.add(new NumericCard(i,'b'));
        }


        cardsStorage.add(new Skip('r'));
        cardsStorage.add(new Skip('r'));
        cardsStorage.add(new Skip('y'));
        cardsStorage.add(new Skip('y'));
        cardsStorage.add(new Skip('g'));
        cardsStorage.add(new Skip('g'));
        cardsStorage.add(new Skip('b'));
        cardsStorage.add(new Skip('b'));

        cardsStorage.add(new Reverse('r'));
        cardsStorage.add(new Reverse('r'));
        cardsStorage.add(new Reverse('y'));
        cardsStorage.add(new Reverse('y'));
        cardsStorage.add(new Reverse('g'));
        cardsStorage.add(new Reverse('g'));
        cardsStorage.add(new Reverse('b'));
        cardsStorage.add(new Reverse('b'));

        cardsStorage.add(new Draw('r'));
        cardsStorage.add(new Draw('r'));
        cardsStorage.add(new Draw('y'));
        cardsStorage.add(new Draw('y'));
        cardsStorage.add(new Draw('g'));
        cardsStorage.add(new Draw('g'));
        cardsStorage.add(new Draw('b'));
        cardsStorage.add(new Draw('b'));

        for (int i = 0; i < 4; i++) {

            cardsStorage.add(new WildColor());
        }

        for (int i = 0; i < 4; i++) {

            cardsStorage.add(new WildDraw());
        }
    }

    /**
     * getting storage cards list.
     * @return CardsStorage list field
     */
    public ArrayList<Card> getCardsStorage() {
        return cardsStorage;
    }

    /**
     * getting the players list.
     * @return players list field
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * It determined game orientation.
     * @return if orientation was clockwise will return true.
     */
    public boolean isOrientation() {
        return orientation;
    }

    /**
     * changing the Orientation.
     * if orientation was clockwise it will change it to anti clockwise.
     */
    public void changeOrientation() {
        orientation = !isOrientation();
    }

    /**
     * getting the player by index.
     * @param index players index
     * @return player that was asked
     */
    public Player getPlayer(int index) {
        return players.get(index);
    }

    /**
     * getting the center card.
     * @return centerCard field
     */
    public Card getCenterCard() {
        return centerCard;
    }

    /**
     * setting the center card.
     * @param centerCard centerCard field
     */
    public void setCenterCard(Card centerCard) {
        this.centerCard = centerCard;
    }

    /**
     * in the beginning of the game after asking how many players we want.
     * and determining the first center card.
     * @param n players number
     */
    public void addPlayers(int n){

        Random rand = new Random();
        int num;

        for (int i = 0; i < n; i++) {

            Player player = new Player();
            players.add(player);

            for (int j = 0; j < 7; j++) {

                num = rand.nextInt(cardsStorage.size());
                player.addCard(cardsStorage.get(num));
                cardsStorage.remove(num);
            }
        }

        int index;
        do{
            index = rand.nextInt(cardsStorage.size());
            centerCard = cardsStorage.get(index);

        }while ( cardsStorage.get(index) instanceof WildDraw || cardsStorage.get(index) instanceof WildColor );

        cardsStorage.remove(index);
    }

    /**
     * @param currentPlayer it will tell the current player.
     * @return will return the next player
     */
    public Player nextPlayer(Player currentPlayer){

        int currentIndex = players.indexOf(currentPlayer);

        if(orientation){

            if(currentIndex == players.size()-1)
                return players.get(0);

            return players.get(currentIndex+1);
        } else {

            if (currentIndex == 0)
                return players.get(players.size()-1);

            return players.get(currentIndex-1);
        }
    }

    /**
     * It will draw cards after wild draw or draw card.
     * @param index players index
     * @param n how many to draw
     */
    public void draw(int index, int n){
        Random rand = new Random();

        for (int i = 0; i < n; i++) {

            int indexRandCard = rand.nextInt( getCardsStorage().size() );
            getPlayer(index).addCard( getCardsStorage().get(indexRandCard) );
            getCardsStorage().remove(indexRandCard);
        }
    }

    /**
     * determining the cards that the player can play currently.
     * @param player current player
     * @return It will return a list of cards that the player can play
     */
    public ArrayList<Card> cardsTOPlay(Player player){

        ArrayList<Card> allowedCards = new ArrayList<Card>();

        for (Card card : player.getCards()){

            if( card instanceof WildColor )
                allowedCards.add(card);

            else if (card.getCOLOR() == centerCard.getCOLOR())
                allowedCards.add(card);

            else if (centerCard instanceof NumericCard && card instanceof NumericCard){

                if ( ((NumericCard) centerCard).getNUMBER() == ((NumericCard) card).getNUMBER() )
                    allowedCards.add(card);

            } else if( (centerCard instanceof Draw && card instanceof Draw) || (centerCard instanceof Reverse && card instanceof Reverse) || (centerCard instanceof Skip && card instanceof Skip) ){
                allowedCards.add(card);

            } else if (card instanceof WildDraw)
                allowedCards.add(card);
        }

        return allowedCards;
    }

    /**
     * It will check that chosen card by the player is valid to play or not.
     * @param allowedCards cards that player can play
     * @param card chosen card
     * @return if it's valid to play will return true
     */
    public boolean isAllowed( ArrayList<Card> allowedCards, Card card){

        for (Card tempCard : allowedCards){

            if (tempCard instanceof NumericCard && card instanceof NumericCard){
                if  ( ( (NumericCard)tempCard ).equals(card) )
                    return true;

            }

            else if( tempCard instanceof WildColor && card instanceof WildColor ){
                if (( (WildColor)tempCard ).equals(card))
                    return true;
            }

            else if ( tempCard instanceof Draw && card instanceof Draw){
                if (( (Draw)tempCard ).equals(card))
                    return true;
            }

            else if (tempCard instanceof Reverse && card instanceof Reverse) {
                if (( (Reverse)tempCard ).equals(card))
                    return true;
            }
            else if ( tempCard instanceof Skip && card instanceof Skip) {
                if (( (Skip)tempCard ).equals(card))
                    return true;
            }
            else if (tempCard instanceof WildDraw && card instanceof WildDraw){
                if (( (WildDraw)tempCard ).equals(card))
                    return true;
            }
        }

        return false;
    }

    /**
     * calculating each players Points.
     */
    public void calculatePoints(){

        for (Player player : players){

            for (Card card : player.getCards()){
                player.addPoints( card.getPoint() );
            }
        }
    }
}
