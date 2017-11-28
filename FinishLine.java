import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Toolkit;

public class FinishLine {
	//Finish line
	private static final int X = 700;
	private static final int WIDTH = 5;
	private static final int HEIGHT = 400;
	// private static final int Y = 150;
	// private static final int WIDTH = 20;
	// private static final int HEIGHT = 50;

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int screenWidth = screenSize.width;
	int screenHeight = screenSize.height;

	private Game game;

	public FinishLine(Game game) {
		this.game= game;
	}	
	void move() {
		if (collision()){
			game.gameOver();
		}
	}
	public void paint(Graphics2D g) {
		//Use bottom lines for final. Smaller screen for testing
		// g.fillRect(X, 0, WIDTH, HEIGHT);		
		g.fillRect(screenWidth-30, 0, WIDTH, screenHeight);
	}
	public Rectangle getBounds() {
		// return new Rectangle(X, 0, WIDTH, HEIGHT);
		return new Rectangle(screenWidth-30, 0, WIDTH, screenHeight);
	}
	private boolean collision() {
		return game.player.getBounds().intersects(getBounds());
	}
}