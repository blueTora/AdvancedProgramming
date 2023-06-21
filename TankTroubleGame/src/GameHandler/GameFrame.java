package GameHandler;

import java.awt.*;
import java.awt.image.*;
import javax.swing.JFrame;

/**
 * The window on which the rendering is performed.
 *
 * @author Negar Karami
 * @since 2020-07-26
 * @version 0.0
 */
public class GameFrame extends JFrame {

	public static int GAME_HEIGHT = 720;
	public static int GAME_WIDTH = 16 * GAME_HEIGHT / 9;

	public static int bellowWindowWidth;
	public static int bellowWindowHeight;

	private BufferStrategy bufferStrategy;

	private State STATE;
	private static String winner = "";

	/**
	 * Creating the main frame
	 * @param title Game title
	 * @param width game width
	 * @param height game height
	 */
	public GameFrame(String title, int width, int height) {
		super(title);
		setResizable(false);
		GAME_HEIGHT = height;
		GAME_WIDTH = width;
		bellowWindowWidth = width;
		bellowWindowHeight = 100;
		setSize(GAME_WIDTH, GAME_HEIGHT + bellowWindowHeight);
	}
	
	/**
	 * This must be called once after the JFrame is shown:
	 * and before any rendering is started.
	 */
	public void initBufferStrategy() {
		createBufferStrategy(3);
		bufferStrategy = getBufferStrategy();
	}

	/**
	 * Game rendering with triple-buffering using BufferStrategy.
	 * calling doRendering for painting
	 * @param state Game State
	 * @param start Starting page
	 * @param login Login Page
	 * @param menu Menu Page
	 */
	public void render(GameState state, StartingPage start, LoginPage login, Menu menu) {
		do {
			do {
				Graphics graphics = bufferStrategy.getDrawGraphics();

				try {
					doRendering(graphics, state, start, login, menu);
				} finally {
					graphics.dispose();
				}
			} while (bufferStrategy.contentsRestored());

			bufferStrategy.show();
			Toolkit.getDefaultToolkit().sync();

		} while (bufferStrategy.contentsLost());
	}

	/**
	 * Rendering all game elements based on the game STATE.
	 * @param g window Graphics
	 * @param state Game State
	 * @param start Starting page
	 * @param login Login Page
	 * @param menu Menu Page
	 */
	private void doRendering(Graphics g, GameState state, StartingPage start, LoginPage login, Menu menu) {
		Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.GREEN);
        g2d.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        if (STATE == State.GAME)
			state.rendering(g2d);

        if (STATE == State.START)
			start.rendering(g2d);

		if (STATE == State.LOGIN)
			login.rendering(g);

		if (STATE == State.MENU)
			menu.rendering(g);

		if (GameLoop.isGameOver()) {
			String str = "GAME OVER";
			g2d.setColor(Color.WHITE);
			g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(64.0f));
			int strWidth = g2d.getFontMetrics().stringWidth(str);
			g2d.drawString(str, (GAME_WIDTH - strWidth) / 2, GAME_HEIGHT / 2);

			String str1 = "Winner is " + winner;
			g2d.setColor(Color.WHITE);
			g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(32.0f));
			strWidth = g2d.getFontMetrics().stringWidth(str1);
			g2d.drawString(str1, (GAME_WIDTH - strWidth) / 2, GAME_HEIGHT / 2 + 50);
		}
	}

	/**
	 * setting the game STATE for rendering
	 * @param STATE game state at the moment
	 */
	public void setSTATE(State STATE) {
		this.STATE = STATE;
	}

	/**
	 * setting the the game winner
	 * @param name winner name
	 */
	public static void setWinner(String name) {
		winner = name;
	}
}
