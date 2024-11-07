package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import gamestate.Gamestates;
import game.gamePanel;

public class Mouseinput implements MouseListener, MouseMotionListener {

	private gamePanel gamePanel;

	public Mouseinput(gamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		switch(Gamestates.state) {
		case MENU:
			gamePanel.getGame().getMenu().mouseMove(e);
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().mouseMove(e);
			break;
		default:
			break;
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch(Gamestates.state) {
		case PLAYING:
			gamePanel.getGame().getPlaying().mouseClicked(e);
			break;
		default:
			break;
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch(Gamestates.state) {
		case MENU:
			gamePanel.getGame().getMenu().mousePressed(e);
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().mousePressed(e);
			break;
		default:
			break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch(Gamestates.state) {
		case MENU:
			gamePanel.getGame().getMenu().mouseReleased(e);
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().mouseReleased(e);
			break;
		default:
			break;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}