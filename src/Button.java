// Button.java

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Button extends JFrame {
	private int x, y, width, height;
	private boolean isHighlighted;
	private Image img, imgHighlighted;
	private String action;

	public Button(String imDir, String action, int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.isHighlighted = false;
		this.img = new ImageIcon(imDir + ".png").getImage();
		this.imgHighlighted = new ImageIcon(imDir + " high.png").getImage();
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
		if (action.equals("start")) {
			ScreenManager.activate("game");
		}
		else if (action.equals("instruct")) {
			ScreenManager.activate("instruction");
		}
		else if (action.equals("back")) {
			ScreenManager.activate(ScreenManager.getPrevious());
		}
	}

	// Get methods
	public boolean getHighlight() {
		return this.isHighlighted;
	}

	// Set methods
	public void highlight(boolean value) {
		this.isHighlighted = value;
	}
}