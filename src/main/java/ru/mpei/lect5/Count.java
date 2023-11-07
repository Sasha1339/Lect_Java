package ru.mpei.lect5;

import lombok.Data;

@Data
public class Count {

    private int sum = 0;

    public void test(){
        synchronized (this){
            sum++; // можно делать синхронизирвоанную переменную
        }
    }

    public synchronized void increment(){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        sum++;
    } // синхронизируем потоки (может получить доступ только один поток) и вывполнить функцию внутри
    // таким образом для нас получим (20000)


}
