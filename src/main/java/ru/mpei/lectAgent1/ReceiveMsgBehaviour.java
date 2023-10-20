package ru.mpei.lectAgent1;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ReceiveMsgBehaviour extends Behaviour {

    private boolean msgReceived = false;

    @Override
    public void onStart() {
        System.out.println(myAgent.getLocalName()+": Я ожидаю сообщение");
    }

    @Override
    public void action() {

        ACLMessage receive = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.CFP));
        if (receive != null){
            System.out.println(myAgent.getLocalName()+": я получил сообщение "+receive.getContent());
        }else{
            block();
        }

    }

    @Override
    public boolean done() {
        return msgReceived;
    }
}
