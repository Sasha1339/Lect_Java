package ru.mpei.lectAgent3;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class TmelsBehaviour extends WakerBehaviour {

    public TmelsBehaviour(Agent a, long timeout) {
        super(a, timeout);
    }

    @Override
    protected void onWake() {
        log.warn("Time is up");
    }
}
