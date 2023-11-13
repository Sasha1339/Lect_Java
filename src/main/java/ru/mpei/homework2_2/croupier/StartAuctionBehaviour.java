package ru.mpei.homework2_2.croupier;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;
import ru.mpei.homework2_2.AgentService;

import java.util.List;

/**
 * поведние начала аукциона
 */
@Slf4j
public class StartAuctionBehaviour extends OneShotBehaviour {
    @Override
    public void action() {

        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        List<AID> aids = AgentService.findAgents(myAgent, "Auction");
        aids.stream()
                        .filter(aid -> !aid.getName().equals(myAgent.getName()))
                                .forEach(aid -> message.addReceiver(aid));
        message.setContent("Auction was started!");
        log.info("Auction was Started!");
        myAgent.send(message);

    }
}
