package ru.mpei.homework2.croupier.helper.behaviour;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WaitBehaviour extends WakerBehaviour {
    public WaitBehaviour(Agent a, long timeout) {
        super(a, timeout);
    }

    @Override
    protected void onWake() {

    }
}
