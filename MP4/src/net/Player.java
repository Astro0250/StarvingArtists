package net;

import java.net.InetAddress;

public class Player {
    int port;
    InetAddress ipAddress;
    String name;
    public Player(InetAddress ipAddress, String name, int port) {
        this.ipAddress = ipAddress;
        this.name = name;
        this.port = port;
    }
}
