package ru.mpei.lectAgent5PCAP;

import com.sun.jna.NativeLibrary;
import lombok.SneakyThrows;
import ru.mpei.lectAgent5.UdpSocketClient;

/**
 * pcap - захват трафика, как я понял, всего сетвого интерфейса
 * по этому принципу работет wireshark
 * то есть он прослушивает по всей видимости абсолютно все трафики сетевого интерфейса
 * <dependency>
 *   <groupId>org.pcap4j</groupId>
 *   <artifactId>pcap4j-core</artifactId>
 *   <version>2.0.0-alpha.6</version>
 * </dependency>
 * Row Socket
 */


public class Main {

    static {
        if (System.getProperty("os.name").toLowerCase().contains("win")){
            NativeLibrary.addSearchPath("wpcap", "C:\\Windows\\System32\\Npcap");
        }
    }

    @SneakyThrows
    public static void main(String[] args) {

        RawUdpSocketServer rawUdpSocketServer = new RawUdpSocketServer();
        rawUdpSocketServer.start(1200);

        PacketDecoder packetDecoder = new PacketDecoder();

        RawUdpSocketClient rawUdpSocketClient = new RawUdpSocketClient();
        rawUdpSocketClient.init(1200);
        byte[] data = packetDecoder.code("AgentNumber3");


            //rawUdpSocketClient.send(new byte[]{2, 0, 0, 0, 69, 0, 0, 34, 2, 82, 0, 0, 64, 17, 0, 0, 10, 3, 2, 71, 10, 3, 2, 71, -105, 33, 4, -80, 0, 14, 7, 107, 104, 101, 108, 108, 111, 48});
        while(true){
            Thread.sleep(1000);
            rawUdpSocketClient.send(data);
        }





//        new Thread(() -> {
//            while (true){
//                rawUdpSocketClient.send(datad);
//            }
//        }).start();



//        UdpSocketClient c = new UdpSocketClient();
//        /**
//         * создаем нашего клиента
//         */
//        c.init(1200);
//
//        int counter = 0;
//        while (true){
//            c.send("hello0");
//            Thread.sleep(1000);
//        }

    }

}
