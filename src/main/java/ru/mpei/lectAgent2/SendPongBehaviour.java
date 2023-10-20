package ru.mpei.lectAgent2;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.List;

public class SendPongBehaviour extends OneShotBehaviour {
    @Override
    public void action() {
        /**
         * прописываем что отправляем агенту номер 2
         */
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.setContent("PONG");
        //message.addReceiver(new AID("Pinger", false));.
        List<AID> agents = AgentService.findAgents(myAgent, "PingerType");
        agents.forEach(aid -> message.addReceiver(aid));
        myAgent.send(message);
    }
}
