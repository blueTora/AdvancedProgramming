import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * <h1>UNO</h1>
 *
 * <b>Main</b>
 *
 * This Program is a multi player card game.
 * User will be Player 1 and other players would be computer.
 * This game can have 3 or 4 or 5 player.
 * After each move from computer there is a few second pause
 * so you can see the move before and after computer plays.
 * we have 108 cards in total and each player will get 7 card in the beginning.
 * each player who finishes it's cards first will win and after that other players points will be showed.
 *
 * @author Negar
 * @since 2020-04-18
 * @version 0.0
 */
public class Main {

    /**
     * we have the process of the game here.
     * we will get player inputs in here.
     * @param args args Unused.
     */
    public static void main(String[] args) {

        //starting the game.
        Scanner scan = new Scanner(System.in);

        DisplayGame game = new DisplayGame();
        game.startGame();

        System.out.println("How many players?\t(3 or 4 or 5)");
        int playerNum = scan.nextInt();

        while (playerNum < 3 || playerNum > 5) {

            System.out.println("You can only have 3 or 4 or 5 players.\nTry again!");
            playerNum = scan.nextInt();
        }

        game.addPlayers(playerNum);

        System.out.println("\n« You are Player 1 »\n");

        Random rand = new Random();
        int randNum = rand.nextInt(playerNum);

        Player player = game.getPlayer(randNum);
        game.display(player);
        Player prePlayer = player;

        System.out.println("Player " + player.getName() + " Turn : \n");

        if (game.getCenterCard() instanceof Skip){

            System.out.println("Player " + player.getName() + " Turn was Skipped.\n");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            player = game.nextPlayer(player);
            game.display(player);
            System.out.println("Player " + player.getName() + " Turn : \n");
        }

        if (game.getCenterCard() instanceof Reverse){

            System.out.println("\n« Anti Clock Wise Orientation »\n");
            game.changeOrientation();
            System.out.println("Orientation was Reversed.");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        int drawNum = 0;
        boolean draw = true;

        //beginning of the game.
        while (prePlayer.getCards().size() != 0) {

            ArrayList<Card> allowedCards = game.cardsTOPlay(player);
            Card chosenCard;


            //checking for draw and wild draw moves.
            if ( (game.getCenterCard() instanceof WildDraw || game.getCenterCard() instanceof Draw) && draw) {

                if (game.getCenterCard() instanceof WildDraw) {

                    if (player.getName() == '1') {

                        int temp = 0;
                        for (Card card : player.getCards()) {
                            if (card instanceof WildDraw){
                                chosenCard = card;

                                ((WildDraw) chosenCard).changeColor(player.getName(),game.getCenterCard().getCOLOR());

                                game.getCardsStorage().add( game.getCenterCard() );
                                game.setCenterCard(chosenCard);
                                player.remove(chosenCard);

                                temp++;
                                break;
                            }
                        }
                        drawNum += 4;

                        if (temp == 0) {

                            int playerIndex = game.getPlayers().indexOf(player);
                            game.draw(playerIndex, drawNum);
                            drawNum = 0;
                            game.display(player);
                            try {
                                Thread.sleep(6000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            draw = false;
                        }
                    } else {

                        int temp = 0;
                        for (Card card : allowedCards) {
                            if (card instanceof WildDraw)
                                temp++;
                        }
                        drawNum += 4;

                        if (temp != 0) {

                            int n = player.getCards().size();
                            int cardNum = choseCard(n);
                            chosenCard = player.getCards().get(cardNum - 1);

                            while (!(chosenCard instanceof WildDraw)) {
                                System.out.println("You should Chose Your Wild Draw card.\nTry Again!");
                                cardNum = choseCard(n);
                                chosenCard = player.getCards().get(cardNum - 1);
                            }

                            ((WildDraw) chosenCard).changeColor(player.getName(),game.getCenterCard().getCOLOR());

                            game.getCardsStorage().add( game.getCenterCard() );
                            game.setCenterCard(chosenCard);
                            player.remove(chosenCard);

                        } else {

                            int playerIndex = game.getPlayers().indexOf(player);
                            game.draw(playerIndex, drawNum);
                            drawNum = 0;
                            game.display(player);
                            try {
                                Thread.sleep(6000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            draw = false;
                        }
                    }
                }

                if (game.getCenterCard() instanceof Draw) {

                    if (player.getName() == '1'){

                        int temp = 0;
                        for (Card card : allowedCards) {
                            if (card instanceof Draw)
                                temp++;
                        }
                        drawNum += 2;

                        if (temp != 0) {

                            int n = player.getCards().size();
                            int cardNum = choseCard(n);
                            chosenCard = player.getCards().get(cardNum - 1);

                            while (!(chosenCard instanceof Draw)) {
                                System.out.println("You should Chose Your Draw card.\nTry Again!");
                                cardNum = choseCard(n);
                                chosenCard = player.getCards().get(cardNum - 1);
                            }

                            game.getCardsStorage().add(game.getCenterCard());
                            game.setCenterCard(chosenCard);
                            player.remove(chosenCard);

                        } else {

                            int playerIndex = game.getPlayers().indexOf(player);
                            game.draw(playerIndex, drawNum);
                            drawNum = 0;
                            game.display(player);
                            try {
                                Thread.sleep(6000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            draw = false;
                        }
                    } else {

                        int temp = 0;
                        for (Card card : player.getCards()) {
                            if (card instanceof Draw){
                                chosenCard = card;

                                game.getCardsStorage().add(game.getCenterCard());
                                game.setCenterCard(chosenCard);
                                player.remove(chosenCard);

                                temp++;
                                break;
                            }
                        }
                        drawNum += 2;

                        if (temp == 0) {

                            int playerIndex = game.getPlayers().indexOf(player);
                            game.draw(playerIndex, drawNum);
                            drawNum = 0;
                            game.display(player);
                            try {
                                Thread.sleep(6000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            draw = false;
                        }
                    }
                }

                prePlayer = player;
                player = game.nextPlayer(player);
                game.display(player);
                System.out.println("\nPlayer " + player.getName() + " Turn : \n");
                if (player.getName() != '1'){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                continue;
            }

            draw = true;

            //checking if we can have move or not.
                if (allowedCards.size() > 0) {
                    int cardNum;
                    int n = player.getCards().size();

                    int temp = 0;
                    for (Card card : allowedCards) {
                        if (card instanceof WildDraw)
                            temp++;
                    }

                    if(player.getName() == '1'){

                        cardNum = choseCard(n);
                        chosenCard = player.getCards().get(cardNum - 1);

                        while (chosenCard instanceof WildDraw && temp != allowedCards.size()) {

                            System.out.println("Not allowed to use wild draw.\nYou have other options.\nTry Again!");
                            cardNum = choseCard(n);
                            chosenCard = player.getCards().get(cardNum - 1);
                        }

                    } else {
                        do {
                            cardNum = rand.nextInt(player.getCards().size());
                            chosenCard = player.getCards().get(cardNum);

                        }while (!game.isAllowed(allowedCards, chosenCard));

                        while (chosenCard instanceof WildDraw && temp != allowedCards.size()) {

                            do {
                                cardNum = rand.nextInt(player.getCards().size());
                                chosenCard = player.getCards().get(cardNum);

                            }while (!game.isAllowed(allowedCards, chosenCard));
                        }

                    }

                } else {

                    System.out.println("You don't have any option.\nYou must take card from storage cards.");
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    int index = rand.nextInt(game.getCardsStorage().size());
                    Card randCard = game.getCardsStorage().get(index);

                    player.addCard(randCard);
                    game.getCardsStorage().remove(randCard);
                    game.display(player);

                    allowedCards = game.cardsTOPlay(player);

                    if (game.isAllowed(allowedCards, randCard)){

                        chosenCard = randCard;
                        System.out.println("Now you can use the new card you toke from storage.");
                        try {
                            Thread.sleep(6000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    } else {

                        System.out.println("You don't have any move.\nYour Turn is Passed.");
                        try {
                            Thread.sleep(6000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        prePlayer = player;
                        player = game.nextPlayer(player);
                        game.display(player);
                        System.out.println("\nPlayer " + player.getName() + " Turn : \n");
                        continue;
                    }
                }

                //checking if our move is valid or not and making the move.
                if (game.isAllowed(allowedCards, chosenCard)) {

                    if (!(chosenCard instanceof NumericCard)) {

                        if (chosenCard instanceof Reverse){
                            game.changeOrientation();
                            System.out.println("Orientation was Reversed.");
                            try {
                                Thread.sleep(4000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        else if (chosenCard instanceof Skip){

                            game.getCardsStorage().add( game.getCenterCard() );
                            game.setCenterCard(chosenCard);
                            player.remove(chosenCard);
                            prePlayer = player;
                            player = game.nextPlayer(player);
                            System.out.println("\nPlayer " + player.getName() + " Turn : \n");
                            System.out.println("Player " + player.getName() + " Turn was Skipped.\n");
                            try {
                                Thread.sleep(4000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            player = game.nextPlayer(player);
                            game.display(player);
                            System.out.println("\nPlayer " + player.getName() + " Turn : \n");

                            continue;
                        }

                        else if (chosenCard instanceof WildColor)
                            ((WildColor) chosenCard).changeColor(player.getName(),game.getCenterCard().getCOLOR());

                        else if (chosenCard instanceof WildDraw)
                            ((WildDraw) chosenCard).changeColor(player.getName(),game.getCenterCard().getCOLOR());

                    }

                } else {
                    System.out.println("Wrong Move!\nYou can't use this card.\nTry Again!");
                    continue;
                }

                //end of a player turn.
            if (player.getName() != '1'){

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

                game.getCardsStorage().add( game.getCenterCard() );
                game.setCenterCard(chosenCard);
                player.remove(chosenCard);

            if (player.getName() != '1'){

                System.out.println("Player " + player.getName() + " made it's move.");
                game.display(player);

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

                prePlayer = player;
                player = game.nextPlayer(player);
                game.display(player);
                System.out.println("\nPlayer " + player.getName() + " Turn : \n");
        }


        //game ending.
        System.out.println("Game Over!");
        System.out.println("Player " + prePlayer.getName() + "is Winner!");

        System.out.println("Results :");
        game.calculatePoints();

        PlayerComparator comparator = new PlayerComparator();
        game.getPlayers().sort(comparator);

        for (Player tempPlayer : game.getPlayers()) {
            System.out.println(tempPlayer.toString());
        }
    }

    /**
     * It will get the card number and see if its valid or not.
     * @param n number of player cards
     * @return cards index
     */
    public static int choseCard (int n){

        Scanner scan = new Scanner(System.in);

        System.out.println("Chose your card\nWrite it's Number :");
        int cardNum = scan.nextInt();

        while (cardNum > n || cardNum < 1) {

            System.out.println("Wrong card number.\nTry again!");
            cardNum = scan.nextInt();
        }

        return cardNum;
    }
}