package game;

import java.awt.Graphics;
import gamestate.Gamestates;
import gamestate.Menu;
import gamestate.Playing;


public class Game implements Runnable{
	
	private gameDisplay gameDisplay;
	private gamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;
	
	private Playing playing;
	private Menu menu;
	
	public final static int TILES_DEFAULT_SIZE = 32;
	public final static float SCALE = 3f;
	public final static int TILES_IN_WIDTH = 13;
	public final static int TILES_IN_HEIGHT = 8;
	public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
	
	public Game() {
		
		initClasses();
		
		gamePanel = new gamePanel(this);
		gameDisplay = new gameDisplay(gamePanel);
		gamePanel.requestFocus();
		
		GameStart();
		
	}
	
	private void initClasses() {
		
		menu = new Menu(this);
		playing = new Playing(this);
		
	}

	private void GameStart() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void update() {
		
		switch(Gamestates.state) {
		case MENU:
			menu.update();
			break;
		case PLAYING:
			playing.update();
			break;
		default:
			break;
		}
	}
	
	public void render(Graphics g) {
		
		switch(Gamestates.state) {
		case MENU:
			menu.draw(g);
			break;
		case PLAYING:
			playing.draw(g);
			break;
		default:
			break;
		}
	}

	@Override
	public void run() {
		
		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;
		
		long previousTime = System.nanoTime();
		
		int frames = 0;
		int updates = 0;
		long lastcheck = System.currentTimeMillis();
		
		double deltaU = 0;
		double deltaF = 0;
		
		while(true) {
			
			long currentTime = System.nanoTime();
			
			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;
			
			if(deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}
			
			if(deltaF >= 1) {
				gamePanel.repaint();
				frames++;
				deltaF--;
			}
			
			if(System.currentTimeMillis() - lastcheck >= 1000) {
				lastcheck = System.currentTimeMillis();
				System.out.println("FPS : " + frames + "| UPS :" + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	public void windowFocusLost() {
		if(Gamestates.state == Gamestates.PLAYING) {
			playing.getPlayer().resetDirBooleans();
		}
	}
	
	public Menu getMenu() {
		return menu;
	}
	
	public Playing getPlaying() {
		return playing;
	}

}
