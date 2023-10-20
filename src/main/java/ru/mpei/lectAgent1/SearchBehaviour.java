package ru.mpei.lectAgent1;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class SearchBehaviour extends TickerBehaviour {

    /**
     * для данного поведения необходимо в конструктор передать агента и период совершения дейсвий
     * @param a
     * @param period
     */

    public SearchBehaviour(Agent a, long period) {
        super(a, period);
    }

    @Override
    protected void onTick() {
        System.out.println("I'll be back");
    }
}
