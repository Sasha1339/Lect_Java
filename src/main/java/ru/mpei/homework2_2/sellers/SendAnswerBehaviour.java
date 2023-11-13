package ru.mpei.homework2_2.sellers;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;
import ru.mpei.homework2_2.AgentService;

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

        ACLMessage sendMessage = new ACLMessage(ACLMessage.REQUEST);
        List<AID> aids = AgentService.findAgents(myAgent, "Auction");

        aids.stream()
                .filter(aid -> aid.getName().equals(nameCroupier))
                .forEach(aid -> sendMessage.addReceiver(aid));

        sendMessage.setContent(String.valueOf(bet));
        log.info(myAgent.getLocalName()+": my Bet = "+bet);
        myAgent.send(sendMessage);

    }
}
