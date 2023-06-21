package GameHandler;

import GameObjects.PlayerData;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The menu page and setting page and game starting page.
 *
 * @author Negar Karami
 * @since 2020-07-26
 * @version 0.0
 */
public class Menu {

    private RoundRectangle2D playVsComputer;
    private RoundRectangle2D setting;
    private RoundRectangle2D multiPlayer;
    private RoundRectangle2D exitAccount;

    private RoundRectangle2D okButton;
    private RoundRectangle2D backButton;

    private RoundRectangle2D tankLifeField;
    private RoundRectangle2D shotPowerField;
    private RoundRectangle2D wallLifeField;

    private StringBuilder tankLife;
    private StringBuilder shotPower;
    private StringBuilder wallLife;

    private String newTankLife;
    private String newWallLife;
    private String newShotPower;

    private boolean tankLifeClicked;
    private boolean shotPowerClicked;
    private boolean wallLifeClicked;

    //Player Data
    private PlayerData player;
    private RoundRectangle2D playerPlayingTime;
    private RoundRectangle2D playerName;
    private RoundRectangle2D playerWinsVsComputer;
    private RoundRectangle2D playerLossesVsComputer;
    private RoundRectangle2D playerWinsVsMultiPlayer;
    private RoundRectangle2D playerLossesVsMultiPlayer;

    //private ArrayList<String> playersName;

    private BufferedImage image;
    private GameLoop.MouseHandler mouse;
    private GameLoop.KeyHandler key;

    private int buttonWidth;
    private int buttonHeight;

    private enum Mode{
        main,
        setting,
        startGame;
    }
    private Mode mode;

    private RoundRectangle2D tankLifeField2;
    private RoundRectangle2D shotPowerField2;
    private RoundRectangle2D wallLifeField2;

    private RoundRectangle2D okButton2;
    private RoundRectangle2D backButton2;

