package utilz;

import game.Game;

public class constance {
	
	public static class UI{
		public static class Buttons{
			public static final int B_WIDTH_DEFAULT = 140;
			public static final int B_HEIGHT_DEFAULT = 56;
			public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
			public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
		}
	}
	
	public static class Directions{
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}
	
	public static class Playerconstance{
		public static final int IDLE = 0;
		public static final int RIGHT_RUNNING = 1;
		public static final int LEFT_RUNNING = 2;
		public static final int JUMP = 3;
		public static final int ATTACK = 4;
		public static final int FALLING = 5;
		
		public static int GetSpriteAmount(int player_action) {
			
			switch(player_action) {
			case IDLE:
				return 6;
			case LEFT_RUNNING:
				return 5;
			case RIGHT_RUNNING:
				return 5;
			case JUMP:
				return 4;
			case ATTACK:
				return 4;
			case FALLING:
				return 1;
			default:
				return 1;
			}
		}
	}
}
