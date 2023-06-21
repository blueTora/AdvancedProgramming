package GameHandler;

import GameObjects.*;
import GameObjects.Map;

import java.awt.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class holds the state of the game and all of its elements.
 * This class also handles user inputs, which affect the game state.
 * 
 * @author Negar Karami
 * @since 2020-07-26
 * @version 0.0
 */
public class GameState {

	private boolean gameOver;
	private long giftTime;

	private static int shotPower;
	private static int tankLife;
	private static int wallLife;

	private static int tankSpeed = 6;
	private static int shotSpeed = 16;

	private static CopyOnWriteArrayList<GameObject> objects;
	private static Player currentPlayer ;
	private CopyOnWriteArrayList<Player> players;

	private static int[][] inputMap = null;

	private int temp = 0;

	/**
	 * Creating the game frame and managing it
	 */
	public GameState() {
		gameOver = false;

		objects = new CopyOnWriteArrayList<GameObject>();
		players = new CopyOnWriteArrayList<>();

		//Default Values
		shotPower = 1;
		tankLife = 10;
		wallLife = 5;

		inputMap = Main.readMap();
		GameFrame.setWinner("");
	}

	/**
	 * adding the game objects
	 */
	public void addGameObjects(){
		Map m = new Map();
		addObject(m);
		drawMapSideWalls();
		drawMapWalls();
		setMapBlocks();

		Block b1 = new Block(0, Main.y0);
		b1.setWidth(GameFrame.GAME_WIDTH);
		b1.setHeight(30);
		addObject(b1);
		Block b2 = new Block(0, GameFrame.GAME_HEIGHT - 30);
		b2.setWidth(GameFrame.GAME_WIDTH);
		b2.setHeight(30);
		addObject(b2);
		Block b3 = new Block(0, Main.y0);
		b3.setWidth(30);
		b3.setHeight(GameFrame.GAME_HEIGHT);
		addObject(b3);
		Block b4 = new Block(GameFrame.GAME_WIDTH - 30, Main.y0);
		b4.setWidth(30);
		b4.setHeight(GameFrame.GAME_HEIGHT);
		addObject(b4);

		for (int i = 0; i < 5 ; i++) {
			addObject(makeNewGift());
		}
		giftTime = System.currentTimeMillis();

		boolean notThere = true;
		Player player1 = null;
		for (String name : GameLoop.getPlayersList().keySet()){
			if(name.equals("Computer")){

				PlayerData data = GameLoop.getPlayersList().get(name);

				int xy[] = addNewPlayer();
				player1 = new Player(xy[0], xy[1], data.getName());
				player1.setImage(data.getImageAddress());
				player1.setData(data);

				notThere = false;
				System.out.println("read old " + data.getName());
			}
		}
		if (notThere){
			int xy[] = addNewPlayer();
			player1 = new Player(xy[0], xy[1], "Computer");

			PlayerData data = new PlayerData(player1.getName());
			player1.setImage(data.getImageAddress());
			player1.setData(data);

			ConcurrentHashMap<String, PlayerData> list =  GameLoop.getPlayersList();
			list.put(player1.getName(), data);
		}
		player1.setComputer(true);
		addObject(player1);
		players.add(player1);

		Player player2 = null;
		for (String name : GameLoop.getPlayersList().keySet()){
			if(name.equals(LoginPage.getName())){

				PlayerData data = GameLoop.getPlayersList().get(LoginPage.getName());

				int xy[] = addNewPlayer();
				player2 = new Player(xy[0], xy[1], data.getName());
				player2.setImage(data.getImageAddress());
				player2.setData(data);

				notThere = false;
				System.out.println("read old " + data.getName());
			}
		}
		addObject(player2);
		players.add(player2);
		currentPlayer = player2;
	}

