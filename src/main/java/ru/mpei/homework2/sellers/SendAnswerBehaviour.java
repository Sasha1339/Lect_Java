package ru.mpei.homework2.sellers;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;
import ru.mpei.homework2.AgentService;
import ru.mpei.homework2.sellers.helper.behaviour.InfoCroupier;

import java.util.List;

/**
 * поведение отправки ставки
 */
@Slf4j
public class SendAnswerBehaviour extends OneShotBehaviour {

    InfoCroupier infoCroupier;

    public SendAnswerBehaviour(InfoCroupier infoCroupier) {
        this.infoCroupier = infoCroupier;
    }
    @Override
    public void action() {
        ACLMessage sendMessage = new ACLMessage(ACLMessage.REQUEST);
        List<AID> aids = AgentService.findAgents(myAgent, "Auction");

        aids.stream()
                .filter(aid -> aid.getName().equals(infoCroupier.getNameCroupier()))
                .forEach(aid -> sendMessage.addReceiver(aid));


        int bet;
        int choice = (int)(Math.random() * 3);
        switch (choice){
            case 0:
            {
                bet = (int) (Math.random() * 500);
                sendMessage.setContent(String.valueOf(bet));
                log.info(myAgent.getLocalName()+": my Bet = "+bet);
                myAgent.send(sendMessage);
                //myAgent.addBehaviour(new SendAnswerBehaviour(bet, message.getSender().getName()));

            }
            break;
            case 1:
            {
                bet = ((int) (Math.random() * 500)) * -1;
                sendMessage.setContent(String.valueOf(bet));
                log.info(myAgent.getLocalName()+": my Bet = "+bet);
                myAgent.send(sendMessage);
                //myAgent.addBehaviour(new SendAnswerBehaviour(bet, message.getSender().getName()));
            }
            break;
            case 2:
            {
                log.info(myAgent.getLocalName()+": I dont wont proposes!");
            }
            break;
        }



    }
}
