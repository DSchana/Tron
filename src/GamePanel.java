// GamePanel.java
// Dilpreet Chana
// Class GamePanel - Handle game startup and setup of objects

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class GamePanel extends JPanel implements KeyListener {
	private boolean[] keys;
	private boolean crashed;
	private Image back, boost1, boost2, boostBlank;
	private Tron mainFrame;
	private int[][] board;  // Board for collision detection
	private Player p1, p2;
	private Screen splashScreen;
	
	public GamePanel(Tron m) {
		ScreenManager.start();

		// Fill board with 0s to reset
		board = new int[103][80];
		for (int i = 0; i < 103; i++) {
			Arrays.fill(board[i], 0);
		}

		ScreenManager.add("splash", "../Resources/Splash Screen.png");
		ScreenManager.add("instruction", "../Resources/Instruct.png");
		ScreenManager.add("game over", "../Resources/Game Over.png");
		ScreenManager.add("player selection", "../Resources/Player Selection.png");

		// Add buttons
		ScreenManager.addEntity("splash", new Button("../Resources/Start", "start", 180, 355, 252, 43));
		ScreenManager.addEntity("splash", new Button("../Resources/Instruction", "instruct", 180, 408, 252, 43));

		ScreenManager.addEntity("instruction", new Button("../Resources/Back", "back", 180, 400, 252, 43));

		ScreenManager.addEntity("player selection", new Button("../Resources/1 player", "single", 180, 355, 252, 43));
		ScreenManager.addEntity("player selection", new Button("../Resources/2 player", "multi", 180, 408, 252, 43));

		// Activate aplash screen as initial screen
		ScreenManager.activate("splash");

		keys = new boolean[KeyEvent.KEY_LAST+1];
		back = new ImageIcon("../Resources/Board.png").getImage();
		boost1 = new ImageIcon("../Resources/boost 1.png").getImage();
		boost2 = new ImageIcon("../Resources/boost 2.png").getImage();
		boostBlank = new ImageIcon("../Resources/boost blank.png").getImage();
		p1 = new Player(1, 470, 265, new Color(255, 102, 255));
		p2 = new Player(2, 130, 265, new Color(51, 204, 153));
		mainFrame = m;
		crashed = true;
		setSize(600,520);
        addKeyListener(this);
	}
	
    public void addNotify() {
        super.addNotify();
        requestFocus();
        mainFrame.start();
    }
	
	public void update() {
		if (ScreenManager.getActive().equals("game")){
			// Activate boost
			if (keys[KeyEvent.VK_SPACE] && !p1.isBoosting() && !p1.isAI()) {
				p1.boost();
			}
			if (keys[KeyEvent.VK_SHIFT] && !p2.isBoosting() && !p2.isAI()) {
				p2.boost();
			}

			// Player 1
			if (!p1.isAI()) {
				if(keys[KeyEvent.VK_RIGHT] && p1.getDirection() != Direction.LEFT){
					p1.changeDirection(Direction.RIGHT);
				}
				else if(keys[KeyEvent.VK_LEFT] && p1.getDirection() != Direction.RIGHT){
					p1.changeDirection(Direction.LEFT);
				}
				else if(keys[KeyEvent.VK_UP] && p1.getDirection() != Direction.DOWN){
					p1.changeDirection(Direction.UP);
				}
				else if(keys[KeyEvent.VK_DOWN] && p1.getDirection() != Direction.UP){
					p1.changeDirection(Direction.DOWN);
				}
			}

			// Player 2
			if (!p2.isAI()) {
				if(keys[KeyEvent.VK_D] && p2.getDirection() != Direction.LEFT){
					p2.changeDirection(Direction.RIGHT);
				}
				else if(keys[KeyEvent.VK_A] && p2.getDirection() != Direction.RIGHT){
					p2.changeDirection(Direction.LEFT);
				}
				else if(keys[KeyEvent.VK_W] && p2.getDirection() != Direction.DOWN){
					p2.changeDirection(Direction.UP);
				}
				else if(keys[KeyEvent.VK_S] && p2.getDirection() != Direction.UP){
					p2.changeDirection(Direction.DOWN);
				}
			}

			p1.move(board);
			p2.move(board);

			checkCollision();
		}

		if (ScreenManager.getActive().equals("game over")) {
			if (keys[KeyEvent.VK_SPACE]) {
				ScreenManager.activate("splash");
			}
		}
	}

	public void checkCollision() {
		if (p1.isColliding(board) && p2.isColliding(board)) {
			System.out.println("Tie");
			newGame();
		}
		else if (p1.isColliding(board)) {
			System.out.println("Player 1 wins!");
			p1.setScore(p1.getScore() + 1);
			newGame();
		}
		else if (p2.isColliding(board)) {
			System.out.println("Player 2 wins!");
			p2.setScore(p2.getScore() + 1);
			newGame();
		}
	}
	
    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }
    
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
    
    public void paintComponent(Graphics g){
    	if (ScreenManager.getActive().equals("splash")) {
    		ScreenManager.show("splash", g, keys, p1, p2);
    	}
    	else if (ScreenManager.getActive().equals("instruction")) {
    		ScreenManager.show("instruction", g, keys, p1, p2);
    	}
    	else if (ScreenManager.getActive().equals("player selection")) {
    		ScreenManager.show("player selection", g, keys, p1, p2);
    	}
    	else if (ScreenManager.getActive().equals("game")) {
	    	if (crashed) {
				g.drawImage(back, 0, 0, this);

				// Show score
	    		g.drawImage(new ImageIcon("../Resources/Score/" + p1.getScore() + "1.png").getImage(), 190, 40, this);
	    		g.drawImage(new ImageIcon("../Resources/Score/" + p2.getScore() + "2.png").getImage(), 392, 40, this);

				crashed = false;
	    	}

	    	// Show boosts
	    	for (int i = 0; i < p1.getBoostCount(); i++) {
    			g.drawImage(boost1, 119 + (12 * i), 56, this);
    		}
    		for (int i = p1.getBoostCount(); i < 3; i++) {
    			g.drawImage(boostBlank, 143 - (12 * (2 - i)), 56, this);
    		}
    		for (int i = 0; i < p2.getBoostCount(); i++) {
    			g.drawImage(boost2, 451 + (12 * i), 56, this);
    		}
    		for (int i = p2.getBoostCount(); i < 3; i++) {
    			g.drawImage(boostBlank, 475 - (12 * (2 - i)), 56, this);
    		}

	    	p1.render(g);
	    	p2.render(g);
	    	p1.leaveTrail(g, board);
	    	p2.leaveTrail(g, board);
	    }
	    else if (ScreenManager.getActive().equals("game over")) {
	    	ScreenManager.show("game over", g, keys, p1, p2);
	    }
    }

    public void newGame() {
    	for (int i = 0; i < 103; i++) {
			Arrays.fill(board[i], 0);
		}
		crashed = true;
		p1.reset();
		p2.reset();

		if (p1.getScore() > 3 || p2.getScore() > 3) {
			p1.resetScore();
			p2.resetScore();
			p2.makeAI(false);
			ScreenManager.activate("game over");
		}
    }
}