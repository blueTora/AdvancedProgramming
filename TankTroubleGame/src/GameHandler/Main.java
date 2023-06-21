package GameHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Program start.
 *
 * @author Negar Karami
 * @since 2020-07-26
 * @version 0.0
 */
public class Main {

    public static int blockSize;
    public static int y0 = 30;
    public static CopyOnWriteArrayList<String> mapAddresses;
    public static CopyOnWriteArrayList<String> playersAddress;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ThreadPool.init();

        //TODO: Show the game menu ...


        int min = 64;
        int max = 128;
        blockSize = max;

        mapAddresses = new CopyOnWriteArrayList<>();

        mapAddresses.add("maps\\map1.txt");
        mapAddresses.add("maps\\map2.txt");

        playersAddress = new CopyOnWriteArrayList<>();
        playersAddress.add("images\\tank\\tank_red.png");
        playersAddress.add("images\\tank\\tank_blue.png");
        playersAddress.add("images\\tank\\tank_dark.png");
        playersAddress.add("images\\tank\\tank_green.png");
        playersAddress.add("images\\tank\\tank_red.png");
        playersAddress.add("images\\tank\\tank_sand.png");

        // After the player clicks 'PLAY' ...
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                int[][] inputMap = Main.readMap();
                int x = (inputMap[0].length - 1)/2 * Main.blockSize;
                int y = (inputMap.length - 1)/2* Main.blockSize + Main.y0;

                GameFrame frame = new GameFrame("Tank Trouble", x, y);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.initBufferStrategy();

                GameLoop game = new GameLoop(frame);
                game.init();
                ThreadPool.execute(game);
            }
        });
    }

    /**
     * an nxn array for game map
     * @return the input map for the game
     */
    protected static int[][] readMap(){
        int[][] inputMap = null;

        ArrayList<String> lines = new ArrayList<>();

        Random rand = new Random();
        int i = rand.nextInt(mapAddresses.size());
        String address = mapAddresses.get(i);

        try {
            FileReader r = new FileReader(address);
            BufferedReader in = new BufferedReader(r);

            String temp;
            while ((temp = in.readLine()) != null){
                lines.add(temp);
            }

            in.close();
            r.close();

            int x = lines.size();
            int y = lines.get(0).length();
            inputMap = new int[x][y];

            for (int j = 0; j < x; j++) {
                String str = lines.get(j);

                for (int k = 0; k < y ; k++) {
                    inputMap[j][k] = Integer.parseInt(String.valueOf(str.charAt(k)));
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputMap;
    }

    /**
     * reading image and returning it
     * @param address the image address
     * @return image buffer
     */
    public static BufferedImage inputImage(String address){
        BufferedImage image = null;
        try{
            image = ImageIO.read(new File(address));
        }
        catch(IOException e){
            System.out.println(e);
        }
        return image;
    }

    /**
     * reading the registered accounts name and password
     * @return login info
     */
    protected static ConcurrentHashMap<String, String> readInfo(){
        ConcurrentHashMap<String, String> info = new ConcurrentHashMap<>();
        String address = "loginInfo.bin";

        try (FileInputStream r = new FileInputStream(address)){
            ObjectInputStream in = new ObjectInputStream(r);

            info = (ConcurrentHashMap<String, String>)in.readObject();
            in.close();

        } catch (FileNotFoundException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return info;
    }

    /**
     * saving the registered accounts name and password
     * @param info login info
     */
    protected static void saveInfo(ConcurrentHashMap<String, String> info){
        String address = "loginInfo.bin";

        try (FileOutputStream w = new FileOutputStream(address)){
            ObjectOutputStream out = new ObjectOutputStream(w);

            out.writeObject(info);
            out.close();

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
