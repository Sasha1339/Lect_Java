package ru.mpei.homework2_2;

import jade.core.behaviours.OneShotBehaviour;
import lombok.extern.slf4j.Slf4j;

/**
 * поведение в случае выигрыша
 */
@Slf4j
public class SuccessBehaviour extends OneShotBehaviour {
    @Override
    public void action() {
        log.info(myAgent.getLocalName()+": Auction was successful");
    }
}
