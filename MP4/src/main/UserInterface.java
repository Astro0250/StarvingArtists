package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class UserInterface {
	
	Graphics2D g2;
	GamePanel gp;
	Font Ariel_50, Ariel_30_Italic, Ariel_80_Bold,TERMINAL_50;
    BufferedImage mscOne, mscTwo;
	public int commandNum = 0;
	public void setCommandNum(int i) {
		commandNum = i;
	}

	public UserInterface(GamePanel gp) {
		this.gp = gp;
		
		Ariel_50 = new Font("Ariel", Font.PLAIN, 50);
		Ariel_30_Italic = new Font("Ariel", Font.ITALIC, 30);
		Ariel_80_Bold = new Font("Ariel", Font.BOLD, 80);
		TERMINAL_50 = new Font("Tifax", Font.PLAIN, 60);
		
		setImages();
	}  
    public void setImages() {
        try {
            mscOne = ImageIO.read(new File("res/MainMenu/MenuImageOne.png"));
            mscTwo = ImageIO.read(new File("res/MainMenu/MenuImageTwo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        this.g2 = g2;

        if(gp.gameState == gp.menuState) {
			drawMenu();
		}
        if(gp.gameState == gp.hostOrPlayState) {
            drawHostOrPlay();
        }
		if(gp.gameState == gp.roomState) {
            if(gp.isPlayer()){
                drawPlayer();
            }else {
                drawHost();
            }
		}
    }
    public void drawHostOrPlay() {
        gp.setBackground(new Color(135,72,72));

		g2.setColor(Color.black);
		g2.setFont(Ariel_80_Bold);
		String text = "HOST GAME?";
		int x = getXcenterText(text); 
		int y = 500;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-60, y);
		}
		text = "JOIN GAME?";
		x = getXcenterText(text); 
		y = 600;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-60, y);
		}
    }

    public void drawMenu() {
		gp.setBackground(new Color(135,72,72));

		g2.setColor(Color.black);
		g2.setFont(Ariel_80_Bold);
		String text = "PLAY";
		int x = getXcenterText(text); 
		int y = 500;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-60, y);
		}
		text = "EXIT";
		x = getXcenterText(text); 
		y = 600;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-60, y);
		}
        g2.setFont(new Font("Ariel", Font.BOLD, 150));
        text = "Starving Artists";
        x = getXcenterText(text);
        y = 300;
        g2.drawString(text, x, y);

        g2.drawImage(mscOne, 1000, 450, 300, 300, null);
        g2.drawImage(mscTwo, 175, 450, 300, 300, null);
    }
	public int getXcenterText(String a) {
		int x = (gp.getScreenWidth()/2) - ((int) g2.getFontMetrics().getStringBounds(a, g2).getWidth()/2);
		return x;
	}

    public void drawHost() {
        if(gp.gameState == gp.roomState){
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Ariel", Font.BOLD, 150));
            String text = gp.host.numPlayers+"/"+gp.host.roomSize;
            int x = getXcenterText(text);
            int y = 300;
            g2.drawString(text, x, y);
        } else if(gp.gameState == gp.playState){
            // later implement
        }
    }

    public void drawPlayer() {
        if(gp.gameState == gp.roomState){
            gp.player.setPlayerName(JOptionPane.showInputDialog("What is your name?"));
            gp.player.sendJoinPacket();
        } else if(gp.gameState == gp.playState){
            gp.add(gp.drawArea);
            gp.drawArea.setBounds(50,50,(int)(gp.getScreenWidth()*0.8),(int)(gp.getScreenHeight()*0.8));
            gp.drawArea.draw(g2);
            g2.dispose();
        }
    }
}
