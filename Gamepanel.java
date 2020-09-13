import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Gamepanel extends JPanel implements Runnable, KeyListener{
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 500, HEIGHT = 500;
	
	private Thread thread;
	
	private boolean running;
	
	//Normally I would not to use public static variables
	private static int speed;

	public static void setSpeed(int val) {
		speed = val;
	}

	private  boolean right = true, left = false, up = false, down = false;
	
	private BodyPart b;
	private ArrayList<BodyPart> snake;
	
	private Apple apple;
	private ArrayList<Apple> apples;
	
	private Random r;
	
	public static enum STATE{
		MENU,
		MODE,
		GAME,
		OVER;
	};
	
	public static enum WALLMODE{
		WALLS,
		NoWALLS;
	};

	public static enum PARTYMODE{
		painter,
		crazy, 
		none;
	};
	/*
	 * The different modes for self collision
	 * Death: Snake will die if it runs into self
	 * Pass: Snake can pass through itself with no consequences
	 * Cut: Tail of where the snake ran through itself will be cut off
	 * */
	public static enum SELFCOLL{
		DEATH,
		PASS,
		CUT;
	};
	
	public static STATE State = STATE.MENU; 
	
	public static WALLMODE Wallmode = WALLMODE.WALLS;
	
	public static SELFCOLL SelfCol = SELFCOLL.DEATH;
	
	public static PARTYMODE PartyMode = PARTYMODE.none;

	private Menu menu;
	
	private Mode mode;
	
	private GameOverMenu overMenu;
	
	private int xCoor = 10, yCoor = 10, size = 10;
	
	private int ticks = 0;
	
	public Gamepanel() {

		setFocusable(true);
		
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		addKeyListener(this);
		
		snake = new ArrayList<BodyPart>();
		apples = new ArrayList<Apple>();
		
		r = new Random();
		
		menu = new Menu();
		
		mode = new Mode();
		
		overMenu = new GameOverMenu();
		
		start();
		
		speed = 250000;
		addMouseListener(new MouseInput());
	}
	
	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	public void stop() {
		//running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * This is the most important function. This handles all the game logic. 
	 * Var ticks is used as a way to reduce the frame rate. I squeezed all the 
	 * game mode in here, so it can look really messy. 
	 * */
	public void tick() {
		if(snake.size() == 0) {
			b = new BodyPart(xCoor, yCoor, 10);
			snake.add(b);
		}
		ticks++;
		if (PartyMode == PARTYMODE.crazy) {
			speed = 100000;
		}
		if(ticks > speed) {
			if(right) xCoor++;
			if(left) xCoor--;
			if(up) yCoor--;
			if (down) yCoor++;
			
			//For the Painter Mode
			if (PartyMode == PARTYMODE.painter) {
				size++;
			}
			
			ticks = 0;
			
			b = new BodyPart(xCoor, yCoor, 10);
			snake.add(b);
			
			if(snake.size() > size) {
				snake.remove(0); 
			}
		}
		if (apples.size() == 0) {
			int xCoor = r.nextInt(49);
			int yCoor = r.nextInt(49);
			apple = new Apple(xCoor, yCoor, 10);
			apples.add(apple);
		}
		
		for(int i = 0; i < apples.size(); i++) {
			if(xCoor == apples.get(i).getxCoor() && yCoor == apples.get(i).getyCoor()) {
				if (PartyMode == PARTYMODE.crazy) {
					size += 20;
				}else {
					size ++;
				}
				
				apples.remove(i);
				i++;
			}
		}
		
		if (SelfCol == SELFCOLL.CUT) {
			//collision on snake
			ArrayList<BodyPart> tempList = new ArrayList<BodyPart>();
			boolean collision = false;
			for (int i = 0; i < snake.size(); i++) {
				if (collision) {
					tempList.add(snake.get(i));
				}
				else {
					if(xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor()) {
						if(i != snake.size()-1) {
							collision = true;
							size = i;
						}
					}
				}
			}
			if (collision) {
				snake.clear();
				snake = tempList;
			}
		} else if (SelfCol == SELFCOLL.DEATH) {
			for (int i = 0; i < snake.size(); i++) {
				if(xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor()) {
					if(i != snake.size()-1) {
						State = STATE.OVER;
						System.out.println("SELF COLL Game Over");
						//stop();
					}
				}
			}
		}//else it passes through and do nothing
		
		
		//collision on border
		if (Wallmode == WALLMODE.WALLS) {
			if (xCoor < 0|| xCoor>49|| yCoor<0|| yCoor>49) {
				System.out.println("WALL Game Over");
				State = STATE.OVER;
				//stop();
			}
		}
		
		//no collision and goes through Wall
		if (Wallmode == WALLMODE.NoWALLS) {
			if (xCoor < 0|| xCoor>49|| yCoor<0|| yCoor>49) {
				if(xCoor < 0) {
					xCoor = 49;
				}
				else if (xCoor > 49) {
					xCoor = 0;
				}
				else if (yCoor < 0) {
					yCoor = 49;
				}
				else {
					yCoor = 0;
				}
				b = new BodyPart(xCoor, yCoor, 10);
				snake.add(b);
				snake.remove(0); 
			}
		}
		
	}
	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if (State == STATE.MENU) {
			menu.render(g);
		}
		else if (State == STATE.GAME|| State == STATE.OVER) {
			for(int i = 0; i < WIDTH/10; i++) {
				g.drawLine(i*10, 0, i*10, HEIGHT);
			}
			for(int i = 0; i < HEIGHT/10; i++) {
				g.drawLine(0, i*10, HEIGHT, i*10);
			}
			for (int i = 0; i < snake.size(); i++) {
				snake.get(i).draw(g);
			}
			for(int i = 0; i<apples.size(); i++) {
				apples.get(i).draw(g);
			}
			if (State == STATE.OVER) {
				overMenu.render(g);
			}
		}
		else if (State == STATE.MODE) {
			mode.render(g);
			
		}
	}

	@Override
	public void run() {
		while (running) {
			if (State == STATE.GAME) {
				tick();
			}
			if (State == STATE.OVER) {
				//reinitializing the game
				snake.clear();
				xCoor = 10;
				yCoor = 10;
				right = true;
				left = false;
				up = false;
				down = false;
				size = 10; 
			}
			repaint();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (State == STATE.GAME) {
			if(key == KeyEvent.VK_RIGHT && !left) {
				right = true;
				up = false;
				down = false;
			}
			if(key == KeyEvent.VK_LEFT && !right) {
				left = true;
				up = false;
				down = false;
			}
			if(key == KeyEvent.VK_UP && !down) {
				right = false;
				up = true;
				left = false;
			}
			if(key == KeyEvent.VK_DOWN && !up){
				down = true;
				left = false;
				right = false; 
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
