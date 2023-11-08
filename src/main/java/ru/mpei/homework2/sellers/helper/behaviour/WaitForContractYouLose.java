package ru.mpei.homework2.sellers.helper.behaviour;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * поведения ожидания отказа предложения
 */
public class WaitForContractYouLose  extends Behaviour {
    boolean isDone = false;
    @Override
    public void action() {
        ACLMessage losMessage = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REJECT_PROPOSAL));
        if (losMessage != null) {

            isDone = true;

        } else {
            block();
        }

    }
    @Override
    public boolean done() {
        return isDone;
    }

}
