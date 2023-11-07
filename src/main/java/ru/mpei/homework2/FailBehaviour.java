package ru.mpei.homework2;

import jade.core.behaviours.OneShotBehaviour;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FailBehaviour extends OneShotBehaviour {
    @Override
    public void action() {
        log.info(myAgent.getLocalName()+": Провал аукциона");
    }
}