    /**
     * creating the menu and its objects
     * @param mouse mouse handler
     * @param key key handler
     */
    public Menu(GameLoop.MouseHandler mouse, GameLoop.KeyHandler key){
        this.mouse = mouse;
        this.key = key;

        image = Main.inputImage("images\\menu1.jpg");

        mode = Mode.main;

        buttonHeight = 50;
        buttonWidth = 300;

        tankLife = new StringBuilder(String.valueOf(GameState.getTankLife()));
        wallLife = new StringBuilder(String.valueOf(GameState.getWallLife()));
        shotPower = new StringBuilder(String.valueOf(GameState.getShotPower()));

        newTankLife = "";
        newWallLife = "";
        newShotPower = "";

        tankLifeClicked = false;
        wallLifeClicked = false;
        shotPowerClicked = false;

        playVsComputer = new RoundRectangle2D.Float(StartingPage.GAME_WIDTH/2 - buttonWidth/2, StartingPage.GAME_HEIGHT/2 - 20, buttonWidth, buttonHeight, 50, 50);
        multiPlayer = new RoundRectangle2D.Float(StartingPage.GAME_WIDTH/2 - buttonWidth/2, StartingPage.GAME_HEIGHT/2 + 60, buttonWidth, buttonHeight,50,50);
        setting = new RoundRectangle2D.Float(StartingPage.GAME_WIDTH/2 - buttonWidth/2, StartingPage.GAME_HEIGHT/2 + 140, buttonWidth, buttonHeight,50,50);
        exitAccount = new RoundRectangle2D.Float(StartingPage.GAME_WIDTH/2 - buttonWidth/2, StartingPage.GAME_HEIGHT/2 + 220, buttonWidth, buttonHeight,50,50);

        backButton = new RoundRectangle2D.Float(20, StartingPage.GAME_HEIGHT - 55, buttonWidth/4, 3*buttonHeight/4, 50,50);
        okButton = new RoundRectangle2D.Float(StartingPage.GAME_WIDTH - buttonWidth/4 - 20, StartingPage.GAME_HEIGHT - 55, buttonWidth/4, 3*buttonHeight/4, 50,50);

        tankLifeField = new RoundRectangle2D.Float(StartingPage.GAME_WIDTH - buttonWidth/3 - 50, 160 + 80, buttonWidth/3, buttonHeight, 20, 20);
        wallLifeField = new RoundRectangle2D.Float(StartingPage.GAME_WIDTH - buttonWidth/3 - 50, 160 + 150, buttonWidth/3, buttonHeight, 20, 20);
        shotPowerField = new RoundRectangle2D.Float(StartingPage.GAME_WIDTH - buttonWidth/3 - 50, 160 + 220, buttonWidth/3, buttonHeight, 20, 20);

        tankLifeField2 = new RoundRectangle2D.Float(StartingPage.GAME_WIDTH/2 - buttonWidth/4, 300, buttonWidth/2, buttonHeight, 20, 20);
        wallLifeField2 = new RoundRectangle2D.Float(StartingPage.GAME_WIDTH/2 - buttonWidth/4, 400, buttonWidth/2, buttonHeight, 20, 20);
        shotPowerField2 = new RoundRectangle2D.Float(StartingPage.GAME_WIDTH/2 - buttonWidth/4, 500, buttonWidth/2, buttonHeight, 20, 20);

        backButton2 = new RoundRectangle2D.Float(StartingPage.GAME_WIDTH/2 - 200 - buttonWidth/8, 600, buttonWidth/4, 3*buttonHeight/4, 50,50);
        okButton2 = new RoundRectangle2D.Float(StartingPage.GAME_WIDTH/2 + 200 - buttonWidth/8, 600, buttonWidth/4, 3*buttonHeight/4, 50,50);

        playerName = new RoundRectangle2D.Float(250, 160 + 80, buttonWidth/2, buttonHeight, 20, 20);
        playerPlayingTime = new RoundRectangle2D.Float(250, 160 + 150, buttonWidth/2, buttonHeight, 20, 20);
        playerWinsVsComputer = new RoundRectangle2D.Float(250, 160 + 220, buttonWidth/2, buttonHeight, 20, 20);
        playerLossesVsComputer = new RoundRectangle2D.Float(250, 160 + 290, buttonWidth/2, buttonHeight, 20, 20);
        playerWinsVsMultiPlayer = new RoundRectangle2D.Float(250, 160 + 360, buttonWidth/2, buttonHeight, 20, 20);
        playerLossesVsMultiPlayer = new RoundRectangle2D.Float(250, 160 + 430, buttonWidth/2, buttonHeight, 20, 20);

        //playersName = new ArrayList<>();
    }

    /**
     * getting the the login player data and
     * creating one its a new player.
     */
    public void init(){
        //TODO: fix it
        boolean notThere = true;
        for (String name : GameLoop.getPlayersList().keySet()){
            if (LoginPage.getName().equals(name)){
                player = GameLoop.getPlayersList().get(name);
                notThere = false;
                break;
            }
        }
        if (notThere){
            player = new PlayerData(LoginPage.getName());
            ConcurrentHashMap<String, PlayerData> list = GameLoop.getPlayersList();
            list.put(player.getName(), player);
            System.out.println("new Data   " + player.getName());
        }
    }

