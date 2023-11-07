package ru.mpei.lect5;

import lombok.Data;
import lombok.SneakyThrows;

public class Main {

    /**
     * многопоточность Java
     * @param args
     */

    @SneakyThrows
    public static void main(String[] args) {
//        System.out.println("Hello: "+Thread.currentThread().getName());
//        MyCalculateionThread t1 = new MyCalculateionThread();
//        t1.start(); // пока работает этот поток, поток main не остановится
//        // приложение visualvm для мониторинга потоков в приложении
//        Thread t2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i<5; i++){
//                    System.out.println(Thread.currentThread().getName());
//                }
//            }
//        });
//        t2.setName("lala");
//        t2.start();
//
//        Thread.sleep(2000);
//        t2.interrupt(); // - это означает прервать.
//

        Count c = new Count();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++){
                //c.setSum(c.getSum() + 1); // метод действует не синхронизированно
                c.increment(); // синхронизированное обращение к методу
            }
        });

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10000; i++){
                //c.setSum(c.getSum() + 1); // метод действует не синхронизированно
                c.increment(); // синхронизированное обращение к методу
            }
        });

        thread.start();
        t1.start();

        Thread.sleep(1000);
        /**
         * почему не будет 20000 в сумме? каждый поток работает изолировано - объект коунт
         * потому что каждый поток не устпевает записывать, как второй берет прошлую переменную
         * (по прошедг=шему времени)
         */

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

            long start = System.currentTimeMillis(); // время с момента 1850...
            @Override
            public void run() {

                System.out.println(System.currentTimeMillis() - start);

            }
        }));


    }


    /**
     * вид потоков - демоны
     * каждый поток пможно сделать демоном, если он не заверршился, то Джава не будет ждать
     * поток демона - его завершение.
     */

}
