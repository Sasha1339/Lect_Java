package ru.mpei.lectAgent3;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReceiveMesBehav extends Behaviour {

    private int count = 0;
    @Override
    public void action() {
        ACLMessage receive = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.PROXY));
        if (receive != null){
            log.info("{}", receive.getContent());
            count++;
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
