package ru.mpei.homework2.croupier;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;
import ru.mpei.homework2.AgentService;

import java.util.List;

/**
 * повение для отправки контракта победителю
 */
@Slf4j
public class SendContractBehaviour extends OneShotBehaviour {

    private String nameWinner;
    private String nameLocal;

    public SendContractBehaviour(String nameWinner, String nameLocal) {
        this.nameWinner = nameWinner;
        this.nameLocal = nameLocal;
    }

    @Override
    public void action() {

        ACLMessage messageWinner = new ACLMessage(ACLMessage.PROPOSE);
        ACLMessage messageFail = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
        List<AID> aids = AgentService.findAgents(myAgent, "Auction");

        aids.stream()
                .filter(aid -> aid.getName().equals(nameWinner))
                .forEach(aid -> messageWinner.addReceiver(aid));

        aids.stream()
                .filter(aid -> !aid.getName().equals(myAgent.getName()))
                .filter(aid -> !aid.getName().equals(nameWinner))
                .forEach(aid -> messageFail.addReceiver(aid));

        messageWinner.setContent("You win! Give me contract");
        log.info(myAgent.getLocalName()+": "+nameLocal+" you win!");
        messageFail.setContent("You lose! You lohonulsya!");
        myAgent.send(messageWinner);
        myAgent.send(messageFail);

    }
}
