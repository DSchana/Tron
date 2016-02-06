// Player.java

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Player extends JFrame {
	int p_num;
	int x, y, init_x, init_y;
	int speed;
	Color colour;
	Color enemyColour;
	Direction direction, init_direction;
	Image img;
	boolean newDirectionMoved;  // Is set to false if the player changes direction but has not moved in that direction yet

	public Player(int p_num, int x, int y, Color colour) {
		this.speed = 5;
		this.p_num = p_num;
		this.x = x;
		this.y = y;
		this.init_x = x;
		this.init_y = y;
		this.colour = colour;
		if (p_num == 1) {
			this.direction = Direction.LEFT;
			this.init_direction = Direction.LEFT;
			this.enemyColour = new Color(51, 204, 153);
		}
		else {
			this.direction = Direction.RIGHT;
			this.init_direction = Direction.RIGHT;
			this.enemyColour = new Color(255, 102, 255);
		}

		this.img = new ImageIcon("../Resources/p" + p_num + ".png").getImage();
	}

	public void move() {
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
		}

		g.setColor(this.colour);
		if (this.newDirectionMoved) {
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
	}

	public void render(Graphics g) {
		g.drawImage(this.img, this.x, this.y, this);
	}

	public void reset() {
		this.direction = this.init_direction;
		this.x = this.init_x;
		this.y = this.init_y;
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

	// Set methods
	public void changeDirection(Direction newDirect) {
		this.direction = newDirect;
		this.newDirectionMoved = false;
	}
}