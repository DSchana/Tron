// Player.java

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Player extends JFrame {
	int p_num;
	int x, y;
	int speed;
	Color colour;
	Direction direction;
	Image img;

	public Player(int p_num, int x, int y, Color colour) {
		this.speed = 5;
		this.p_num = p_num;
		this.x = x;
		this.y = y;
		this.colour = colour;
		if (p_num == 1) {
			this.direction = Direction.LEFT;
		}
		else {
			this.direction = Direction.RIGHT;
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
	}

	public void leaveTrail(Graphics g) {
		g.setColor(this.colour);
		g.fillRect(this.x, this.y, 5, 5);
	}

	public void render(Graphics g) {
		g.drawImage(this.img, this.x, this.y, this);
	}

	public boolean isColliding() {
		if (this.x < 40 || this.x > 556 || this.y < 70 || this.y > 466) {
			return true;
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
	}
}