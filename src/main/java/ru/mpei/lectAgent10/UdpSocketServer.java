package ru.mpei.lectAgent10;

import lombok.SneakyThrows;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpSocketServer {

    private byte[] packetData = new byte[500];


    @SneakyThrows
    public void start(int port){
        DatagramSocket socket = new DatagramSocket(port);
        /**
         * конструктор без параметров нужен для отправки пакетов!
         */
        DatagramPacket packet = new DatagramPacket(packetData, packetData.length);
        /**
         * DatagramPacket нужен для того чтобы получать данные - это есть некая абстракция
         * в его конструктор передаем сам вакет данных и его длину
         */
        //socket.receive(packet); // начать получать данные
        /**
         * вызов метода ресив, один раз если оставим так, он дождется и все и заблокируется
         * поэтому делаем в цикле.
         */
        new Thread(() -> { // внимание мы не сможем запустить клиент, если не указать, что он не запускается в отдельном потоке,
            //иначе основной поток займет, кто? правильно! сервер, поскольку он поток занимает первым
            while (true){
                try {
                    socket.receive(packet);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                byte[] data = packet.getData();
                String s = new String(data).replace("\000", ""); //чтобы убрать nullnullnullnull...
                System.out.println(s); // работа ть не будет это нужно деалть  все в отдельном потоке иначе вайл займет
                // основной поток.
            }
        }).start();

    }

    public void stop(){

    }

}
