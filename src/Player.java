// Player.java
// Dilpreet Chana
// Class Player - Model of the tron bike with and without AI

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Player extends JFrame {
	int p_num;
	int x, y, init_x, init_y;
	int speed;
	int boostCount, boostTime;
	int score;
	Color colour;
	Color enemyColour;
	Color boostColour;
	Direction direction, init_direction;
	Image img;
	boolean newDirectionMoved;  // Is set to false if the player changes direction but has not moved in that direction yet
	boolean boosting;
	boolean isAI;
	Random rand;

	public Player(int p_num, int x, int y, Color colour) {
		this.isAI = false;
		this.speed = 5;
		this.p_num = p_num;
		this.x = x;
		this.y = y;
		this.init_x = x;
		this.init_y = y;
		this.colour = colour;
		this.boosting = false;
		this.boostCount = 3;
		this.boostTime = 0;
		this.rand = new Random();
		if (p_num == 1) {
			this.direction = Direction.LEFT;
			this.init_direction = Direction.LEFT;
			this.enemyColour = new Color(51, 204, 153);
			this.boostColour = new Color(190, 86, 193);
		}
		else {
			this.direction = Direction.RIGHT;
			this.init_direction = Direction.RIGHT;
			this.enemyColour = new Color(255, 102, 255);
			this.boostColour = new Color(47, 157, 121);
		}

		this.img = new ImageIcon("../Resources/p" + p_num + ".png").getImage();
	}

	// AI Constructor
	public Player(int p_num, int x, int y, Color colour, boolean isAI) {
		this.isAI = true;
		this.speed = 5;
		this.p_num = p_num;
		this.x = x;
		this.y = y;
		this.init_x = x;
		this.init_y = y;
		this.colour = colour;
		this.boosting = false;
		this.boostCount = 3;
		this.boostTime = 0;
		if (p_num == 1) {
			this.direction = Direction.LEFT;
			this.init_direction = Direction.LEFT;
			this.enemyColour = new Color(51, 204, 153);
			this.boostColour = new Color(190, 86, 193);
		}
		else {
			this.direction = Direction.RIGHT;
			this.init_direction = Direction.RIGHT;
			this.enemyColour = new Color(255, 102, 255);
			this.boostColour = new Color(47, 157, 121);
		}

		this.img = new ImageIcon("../Resources/p" + p_num + ".png").getImage();
	}

	// Get the length of blank space in specified direction
	private int getBlankLength(int[][] board, Direction direct) {
		int count = 0;
		int x = this.x;
		int y = this.y;

		if (direct == Direction.UP) {
			while (true) {
				if (x < 103 || x >= 0 || y < 80 || y >= 0) {
					break;
				}
				else if (board[x][y] != 0) {
					break;
				}
				else {
					y--;
					count++;
				}
			}
		}
		else if (direct == Direction.DOWN) {
			while (true) {
				if (x < 103 || x >= 0 || y < 80 || y >= 0) {
					break;
				}
				else if (board[x][y] != 0) {
					break;
				}
				else {
					y++;
					count++;
				}
			}
		}
		else if (direct == Direction.LEFT) {
			while (true) {
				if (x < 103 || x >= 0 || y < 80 || y >= 0) {
					break;
				}
				else if (board[x][y] != 0) {
					break;
				}
				else {
					x--;
					count++;
				}
			}
		}
		else if (direct == Direction.RIGHT) {
			while (true) {
				if (x < 103 || x >= 0 || y < 80 || y >= 0) {
					break;
				}
				else if (board[x][y] != 0) {
					break;
				}
				else {
					x++;
					count++;
				}
			}
		}
		return count;
	}

	// Choose direction to move when approaching walls
	public void think(int[][] board) {
		if (this.direction == Direction.UP) {
			if ((this.y-70)/5 - 3 <= 0) {
				if (getBlankLength(board, Direction.LEFT) >= getBlankLength(board, Direction.RIGHT)) {
					this.direction = Direction.LEFT;
				}
				else {
					this.direction = Direction.RIGHT;
				}
			}
			else if (board[(this.x-40)/5][(this.y-70)/5 - 3] != 0) {
				if (getBlankLength(board, Direction.LEFT) >= getBlankLength(board, Direction.RIGHT)) {
					this.direction = Direction.LEFT;
				}
				else {
					this.direction = Direction.RIGHT;
				}
			}
		}
		if (this.direction == Direction.DOWN) {
			if ((this.y-70)/5 + 3 > 80) {
				if (getBlankLength(board, Direction.LEFT) >= getBlankLength(board, Direction.RIGHT)) {
					this.direction = Direction.LEFT;
				}
				else {
					this.direction = Direction.RIGHT;
				}
			}
			else if (board[(this.x-40)/5][(this.y-70)/5 + 3] != 0) {
				if (getBlankLength(board, Direction.LEFT) >= getBlankLength(board, Direction.RIGHT)) {
					this.direction = Direction.LEFT;
				}
				else {
					this.direction = Direction.RIGHT;
				}
			}
		}
		if (this.direction == Direction.LEFT) {
			if ((this.x-40)/5 - 3 <= 0) {
				if (getBlankLength(board, Direction.UP) >= getBlankLength(board, Direction.DOWN)) {
					this.direction = Direction.UP;
				}
				else {
					this.direction = Direction.DOWN;
				}
			}
			else if (board[(this.x-40)/5 - 3][(this.y-70)/5] != 0) {
				if (getBlankLength(board, Direction.UP) >= getBlankLength(board, Direction.DOWN)) {
					this.direction = Direction.UP;
				}
				else {
					this.direction = Direction.DOWN;
				}
			}
		}
		if (this.direction == Direction.RIGHT) {
			if ((this.x-40)/5 + 3 > 103) {
				if (getBlankLength(board, Direction.UP) >= getBlankLength(board, Direction.DOWN)) {
					this.direction = Direction.UP;
				}
				else {
					this.direction = Direction.DOWN;
				}
			}
			else if (board[(this.x-40)/5 + 3][(this.y-70)/5] != 0) {
				if (getBlankLength(board, Direction.UP) >= getBlankLength(board, Direction.DOWN)) {
					this.direction = Direction.UP;
				}
				else {
					this.direction = Direction.DOWN;
				}
			}
		}
	}

	public void move(int[][] board) {
		if (this.boosting) {
			this.speed = 10;
			this.boostTime++;
		}
		else {
			this.speed = 5;
		}

		if (this.boostTime == 10) {
			this.boosting = false;
			this.boostTime = 0;
		}

		if (this.isAI) {
			think(board);
		}

		if (this.direction == Direction.UP) {
			this.y -= this.speed;
		}
		if (this.direction == Direction.DOWN) {
			this.y += this.speed;
		}
		if (this.direction == Direction.LEFT) {
			this.x -= this.speed;
		}
		if (this.direction == Direction.RIGHT) {
			this.x += this.speed;
		}
		this.newDirectionMoved = true;
	}

	public void leaveTrail(Graphics g, int[][] board) {
		if ((this.x-40)/5 >= 0 && (this.x-40)/5 < 103 && (this.y-70)/5 >= 0 && (this.y-70)/5 < 80) {
			board[(this.x-40)/5][(this.y-70)/5] = this.p_num;
			
			if (this.boosting) {
				if (this.direction == Direction.UP) {
					board[(this.x-40)/5][(this.y-70)/5 + 1] = this.p_num;
				}
				else if (this.direction == Direction.DOWN) {
					board[(this.x-40)/5][(this.y-70)/5 - 1] = this.p_num;
				}
				else if (this.direction == Direction.LEFT) {
					board[(this.x-40)/5 + 1][(this.y-70)/5] = this.p_num;
				}
				else if (this.direction == Direction.RIGHT) {
					board[(this.x-40)/5 - 1][(this.y-70)/5] = this.p_num;
				}
			}
		}

		if (this.newDirectionMoved) {
			if (!this.boosting) {
				g.setColor(this.colour);

				if (this.direction == Direction.UP) {
					g.fillRect(this.x, this.y+5, 5, 5);
				}
				else if (this.direction == Direction.DOWN) {
					g.fillRect(this.x, this.y-5, 5, 5);
				}
				else if (this.direction == Direction.LEFT) {
					g.fillRect(this.x+5, this.y, 5, 5);
				}
				else if (this.direction == Direction.RIGHT) {
					g.fillRect(this.x-5, this.y, 5, 5);
				}
			}
			else {
				g.setColor(this.boostColour);

				if (this.direction == Direction.UP) {
					g.fillRect(this.x, this.y+5, 5, 10);
				}
				else if (this.direction == Direction.DOWN) {
					g.fillRect(this.x, this.y-5, 5, 10);
				}
				else if (this.direction == Direction.LEFT) {
					g.fillRect(this.x+5, this.y, 10, 5);
				}
				else if (this.direction == Direction.RIGHT) {
					g.fillRect(this.x-5, this.y, 10, 5);
				}
			}
		}
	}

	public void render(Graphics g) {
		g.drawImage(this.img, this.x, this.y, this);
	}

	public void reset() {
		this.direction = this.init_direction;
		this.x = this.init_x;
		this.y = this.init_y;
		this.boostCount = 3;
		this.boosting = false;
		this.boostTime = 0;
	}

	public boolean isColliding(int[][] board) {
		if (this.x < 40 || this.x > 556 || this.y < 70 || this.y > 466) {
			return true;
		}
		else if ((this.x-40)/5 >= 0 && (this.x-40)/5 < 103 && (this.y-70)/5 >= 0 && (this.y-70)/5 < 80) {
			if (board[(this.x-40)/5][(this.y-70)/5] != 0) {
				return true;
			}
		}
		return false;
	}

	// Get methods
	public Color getColour() {
		return this.colour;
	}

	public Direction getDirection() {
		return this.direction;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getScore() {
		return this.score;
	}

	public int getBoostCount() {
		return this.boostCount;
	}

	public boolean isBoosting() {
		return this.boosting;
	}

	public boolean isAI() {
		return this.isAI;
	}

	// Set methods
	public void changeDirection(Direction newDirect) {
		this.direction = newDirect;
		this.newDirectionMoved = false;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void resetScore() {
		this.score = 0;
	}

	public void boost() {
		if (boostCount > 0) {
			this.boosting = true;
			this.boostCount--;
		}
	}

	public void makeAI(boolean ai) {
		this.isAI = ai;
	}
}