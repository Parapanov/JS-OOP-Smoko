import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class GameBoard extends JPanel implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 500 , HEIGHT = 500;
	private Thread thread;
	private boolean running;
	private boolean right = true, left =false, up = false, down = false;
	private Snake b;
	private ArrayList<Snake> snake;
	
	private Food food;
	private ArrayList<Food> foods;
	private int xCoor = 10 , yCoor = 10, size =1;
	private int ticks = 0;
	private Random r;
	private int  score = 0; 
	public GameBoard() {
		setFocusable(true);
		addKeyListener(this);
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		    r = new Random();
	       
	        snake = new ArrayList<Snake>();
	        foods = new ArrayList<Food>();
	       
	        start();
	    }
	 
	    public void tick() {
	        if (snake.size() == 0) {
	            b = new Snake(xCoor, yCoor, 10);
	            snake.add(b);
	        }
	        if(foods.size() == 0) {
	            int xCoor = r.nextInt(39);
	            int yCoor = r.nextInt(39);
	           
	            food = new Food(xCoor, yCoor, 12);
	            foods.add(food);
	        }
	       
	        for(int i = 0; i < foods.size(); i++) {
	            if(xCoor == foods.get(i).getxCoor() &&
	                    yCoor == foods.get(i).getyCoor()) {
	                size++;
	               foods.remove(i);
	                i++;
	                score+= 10 ;
	            }
	        }
	       
	        for(int i =0; i < snake.size(); i++) {
	            if(xCoor == snake.get(i).getxCoor() &&
	                    yCoor == snake.get(i).getyCoor()) {
	                if(i != snake.size() - 1) {
	                    stop();
	                }
	            }
	        }
	        if(score == 300) {
	        	stop();
	        }
	        if(xCoor >39) {
	        	xCoor = 0;
	        	
	        }if(xCoor < 0) {
	        	xCoor = 39;
	        	
	        }if(yCoor > 39) {
	        	yCoor = 0;
	        }
	        if(yCoor  < 0) {
	        	yCoor= 39;
	        	
	        }
	        if(xCoor < 0 || xCoor > 39 || yCoor < 0 || yCoor > 39) {
	            stop();
	        }
	       
	       
	        ticks++;
	       
	        if(ticks > 500000) {
	            if(right) xCoor++;
	            if(left) xCoor--;
	            if(up) yCoor--;
	            if(down) yCoor++;
	           
	            ticks = 0;
	           
	            b = new Snake(xCoor, yCoor, 12);
	            snake.add(b);
	           
	            if(snake.size() > size) {
	                snake.remove(0);
	            }
	        }
	    }
	 
	    public void paint(Graphics g) {
	        g.clearRect(0, 0, WIDTH, HEIGHT);
	        g.setColor(Color.BLACK);
	        g.fillRect(0, 0, WIDTH, HEIGHT);
	       
	        g.setColor(Color.BLACK);
	        for (int i = 0; i < WIDTH / 15; i++) {
	            g.drawLine(i * 15, 0, i * 15, HEIGHT);
	        }
	        for (int i = 0; i < HEIGHT / 10; i++) {
	            g.drawLine(0, i * 10, WIDTH, i * 10);
	        }
	 
	        for (int i = 0; i < snake.size(); i++) {
	            snake.get(i).draw(g);
	        }
	        for(int i = 0; i < foods.size(); i++) {
	            foods.get(i).draw(g);
	        }
	        g.drawString("Score:" + score,WIDTH / 15 ,HEIGHT / 15);
	    }
	 
	    public void start() {
	        running = true;
	        thread = new Thread(this);
	        thread.start();
	    }
	 
	    public void stop() {
	        running = false;
	        try {
	            thread.join();
	        } catch (InterruptedException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
	 
	    public void run() {
	        while (running) {
	            tick(); 
	            repaint();
	        }
	    }
	 
	    @Override
	    public void keyPressed(KeyEvent e) {
	        int key = e.getKeyCode();
	        if(key == KeyEvent.VK_RIGHT && !left) {
	            up = false;
	            down = false;
	            right = true;
	        }
	        if(key == KeyEvent.VK_LEFT && !right) {
	            up = false;
	            down = false;
	            left = true;
	        }
	        if(key == KeyEvent.VK_UP && !down) {
	            left = false;
	            right = false;
	            up = true;
	        }
	        if(key == KeyEvent.VK_DOWN && !up) {
	            left = false;
	            right = false;
	            down = true;
	        }
	        }
	    @Override
	    public void keyReleased(KeyEvent arg0) {   
	    }
	    public void keyTyped(KeyEvent arg0) {  
	    }    
	}
