// Main.java

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Tron extends JFrame implements ActionListener {
	Timer gameTimer;
	GamePanel game;

	public Tron() {
		super("Tron");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 520);

		game = new GamePanel(this);
		add(game);

		gameTimer = new Timer(30, this);

		setResizable(false);
		setVisible(true);
	}

	public void start() {
		gameTimer.start();
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		game.update();
		game.repaint();
	}

	public static void main(String[] args) {
		Tron frame = new Tron();
	}
}