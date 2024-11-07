package gamestate;

import java.awt.event.MouseEvent;

import game.Game;
import ui.Menubotton;

public class State {

	protected Game game;
	public State(Game game) {
		this.game = game;
	}
	
	public boolean isIn(MouseEvent e, Menubotton mb) {
		return mb.getBounds().contains(e.getX(), e.getY());
	}
	
	public Game getGame() {
		return game;
	}
}