	/**
	 * The method which updates the game state.
	 */
	public void update() {
		long start = 0;
		if (!gameOver){
			Iterator it = objects.iterator();
			while (it.hasNext())
			{
				GameObject item = (GameObject) it.next();
				item.update();

				if (item.getObjectId() == ObjectId.shot){
					if (((Shot)item).isDestroyed()){
						removeObject(item);
					} else if (item.getTime()/1000 > 4){
						removeObject(item);
					} else if (((Shot)item).getId() == ShotId.laserShot && item.getTime()/1000 > 3)
						removeObject(item);

				} else if (item.getObjectId() == ObjectId.player){
					if (((Player) item).getLife() <= 0){
						addObject(new Explosion(item.getX(), item.getY()));
						removeObject(item);
					}
				} else if (item.getObjectId() == ObjectId.destructibleWall){
					if (((DestructibleWall) item).getLife() <= 0){
						removeObject(item);
						DestructibleWall wall = (DestructibleWall) item;
						int block = Main.blockSize;
						int i = 2 * (wall.getY()-Main.y0)/block + 1;
						int j = 2 * wall.getX()/block + 1;
						inputMap[i][j] = 0;
					}

				} else if (item.getObjectId() == ObjectId.explosion){
					if (item.getTime()/1000 >= 1)
						removeObject(item);

				} else if (item.getObjectId() == ObjectId.gift){
					if (((Gifts) item).isUsed()){
						removeObject(item);
					}
				}
			}

			//TODO: fix making new gifts position under the player
			long t = System.currentTimeMillis() - giftTime;
			if (t/1000 > 60){
				addObject(makeNewGift());
				giftTime = System.currentTimeMillis();
			}

			Iterator p = players.iterator();
			while (p.hasNext()) {
				Player player = (Player) p.next();

				if (player.getLife() <= 0){
					player.setWin(false);

					if (temp == 0){
						start = System.currentTimeMillis();
						temp++;
					}

					if (System.currentTimeMillis() - start > 1500)
						gameOver = true;
				}
			}
		}
	}

	/**
	 * rendering the the game objects
	 * @param g the frame Graphics
	 */
	public void rendering(Graphics g){
		Iterator it = objects.iterator();
		while (it.hasNext()){
			GameObject item = (GameObject) it.next();
			item.rendering(g);
		}

		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(Color.cyan);
		g2d.fillRect(0, GameFrame.GAME_HEIGHT, GameFrame.bellowWindowWidth, GameFrame.bellowWindowHeight);
		g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(15.0f));
		g2d.setColor(Color.BLACK);

		int n = players.size();
		int iter = 0;
		Iterator p = players.iterator();
		while (p.hasNext()) {
			Player player = (Player) p.next();

			String str3 = "Wins: " + player.getData().getPlayerWinsVsComputer();
			g2d.drawString(str3, 10 + iter * GameFrame.GAME_WIDTH/n, GameFrame.GAME_HEIGHT + 40);

			String str4 = "Losses: " + player.getData().getPlayerLossesVsComputer();
			g2d.drawString(str4, 10 + iter * GameFrame.GAME_WIDTH/n, GameFrame.GAME_HEIGHT + 70);
			int strWidth = g2d.getFontMetrics().stringWidth(str4);

			g2d.drawImage(player.getImage(), strWidth + 10 + 10 + iter * GameFrame.GAME_WIDTH/n, GameFrame.GAME_HEIGHT + 20, null);

			String str = "Name: " + player.getName();
			g2d.drawString(str, strWidth + 10 + iter * GameFrame.GAME_WIDTH/n + player.getWidth() + 10 + 10, GameFrame.GAME_HEIGHT + 30);

			String str1 = "Life: " + player.getLife();
			g2d.drawString(str1,strWidth + 10 + iter * GameFrame.GAME_WIDTH/n + player.getWidth() + 10 + 10, GameFrame.GAME_HEIGHT + 55);

			String str2 = "Gift: " + player.getGiftId().name();
			g2d.drawString(str2,strWidth + 10 + iter * GameFrame.GAME_WIDTH/n + player.getWidth() + 10 + 10, GameFrame.GAME_HEIGHT + 80);
			iter++;
		}

