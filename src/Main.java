// Main.java

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame implements ActionListener {
	Timer gameTimer;

	Image board;

	public Main() {
		super("Pac-Man");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 520);

		gameTimer = new Timer(10, this);
		gameTimer.start();

		board = new ImageIcon("../Resources/Board.png").getImage();

		setResizable(false);
		setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(board, 0, 0, this);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		repaint();
	}

	public static void main(String[] args) {
		KeyManager.init();
		Main frame = new Main();
	}
}