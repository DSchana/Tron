// Button.java

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Button {
	private int x, y;
	private boolean isHighlighted;
	private Image img, imgHighlighted;
	private Action action

	public Button(int x, int y, String imDir, Action action) {
		this.x = x;
		this.y = y;
		this.isHighlighted = false;
		this.img = new ImageIcon(imDir + ".png").getImage();
		this.imgHighlighted = new ImageIcon(imDir + "high.png");
		this.action = action;
	}

	public void render(Graphics g) {
		if (this.isHighlighted) {
			g.drawImage(this.imgHighlighted, this.x, this.y, this);
		}
		else {
			g.drawImage(this.img, this.x, this.y, this);
		}
	}

	public void trigger() {
		Action.trigger(this.action);
	}

	// Get methods
	public boolean getHighlight() {
		return this.isHighlighted;
	}

	public boolean isCollide(Point mPoint) {
		// TODO: Check if image has width and height properties
		if (mPoint.x > this.x && mPoint.x < this.img.width && mPoint.y > this.y && mPoint.y < this.img.height) {
			return true;
		}
		return false;
	}

	// Set methods
	public void highlight(boolean value) {
		this.isHighlighted = value;
	}
}