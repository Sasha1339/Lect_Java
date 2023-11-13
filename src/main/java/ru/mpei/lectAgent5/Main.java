package ru.mpei.lectAgent5;

import lombok.SneakyThrows;

/**
 * ТСР - (формирование пакета данных) протокол управления передачей, который обеспечивает надежную связь между
 * двумя приложениями
 * (законченная последовательность - называется пакетом - последовательнсоть байтов) -последовательность байтов -
 * парсится по опредленному соглашению (протоколу) - есть зафиксирвоанные длины поля данных (протокол показыывает для кого этот пакет
 * и как его распарсить
 *
 * основных существует две штуки - UDP - это простой протокол, который шлет прсто без каких либо подтверждений, целые они или нет
 * не важно
 * если важна простота и ненадежность - UDP (в заголовке 8 байт)
 * если важна надежность передачи TCP (в заголовке от 20-60 байт) (гарантированность доставки)
 * для TCP - нужно еще три пакета для установки соединения
 *
 * *
 *
 * Сервер - принимает
 * Клиент - отправляет
 * У сервера
 * есть специальный класс UDP - DatagramSocket?
 * задается порт для прослушивания
 * DatagramPacket - дл приема пакета
 * метод receive - блокирующий
 *
 * У клиента
 * порт не задается
 * для отправки пакета, указать строку с данными длину и ip + port
 * метод send - для отправки
 *
 * поехали создадим сервер
 *
 */

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        UdpSocketServer s = new UdpSocketServer();
        s.start(1200); // для разных агентов нужны разны порты !!!, если  их запускать много агентов

        UdpSocketClient c = new UdpSocketClient();

        int counter = 0;
        while (true){
            c.send("hello"+counter++);
            Thread.sleep(1000);
        }


    }
    /**
     * также можно использовать pcap для того чтобы захватывать поток
     */

}
