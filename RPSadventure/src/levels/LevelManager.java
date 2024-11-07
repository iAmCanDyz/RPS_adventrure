package levels;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.Game;
import utilz.Loadsave;

public class LevelManager {

	private Game game;
	private BufferedImage[] levelSprite;
	private Level levelnormal;
	
	public LevelManager(Game game) {
		this.game = game;
		importplantFrom();
		levelnormal = new Level(Loadsave.GetLeveldata());
	}
	
	private void importplantFrom() {
		
		BufferedImage img = Loadsave.GetSpriteAtlas(Loadsave.LEVEL_ATLAS);
		levelSprite = new BufferedImage[10];
		for(int j = 0;j < 2; j++) 
			for(int i = 0; i < 5 ; i++) {
				int index = j*5+i;
				levelSprite[index] = img.getSubimage(i*32, j*32, 32, 32);
			}
	}

	public void draw(Graphics g) {
		for(int j=0; j < Game.TILES_IN_HEIGHT; j++)
			for(int i=0; i < Game.TILES_IN_WIDTH; i++) {
				int index = levelnormal.getSpriteIndex(i, j);
				g.drawImage(levelSprite[index], Game.TILES_SIZE*i, Game.TILES_SIZE*j,Game.TILES_SIZE,Game.TILES_SIZE, null);
			}
		
	}

	public void update() {
		
	}
	
	public Level getCurrentLevel() {
		return levelnormal;
	}

}
