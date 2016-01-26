// GamePanel.java

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class KeyManager implements KeyListener {
	static private boolean[] keys;
	static private boolean[] moveKeys;

	public static void init() {
		KeyManager.keys = new boolean[KeyEvent.KEY_LAST + 1];
		KeyManager.moveKeys = new boolean[4];
	}

	public static boolean[] getMoveKeys() {
		Arrays.fill(KeyManager.moveKeys, false);

		if (KeyManager.keys[KeyEvent.VK_RIGHT]) {
			KeyManager.moveKeys[0] = true;
		}
		if (KeyManager.keys[KeyEvent.VK_LEFT]) {
			KeyManager.moveKeys[1] = true;
		}
		if (KeyManager.keys[KeyEvent.VK_UP]) {
			KeyManager.moveKeys[2] = true;
		}
		if (KeyManager.keys[KeyEvent.VK_DOWN]) {
			KeyManager.moveKeys[3] = true;
		}

		return KeyManager.moveKeys;
	}

	public void keyTyped(KeyEvent e) { }

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
}