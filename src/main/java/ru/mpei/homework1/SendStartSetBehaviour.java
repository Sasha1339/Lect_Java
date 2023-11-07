package ru.mpei.homework1;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class SendStartSetBehaviour extends OneShotBehaviour {
    @Override
    public void action() {

        int valueSet = (int)(0 + Math.random() * 500);
        ACLMessage sendMessage = new ACLMessage(ACLMessage.INFORM);
        List<AID> agents = AgentService.findAgent(myAgent, "Agents");
        agents.stream()
                .filter(agent -> !agent.getName().equals(myAgent.getName()))
                .forEach(aid -> sendMessage.addReceiver(aid));
        log.info(myAgent.getLocalName() + ": Новая ставка: "+valueSet);
        sendMessage.setContent("New set : "+valueSet);
        myAgent.send(sendMessage);

    }
}
