package ru.mpei.lectAgent3.fsm;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.List;

public class SendInvits extends OneShotBehaviour {

    private List<String> agents;

    public SendInvits(List<String> agents) {

        this.agents = agents;

    }

    @Override
    public void action() {

        ACLMessage m = new ACLMessage(ACLMessage.CFP);

        agents.stream()
                .map(e -> new AID(e, false))
                .forEach(aid -> m.addReceiver(aid));

    }
}
