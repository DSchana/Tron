// ScreenManager.java

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

	public static void add(String id, String imDir) {
		ScreenManager.screenDic.put(id, new Screen(imDir));
	}

	public static void addEntity(String id, Button button) {
		ScreenManager.screenDic.get(id).addEntity(button);
	}

	public static void show(String id, Graphics g, boolean[] keys) {
		ScreenManager.screenDic.get(id).update(keys);
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