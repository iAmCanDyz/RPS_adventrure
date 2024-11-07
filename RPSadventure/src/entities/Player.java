package entities;

import static utilz.constance.Playerconstance.GetSpriteAmount;
import static utilz.constance.Playerconstance.*;
import static utilz.HelpMethods.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Game;
import utilz.Loadsave;

public class Player extends Entity {

	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 28;
	private int playerAction = IDLE;
	private boolean moving = false, attacking = false;
	private boolean left, up, right, down, jump;
	private float playerSpeed = 2.0f;
	private int[][] lvldata;
	private float xDrawoffset = 11 * Game.SCALE;
	private float yDrawoffset = 10 * Game.SCALE;
	
	//jump
	private float airSpeed = 0f;
	private float gravity = 0.04f * Game.SCALE;
	private float jumpSpeed = -2.25f * Game.SCALE;
	private float fallSpeedafterCollision = 0.5f * Game.SCALE;
	private boolean inAir = false;

	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();
		initHitbox(x, y, 10 * Game.SCALE, 18 * Game.SCALE);
		
	}

	public void update() {
		updatePos();
		updateAnimationTick();
		setAnimation();
	}

	public void render(Graphics g) {
		g.drawImage(animations[playerAction][aniIndex], (int) (hitbox.x - xDrawoffset) , (int) (hitbox.y - yDrawoffset), width, height, null);
	}

	private void updateAnimationTick() {

		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(playerAction)) {
				aniIndex = 0;
				attacking = false;
			}
		}

	}

	private void setAnimation() {

		int startAni = playerAction;
		if (moving) {
			if (left) {
				playerAction = LEFT_RUNNING;
			} else if (right) {
				playerAction = RIGHT_RUNNING;
			} else if (up) {

			} else if (down) {

			}
		} else {
			playerAction = IDLE;
		}
		
		if(attacking)
			playerAction = ATTACK;
		
		if(inAir) {
			if(airSpeed < 0)
				playerAction = JUMP;
			else 
				playerAction = FALLING;
		}
		
		if(startAni != playerAction) {
			resetAniTick();
		}
	}

	private void resetAniTick() {
		aniTick=0;
		aniIndex=0;
	}

	private void updatePos() {
		moving = false;

		if(jump)
			jump();
		if(!left && !right && !inAir)
			return;
		
		float xSpeed = 0;

		if (left)
			xSpeed -= playerSpeed;
		if (right)
			xSpeed += playerSpeed;
		
		if(!inAir) 
			if(!IsentityOnfloor(hitbox, lvldata)) 
				inAir = true;

		if(inAir) {
			
			if(canMovehere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvldata)) {
				hitbox.y += airSpeed;
				airSpeed += gravity;
				upDateXPos(xSpeed);
			} else {
				hitbox.y = GetentityYPosunderRooforAbovefloor(hitbox, airSpeed);
				if(airSpeed > 0) {
					resetInair();
				}else {
					airSpeed = fallSpeedafterCollision;
				}
				upDateXPos(xSpeed);
			}
			
		} else 
			upDateXPos(xSpeed);
		moving = true;
		
		
	}

	private void jump() {
		if(inAir)
			return;
		inAir = true;
		airSpeed = jumpSpeed;
	}

	private void resetInair() {
		inAir = false;
		airSpeed = 0;
	}

	private void upDateXPos(float xSpeed) {
		if(canMovehere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvldata)) {
			hitbox.x += xSpeed;
		}else {
			hitbox.x = GetentityxPosNextTowall(hitbox, xSpeed);
		}
	}

	private void loadAnimations() {

		BufferedImage img = Loadsave.GetSpriteAtlas(Loadsave.PLAYER_ATLAS);
		animations = new BufferedImage[6][6];

		for (int j = 0; j < animations.length; j++) {
			for (int i = 0; i < animations[j].length; i++) {
				animations[j][i] = img.getSubimage(i * 32, j * 32, 32, 32);
			}
		}

	}

	public void loadLvldata(int[][] lvldata) {
		this.lvldata = lvldata;
		if(!IsentityOnfloor(hitbox, lvldata))
			inAir = true;
	}

	public void resetDirBooleans() {
		left = false;
		right = false;
		up = false;
		down = false;
	}
	
	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
	
	public void setJump(boolean jump) {
		this.jump = jump;
	}

}
