package GameHandler;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The starting page and new account page.
 *
 * @author Negar Karami
 * @since 2020-07-26
 * @version 0.0
 */
public class StartingPage {

    public static final int GAME_HEIGHT = 700;
    public static final int GAME_WIDTH = 900;

    private RoundRectangle2D makeNewAccountButton;
    private RoundRectangle2D loginButton;
    private RoundRectangle2D okButton;
    private RoundRectangle2D nameField;
    private RoundRectangle2D passwordField;
    private RoundRectangle2D backButton;

    private int buttonWidth;
    private int buttonHeight;

    private StringBuilder name;
    private StringBuilder pass;
    private String newNameText;
    private String newPassText;

    private BufferedImage image;

    private GameLoop.MouseHandler mouse;
    private GameLoop.KeyHandler key;

    private boolean newAccount;
    private boolean nameClicked;
    private boolean passClicked;

    /**
     * Creating the starting page
     * @param mouse mouse handler
     * @param key key handler
     */
    public StartingPage(GameLoop.MouseHandler mouse, GameLoop.KeyHandler key){
        this.mouse = mouse;
        this.key = key;

        image = Main.inputImage("images\\menu1.jpg");
        newAccount= false;
        nameClicked = false;
        passClicked = false;
        buttonHeight = 50;
        buttonWidth = 300;

        makeNewAccountButton = new RoundRectangle2D.Float(GAME_WIDTH/2 - buttonWidth/2, GAME_HEIGHT/2 + 20, buttonWidth, buttonHeight, 50, 50);
        loginButton = new RoundRectangle2D.Float(GAME_WIDTH/2 - buttonWidth/2, GAME_HEIGHT/2 + 100, buttonWidth, buttonHeight,50,50);

        nameField = new RoundRectangle2D.Float(GAME_WIDTH/2 - buttonWidth/2, GAME_HEIGHT/2 + 20, buttonWidth, buttonHeight,20,20);
        passwordField = new RoundRectangle2D.Float(GAME_WIDTH/2 - buttonWidth/2, GAME_HEIGHT/2 + 100, buttonWidth, buttonHeight,20,20);

        name = new StringBuilder();
        pass = new StringBuilder();
        newPassText = "";
        newNameText = "";

        backButton = new RoundRectangle2D.Float(GAME_WIDTH/2 - buttonWidth/8 - 150, GAME_HEIGHT/2 + 100 + buttonHeight + 50, buttonWidth/4, 3*buttonHeight/4, 50,50);
        okButton = new RoundRectangle2D.Float(GAME_WIDTH/2 - buttonWidth/8 + 150, GAME_HEIGHT/2 + 100 + buttonHeight + 50, buttonWidth/4, 3*buttonHeight/4, 50,50);
    }

    /**
     * updating the starting page and new account
     */
    public void update(){
        int x = mouse.getX();
        int y = mouse.getY();

        if (!newAccount){
            if ((x >= makeNewAccountButton.getX() && x <= makeNewAccountButton.getX() + makeNewAccountButton.getWidth()) && (y >= makeNewAccountButton.getY() && y <= makeNewAccountButton.getY() + makeNewAccountButton.getHeight())){
                newAccount = true;
            } else if ((x >= loginButton.getX() && x <= loginButton.getX() + loginButton.getWidth()) && (y >= loginButton.getY() && y <= loginButton.getY() + loginButton.getHeight())){
                GameLoop.setSTATE(State.LOGIN);
            }
        } else {

            if ((x >= nameField.getX() && x <= nameField.getX() + nameField.getWidth()) && (y >= nameField.getY() && y <= nameField.getY() + nameField.getHeight())) {

                newNameText = key.getNewString();
                newPassText = "";
                nameClicked = true;
                passClicked = false;

            } else if ((x >= passwordField.getX() && x <= passwordField.getX() + passwordField.getWidth()) && (y >= passwordField.getY() && y <= passwordField.getY() + passwordField.getHeight())){

                newPassText = key.getNewString();
                newNameText = "";
                nameClicked = false;
                passClicked = true;

            } else if ((x >= okButton.getX() && x <= okButton.getX() + okButton.getWidth()) && (y >= okButton.getY() && y <= okButton.getY() + okButton.getHeight())){
                // TODO: Remember me check box - name = "";
                ConcurrentHashMap<String, String> loginInfo = Main.readInfo();
                loginInfo.put(name.toString(), pass.toString());
                Main.saveInfo(loginInfo);

                newAccount = false;
            } else if ((x >= backButton.getX() && x <= backButton.getX() + backButton.getWidth()) && (y >= backButton.getY() && y <= backButton.getY() + backButton.getHeight())){
                newAccount = false;
            }
        }
    }

