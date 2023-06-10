package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import main.GamePanel;

public class GamePlayer extends Thread{
    private InetAddress ipAddress;
    private DatagramSocket socket;
    private GamePanel gp;
    private String name;
    public void setPlayerName(String a){
        name = a;
    }

    public GamePlayer(GamePanel gp) {
        this.gp = gp;
        try {
            this.socket = new DatagramSocket();
            this.ipAddress = InetAddress.getByName(gp.roomCode);
        } 
        catch (SocketException e) {e.printStackTrace();}
        catch (UnknownHostException e) {e.printStackTrace();}
    }
    public void run() {
        while(true) {
            if(gp.gameState == gp.roomState){

            }
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {e.printStackTrace();}
            System.out.println("SERVER > "+(new String(packet.getData())).trim());
        }
    }
    public void sendJoinPacket() {
        byte[] data = name.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331);
        try {
            socket.send(packet);
        } catch (IOException e) {e.printStackTrace();}
    }
    public void sendData(byte[] data){
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331);
        try {
            socket.send(packet);
        } catch (IOException e) {e.printStackTrace();}
    }
}
