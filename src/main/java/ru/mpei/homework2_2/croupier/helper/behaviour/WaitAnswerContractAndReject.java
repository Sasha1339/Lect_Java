package ru.mpei.homework2_2.croupier.helper.behaviour;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;


/**
 * повдение отлавливания отказа от контракта
 */
public class WaitAnswerContractAndReject extends Behaviour {

    private boolean isDone = false;
    @Override
    public void action() {
        ACLMessage message = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.FAILURE));
        if (message != null){
            isDone = true;
        }else {
            block();
        }
    }

    @Override
    public boolean done() {
        return isDone;
    }

}
