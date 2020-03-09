import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GameBoard extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 500 , HEIGHT = 500;
	private Thread thread;
	private boolean running;
	private boolean right = true, left =false, up = false, down = false;
	private BodyPart b;
	Private ArrayList<BodyPart> snake;
	
	private Food food;
	private ArrayList<Food> food;
	private int xCoor = 10 , yCoor = 10, size =5;
	private int ticks = 0;
	
	public GameBoard() {
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		addKeyListener(this);
		snake = new ArrayList<BodyPart>();
		start();
	}
	public void start() {
		
	}
	public void stop() {
		
	}
	public void tick() {
		
	}
	public void paint(Graphics g) {
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
