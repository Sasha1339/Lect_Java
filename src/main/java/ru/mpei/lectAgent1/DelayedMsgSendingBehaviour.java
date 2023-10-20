package ru.mpei.lectAgent1;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Date;

public class DelayedMsgSendingBehaviour extends WakerBehaviour {
    public DelayedMsgSendingBehaviour(Agent a, long wakeupTime) {
        super(a, wakeupTime);
    }

    @Override
    protected void onWake() {
        ACLMessage m = new ACLMessage(ACLMessage.CFP);
        m.setContent("Hello!");
        m.addReceiver(new AID("Sesh", false)); //кому отправляем
        myAgent.send(m);
        System.out.println(myAgent.getLocalName()+": sent msg");

    }
}
