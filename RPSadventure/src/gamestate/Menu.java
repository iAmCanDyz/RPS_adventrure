package gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import game.Game;
import ui.Menubotton;

public class Menu extends State implements Statemethods{
	
	private Menubotton[] buttons = new Menubotton[3];
	
	public Menu(Game game) {
		super(game);
		loadButtons();
	}

	private void loadButtons() {
		buttons[0] = new Menubotton(Game.GAME_WIDTH / 2, (int) (20 * Game.SCALE), 0, Gamestates.PLAYING);
		buttons[1] = new Menubotton(Game.GAME_WIDTH / 2, (int) (90 * Game.SCALE), 1, Gamestates.OPTIONS);
		buttons[2] = new Menubotton(Game.GAME_WIDTH / 2, (int) (160 * Game.SCALE), 2, Gamestates.QUIT);
	}

	@Override
	public void update() {
		for (Menubotton mb : buttons)	
			mb.update();
	}
	@Override
	public void draw(Graphics g) {
		for (Menubotton mb : buttons)
			mb.draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (Menubotton mb : buttons) {
			if (isIn(e, mb)) {
				mb.setMousePressed(true);
			}
		}
			
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (Menubotton mb : buttons) {
			if (isIn(e, mb)) {
				if (mb.isMousePressed())
					mb.applyGamestate();
				break;
			}
		}
		resetButtons();
	}

	private void resetButtons() {
		for (Menubotton mb : buttons)
			mb.resetBools();	
	}

	@Override
	public void mouseMove(MouseEvent e) {
		for (Menubotton mb : buttons)
			mb.setMouseOver(false);

		for (Menubotton mb : buttons)
			if (isIn(e, mb)) {
				mb.setMouseOver(true);
				break;
			}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			Gamestates.state = Gamestates.PLAYING;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
