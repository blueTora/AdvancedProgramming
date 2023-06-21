package GameObjects;

import GameHandler.GameFrame;
import GameHandler.GameState;
import GameHandler.Main;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * the game Map
 *
 * @author Negar Karami
 * @since 2020-07-26
 * @version 0.0
 */
public class Map extends GameObject{

    private int[][] map;
    private static BufferedImage[] mapBlocks;
    //0 - passage
    //1 - destructible wall
    //2 - indestructible wall
    private int xx, yy;

    /**
     * Creating the map
     */
    public Map(){
        super(0, Main.y0, ObjectId.map);
        map = GameState.getInputMap();

        mapBlocks = new BufferedImage[12];

        mapBlocks[0] = inputImage("images\\map\\grass.png");
        mapBlocks[1] = inputImage("images\\map\\roadCornerLL.png");
        mapBlocks[2] = inputImage("images\\map\\roadCornerLR.png");
        mapBlocks[3] = inputImage("images\\map\\roadCornerUL.png");
        mapBlocks[4] = inputImage("images\\map\\roadCornerUR.png");
        mapBlocks[5] = inputImage("images\\map\\roadCrossing.png");
        mapBlocks[6] = inputImage("images\\map\\roadEast.png");
        mapBlocks[7] = inputImage("images\\map\\roadNorth.png");
        mapBlocks[8] = inputImage("images\\map\\roadSplitE.png");
        mapBlocks[9] = inputImage("images\\map\\roadSplitN.png");
        mapBlocks[10] = inputImage("images\\map\\roadSplitS.png");
        mapBlocks[11] = inputImage("images\\map\\roadSplitW.png");

        setHeight(mapBlocks[0].getHeight());
        setWidth(mapBlocks[0].getHeight());
    }

    @Override
    public void rendering(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        drawDefaultMap(g2d);
        drawMapSides(g2d);
        g2d.dispose();
    }

    /**
     * drawing the basic and default map
     * @param g2d the map graphics
     */
    private void drawDefaultMap(Graphics2D g2d){
        map = GameState.getInputMap();
        xx = (map[0].length - 1)/2;
        yy = (map.length - 1)/2;

        int block = getHeight();
        int w = GameFrame.GAME_WIDTH;
        int h = GameFrame.GAME_HEIGHT;
        int y0 = Main.y0;

        g2d.drawImage(mapBlocks[2],0,y0,null);

        for (int j = 1; j < xx - 1 ; j++) {
            g2d.drawImage(mapBlocks[10], block * j, y0,null);
        }

        g2d.drawImage(mapBlocks[1],w - block,y0,null);

        for (int i = 1; i < yy - 1 ; i++) {

            g2d.drawImage(mapBlocks[8], 0, block * i + y0,null);

            for (int j = 1; j < xx - 1 ; j++) {
                g2d.drawImage(mapBlocks[5], block * j, block * i + y0,null);
            }

            g2d.drawImage(mapBlocks[11], w - block, block * i + y0,null);
        }

        g2d.drawImage(mapBlocks[4], 0, h - block,null);

        for (int j = 1; j < xx - 1 ; j++) {
            g2d.drawImage(mapBlocks[9], block * j, h - block,null);
        }

        g2d.drawImage(mapBlocks[3], w - block, h - block,null);
    }

    /**
     * drawing the side map blocks
     * @param g2d the map graphics
     */
    private void drawMapSides(Graphics2D g2d){
       int block = getHeight();
       int y0 = Main.y0;
       int[] jj = {0, map[0].length - 1};
       int[] kk = {0, map.length - 1};

       //horizontal
       int i = 1;
       for (int j : jj){
           if (map[i][j] == 0)
               g2d.drawImage(mapBlocks[10], (j-1)/2 * block, (i-1)/2 * block + y0,null);
       }

       for (i += 2; i < map.length - 1; i+=2) {

           for (int j : jj){
               if (map[i][j] == 0)
               g2d.drawImage(mapBlocks[5], (j-1)/2 * block, (i-1)/2 * block + y0,null);
           }
       }

       i-=2;
       for (int j : jj){
           if (map[i][j] == 0)
               g2d.drawImage(mapBlocks[9], (j-1)/2 * block, (i-1)/2 * block + y0,null);
       }

       //vertical
       int j = 1;
       for (int k : kk){
           if (map[k][j] == 0)
               g2d.drawImage(mapBlocks[8], (j-1)/2 * block, (k-1)/2 * block + y0,null);
       }

       for (j += 2; j < map.length - 1; j+=2) {

           for (int k : kk){
               if (map[k][j] == 0)
                   g2d.drawImage(mapBlocks[5], (j-1)/2 * block, (k-1)/2 * block + y0,null);
           }
       }

       j-=2;
       for (int k : kk){
           if (map[k][j] == 0)
               g2d.drawImage(mapBlocks[11], (j-1)/2 * block, (k-1)/2 * block + y0,null);
       }

       int[][] corners = {{0 , 1 , 1 , 0} , {0 , map[0].length - 2 , 1 , map[0].length - 1} , {map.length - 2 , 0 , map.length - 1 , 1} , {map.length - 2 , map[0].length - 2 , map.length - 1 , map[0].length - 1}};

       for (int[] temp : corners){
           if (map[temp[0]][temp[1]] == 0 && map[temp[2]][temp[3]] == 0)
               g2d.drawImage(mapBlocks[5], (temp[3]-1)/2 * block, (temp[2]-1)/2 * block + y0 ,null);
       }
   }

    @Override
    public void update() {
    }
}
