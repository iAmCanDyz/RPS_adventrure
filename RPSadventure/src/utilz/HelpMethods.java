package utilz;

import java.awt.geom.Rectangle2D;

import game.Game;

public class HelpMethods {

	public static boolean canMovehere(float x, float y, float width, float height, int[][] lvldata) {
		
		if(!isSolid(x, y, lvldata))
			if(!isSolid(x+width, y+height, lvldata))
				if(!isSolid(x+width, y, lvldata))
					if(!isSolid(x, y+height, lvldata))
						return true;
		return false;
	}
	
	private static boolean isSolid(float x, float y, int[][] lvldata) {
		if(x < 0 || x >= Game.GAME_WIDTH) {
			return true;
		}
		if(y < 0 || y >= Game.GAME_HEIGHT) {
			return true;
		}
		
		float xIndex = x / Game.TILES_SIZE;
		float yIndex = y / Game.TILES_SIZE;
		
		int value = lvldata[(int) yIndex][(int) xIndex];
		
		if(value >= 5 || value < 0 || value != 4) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public static float GetentityxPosNextTowall(Rectangle2D.Float hitbox, float xSpeed) {
		int currentTile = (int)(hitbox.x / Game.TILES_SIZE);
		if(xSpeed > 0 ) {
			int tileXpos = currentTile * Game.TILES_SIZE;
			int xoffset = (int)(Game.TILES_SIZE - hitbox.width);
			return tileXpos + xoffset - 1;
		} else {
			return currentTile * Game.TILES_SIZE;
		}
	}
	
	public static float GetentityYPosunderRooforAbovefloor(Rectangle2D.Float hitbox, float airSpeed) {
		int currentTile = (int)(hitbox.y / Game.TILES_SIZE);
		if(airSpeed > 0) {
			int tileYpos = currentTile * Game.TILES_SIZE;
			int yoffset = (int)(Game.TILES_SIZE - hitbox.height);
			return tileYpos + yoffset - 1;
		}else {
			return currentTile * Game.TILES_SIZE;
		}
	}
	
	public static boolean IsentityOnfloor(Rectangle2D.Float hitbox, int[][] lvldata) {
		if(!isSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvldata))
			if(!isSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvldata))
				return false;
		return true;
	}
	
	
}
