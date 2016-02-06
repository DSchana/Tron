// Menu.java

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Screen {
	private Image background;
	private ArrayList<Button> buttons;

	public Screen(String backDir) {
		this.buttons = new ArrayList<Button>();
		this.background = new ImageIcon(backDir).getImage();
	}

	public Screen(String backDir, ArrayList<Button> buttons) {
		this.buttons = buttons;
		this.background = new ImageIcon(backDir).getImage();
	}

	public void render(Graphics g) {
		g.drawImage(this.background, 0, 0, this);
		for (Button butt : buttons) {
			butt.render(g);
		}
	}

	public void update(Point mPoint, boolean isMouseDown) {
		for (Button butt : buttons) {
			butt.highlight(butt.isCollide(mPoint));
			if (isMouseDown && butt.getHighlight()) {
				butt.trigger();
			}
		}
	}

	// Set methods
	public void addButton(Button button) {
		this.buttons.add(button);
	}
}