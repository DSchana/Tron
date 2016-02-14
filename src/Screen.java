// Menu.java

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Screen extends JPanel implements KeyListener {
	private Image background;
	private ArrayList<Button> buttonEntities;
	private boolean isActive;
	private boolean[] keys;
	private boolean isKeyDown;
	private int buttSelection;

	public Screen(String backDir) {
		this.buttonEntities = new ArrayList<Button>();
		this.background = new ImageIcon(backDir).getImage();
		this.buttSelection = 0;
		this.isKeyDown = false;
	}

	public void render(Graphics g) {
		g.drawImage(this.background, 0, 0, this);
		for (Button entity : buttonEntities) {
			entity.render(g);
		}

	}

	public void update(boolean[] nKeys) {
		keys = nKeys;

		// Put this in a friggin key manager
		if (!keys[KeyEvent.VK_DOWN] && !keys[KeyEvent.VK_UP] && !keys[KeyEvent.VK_ENTER]) {
			this.isKeyDown = false;
		}

		if (keys[KeyEvent.VK_DOWN] && !this.isKeyDown) {
			if (this.buttSelection + 1 > buttonEntities.size() - 1) {
				this.buttSelection = 0;
			}
			else {
				buttSelection++;
			}
			this.isKeyDown = true;
		}
		else if (keys[KeyEvent.VK_UP] && !this.isKeyDown) {
			if (this.buttSelection - 1 < 0) {
				this.buttSelection = buttonEntities.size() - 1;
			}
			else {
				buttSelection--;
			}
			this.isKeyDown = true;
		}

		for (int i = 0; i < buttonEntities.size(); i++) {
			if (i == this.buttSelection) {
				buttonEntities.get(i).highlight(true);
			}
			else {
				buttonEntities.get(i).highlight(false);
			}

			if (keys[KeyEvent.VK_ENTER] && !this.isKeyDown && buttonEntities.get(i).getHighlight()) {
				buttonEntities.get(i).trigger();
				this.isKeyDown = true;
			}
		}
	}

	// Set methods
	public void addEntity(Button button) {
		this.buttonEntities.add(button);
	}

	// KeyListener
	public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }
    
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}