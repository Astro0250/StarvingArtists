package main;

import javax.swing.JPanel;

import drawTool.DrawArea;
import net.GameHost;
import net.GamePlayer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Color;

public class GamePanel extends JPanel implements Runnable {

	// Game Settings
	private final int screenWidth = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.99);
    public int getScreenWidth(){
        return screenWidth;
    }
	public boolean isPlayer() {
        return isPlayer;
    }

    public void setPlayer(boolean isPlayer) {
        this.isPlayer = isPlayer;
    }
    private final int screenHeight = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.92);
    public int getScreenHeight(){
        return screenHeight;
    }
    public String roomCode;
	private final int FPS = 60;
    private boolean isPlayer = true;
	// Game Initialization
	public UserInterface ui = new UserInterface(this);
	private KeyHandler keyH = new KeyHandler(this);
    public GamePlayer player;
    public GameHost host;
	Thread gameThread;
    DrawArea drawArea = new DrawArea(this);
    public int gameTime = 120;
	// Sound music = new Sound();

	// GameStates
	public int gameState = 0;
	public final int menuState = 0;
    public final int hostOrPlayState = 1;
    public final int roomState = 2;
	public final int playState = 3;
	public final int voteState = 4;
    public final int winnerState = 5;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.green);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.addKeyListener(keyH);
	}

	public void update() {
		if (gameState == playState) {
			
		}
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void setUpGame() {
		if(isPlayer) {
            player = new GamePlayer(this);
        } else {
            roomCode = "192.168.1.1";
            host = new GameHost(this);
        }
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (gameState == menuState) {
			ui.draw(g2);
		}
        if(gameState == hostOrPlayState) {
            ui.draw(g2);
        }
		if (gameState == playState) {
            if(isPlayer()){
			    ui.draw(g2);
            }
            if(!isPlayer()) {
                ui.draw(g2);
            }
		}
	}

	@Override
	public void run() {
		double drawInterval = 1000000000 / (FPS);
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		while (gameThread != null) {
			currentTime = System.nanoTime();

			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;

			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}

		}
	}
	public void loopMusic(int i) {
		
		// music.setFile(i);
		// music.play();
		// music.loop();
	}
}
