package GameHandler;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The login page.
 *
 * @author Negar Karami
 * @since 2020-07-26
 * @version 0.0
 */
public class LoginPage {

    private RoundRectangle2D nameField;
    private RoundRectangle2D passwordField;
    private RoundRectangle2D okButton;
    private RoundRectangle2D backButton;

    private BufferedImage image;
    private GameLoop.MouseHandler mouse;
    private GameLoop.KeyHandler key;

    private static StringBuilder name;
    private StringBuilder pass;
    private String newNameText;
    private String newPassText;

    private int buttonWidth;
    private int buttonHeight;
    private boolean wrongData;
    private boolean nameClicked;
    private boolean passClicked;

    private boolean rememberMe;
    private Rectangle checkBox;

    /**
     * creating the login page
     * @param mouse mouse handler
     * @param key key handler
     */
    public LoginPage(GameLoop.MouseHandler mouse, GameLoop.KeyHandler key){
        this.mouse = mouse;
        this.key = key;

        image = Main.inputImage("images\\menu1.jpg");
        buttonHeight = 50;
        buttonWidth = 300;
        wrongData = false;
        nameClicked = false;
        passClicked = false;
        rememberMe = false;

        name = new StringBuilder();
        pass = new StringBuilder();

        String in = readLogin();
        if (in != null){
            String[] inputs = in.split(" ");
            name.append(inputs[0]);
            pass.append(inputs[1]);
            rememberMe = true;
        }

        newPassText = "";
        newNameText = "";

        nameField = new RoundRectangle2D.Float(StartingPage.GAME_WIDTH/2 - buttonWidth/2, StartingPage.GAME_HEIGHT/2 + 20, buttonWidth, buttonHeight,20,20);
        passwordField = new RoundRectangle2D.Float(StartingPage.GAME_WIDTH/2 - buttonWidth/2, StartingPage.GAME_HEIGHT/2 + 100, buttonWidth, buttonHeight,20,20);

        backButton = new RoundRectangle2D.Float(StartingPage.GAME_WIDTH/2 - buttonWidth/8 - 150, StartingPage.GAME_HEIGHT/2 + 80 + buttonHeight + 50 + 50, buttonWidth/4, 3*buttonHeight/4, 50,50);
        okButton = new RoundRectangle2D.Float(StartingPage.GAME_WIDTH/2 - buttonWidth/8 + 150, StartingPage.GAME_HEIGHT/2 + 80 + buttonHeight + 50 + 50, buttonWidth/4, 3*buttonHeight/4, 50,50);

        checkBox = new Rectangle(StartingPage.GAME_WIDTH/2 + 40, StartingPage.GAME_HEIGHT/2 + 80 + buttonHeight + 50, 16,16);
    }

    /**
     * updating the login page.
     */
    public void update(){
        int x = mouse.getX();
        int y = mouse.getY();

        if ((x >= nameField.getX() && x <= nameField.getX() + nameField.getWidth()) && (y >= nameField.getY() && y <= nameField.getY() + nameField.getHeight())){

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
            boolean check = false;
            ConcurrentHashMap<String, String> loginInfo = Main.readInfo();

            for (String n : loginInfo.keySet()){
                if(n.equals(name.toString())){
                    if (pass.toString().equals( loginInfo.get(n) )){
                        check = true;
                    }
                }
            }

            if (check){
                GameLoop.setSTATE(State.MENU);

                if (rememberMe)
                    saveLogin(name.toString() + " " + pass.toString());
                else
                    saveLogin("");
            } else
                wrongData = true;
        } else if ((x >= backButton.getX() && x <= backButton.getX() + backButton.getWidth()) && (y >= backButton.getY() && y <= backButton.getY() + backButton.getHeight())){
            GameLoop.setSTATE(State.START);

        } else if ((x >= checkBox.getX() && x <= checkBox.getX() + checkBox.getWidth()) && (y >= checkBox.getY() && y <= checkBox.getY() + checkBox.getHeight())){
            rememberMe = !rememberMe;
            mouse.setY(0);
            mouse.setX(0);
        }
    }

