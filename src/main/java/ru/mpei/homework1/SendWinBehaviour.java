package ru.mpei.homework1;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class SendWinBehaviour extends OneShotBehaviour {

    String nameAgent;
    public SendWinBehaviour(String nameAgent){
        this.nameAgent = nameAgent;
    }

    @Override
    public void action() {

        ACLMessage sendMessage = new ACLMessage(ACLMessage.FAILURE);
        List<AID> agents = AgentService.findAgent(myAgent, "Agents");
        agents.stream()
                .filter(agent -> agent.getLocalName().equals(nameAgent))
                .forEach(aid -> sendMessage.addReceiver(aid));
        sendMessage.setContent("You win!");
        myAgent.send(sendMessage);

    }

}
