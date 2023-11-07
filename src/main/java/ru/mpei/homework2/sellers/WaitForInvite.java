package ru.mpei.homework2.sellers;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;
import ru.mpei.homework2.sellers.SendAnswerBehaviour;

@Slf4j
public class WaitForInvite extends Behaviour {
    @Override
    public void action() {
        ACLMessage message = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
        if (message != null){
            int choice = (int)(Math.random() * 3);
            int bet;
            switch (choice){
                case 0:
                {
                    bet = (int) (Math.random() * 500);
                    myAgent.addBehaviour(new SendAnswerBehaviour(bet, message.getSender().getName()));
                }
                break;
                case 1:
                {
                    bet = ((int) (Math.random() * 500)) * -1;
                    myAgent.addBehaviour(new SendAnswerBehaviour(bet, message.getSender().getName()));
                }
                break;
                case 2:
                {
                    log.info("I dont wont proposes!");
                }
                break;
            }

        }else {
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
