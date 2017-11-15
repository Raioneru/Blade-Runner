import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Player {
	//Player sprite dimensions
	private static final int Y = 150;
	private static final int WIDTH = 20;
	private static final int HEIGHT = 50;

	//Initial position
	int x = 0;
	int xa = 0;

	private Game game;

	public Player(Game game) {
		this.game = game;
	}

	//Move player only to the right
	public void move() {
		if (x + xa > 0 && x + xa < game.getWidth() - WIDTH){
			x = x + xa;
		}
	}
	//Create the player
	public void paint(Graphics2D g) {
		g.fillRect(x, Y, WIDTH, HEIGHT);
	}
	//Stop when the key is released
	public void keyReleased(KeyEvent e) {
		xa = 0;
	}
	//When the keyboard is pressed
	public void keyPressed(KeyEvent e) {
		//Only allow movement to the right
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			xa = 1;
	}
	//Check to see position of the player. Used to see if he's crossed the finish line
	public Rectangle getBounds() {
		return new Rectangle(x, Y, WIDTH, HEIGHT);
	}
}