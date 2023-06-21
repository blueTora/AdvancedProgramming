package GameHandler;

import GameObjects.Player;
import GameObjects.PlayerData;
import GameObjects.Shot;

import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The window on which the updating is performed.
 * and the game loop and thread.
 * @author Negar Karami
 * @since 2020-07-26
 * @version 0.0
 */
public class GameLoop implements Runnable {
	
	/**
	 * Frame Per Second.
	 */
	public static final int FPS = 30;
	
	private GameFrame canvas;
	private GameState state;

	private static State STATE;
	private StartingPage start;
	private Menu menu;
	private LoginPage login;
	private static boolean gameOver;

	private MouseHandler mouseHandler;
	private KeyHandler keyHandler;

	private static ConcurrentHashMap<String, PlayerData> playersList;

	/**
	 * creating the game.
	 * @param frame game frame
	 */
	public GameLoop(GameFrame frame) {
		canvas = frame;
		playersList = new ConcurrentHashMap<>();
		readPlayersInfo();
	}
	
	/**
	 * This must be called before the game loop starts.
	 */
	public void init() {
		mouseHandler = new MouseHandler();
		keyHandler = new KeyHandler();

		state = new GameState();
		canvas.addKeyListener(keyHandler);
		canvas.addMouseListener(mouseHandler);

		start = new StartingPage(mouseHandler, keyHandler);
		menu = new Menu(mouseHandler, keyHandler);
		login = new LoginPage(mouseHandler, keyHandler);

		STATE = State.START;
		gameOver = false;

//		for (String name : names){
//			state.addNewPlayer(name);
//		}
	}

	/**
	 * Game thread
	 */
	@Override
	public void run() {
		int temp1 = 0, temp2 = 0;

		while (!gameOver) {
			try {
				long startTime = System.currentTimeMillis();

				if (STATE == State.GAME){
					if (temp1 == 0){
						state.addGameObjects();
						temp1++;
					}
					canvas.setSize(GameFrame.GAME_WIDTH, GameFrame.GAME_HEIGHT + GameFrame.bellowWindowHeight);
					state.update();

				} else {
					canvas.setSize(StartingPage.GAME_WIDTH, StartingPage.GAME_HEIGHT);

					if (STATE == State.START)
						start.update();

					if (STATE == State.LOGIN)
						login.update();

					if (STATE == State.MENU){
						if (temp2 == 0){
							menu.init();
							temp2++;
						}
						menu.update();
					}
				}

				canvas.setSTATE(STATE);
				canvas.render(state, start, login, menu);

				gameOver = state.isGameOver();

				long delay = (1000 / FPS) - (System.currentTimeMillis() - startTime);
				if (delay > 0)
					Thread.sleep(delay);

				if (gameOver){
					Iterator p = state.getPlayers().iterator();
					while (p.hasNext()) {
						Player player = (Player) p.next();

						if (player.isWin())
							GameFrame.setWinner(player.getName());
					}

					canvas.render(state, start, login, menu);
					long t = System.currentTimeMillis();
					while (System.currentTimeMillis() - t < 4000) {
						startTime = System.currentTimeMillis();

						canvas.render(state, start, login, menu);
						gameOver = state.isGameOver();

						delay = (1000 / FPS) - (System.currentTimeMillis() - startTime);
						if (delay > 0)
							Thread.sleep(delay);
					}

					gameOver = false;
					savePlayersInfo();

					STATE = State.MENU;
					menu.update();
					canvas.render(state, start, login, menu);

					state = new GameState();
					temp1 = 0;
				}
			} catch (InterruptedException ex) {
			}
		}
	}

	/**
	 * setting the game STATE for rendering
	 * @param STATE game state at the moment
	 */
	public static void setSTATE(State STATE) {
		GameLoop.STATE = STATE;
	}

	/**
	 * Mouse Handling Class
	 */
	class MouseHandler extends MouseAdapter {

		private int x;
		private int y;

		/**
		 * initializing the mouse x and y location on the screen.
		 * @param e mouse object
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			x = e.getX();
			y = e.getY();
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public void setX(int x) {
			this.x = x;
		}

		public void setY(int y) {
			this.y = y;
		}
	}

	/**
	 * The keyboard handler.
	 */
	class KeyHandler extends KeyAdapter {

