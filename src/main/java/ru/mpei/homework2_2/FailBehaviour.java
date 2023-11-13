package ru.mpei.homework2_2;

import jade.core.behaviours.OneShotBehaviour;
import lombok.extern.slf4j.Slf4j;

/**
 * повдение в случае проигрыша
 */
@Slf4j
public class FailBehaviour extends OneShotBehaviour {

    String message;
    public FailBehaviour(String message){
        this.message = message;
    }

    @Override
    public void action() {
        log.info(myAgent.getLocalName()+": "+message);
    }
}
