// ScreenManager.java
// Dilpreet Chana
// Class ScreenManager - Store, update and control rendering of game Screens

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class ScreenManager extends JPanel {
	private static Map<String, Screen> screenDic;
	private static String active, previous;

	public static void start() {
		ScreenManager.screenDic = new HashMap<String, Screen>();
		ScreenManager.active = "";
	}

	// Add screen to manage
	public static void add(String id, String imDir) {
		ScreenManager.screenDic.put(id, new Screen(imDir));
	}

	// Add button to screen
	public static void addEntity(String id, Button button) {
		ScreenManager.screenDic.get(id).addEntity(button);
	}

	public static void show(String id, Graphics g, boolean[] keys, Player p1, Player p2) {
		ScreenManager.screenDic.get(id).update(keys, p1, p2);
		ScreenManager.screenDic.get(id).render(g);
	}

	public static String getActive() {
		return active;
	}

	public static void activate(String id) {
		if (ScreenManager.active.equals("")) {
			ScreenManager.previous = id;
		}
		else {
			ScreenManager.previous = ScreenManager.active;
		}
		active = id;
	}

	public static String getPrevious() {
		return ScreenManager.previous;
	}
}