    /**
     * rendering the starting page and new account
     * @param g the frame Graphics
     */
    public void rendering(Graphics g){

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        g2d.drawImage(image, 0,Main.y0,null);
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(16.0f));

        if (!newAccount){
            g2d.setColor(Color.WHITE);
            g2d.fill(makeNewAccountButton);

            String str1 = "New Account";
            g2d.setColor(Color.BLACK);
            int strWidth = g2d.getFontMetrics().stringWidth(str1);
            g2d.drawString(str1, (GAME_WIDTH - strWidth) / 2, GAME_HEIGHT/2 + 20 + 30);

            g2d.setColor(Color.WHITE);
            g2d.fill(loginButton);

            String str2 = "Login";
            g2d.setColor(Color.BLACK);
            strWidth = g2d.getFontMetrics().stringWidth(str2);
            g2d.drawString(str2, (GAME_WIDTH - strWidth) / 2, GAME_HEIGHT/2 + 100 + 30);

        } else {
            String s = "New Account";
            g2d.setColor(Color.WHITE);
            int strWidth = g2d.getFontMetrics().stringWidth(s);
            g2d.drawString(s, (GAME_WIDTH - strWidth) / 2, GAME_HEIGHT/2 - 10);

            //Name:
            g2d.setColor(Color.WHITE);
            g2d.fill(nameField);

            String str1 = "Name: ";
            g2d.setColor(Color.WHITE);
            strWidth = g2d.getFontMetrics().stringWidth(str1);
            g2d.drawString(str1, GAME_WIDTH/2 - buttonWidth/2 - strWidth - 20, GAME_HEIGHT/2 + 20 + 30);

            strWidth = g2d.getFontMetrics().stringWidth(name.toString() + newNameText);
            if (strWidth > buttonWidth){
                String str = "too many Password characters";
                g2d.setColor(Color.WHITE);
                strWidth = g2d.getFontMetrics().stringWidth(str);
                g2d.drawString(str, GAME_WIDTH/2 - strWidth/2, GAME_HEIGHT - 70);
            } else
                name.append(newNameText);

            g2d.setColor(Color.BLACK);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(16.0f));
            g2d.drawString(name.toString(), GAME_WIDTH/2 - buttonWidth/2, GAME_HEIGHT/2 + 20 + 30);


            //Password:
            g2d.setColor(Color.WHITE);
            g2d.fill(passwordField);

            String str2 = "Password: ";
            g2d.setColor(Color.WHITE);
            strWidth = g2d.getFontMetrics().stringWidth(str2);
            g2d.drawString(str2, GAME_WIDTH/2 - buttonWidth/2 - strWidth - 20, GAME_HEIGHT/2 + 100 + 30);

            strWidth = g2d.getFontMetrics().stringWidth(pass.toString() + newPassText);
            if (strWidth > buttonWidth){
                String str = "too many Name characters";
                g2d.setColor(Color.WHITE);
                strWidth = g2d.getFontMetrics().stringWidth(str);
                g2d.drawString(str, GAME_WIDTH/2 - strWidth/2, GAME_HEIGHT - 50);
            } else
                pass.append(newPassText);

            g2d.setColor(Color.BLACK);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(16.0f));
            g2d.drawString(pass.toString(), GAME_WIDTH/2 - buttonWidth/2, GAME_HEIGHT/2 + 100 + 30);

            //OK Buttons
            g2d.setColor(Color.WHITE);
            g2d.fill(okButton);

            String str3 = "OK";
            g2d.setColor(Color.BLACK);
            strWidth = g2d.getFontMetrics().stringWidth(str3);
            g2d.drawString(str3, GAME_WIDTH/2 + 150 - strWidth/2, GAME_HEIGHT/2 + 100 + buttonHeight + 50 + 25);

            //Back Button
            g2d.setColor(Color.WHITE);
            g2d.fill(backButton);

            String str4 = "Back";
            g2d.setColor(Color.BLACK);
            strWidth = g2d.getFontMetrics().stringWidth(str4);
            g2d.drawString(str4, GAME_WIDTH/2 - 150 - strWidth/2, GAME_HEIGHT/2 + 100 + buttonHeight + 50 + 25);
        }

        g2d.dispose();
    }

    /**
     * the backspace
     */
    public void delete(){
        if (nameClicked){
            int i = name.length();
            if (i != 0)
                name.deleteCharAt(i-1);

        } else if (passClicked){
            int i = pass.length();
            if (i != 0)
                pass.deleteCharAt(i-1);
        }
    }
}
