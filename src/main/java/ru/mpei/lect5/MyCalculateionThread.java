package ru.mpei.lect5;

public class MyCalculateionThread extends Thread{

    @Override
    public void run() {

        while (true){
            System.out.println(Thread.currentThread().getName());
            System.out.println();
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // это эксепшин говрит, что его может кто-то разбудить
                throw new RuntimeException(e);
            }
        }
    }
}
