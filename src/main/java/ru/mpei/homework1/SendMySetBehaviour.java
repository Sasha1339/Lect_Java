package ru.mpei.homework1;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class SendMySetBehaviour extends OneShotBehaviour {

    int mySet;
    public SendMySetBehaviour(int mySet){
        this.mySet = mySet;
    }

    @Override
    public void action() {

        ACLMessage sendMessage = new ACLMessage(ACLMessage.REQUEST);
        List<AID> agents = AgentService.findAgent(myAgent, "Agents");
        agents.stream()
                .filter(agent -> agent.getName().equals(agents.get(0).getName()))
                        .forEach(aid -> sendMessage.addReceiver(aid));
        log.info(myAgent.getLocalName() + ": Моя ставка: "+mySet);
        sendMessage.setContent("My set : "+mySet);
        myAgent.send(sendMessage);

    }
}
