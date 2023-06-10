package main;

import java.io.IOException;

import javax.swing.JFrame;

import net.NetUtil;

public class Main {
    public static void main(String[] args) {
        try {
            byte[] arr = NetUtil.convertPNGtoByteArr("res/coconut.png");
            NetUtil.convertByteArrToPNG(arr, "res/Output.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Starving Artists");

		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);

		window.pack();

		window.setLocationRelativeTo(null);
		window.setVisible(true);

		gamePanel.startGameThread();
    }
}