		private String newString = "";

		/**
		 * getting the new keyboard character
		 * @return new char
		 */
		public String getNewString() {
			String temp = newString;
			newString = "";
			return temp;
		}

		@Override
		public void keyPressed(KeyEvent e) {

			if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
				if (STATE == State.START){
					start.delete();
				} else if (STATE == State.LOGIN){
					login.delete();
				} else if (STATE == State.MENU){
					menu.delete();
				}
			}

			if (STATE == State.GAME){
				switch (e.getKeyCode()) {

					case KeyEvent.VK_UP:
						GameState.getCurrentPlayer().setUP(true);
						break;
					case KeyEvent.VK_DOWN:
						GameState.getCurrentPlayer().setDOWN(true);
						break;
					case KeyEvent.VK_LEFT:
						GameState.getCurrentPlayer().setTurnLEFT(true);
						break;
					case KeyEvent.VK_RIGHT:
						GameState.getCurrentPlayer().setTurnRIGHT(true);
						break;
					case KeyEvent.VK_SPACE:
						if (GameState.getCurrentPlayer().getShotTime()/1000 < 1){
							if (GameState.getCurrentPlayer().getShotCount() < 2){
								GameState.getObjects().add(new Shot(GameState.getCurrentPlayer().getX(), GameState.getCurrentPlayer().getY(), GameState.getCurrentPlayer().getShotId(),
										GameState.getCurrentPlayer().getRotateDegree() , GameState.getCurrentPlayer().getWidth(), GameState.getCurrentPlayer().getHeight()));
								GameState.getCurrentPlayer().addShotCount();
							}
						} else
							GameState.getCurrentPlayer().resetShotCounts();
						break;
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

			if (STATE == State.GAME){
				switch (e.getKeyCode())
				{
					case KeyEvent.VK_UP:
						GameState.getCurrentPlayer().setUP(false);
						break;
					case KeyEvent.VK_DOWN:
						GameState.getCurrentPlayer().setDOWN(false);
						break;
					case KeyEvent.VK_LEFT:
						GameState.getCurrentPlayer().setTurnLEFT(false);
						break;
					case KeyEvent.VK_RIGHT:
						GameState.getCurrentPlayer().setTurnRIGHT(false);
						break;
				}
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			if (STATE != State.GAME) {
				if ((e.getKeyChar() <= 'Z' && e.getKeyChar() >= 'A') || (e.getKeyChar() <= 'z' && e.getKeyChar() >= 'a') || (e.getKeyChar() <= '9' && e.getKeyChar() >= '0'))
					newString = String.valueOf(e.getKeyChar());
			}
		}
	}

	/**
	 * reading the players previews data.
	 */
	private void readPlayersInfo(){
		String address = "PlayerInfo.bin";
		try (FileInputStream r = new FileInputStream(address)){
			ObjectInputStream in = new ObjectInputStream(r);

			playersList = (ConcurrentHashMap<String, PlayerData>)in.readObject();
			in.close();

		} catch (FileNotFoundException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * saving the players data.
	 */
	private void savePlayersInfo(){

		for (Player p : state.getPlayers()){
			for (String name : playersList.keySet()){
				if(p.getName().equals(name)){

					PlayerData data = playersList.get(name);
					data.updateTotalTime(p.getTime());
					if (p.isWin())
						data.updatePlayerWinsVsComputer();
					else
						data.updatePlayerLossesVsComputer();

					System.out.println("saved  "+ name);
				}
			}
		}

		String address = "PlayerInfo.bin";
		try (FileOutputStream w = new FileOutputStream(address)){
			ObjectOutputStream out = new ObjectOutputStream(w);

			out.writeObject(playersList);
			out.close();

		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * getting the player list
	 * @return player list data
	 */
	public static ConcurrentHashMap<String, PlayerData> getPlayersList() {
		return playersList;
	}

	/**
	 * getting the game over boolean
	 * @return game over boolean
	 */
	public static boolean isGameOver() {
		return gameOver;
	}
}
