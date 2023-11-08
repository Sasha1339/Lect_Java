package ru.mpei.homework2.sellers;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;
import ru.mpei.homework2.AgentService;

import java.util.List;

/**
 * поведение принятия предложенного контракта
 */
@Slf4j
public class SendContractYouWin extends OneShotBehaviour {

    private String nameCroupier;

    public SendContractYouWin(String nameCroupier) {
        this.nameCroupier = nameCroupier;
    }
    @Override
    public void action() {
        ACLMessage message = new ACLMessage(ACLMessage.AGREE);
        List<AID> aids = AgentService.findAgents(myAgent, "Auction");

        aids.stream()
                .filter(aid -> aid.getName().equals(nameCroupier))
                .forEach(aid -> message.addReceiver(aid));

        message.setContent("Contract!");
        log.warn(myAgent.getLocalName()+": I agree!");
        myAgent.send(message);

    }
}
