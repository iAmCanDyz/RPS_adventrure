package game;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import input.Keyboardinput;
import static game.Game.GAME_HEIGHT;
import static game.Game.GAME_WIDTH;

public class gamePanel extends JPanel{
	
	private Game game;

	public gamePanel(Game game) {
		this.game = game;
		
		setPanelSize();
		addKeyListener(new Keyboardinput(this));
	}
	

	private void setPanelSize() {
		
		Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
		setPreferredSize(size);
	}
	
	public void updateGame() {
		
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		game.render(g);
	}
	
	public Game getGame() {
		return game;
	}
	
}
