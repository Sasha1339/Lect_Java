package ru.mpei.homework2.sellers;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;
import ru.mpei.homework2.AgentService;

import java.util.List;

/**
 * поведение отправки ставки
 */
@Slf4j
public class SendAnswerBehaviour extends OneShotBehaviour {
    int bet;
    String nameCroupier;

    public SendAnswerBehaviour(int bet, String nameCroupier) {
        this.bet = bet;
        this.nameCroupier = nameCroupier;
    }
    @Override
    public void action() {
        System.out.println("Z dksdvs");
        ACLMessage sendMessage = new ACLMessage(ACLMessage.REQUEST);
        List<AID> aids = AgentService.findAgents(myAgent, "Auction");

        aids.stream()
                .filter(aid -> aid.getName().equals(nameCroupier))
                .forEach(aid -> sendMessage.addReceiver(aid));



        int choice = (int)(Math.random() * 3);
        System.out.println(choice);
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