    /**
     * rendering the login page
     * @param g frame Graphics
     */
    public void rendering(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, StartingPage.GAME_WIDTH, StartingPage.GAME_HEIGHT);
        g2d.drawImage(image, 0,Main.y0,null);
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(16.0f));

        String s = "Login";
        g2d.setColor(Color.WHITE);
        int strWidth = g2d.getFontMetrics().stringWidth(s);
        g2d.drawString(s, (StartingPage.GAME_WIDTH - strWidth) / 2, StartingPage.GAME_HEIGHT/2 - 10);

        //Name:
        g2d.setColor(Color.WHITE);
        g2d.fill(nameField);

        String str1 = "Name: ";
        g2d.setColor(Color.WHITE);
        strWidth = g2d.getFontMetrics().stringWidth(str1);
        g2d.drawString(str1, StartingPage.GAME_WIDTH/2 - buttonWidth/2 - strWidth - 20, StartingPage.GAME_HEIGHT/2 + 20 + 30);

        strWidth = g2d.getFontMetrics().stringWidth(name.toString() + newNameText);
        if (strWidth > buttonWidth){
            String str = "too many Password characters";
            g2d.setColor(Color.WHITE);
            strWidth = g2d.getFontMetrics().stringWidth(str);
            g2d.drawString(str, StartingPage.GAME_WIDTH/2 - strWidth/2, StartingPage.GAME_HEIGHT - 70);
        } else
            name.append(newNameText);

        g2d.setColor(Color.BLACK);
        g2d.drawString(name.toString(), StartingPage.GAME_WIDTH/2 - buttonWidth/2, StartingPage.GAME_HEIGHT/2 + 20 + 30);


        //Password:
        g2d.setColor(Color.WHITE);
        g2d.fill(passwordField);

        String str2 = "Password: ";
        g2d.setColor(Color.WHITE);
        strWidth = g2d.getFontMetrics().stringWidth(str2);
        g2d.drawString(str2, StartingPage.GAME_WIDTH/2 - buttonWidth/2 - strWidth - 20, StartingPage.GAME_HEIGHT/2 + 100 + 30);

        strWidth = g2d.getFontMetrics().stringWidth(pass.toString() + newPassText);
        if (strWidth > buttonWidth){
            String str = "too many Name characters";
            g2d.setColor(Color.WHITE);
            strWidth = g2d.getFontMetrics().stringWidth(str);
            g2d.drawString(str, StartingPage.GAME_WIDTH/2 - strWidth/2, StartingPage.GAME_HEIGHT - 50);
        } else
            pass.append(newPassText);

        g2d.setColor(Color.BLACK);
        g2d.drawString(pass.toString(), StartingPage.GAME_WIDTH/2 - buttonWidth/2, StartingPage.GAME_HEIGHT/2 + 100 + 30);

        //OK Buttons
        g2d.setColor(Color.WHITE);
        g2d.fill(okButton);

        String str3 = "OK";
        g2d.setColor(Color.BLACK);
        strWidth = g2d.getFontMetrics().stringWidth(str3);
        g2d.drawString(str3, (int)okButton.getX() + (int)okButton.getWidth()/2 - strWidth/2, (int)okButton.getY() + 25);

        //Back Button
        g2d.setColor(Color.WHITE);
        g2d.fill(backButton);

        String str4 = "Back";
        g2d.setColor(Color.BLACK);
        strWidth = g2d.getFontMetrics().stringWidth(str4);
        g2d.drawString(str4, (int)backButton.getX() + (int)backButton.getWidth()/2 - strWidth/2, (int)backButton.getY() + 25);

        //remember me
        String str5 = "Remember Me  ";
        g2d.setColor(Color.WHITE);
        strWidth = g2d.getFontMetrics().stringWidth(str5);
        g2d.drawString(str5, (int)checkBox.getX() - strWidth, (int)checkBox.getY() + 13);

        if (rememberMe)
            g2d.setColor(Color.RED);
        else
            g2d.setColor(Color.WHITE);

        g2d.fill(checkBox);

        if (wrongData){
            String str = "Name or Password is wrong.\nThere is'nt such an Account.";
            g2d.setColor(Color.WHITE);
            strWidth = g2d.getFontMetrics().stringWidth(str);
            g2d.drawString(str, StartingPage.GAME_WIDTH/2 - strWidth/2, StartingPage.GAME_HEIGHT - 50);
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

    /**
     * the name of player who entered
     * @return the name
     */
    public static String getName() {
        return name.toString();
    }

    /**
     * saving the login info if the remember me was true
     * @param str thw name and pass info
     */
    private void saveLogin(String str){
        String address = "LastLogin.txt";

        try (FileOutputStream w = new FileOutputStream(address)){
            OutputStreamWriter out = new OutputStreamWriter(w);

            out.write(str);
            out.close();

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * reading the preview login info if the remember me was true
     * @return the name and pass info
     */
    private String readLogin(){
        String address = "LastLogin.txt";
        String str = "";

        try {
            FileReader r = new FileReader(address);
            BufferedReader in = new BufferedReader(r);

            str = in.readLine();

            in.close();
            r.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str;
    }
}
