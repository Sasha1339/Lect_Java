package ru.mpei.lectAgent5;

import lombok.SneakyThrows;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpSocketClient {

    private int port;
    private DatagramSocket client;

    @SneakyThrows
    public void init(int port){
        client = new DatagramSocket();
        this.port = port;
    }

    @SneakyThrows
    public void send(String data){
        DatagramPacket p = new DatagramPacket(data.getBytes(), data.getBytes().length, InetAddress.getLocalHost(), port);
        client.send(p);
    }

}
