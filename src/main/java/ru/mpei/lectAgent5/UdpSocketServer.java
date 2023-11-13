package ru.mpei.lectAgent5;

import lombok.SneakyThrows;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpSocketServer {

    private byte[] packetData = new byte[500];


    @SneakyThrows
    public void start(int port){
        DatagramSocket socket = new DatagramSocket(port);
        DatagramPacket packet = new DatagramPacket(packetData, packetData.length);
        //socket.receive(packet); // начать получать данные
        /**
         * вызов метода ресив, один раз если оставим так, он дождется и все и заблокируется
         * поэтому делаем в цикле.
         */
        while (true){
            socket.receive(packet);
            byte[] data = packet.getData();
            String s = new String(data).replace("\000", ""); //чтобы убрать nullnullnullnull...
            System.out.println(s); // работа ть не будет это нужно деалть  все в отдельном потоке иначе вайл займет
            // основной поток.
        }
    }

    public void stop(){

    }

}
