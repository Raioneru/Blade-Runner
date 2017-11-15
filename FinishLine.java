import java.awt.Graphics2D;
import java.awt.Rectangle;

public class FinishLine {
	//Finish line
	private static final int X = 250;
	//private static final int WIDTH = 5;
	//private static final int HEIGHT = 400;
	private static final int Y = 150;
	private static final int WIDTH = 20;
	private static final int HEIGHT = 50;

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
		g.fillRect(X, 150, WIDTH, HEIGHT);
	}
	public Rectangle getBounds() {
		return new Rectangle(250, Y, WIDTH, HEIGHT);
	}
	private boolean collision() {
		return game.player.getBounds().intersects(getBounds());
	}
}