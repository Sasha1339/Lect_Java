package ru.mpei.homework2.sellers;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;
import ru.mpei.homework2.AgentService;
import ru.mpei.homework2.sellers.helper.behaviour.InfoCroupier;

import java.util.List;

/**
 * повдение отказа от контракта
 */
@Slf4j
public class SendContractWinAndReject extends OneShotBehaviour {

    private String nameCroupier;

    private InfoCroupier infoCroupier;

    public SendContractWinAndReject(InfoCroupier infoCroupier) {
        this.infoCroupier = infoCroupier;
    }
    @Override
    public void action() {
        ACLMessage message = new ACLMessage(ACLMessage.FAILURE);
        List<AID> aids = AgentService.findAgents(myAgent, "Auction");

        aids.stream()
                .filter(aid -> aid.getName().equals(infoCroupier.getNameCroupier()))
                .forEach(aid -> message.addReceiver(aid));

        message.setContent("No Contract!");
        log.warn(myAgent.getLocalName()+": I reject!");
        myAgent.send(message);

    }
}
