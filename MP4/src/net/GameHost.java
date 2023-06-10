package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import main.GamePanel;

public class GameHost extends Thread{
    private DatagramSocket socket;
    private GamePanel gp;
    private Player[] players = new Player[6];
    public int numPlayers = 0;
    public int roomSize = players.length;
    public GameHost(GamePanel gp) {
        this.gp = gp;
        try {
            this.socket = new DatagramSocket(1331);
        } 
        catch (SocketException e) {e.printStackTrace();}
    }
    public void run() {
        while(true) {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
                System.out.println(packet.getData().toString().toString()+" joined");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(gp.gameState == gp.roomState){
                System.out.println((new String(packet.getData()).trim()) + " joined");
                if(numPlayers>0&&players[numPlayers-1].ipAddress!=packet.getAddress()){
                    players[numPlayers] = new Player(packet.getAddress(), new String(packet.getData()).trim(), packet.getPort());
                    numPlayers++;
                    sendData("connected".getBytes(), packet.getAddress(), packet.getPort());
                }
            }
        }
    }
    public void sendStartSignal(){
        for(Player p : players){
            sendData("start".getBytes(), p.ipAddress, p.port);
        }
    }
    public void sendData(byte[] data, InetAddress ipAddress, int port){
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
        try {
            this.socket.send(packet);
        } catch (IOException e) {e.printStackTrace();}
    }
}
