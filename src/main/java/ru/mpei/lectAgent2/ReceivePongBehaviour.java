package ru.mpei.lectAgent2;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class ReceivePongBehaviour extends Behaviour {
    @Override
    public void action() {
        ACLMessage recivedMessage =  myAgent.receive();
        if (recivedMessage != null){
            System.out.println("I received message "+recivedMessage.getContent()+" from "+recivedMessage.getSender().getName());
            myAgent.addBehaviour(new SendPingBehaviour());
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
