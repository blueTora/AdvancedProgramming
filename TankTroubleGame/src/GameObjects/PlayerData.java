package GameObjects;

import GameHandler.Main;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Player Playing data
 *
 * @author Negar Karami
 * @since 2020-07-26
 * @version 0.0
 */
public class PlayerData implements Serializable {

    private long totalTime;
    private String name;
    private int playerWinsVsComputer;
    private int playerLossesVsComputer;
    private int playerWinsVsMultiPlayer;
    private int playerLossesVsMultiPlayer;
    private String imageAddress;

    /**
     * creating the player account
     * @param name player name
     */
    public PlayerData(String name) {
        this.name = name;

        imageAddress = Main.playersAddress.get( 0 );
        Main.playersAddress.remove(0);

        totalTime = 0;
        playerLossesVsComputer = 0;
        playerLossesVsMultiPlayer = 0;
        playerWinsVsComputer = 0;
        playerWinsVsMultiPlayer = 0;
    }

    public long getTotalTime() {
        return totalTime;
    }

    /**
     * adding the playing time to totL PLAYING TIME
     * @param time the current playing time
     */
    public void updateTotalTime(long time) {
        totalTime += time;
    }

    public String getName() {
        return name;
    }

    public int getPlayerWinsVsComputer() {
        return playerWinsVsComputer;
    }

    /**
     * adding the win and lose conclusion
     */
    public void updatePlayerWinsVsComputer() {
        playerWinsVsComputer++;
    }

    public int getPlayerLossesVsComputer() {
        return playerLossesVsComputer;
    }

    public void updatePlayerLossesVsComputer() {
        playerLossesVsComputer++;
    }

    public int getPlayerWinsVsMultiPlayer() {
        return playerWinsVsMultiPlayer;
    }

    public void updatePlayerWinsVsMultiPlayer() {
        playerWinsVsMultiPlayer++;
    }

    public int getPlayerLossesVsMultiPlayer() {
        return playerLossesVsMultiPlayer;
    }

    public void updatePlayerLossesVsMultiPlayer() {
        playerLossesVsMultiPlayer++;
    }

    public BufferedImage getPlayerTank() {
        return Main.inputImage(imageAddress);
    }

    public String getImageAddress() {
        return imageAddress;
    }
}
