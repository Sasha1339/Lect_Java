package ru.mpei.homework1;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReceivedStartSetBehaviour extends Behaviour {
    @Override
    public void action() {

        MessageTemplate messageTemplate = MessageTemplate.MatchPerformative(ACLMessage.INFORM);

        ACLMessage message = myAgent.receive(messageTemplate);

        MessageTemplate messageTemp = MessageTemplate.MatchPerformative(ACLMessage.FAILURE);

        ACLMessage messageWin = myAgent.receive(messageTemp);

        if (message != null){
            String[] valueString = message.getContent().split(" ");
            int set = Integer.parseInt(valueString[3]);
            int myAnswer = (int)(set + Math.random() * (set*3));
            myAgent.addBehaviour(new SendMySetBehaviour(myAnswer));
        } else if (messageWin != null){
            log.info(myAgent.getLocalName() + ": Ура я выиграл!");
        } else {
            block();
        }

    }

    @Override
    public boolean done() {
        return false;
    }
}
