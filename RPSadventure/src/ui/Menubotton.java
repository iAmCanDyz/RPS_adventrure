package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamestate.Gamestates;
import utilz.Loadsave;
import static utilz.constance.UI.Buttons.*;

public class Menubotton {

	private int xPos, yPos, rowIndex, index;
	private int xOffsetcenter = B_WIDTH / 2;
	private Gamestates state;
	private BufferedImage[] imgs;
	private boolean mouseOver, mousePressed;
	private Rectangle bounds;
	
	public Menubotton(int xPos, int yPos, int rowIndex, Gamestates state) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.rowIndex = rowIndex;
		this.state = state;
		loadImgs();
		initBounds();
	}
	private void initBounds() {
		bounds = new Rectangle(xPos - xOffsetcenter, yPos, B_WIDTH, B_HEIGHT);
	}
	private void loadImgs() {
		imgs = new  BufferedImage[3];
		BufferedImage temp = Loadsave.GetSpriteAtlas(Loadsave.MENU_BOTTON);
		for(int i = 0; i<imgs.length; i++)
			imgs[i] = temp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
	}
	
	public void draw(Graphics g) {
		g.drawImage(imgs[index], xPos - xOffsetcenter, yPos, B_WIDTH, B_HEIGHT, null);
	}
	
	public void update() {
		index = 0;
		if(mouseOver)
			index = 1;
		if(mousePressed)
			index = 2;
	}
	
	public boolean isMouseOver() {
		return mouseOver;
	}
	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}
	public boolean isMousePressed() {
		return mousePressed;
	}
	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void applyGamestate() {
		Gamestates.state = state;
	}
	
	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}
	
}
