// import java.awt.Color;
// import java.awt.Font;
// import java.awt.Graphics;
// import java.awt.Graphics2D;
// import java.awt.RenderingHints;
// import java.awt.event.KeyEvent;
// import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.*;  
import java.awt.event.*;  

public class Game extends JPanel implements MouseListener, MouseMotionListener {
	//Initialize Objects
	FinishLine finishline = new FinishLine(this);
	Player player = new Player(this);

	public Game() {
		//Create and register listeners
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
				player.keyReleased(e);
			}
			@Override
			public void keyPressed(KeyEvent e) {
				player.keyPressed(e);
			}
		});
		//So that the JPanel object receives the keyboard notifications 
		//this allows this file to receive the focus.
		setFocusable(true);

		addMouseListener(this);
		addMouseMotionListener(this);  
        setSize(300,300);  
        setLayout(null);  
        setVisible(true);  
	}
	public void mouseClicked(MouseEvent e) {
	    // Graphics g=getGraphics();  
	    // g.setColor(Color.BLUE);  
	    // g.fillOval(e.getX(),e.getY(),20,20);
	    player.getbounds(); 
	    System.out.println("X: "+e.getX()+" Y: "+e.getY());
    } 
    public void mousePressed(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}



    //Mouse Motion Listener methods need to all be implemented 
    public void mouseDragged(MouseEvent e) {}  
	public void mouseMoved(MouseEvent e) {    
	Graphics g=getGraphics();  
    g.setColor(Color.BLUE);  
    g.fillOval(e.getX(),e.getY(),20,20);  
	} 


	  
	private void move() {
		//Finish line detect for collision
		finishline.move();
		//Player sprite moves
		player.move();
	}
	public void paint(Graphics g) {
		//this clears the screen before reprinting Player at new position
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		//Antialiasing makes the figure smoother
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		finishline.paint(g2d);
		player.paint(g2d);
	} 
	public void gameOver() {
		//Shows a new window to end the game
		JOptionPane.showMessageDialog(this, "Got him!",
				"Game Over", JOptionPane.YES_NO_OPTION);
		System.exit(ABORT);
	}	
	public static void main(String[] args) throws InterruptedException {
		//Name of the window
		JFrame frame = new JFrame("Hidden Game");
		Game game = new Game();
		frame.add(game);
		//Window size
		frame.setSize(300, 400);
		frame.setVisible(true);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//Exits the program when the window gets closed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while (true) {
			game.move();
			game.repaint();
			//tells the processor that the thread which is being 
			//run must sleep for 10 milliseconds, which allows the 
			//processor to execute other threads
			Thread.sleep(10);
		}
	}
}	