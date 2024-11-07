package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import game.Game;

public class Loadsave {
	
	public static final String PLAYER_ATLAS = "mainC.png";
	public static final String LEVEL_ATLAS = "plantfrom.png";
	public static final String NORMAL_LEVEL = "normal.png";
	public static final String MENU_BOTTON = "button_atlas.png";
	
	public static BufferedImage GetSpriteAtlas(String fileName) {
		
		BufferedImage img = null	;
		InputStream is = Loadsave.class.getResourceAsStream("/" + fileName);
		
		try {
			 img = ImageIO.read(is);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}
	
	public static int[][] GetLeveldata() {
		int [][] lvldata = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
		BufferedImage img = GetSpriteAtlas(NORMAL_LEVEL);
		
		for(int j = 0; j< img.getHeight(); j++)
			for(int i = 0; i< img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value;
				if(color.equals(new Color(107, 254, 107))) {
					value = 0;
				} else if(color.equals(new Color(193, 255, 193))) {
					value = 1;
				} else if(color.equals(new Color(0, 255, 0))) {
					value = 2;
				} else if(color.equals(new Color(136, 94, 62))) {
					value = 3;
				} else if(color.equals(new Color(18, 91, 55))) {
					value = 5;
				} else if(color.equals(new Color(67, 130, 98))) {
					value = 6;
				} else {
					value = 4;
				}
					
				lvldata[j][i] = value;
			}
		return lvldata;
	}
	
}

