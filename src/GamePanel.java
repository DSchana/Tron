// GamePanel.java

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class GamePanel extends JPanel implements KeyListener{
	private boolean []keys;
	private boolean isNewGame;
	private Image back;
	private Tron mainFrame;
	Player p1, p2;
	
	public GamePanel(Tron m){
		keys = new boolean[KeyEvent.KEY_LAST+1];
		back = new ImageIcon("../Resources/Board.png").getImage();
		p1 = new Player(1, 470, 265, new Color(255, 102, 255));
		p2 = new Player(2, 130, 265, new Color(51, 204, 153));
		mainFrame = m;
		isNewGame = true;
		setSize(600,520);
        addKeyListener(this);
	}
	
    public void addNotify() {
        super.addNotify();
        requestFocus();
        mainFrame.start();
    }
	
	public void move(){
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
		
		// Point mouse = MouseInfo.getPointerInfo().getLocation();
		// Point offset = getLocationOnScreen();
		// System.out.println("("+(mouse.x-offset.x)+", "+(mouse.y-offset.y)+")");
	}

	public void checkCollision() {
		if (p1.isColliding() && p2.isColliding()) {
			System.out.println("Tie");
		}
		else if (p1.isColliding()) {
			System.out.println("Player 1 wins!");
		}
		else if (p2.isColliding()) {
			System.out.println("Player 2 wins!");
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
    	if (isNewGame) {
    		g.drawImage(back,0,0,this);
    		isNewGame = false;
    	}
    	p1.leaveTrail(g);
    	p2.leaveTrail(g);
    	p1.render(g);
    	p2.render(g);
    }
}