package ru.mpei.homework2.sellers;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;
import ru.mpei.homework2.sellers.helper.behaviour.InfoCroupier;


/**
 * поведение ожидающее начала аукциона и формирования ставки
 */
@Slf4j
public class WaitForInvite extends Behaviour {

    private int number;
    private InfoCroupier infoCroupier;

    public WaitForInvite(InfoCroupier infoCroupier) {
        this.infoCroupier = infoCroupier;
    }

    @Override
    public void action() {
        ACLMessage message = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
        if (message != null){
            infoCroupier.setNameCroupier(message.getSender().getName());
            log.info("I'm understand!");
        }else {
            block();
        }
    }


    @Override
    public boolean done() {
        return false;
    }
}
