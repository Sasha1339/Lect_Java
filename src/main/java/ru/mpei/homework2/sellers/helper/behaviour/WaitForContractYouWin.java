package ru.mpei.homework2.sellers.helper.behaviour;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import ru.mpei.homework2.sellers.helper.behaviour.InfoCroupier;

public class WaitForContractYouWin extends Behaviour {

    private InfoCroupier infoCroupier;
    boolean isDone = false;

    public WaitForContractYouWin(InfoCroupier infoCroupier) {
        this.infoCroupier = infoCroupier;
    }

    @Override
    public void action() {
        ACLMessage winMessage = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.PROPOSE));
        if (winMessage != null){
            infoCroupier.setNameCroupier(winMessage.getSender().getName());
            isDone = true;

        }else {
            block();
        }
    }

    @Override
    public boolean done() {
        return isDone;
    }
}
