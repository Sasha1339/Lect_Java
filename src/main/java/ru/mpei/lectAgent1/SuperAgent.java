package ru.mpei.lectAgent1;

import jade.core.Agent;

public class SuperAgent extends Agent {
    @Override
    protected void setup(){
        this.addBehaviour(
                new DelayedMsgSendingBehaviour(this, 15000)
        );
    }

}
