package ru.mpei.homework2.croupier.helper.behaviour;

import lombok.Data;

/**
 *  класс помощник для указания количества поставивших, победителя и его ставки
 */
@Data
public class WhoIsWinner {
    public WhoIsWinner() {
        this.count = 0;
        this.bet = -1;
    }
    private int count;
    private double bet;
    private String nameWinner = "";
    private String nameLocalWinner = "";

}
