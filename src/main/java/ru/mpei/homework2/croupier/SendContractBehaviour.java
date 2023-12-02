package ru.mpei.homework2.croupier;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;
import ru.mpei.homework2.AgentService;
import ru.mpei.homework2.croupier.helper.behaviour.WhoIsWinner;

import java.util.List;

/**
 * повение для отправки контракта победителю
 */
@Slf4j
public class SendContractBehaviour extends OneShotBehaviour {

    private String nameWinner;
    private String nameLocal;

    private WhoIsWinner whoIsWinner;

    public SendContractBehaviour(WhoIsWinner whoIsWinner) {
        this.whoIsWinner = whoIsWinner;
    }

    @Override
    public void action() {

        ACLMessage messageWinner = new ACLMessage(ACLMessage.PROPOSE);
        ACLMessage messageFail = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
        List<AID> aids = AgentService.findAgents(myAgent, "Auction");

        aids.stream()
                .filter(aid -> aid.getName().equals(whoIsWinner.getNameWinner()))
                .forEach(aid -> messageWinner.addReceiver(aid));

        aids.stream()
                .filter(aid -> !aid.getName().equals(myAgent.getName()))
                .filter(aid -> !aid.getName().equals(whoIsWinner.getNameWinner()))
                .forEach(aid -> messageFail.addReceiver(aid));

        messageWinner.setContent("You win! Give me contract");
        log.info(myAgent.getLocalName()+": "+whoIsWinner.getNameLocalWinner()+" you win!");
        messageFail.setContent("You lose! You lohonulsya!");
        myAgent.send(messageWinner);
        myAgent.send(messageFail);

    }
}
