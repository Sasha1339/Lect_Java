package ru.mpei.lectAgent5;

import lombok.SneakyThrows;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpSocketClient {

    private int port;
    private DatagramSocket client;

    @SneakyThrows
    public void init(int port){ // метод который создает наш сокет (уже без параметров конструктора)
        // для отправки данных
        // пофакту это можно сделать все в конструкторе класса клиента
        client = new DatagramSocket();
        this.port = port;
    }

    @SneakyThrows
    public void send(String data){
        DatagramPacket p = new DatagramPacket(data.getBytes(), data.getBytes().length, InetAddress.getLocalHost(), port);
        //InetAddress.getByName("255.255.255.255"); - широковещательный канал (тоесть отправить всем по udp)
        //передаем класс InetAddress.getLocalHost() то есть шлем на наш комп, по соответсвующему пору port
        // не забудь привести строку в байты
        client.send(p);
    }

}
