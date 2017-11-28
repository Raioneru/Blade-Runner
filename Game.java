
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.*;  
import java.awt.event.*; 
import java.util.Random; 

public class Game extends JPanel implements MouseListener, MouseMotionListener {
	//Initialize Objects
	int clicks =0;

	int count = 0;
	int robot1Count =200;
	int robot2Count =100;
	int robot3Count =300;
	int robot4Count =250;
	int robot5Count =250;

	int numPlayers = 0;
	int ranNum1 = new Random().nextInt(60);
	int ranNum2 = new Random().nextInt(60);
	int ranNum3 = new Random().nextInt(60);
	int ranNum4 = new Random().nextInt(60);
	int ranNum5 = new Random().nextInt(60);
	// int paramLow = new Random().nextInt(30)+3;
	int paramLow = 20;
	int paramHigh = new Random().nextInt(60);	


	FinishLine finishline = new FinishLine(this);
	Player player = new Player(this,numPlayers);
	Player robot1 = new Player(this,1);
	Player robot2 = new Player(this,2);
	Player robot3 = new Player(this,3);
	Player robot4 = new Player(this,4);
	Player robot5 = new Player(this,5);


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
		//System.out.println("Mouse Clicked");
		//Get bound of player icon and if Mouse X and Y intersect end the game
		if (collision(e) && clicks==0){
			gameOver();
		}
		clicks++;
	    //System.out.println("X: "+e.getX()+" Y: "+e.getY());
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
	private boolean collision(MouseEvent e) {
		//System.out.println("Checking collision");
		//System.out.println("Player get bounds: "+player.getBounds());
		return player.getBounds().intersects(getBounds(e));
	}
	public Rectangle getBounds(MouseEvent e) {
		//System.out.println("Getting bounds of click");
		//System.out.println("X: "+e.getX()+" Y: "+e.getY());
		return new Rectangle(e.getX(), e.getY(), 20, 50);
	} 
	private void move() {
		//Finish line detect for collision
		finishline.move();
		//Player sprite moves
		player.move();		
		//System.out.println("Count: "+count+" Low: "+paramLow+" High: "+paramHigh);		
		//AI determines when to move
		if(robot1Count <ranNum1 && count>paramLow && count< paramHigh){
		//if(ranNum1 <2 && count>2 && count< 30){
			robot1.xa = 1;			
		}else{
			robot1.xa = 0;
		}
		robot1.move();
		if(robot2Count <ranNum2 && count>paramLow && count< paramHigh){
		//if(ranNum1 <2 && count>2 && count< 30){
			robot2.xa = 1;			
		}else{
			robot2.xa = 0;
		}
		robot2.move();
		if(robot3Count <ranNum3 && count>paramLow && count< paramHigh){
		//if(ranNum1 <2 && count>2 && count< 30){
			robot3.xa = 1;			
		}else{
			robot3.xa = 0;
		}
		robot3.move();
		if(robot4Count <ranNum4 && count>paramLow && count< paramHigh){
		//if(ranNum1 <2 && count>2 && count< 30){
			robot4.xa = 1;			
		}else{
			robot4.xa = 0;
		}
		robot4.move();
		if(robot5Count <ranNum5 && count>paramLow && count< paramHigh){
		//if(ranNum1 <2 && count>2 && count< 30){
			robot5.xa = 1;			
		}else{
			robot5.xa = 0;
		}
		robot5.move();						

		if (count>600) {
			//System.out.println("Reset count");
			count = 0;
			paramLow = new Random().nextInt(150)+15;
			paramHigh = new Random().nextInt(300);
		}
		if (count>robot1Count) {
			//System.out.println("Reset Robot 1 count");
			ranNum1 = new Random().nextInt(300);
			robot1Count = new Random().nextInt(300); 
		}
		if (count>robot2Count) {
			//System.out.println("Reset Robot 2 count");
			robot2Count = new Random().nextInt(300);
			ranNum2 = new Random().nextInt(300);
		}
		if (count>robot3Count) {
			//System.out.println("Reset Robot 3 count");
			robot3Count = new Random().nextInt(300);
			ranNum3 = new Random().nextInt(300);
		}
		if (count>robot4Count) {
			//System.out.println("Reset Robot 4 count");
			robot4Count = new Random().nextInt(300);
			ranNum4 = new Random().nextInt(300);
		}
		if (count>robot5Count) {
			//System.out.println("Reset Robot 5 count");
			robot5Count = new Random().nextInt(300);
			ranNum5 = new Random().nextInt(300);
		}					
		count++;
		// robot1Count++;
		// robot2Count++;
		// robot3Count++;
		// robot4Count++;
		// robot5Count++;	
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
		g2d.drawImage(player.getImage(), player.getX(),
                player.getY(), this);

		robot1.paint(g2d);
		g2d.drawImage(robot1.getImage(), robot1.getX(),
                robot1.getY(), this);
		robot2.paint(g2d);
		g2d.drawImage(robot2.getImage(), robot2.getX(),
                robot2.getY(), this);
		robot3.paint(g2d);
		g2d.drawImage(robot3.getImage(), robot3.getX(),
                robot3.getY(), this);
		robot4.paint(g2d);
		g2d.drawImage(robot4.getImage(), robot4.getX(),
                robot4.getY(), this);		
		robot5.paint(g2d);
		g2d.drawImage(robot5.getImage(), robot5.getX(),
                robot5.getY(), this);		
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
		// frame.setSize(900, 400);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
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