    /**
     * updating the menu page
     */
    public void update(){
        int x = mouse.getX();
        int y = mouse.getY();

        if ((x >= playVsComputer.getX() && x <= playVsComputer.getX() + playVsComputer.getWidth()) && (y >= playVsComputer.getY() && y <= playVsComputer.getY() + playVsComputer.getHeight())){
           if(mode == Mode.main)
               mode = Mode.startGame;

        } else if ((x >= setting.getX() && x <= setting.getX() + setting.getWidth()) && (y >= setting.getY() && y <= setting.getY() + setting.getHeight())){
            if (mode == Mode.main)
                mode = Mode.setting;

        } else if ((x >= okButton.getX() && x <= okButton.getX() + okButton.getWidth()) && (y >= okButton.getY() && y <= okButton.getY() + okButton.getHeight())){
            if (mode == Mode.setting){
                GameState.setTankLife(Integer.parseInt(tankLife.toString()));
                GameState.setWallLife(Integer.parseInt(wallLife.toString()));
                GameState.setShotPower(Integer.parseInt(shotPower.toString()));

                mode = Mode.main;
            }

        } else if ((x >= backButton.getX() && x <= backButton.getX() + backButton.getWidth()) && (y >= backButton.getY() && y <= backButton.getY() + backButton.getHeight())){
            if (mode == Mode.setting)
                mode = Mode.main;

        } else if ((x >= multiPlayer.getX() && x <= multiPlayer.getX() + multiPlayer.getWidth()) && (y >= multiPlayer.getY() && y <= multiPlayer.getY() + multiPlayer.getHeight())){
            //TODO: multiPlayer
        } else if ((x >= exitAccount.getX() && x <= exitAccount.getX() + exitAccount.getWidth()) && (y >= exitAccount.getY() && y <= exitAccount.getY() + exitAccount.getHeight())){
            if (mode == Mode.main)
                GameLoop.setSTATE(State.START);

        } else if (((x >= tankLifeField.getX() && x <= tankLifeField.getX() + tankLifeField.getWidth()) && (y >= tankLifeField.getY() && y <= tankLifeField.getY() + tankLifeField.getHeight())) || ((x >= tankLifeField2.getX() && x <= tankLifeField2.getX() + tankLifeField2.getWidth()) && (y >= tankLifeField2.getY() && y <= tankLifeField2.getY() + tankLifeField2.getHeight()))){
            if (mode == Mode.setting || mode == Mode.startGame){
                newTankLife = key.getNewString();
                newWallLife = "";
                newShotPower = "";

                tankLifeClicked = true;
                wallLifeClicked = false;
                shotPowerClicked = false;
            }

        } else if (((x >= wallLifeField.getX() && x <= wallLifeField.getX() + wallLifeField.getWidth()) && (y >= wallLifeField.getY() && y <= wallLifeField.getY() + wallLifeField.getHeight())) || (x >= wallLifeField2.getX() && x <= wallLifeField2.getX() + wallLifeField2.getWidth()) && (y >= wallLifeField2.getY() && y <= wallLifeField2.getY() + wallLifeField2.getHeight())){
            if (mode == Mode.setting || mode == Mode.startGame) {
                newTankLife = "";
                newWallLife = key.getNewString();
                newShotPower = "";

                tankLifeClicked = false;
                wallLifeClicked = true;
                shotPowerClicked = false;
            }

        } else if (((x >= shotPowerField.getX() && x <= shotPowerField.getX() + shotPowerField.getWidth()) && (y >= shotPowerField.getY() && y <= shotPowerField.getY() + shotPowerField.getHeight())) || ((x >= shotPowerField2.getX() && x <= shotPowerField2.getX() + shotPowerField2.getWidth()) && (y >= shotPowerField2.getY() && y <= shotPowerField2.getY() + shotPowerField2.getHeight()))){
            if (mode == Mode.setting || mode == Mode.startGame) {
                newTankLife = "";
                newWallLife = "";
                newShotPower = key.getNewString();

                tankLifeClicked = false;
                wallLifeClicked = false;
                shotPowerClicked = true;
            }
        } else if ((x >= okButton2.getX() && x <= okButton2.getX() + okButton2.getWidth()) && (y >= okButton2.getY() && y <= okButton2.getY() + okButton2.getHeight())){
            if (mode == Mode.startGame){
                GameState.setTankLife(Integer.parseInt(tankLife.toString()));
                GameState.setWallLife(Integer.parseInt(wallLife.toString()));
                GameState.setShotPower(Integer.parseInt(shotPower.toString()));

                GameLoop.setSTATE(State.GAME);
                mouse.setX(0);
                mouse.setY(0);
                mode = Mode.main;
            }

        } else if ((x >= backButton2.getX() && x <= backButton2.getX() + backButton2.getWidth()) && (y >= backButton2.getY() && y <= backButton2.getY() + backButton2.getHeight())){
            if (mode == Mode.startGame)
                mode = Mode.main;

        }
    }

