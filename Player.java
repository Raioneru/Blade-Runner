import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.*;

import java.awt.Image;
import javax.swing.ImageIcon;


public class Player{
	//Player sprite dimensions
	//private static final int Y = 150;
	private static final int WIDTH = 10;
	private static final int HEIGHT = 25;
	int Y;
	//Initial position
	int x = 0;
	int xa = 0;

	int timer=0;
	private Game game;
	private static Vector<Integer> positions = new Vector<>(Arrays.asList(50,150,250,350,450,550));
	protected int width;
	protected int height;
	protected boolean vis;
	protected Image image;

	public Player(Game game,int numPlayers) {
		if (numPlayers<1) {
			Collections.shuffle(positions);
		}
		System.out.println(positions);
		this.game = game;
		this.Y = dequeue();
		this.vis = true;
		loadImage("character.png");

	}
	//upload image
    protected void loadImage(String imageName) {
        System.out.println("Sprite loadImage");
        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }
    protected void getImageDimensions() {
        width = image.getWidth(null);
        // System.out.println("width: "+width);
        height = image.getHeight(null);
        // System.out.println("height: "+height);
    }    
    public Image getImage() {
        return image;
    }
    public boolean isVisible() {
        return vis;
    }
    public void setVisible(Boolean visible) {
        vis = visible;
    }
	public static Integer dequeue(){
		Integer obj = positions.remove(0);
		positions.remove(obj);
		return obj;
	}
	//Move player only to the right
	public void move() {
		if (x + xa > 0 && x + xa < game.getWidth() - WIDTH){
			x = x + xa;
		}
	}
	//Create the player
	public void paint(Graphics2D g) {
		g.fillRect(x, Y, 0, 0);
	}
	//Stop when the key is released
	public void keyReleased(KeyEvent e) {
		xa = 0;
	}
	//When the keyboard is pressed
	public void keyPressed(KeyEvent e) {
		//Walk with right key
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			xa = 1;
		//Run with up key
		if (e.getKeyCode() == KeyEvent.VK_UP)
			xa = 2;		
	}
	//Check to see position of the player. Used to see if he's crossed the finish line
	public Rectangle getBounds() {
		//Wait until player is halfway past the finish line
		return new Rectangle(x, Y, 52-30, 65);
	}
	//Return x position
    public int getX() {
        return x;
    }
    //Return y position
    public int getY() {
        return Y;
    }
}