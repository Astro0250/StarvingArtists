package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	private boolean aPressed, dPressed, sPressed, wPressed, upPressed, downPressed, leftPressed, rightPressed,  zPressed, xPressed, commaPressed, periodPressed, rSlashPressed;

	public boolean checkAPressed() {return aPressed;}
	public boolean checkDPressed() {return dPressed;}
	public boolean checkSPressed() {return sPressed;}
	public boolean checkWPressed() {return wPressed;}
	public boolean checkUpPressed() {return upPressed;}
	public boolean checkDownPressed() {return downPressed;}
	public boolean checkLeftPressed() {return leftPressed;}
	public boolean checkRightPressed() {return rightPressed;}
	public boolean checkZPressed() {return zPressed;}
	public boolean checkXPressed() {return xPressed;}
	public boolean checkPeriodPressed() {return periodPressed;}
	public boolean checkRSlashPressed() {return rSlashPressed;}
	public boolean checkCommaPressed() {return commaPressed;}
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		// MENU STATE
		if(gp.gameState == gp.menuState) {
			if(code == KeyEvent.VK_W) {
				if(gp.ui.commandNum == 1) {
					gp.ui.setCommandNum(0);;
				}
			}
			if(code == KeyEvent.VK_S) {
				if(gp.ui.commandNum == 0) {
					gp.ui.setCommandNum(1);;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 0) {
					gp.gameState = gp.hostOrPlayState;
				}else if(gp.ui.commandNum == 1){
					System.exit(0);
				}
			}
		}
        else if(gp.gameState == gp.hostOrPlayState) {
			if(code == KeyEvent.VK_W) {
				if(gp.ui.commandNum == 1) {
					gp.ui.setCommandNum(0);;
				}
			}
			if(code == KeyEvent.VK_S) {
				if(gp.ui.commandNum == 0) {
					gp.ui.setCommandNum(1);;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 0) {
					gp.setPlayer(false);
                    gp.roomCode = JOptionPane.showInputDialog("What is your room code?");
				}else if(gp.ui.commandNum == 1){
					gp.setPlayer(true);
                    gp.roomCode = JOptionPane.showInputDialog("What is the room code you want to join?");
				}
                gp.setUpGame();
                gp.gameState = gp.roomState;
			}
		}
		// PLAY STATE
		else if(gp.gameState == gp.playState) {
			// OOOO
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();
		if(gp.gameState == gp.playState) {	
			if(code == KeyEvent.VK_A) {aPressed = false;}
			if(code == KeyEvent.VK_D) {dPressed = false;}
			if(code == KeyEvent.VK_S) {sPressed = false;}
			if(code == KeyEvent.VK_W) {wPressed = false;}
			if(code == KeyEvent.VK_UP) {upPressed = false;}
			if(code == KeyEvent.VK_DOWN) {downPressed = false;}
			if(code == KeyEvent.VK_LEFT) {leftPressed = false;}
			if(code == KeyEvent.VK_RIGHT) {rightPressed = false;}
			if(code == KeyEvent.VK_C) {zPressed = false;}
			if(code == KeyEvent.VK_V) {xPressed = false;}
			if(code == KeyEvent.VK_COMMA) {commaPressed = false;}
			if(code == KeyEvent.VK_PERIOD) {periodPressed = false;}
		}
	}

}