    /**
     * rendering the menu page.
     * @param g the frame Graphics
     */
    public void rendering(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, StartingPage.GAME_WIDTH, StartingPage.GAME_HEIGHT);
        g2d.drawImage(image, 0,Main.y0,null);
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(16.0f));

        if (mode == Mode.main){
            //Play VS Computer
            g2d.setColor(Color.WHITE);
            g2d.fill(playVsComputer);

            String str1 = "Play VS Computer";
            g2d.setColor(Color.BLACK);
            int strWidth = g2d.getFontMetrics().stringWidth(str1);
            g2d.drawString(str1, (StartingPage.GAME_WIDTH - strWidth) / 2, StartingPage.GAME_HEIGHT/2 + 30 - 20);

            //MultiPlayer
            g2d.setColor(Color.WHITE);
            g2d.fill(multiPlayer);

            String str3 = "MultiPlayer";
            g2d.setColor(Color.BLACK);
            strWidth = g2d.getFontMetrics().stringWidth(str3);
            g2d.drawString(str3, (StartingPage.GAME_WIDTH - strWidth) / 2, StartingPage.GAME_HEIGHT/2 + 60 + 30);

            //Setting
            g2d.setColor(Color.WHITE);
            g2d.fill(setting);

            String str2 = "Setting";
            g2d.setColor(Color.BLACK);
            strWidth = g2d.getFontMetrics().stringWidth(str2);
            g2d.drawString(str2, (StartingPage.GAME_WIDTH - strWidth) / 2, StartingPage.GAME_HEIGHT/2 + 140 + 30);

            //exit Account
            g2d.setColor(Color.WHITE);
            g2d.fill(exitAccount);

            String str4 = "Exit Account";
            g2d.setColor(Color.BLACK);
            strWidth = g2d.getFontMetrics().stringWidth(str4);
            g2d.drawString(str4, (StartingPage.GAME_WIDTH - strWidth) / 2, StartingPage.GAME_HEIGHT/2 + 220 + 30);
        } else if (mode == Mode.setting){
            //tank life
            g2d.setColor(Color.WHITE);
            g2d.fill(tankLifeField);

            String str1 = "Tank Life:  ";
            g2d.setColor(Color.WHITE);
            int strWidth = g2d.getFontMetrics().stringWidth(str1);
            g2d.drawString(str1, (int)tankLifeField.getX() - strWidth, (int)tankLifeField.getY() + 30);

            strWidth = g2d.getFontMetrics().stringWidth(tankLife.toString() + newTankLife);
            if (strWidth > buttonWidth/3){
                String str = "too many Tank Life characters";
                g2d.setColor(Color.WHITE);
                strWidth = g2d.getFontMetrics().stringWidth(str);
                g2d.drawString(str, StartingPage.GAME_WIDTH/2 - strWidth/2, StartingPage.GAME_HEIGHT - 55);
            } else
                tankLife.append(newTankLife);

            g2d.setColor(Color.BLACK);
            g2d.drawString(tankLife.toString(), (int)tankLifeField.getX() + (int)tankLifeField.getWidth()/2 - strWidth/2, (int)tankLifeField.getY() + 30);

            //wall life
            g2d.setColor(Color.WHITE);
            g2d.fill(wallLifeField);

            String str2 = "Wall Life:  ";
            g2d.setColor(Color.WHITE);
            strWidth = g2d.getFontMetrics().stringWidth(str2);
            g2d.drawString(str2, (int)wallLifeField.getX() - strWidth, (int)wallLifeField.getY() + 30);

            strWidth = g2d.getFontMetrics().stringWidth(wallLife.toString() + newWallLife);
            if (strWidth > buttonWidth/3){
                String str = "too many Wall Life characters";
                g2d.setColor(Color.WHITE);
                strWidth = g2d.getFontMetrics().stringWidth(str);
                g2d.drawString(str, StartingPage.GAME_WIDTH/2 - strWidth/2, StartingPage.GAME_HEIGHT - 55);
            } else
                wallLife.append(newWallLife);

            g2d.setColor(Color.BLACK);
            g2d.drawString(wallLife.toString(), (int)wallLifeField.getX() + (int)wallLifeField.getWidth()/2 - strWidth/2, (int)wallLifeField.getY() + 30);

            //shot power
            g2d.setColor(Color.WHITE);
            g2d.fill(shotPowerField);

            String str3 = "Shot Power:  ";
            g2d.setColor(Color.WHITE);
            strWidth = g2d.getFontMetrics().stringWidth(str3);
            g2d.drawString(str3, (int)shotPowerField.getX() - strWidth, (int)shotPowerField.getY() + 30);

            strWidth = g2d.getFontMetrics().stringWidth(shotPower.toString() + newShotPower);
            if (strWidth > buttonWidth/3){
                String str = "too many Shot Power characters";
                g2d.setColor(Color.WHITE);
                strWidth = g2d.getFontMetrics().stringWidth(str);
                g2d.drawString(str, StartingPage.GAME_WIDTH/2 - strWidth/2, StartingPage.GAME_HEIGHT - 55);
            } else
                shotPower.append(newShotPower);

            g2d.setColor(Color.BLACK);
            g2d.drawString(shotPower.toString(), (int)shotPowerField.getX() + (int)shotPowerField.getWidth()/2 - strWidth/2, (int)shotPowerField.getY() + 30);

            //Player.........................................
            //player name
            g2d.setColor(Color.WHITE);
            g2d.fill(playerName);

            String str4 = "player Name:  ";
            strWidth = g2d.getFontMetrics().stringWidth(str4);
            g2d.drawString(str4, (int)playerName.getX() - strWidth, (int)playerName.getY() + 30);

            String str5 = player.getName();
            g2d.setColor(Color.BLACK);
            strWidth = g2d.getFontMetrics().stringWidth(str5);
            g2d.drawString(str5, (int)playerName.getX() + (int)playerName.getWidth()/2 - strWidth/2, (int)playerName.getY() + 30);

            //player Playing Time
            g2d.setColor(Color.WHITE);
            g2d.fill(playerPlayingTime);

            String str6 = "player Playing Time:  ";
            strWidth = g2d.getFontMetrics().stringWidth(str6);
            g2d.drawString(str6, (int)playerPlayingTime.getX() - strWidth, (int)playerPlayingTime.getY() + 30);

            String str7 = String.valueOf(player.getTotalTime());
            g2d.setColor(Color.BLACK);
            strWidth = g2d.getFontMetrics().stringWidth(str7);
            g2d.drawString(str7, (int)playerPlayingTime.getX() + (int)playerPlayingTime.getWidth()/2 - strWidth/2, (int)playerPlayingTime.getY() + 30);

            //player Wins Vs Computer
            g2d.setColor(Color.WHITE);
            g2d.fill(playerWinsVsComputer);

            String str8 = "player Wins VS Computer:  ";
            strWidth = g2d.getFontMetrics().stringWidth(str8);
            g2d.drawString(str8, (int)playerWinsVsComputer.getX() - strWidth, (int)playerWinsVsComputer.getY() + 30);

            String str9 = String.valueOf(player.getPlayerWinsVsComputer());
            g2d.setColor(Color.BLACK);
            strWidth = g2d.getFontMetrics().stringWidth(str9);
            g2d.drawString(str9, (int)playerWinsVsComputer.getX() + (int)playerWinsVsComputer.getWidth()/2 - strWidth/2, (int)playerWinsVsComputer.getY() + 30);

            //player Losses Vs Computer
            g2d.setColor(Color.WHITE);
            g2d.fill(playerLossesVsComputer);

            String str10 = "player Losses VS Computer:  ";
            strWidth = g2d.getFontMetrics().stringWidth(str10);
            g2d.drawString(str10, (int)playerLossesVsComputer.getX() - strWidth, (int)playerLossesVsComputer.getY() + 30);

            String str11 = String.valueOf(player.getPlayerLossesVsComputer());
            g2d.setColor(Color.BLACK);
            strWidth = g2d.getFontMetrics().stringWidth(str11);
            g2d.drawString(str11, (int)playerLossesVsComputer.getX() + (int)playerLossesVsComputer.getWidth()/2 - strWidth/2, (int)playerLossesVsComputer.getY() + 30);

            //player Wins Vs MultiPlayer
            g2d.setColor(Color.WHITE);
            g2d.fill(playerWinsVsMultiPlayer);

            String str12 = "player Wins VS MultiPlayer:  ";
            strWidth = g2d.getFontMetrics().stringWidth(str12);
            g2d.drawString(str12, (int)playerWinsVsMultiPlayer.getX() - strWidth, (int)playerWinsVsMultiPlayer.getY() + 30);

            String str13 = String.valueOf(player.getPlayerWinsVsMultiPlayer());
            g2d.setColor(Color.BLACK);
            strWidth = g2d.getFontMetrics().stringWidth(str13);
            g2d.drawString(str13, (int)playerWinsVsMultiPlayer.getX() + (int)playerWinsVsMultiPlayer.getWidth()/2 - strWidth/2, (int)playerWinsVsMultiPlayer.getY() + 30);

            //player Losses Vs MultiPlayer
            g2d.setColor(Color.WHITE);
            g2d.fill(playerLossesVsMultiPlayer);

            String str14 = "player Losses VS MultiPlayer:  ";
            strWidth = g2d.getFontMetrics().stringWidth(str14);
            g2d.drawString(str14, (int)playerLossesVsMultiPlayer.getX() - strWidth, (int)playerLossesVsMultiPlayer.getY() + 30);

            String str15 = String.valueOf(player.getPlayerLossesVsMultiPlayer());
            g2d.setColor(Color.BLACK);
            strWidth = g2d.getFontMetrics().stringWidth(str15);
            g2d.drawString(str15, (int)playerLossesVsMultiPlayer.getX() + (int)playerLossesVsMultiPlayer.getWidth()/2 - strWidth/2, (int)playerLossesVsMultiPlayer.getY() + 30);

            //player Tank
            g2d.drawImage(player.getPlayerTank() , (int)playerName.getX()+ (int)playerName.getWidth() + 30, (int)playerName.getY(),null);

            //ok button
            g2d.setColor(Color.WHITE);
            g2d.fill(okButton);

            String str16 = "OK";
            g2d.setColor(Color.BLACK);
            strWidth = g2d.getFontMetrics().stringWidth(str16);
            g2d.drawString(str16, (int)okButton.getX() + (int)okButton.getWidth()/2 - strWidth/2, (int)okButton.getY() + 25);

            //back button
            g2d.setColor(Color.WHITE);
            g2d.fill(backButton);

            String str17 = "Back";
            g2d.setColor(Color.BLACK);
            strWidth = g2d.getFontMetrics().stringWidth(str17);
            g2d.drawString(str17, (int)backButton.getX() + (int)backButton.getWidth()/2 - strWidth/2, (int)backButton.getY() + 25);
        } else if (mode == Mode.startGame){

            //tank life
            g2d.setColor(Color.WHITE);
            g2d.fill(tankLifeField2);

            String str1 = "Tank Life:  ";
            g2d.setColor(Color.WHITE);
            int strWidth = g2d.getFontMetrics().stringWidth(str1);
            g2d.drawString(str1, (int)tankLifeField2.getX() - strWidth, (int)tankLifeField2.getY() + 30);

            strWidth = g2d.getFontMetrics().stringWidth(tankLife.toString() + newTankLife);
            if (strWidth > buttonWidth/3){
                String str = "too many Tank Life characters";
                g2d.setColor(Color.WHITE);
                strWidth = g2d.getFontMetrics().stringWidth(str);
                g2d.drawString(str, StartingPage.GAME_WIDTH/2 - strWidth/2, StartingPage.GAME_HEIGHT - 55);
            } else
                tankLife.append(newTankLife);

            g2d.setColor(Color.BLACK);
            g2d.drawString(tankLife.toString(), (int)tankLifeField2.getX() + (int)tankLifeField2.getWidth()/2 - strWidth/2, (int)tankLifeField2.getY() + 30);

            //wall life
            g2d.setColor(Color.WHITE);
            g2d.fill(wallLifeField2);

            String str2 = "Wall Life:  ";
            g2d.setColor(Color.WHITE);
            strWidth = g2d.getFontMetrics().stringWidth(str2);
            g2d.drawString(str2, (int)wallLifeField2.getX() - strWidth, (int)wallLifeField2.getY() + 30);

            strWidth = g2d.getFontMetrics().stringWidth(wallLife.toString() + newWallLife);
            if (strWidth > buttonWidth/3){
                String str = "too many Wall Life characters";
                g2d.setColor(Color.WHITE);
                strWidth = g2d.getFontMetrics().stringWidth(str);
                g2d.drawString(str, StartingPage.GAME_WIDTH/2 - strWidth/2, StartingPage.GAME_HEIGHT - 55);
            } else
                wallLife.append(newWallLife);

            g2d.setColor(Color.BLACK);
            g2d.drawString(wallLife.toString(), (int)wallLifeField2.getX() + (int)wallLifeField2.getWidth()/2 - strWidth/2, (int)wallLifeField2.getY() + 30);

            //shot power
            g2d.setColor(Color.WHITE);
            g2d.fill(shotPowerField2);

            String str3 = "Shot Power:  ";
            g2d.setColor(Color.WHITE);
            strWidth = g2d.getFontMetrics().stringWidth(str3);
            g2d.drawString(str3, (int)shotPowerField2.getX() - strWidth, (int)shotPowerField2.getY() + 30);

            strWidth = g2d.getFontMetrics().stringWidth(shotPower.toString() + newShotPower);
            if (strWidth > buttonWidth/3){
                String str = "too many Shot Power characters";
                g2d.setColor(Color.WHITE);
                strWidth = g2d.getFontMetrics().stringWidth(str);
                g2d.drawString(str, StartingPage.GAME_WIDTH/2 - strWidth/2, StartingPage.GAME_HEIGHT - 55);
            } else
                shotPower.append(newShotPower);

            g2d.setColor(Color.BLACK);
            g2d.drawString(shotPower.toString(), (int)shotPowerField2.getX() + (int)shotPowerField2.getWidth()/2 - strWidth/2, (int)shotPowerField2.getY() + 30);

            //ok button
            g2d.setColor(Color.WHITE);
            g2d.fill(okButton2);

            String str16 = "OK";
            g2d.setColor(Color.BLACK);
            strWidth = g2d.getFontMetrics().stringWidth(str16);
            g2d.drawString(str16, (int)okButton2.getX() + (int)okButton2.getWidth()/2 - strWidth/2, (int)okButton2.getY() + 25);

            //back button
            g2d.setColor(Color.WHITE);
            g2d.fill(backButton2);

            String str17 = "Back";
            g2d.setColor(Color.BLACK);
            strWidth = g2d.getFontMetrics().stringWidth(str17);
            g2d.drawString(str17, (int)backButton2.getX() + (int)backButton2.getWidth()/2 - strWidth/2, (int)backButton2.getY() + 25);
        }

        g2d.dispose();
    }

//    public void addPlayer(String name){
//        playersName.add(name);
//    }

    /**
     * the backspace
     */
    public void delete(){
        if (tankLifeClicked){
            int i = tankLife.length();
            if (i != 0)
                tankLife.deleteCharAt(i-1);

        } else if (wallLifeClicked){
            int i = wallLife.length();
            if (i != 0)
                wallLife.deleteCharAt(i-1);

        } else if (shotPowerClicked){
            int i = shotPower.length();
            if (i != 0)
                shotPower.deleteCharAt(i-1);
        }
    }
}