		g2d.dispose();
	}

	/**
	 * drawing the map side walls and map middle walls
	 */
	private void drawMapSideWalls(){
		int block = Main.blockSize;
		int xx = (inputMap[0].length - 1)/2;
		int yy = (inputMap.length - 1)/2;
		int y0 = Main.y0;
		int x, y;

		int[][] mainLoc = new int[yy][xx];
		for (int i = 0; i < yy; i++) {

			for (int j = 0; j < xx; j++) {
				mainLoc[i][j] = inputMap[2*i+1][2*j+1];
			}
		}

		int bound = 40;
		for (int i = 0; i < yy; i++) {

			for (int j = 0; j < xx; j++) {
				x = j * block + 20;
				y = i * block + y0 + block/2 - 10;

				switch (mainLoc[i][j]){
					case 1:
						DestructibleWall wall1 = new DestructibleWall( x + 3, y, true);
						DestructibleWall wall2 = new DestructibleWall( x + bound - 10, y - bound + 10, false);
						GameState.addObject(wall1);
						GameState.addObject(wall2);
						break;
					case 2:
						IndestructibleWall wall3 = new IndestructibleWall( x + 3, y, true);
						IndestructibleWall wall4 = new IndestructibleWall( x + bound - 10, y - bound + 10, false);
						GameState.addObject(wall3);
						GameState.addObject(wall4);
						break;
				}
			}
		}

		//side walls
		//vertical
		for (int i = 1; i < inputMap.length ; i+=2) {
			int j = 0;
			x = (j-1)/2 * block - 2;
			y = (i-1)/2 * block + y0 + 24;

			if (inputMap[i][j] == 1){
				DestructibleWall wall1 = new DestructibleWall(x, y, false);
				GameState.addObject(wall1);
			} else if (inputMap[i][j] == 2){
				IndestructibleWall wall2 = new IndestructibleWall(x, y, false);
				GameState.addObject(wall2);
			}

			j = inputMap[0].length - 1;
			x = GameFrame.GAME_WIDTH - 25;
			if (inputMap[i][j] == 1){
				DestructibleWall wall1 = new DestructibleWall( x, y, false);
				GameState.addObject(wall1);
			} else if (inputMap[i][j] == 2){
				IndestructibleWall wall2 = new IndestructibleWall( x, y, false);
				GameState.addObject(wall2);
			}
		}

		//horizontal
		for (int j = 1; j < inputMap[0].length ; j+=2) {

			int k = 0;
			x = (j-1)/2 * block + 25;
			y = (k-1)/2 * block + y0 - 5;
			if (inputMap[k][j] == 1){
				DestructibleWall wall1 = new DestructibleWall(x, y, true);
				GameState.addObject(wall1);
			} else if (inputMap[k][j] == 2){
				IndestructibleWall wall2 = new IndestructibleWall(x, y, true);
				GameState.addObject(wall2);
			}

			k = inputMap.length - 1;
			y = GameFrame.GAME_HEIGHT - 25;
			if (inputMap[k][j] == 1){
				DestructibleWall wall1 = new DestructibleWall(x, y, true);
				GameState.addObject(wall1);
			} else if (inputMap[k][j] == 2){
				IndestructibleWall wall2 = new IndestructibleWall(x, y, true);
				GameState.addObject(wall2);
			}
		}
	}

	/**
	 * drawing the map walls
	 */
	private void drawMapWalls(){
		int block = Main.blockSize;
		int y0 = Main.y0;

		for (int i = 2 ; i < inputMap.length - 2 ; i += 2) {
			for (int j = 1; j < inputMap[0].length - 1 ; j += 2) {
				int x = (j-1)/2 * block + 20;
				int y = (i-1)/2 * block + y0 + block - 13;

				switch (inputMap[i][j]){
					case 1:
						GameState.addObject(new DestructibleWall( x, y, true));
						break;
					case 2:
						GameState.addObject(new IndestructibleWall( x, y, true));
						break;
				}
			}
		}

		for (int i = 1 ; i < inputMap.length - 1 ; i += 2) {
			for (int j = 2; j < inputMap[0].length - 2 ; j += 2) {
				int x = (j-1)/2 * block + block - 14;
				int y = (i-1)/2 * block + y0 + 20;

				switch (inputMap[i][j]){
					case 1:
						GameState.addObject(new DestructibleWall( x, y, false));
						break;
					case 2:
						GameState.addObject(new IndestructibleWall( x, y, false));
						break;
				}
			}
		}
	}

	/**
	 * setting the map middle blocks collision
	 */
	private void setMapBlocks(){
		int x, y;
		int xx = (inputMap[0].length - 1)/2;
		int yy = (inputMap.length - 1)/2;
		int block = Main.blockSize;
		for (int i = 0; i <= yy; i++) {
			for (int j = 0; j <= xx; j++) {
				x = j * block - 20;
				y = i * block + 10;
				addObject(new Block(x,y));
			}
		}
	}

	/**
	 * making new Gifts
	 * @return the new Gift
	 */
	private Gifts makeNewGift(){
		int xx = inputMap[0].length - 2;
		int yy = inputMap.length - 2;
		int block = Main.blockSize;
		Random rand = new Random();
		boolean temp;
		Gifts gift;

		while (true){
			gift = new Gifts(rand.nextInt(xx) * block/2 + block/2 - 15, rand.nextInt(yy) * block/2 + Main.y0 + block/2 - 15, rand.nextInt(5));
			temp = false;

			Iterator it = objects.iterator();
			while (it.hasNext()){
				GameObject object = (GameObject) it.next();

				if (object.getObjectId() == ObjectId.destructibleWall || object.getObjectId() == ObjectId.indestructibleWall || object.getObjectId() == ObjectId.block || object.getObjectId() == ObjectId.player || object.getObjectId() == ObjectId.gift){
					if(gift.getBounds().intersects(object.getBounds()))
						temp = true;
				}
			}
			if (!temp)
				break;
		}
		return gift;
	}

	/**
	 * determining a random location for player to begin with
	 * @return the x and y location
	 */
	public int[] addNewPlayer(){
		int i, j;
		int[] xy = new int[2];
		Random rand = new Random();
		int block = Main.blockSize;

		while (true){

			i = rand.nextInt(inputMap.length/2 - 1) * 2 + 1;
			j = rand.nextInt(inputMap[0].length/2 - 1) * 2 + 1;

			int temp1 = 0;
			if (inputMap[i][j] == 0){

				if (inputMap[i+1][j] != 0){
					temp1++;
				}if (inputMap[i-1][j] != 0){
					temp1++;
				}if (inputMap[i][j+1] != 0){
					temp1++;
				}if (inputMap[i][j-1] != 0){
					temp1++;
				}

				if (temp1 < 4){

					boolean temp = false;
					xy[0] = (j-1)/2 * block + block/4;
					xy[1] = (i-1)/2 * block + Main.y0 + block/4;

					Iterator it = objects.iterator();
					while (it.hasNext()){
						GameObject object = (GameObject) it.next();

						if (object.getObjectId() == ObjectId.destructibleWall || object.getObjectId() == ObjectId.indestructibleWall || object.getObjectId() == ObjectId.block || object.getObjectId() == ObjectId.player || object.getObjectId() == ObjectId.gift){
							if((new Rectangle(xy[0], xy[1], 55, 60)).intersects(object.getBounds()))
								temp = true;
						}
					}
					if (!temp)
						break;
				}
			}
		}

		return xy;
	}

	public static int[][] getInputMap() {
		return inputMap;
	}

	public static void addObject(GameObject newObject){
		objects.add(newObject);
	}

	public static void removeObject(GameObject oldObject){
		objects.remove(oldObject);
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public static CopyOnWriteArrayList<GameObject> getObjects() {
		return objects;
	}

	public static int getShotPower() {
		return shotPower;
	}

	public static void setShotPower(int shotPower) {
		GameState.shotPower = shotPower;
	}

	public static int getTankLife() {
		return tankLife;
	}

	public static void setTankLife(int tankLife) {
		GameState.tankLife = tankLife;
	}

	public static int getWallLife() {
		return wallLife;
	}

	public static void setWallLife(int wallLife) {
		GameState.wallLife = wallLife;
	}

	public static int getTankSpeed() {
		return tankSpeed;
	}

	public static int getShotSpeed() {
		return shotSpeed;
	}

	public static Player getCurrentPlayer() {
		return currentPlayer;
	}

	public CopyOnWriteArrayList<Player> getPlayers() {
		return players;
	}
}

