// GamePanel.java

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class GamePanel extends JPanel implements KeyListener {
	private boolean[] keys;
	private boolean crashed;
	private Image back;
	private Tron mainFrame;
	private int[][] board;
	private Player p1, p2;
	private Screen splashScreen;
	
	public GamePanel(Tron m) {
		ScreenManager.start();

		board = new int[103][80];
		for (int i = 0; i < 103; i++) {
			Arrays.fill(board[i], 0);
		}

		ScreenManager.add("splash", "../Resources/Splash Screen.png");
		ScreenManager.add("instruction", "../Resources/Instruct.png");

		ScreenManager.addEntity("splash", new Button("../Resources/Start", "start", 180, 355, 252, 43));
		ScreenManager.addEntity("splash", new Button("../Resources/Instruction", "instruct", 180, 408, 252, 43));

		ScreenManager.addEntity("instruction", new Button("../Resources/Back", "back", 180, 400, 252, 43));

		ScreenManager.activate("splash");

		keys = new boolean[KeyEvent.KEY_LAST+1];
		back = new ImageIcon("../Resources/Board.png").getImage();
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
			if (keys[KeyEvent.VK_SPACE] && !p1.isBoosting()) {
				p1.boost();
			}
			if (keys[KeyEvent.VK_SHIFT] && !p2.isBoosting()) {
				p2.boost();
			}

			// Player 1
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

			// Player 2
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

			p1.move();
			p2.move();

			checkCollision();
		}
	}

	public void checkCollision() {
		if (p1.isColliding(board) && p2.isColliding(board)) {
			System.out.println("Tie");
			newGame();
		}
		else if (p1.isColliding(board)) {
			System.out.println("Player 1 wins!");
			newGame();
		}
		else if (p2.isColliding(board)) {
			System.out.println("Player 2 wins!");
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
    		ScreenManager.show("splash", g, keys);
    	}
    	else if (ScreenManager.getActive().equals("instruction")) {
    		ScreenManager.show("instruction", g, keys);
    	}
    	else if (ScreenManager.getActive() == "game") {
	    	if (crashed) {
				g.drawImage(back, 0, 0, this);

				// Show score
	    		g.drawImage(new ImageIcon("../Resources/Score/" + p1.getScore() + "1.png").getImage(), 190, 40, this);
	    		g.drawImage(new ImageIcon("../Resources/Score/" + p2.getScore() + "2.png").getImage(), 392, 40, this);

				crashed = false;
	    	}
	    	p1.render(g);
	    	p2.render(g);
	    	p1.leaveTrail(g, board);
	    	p2.leaveTrail(g, board);
	    }
    }

    public void newGame() {
    	for (int i = 0; i < 103; i++) {
			Arrays.fill(board[i], 0);
		}
		crashed = true;
		p1.reset();
		p2.reset();
    }
}