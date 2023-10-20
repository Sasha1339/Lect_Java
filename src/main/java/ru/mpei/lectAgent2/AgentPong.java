package ru.mpei.lectAgent2;

import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class AgentPong extends Agent {

    @Override
    protected void setup(){
        System.out.println("Agent" +this.getName()+" was born");

        AgentService.registerAgent(this, "PongerType");

        this.addBehaviour(new ReceivePingBehaviour());

//        Logger logger = LoggerFactory.getLogger("myLogger");
//        logger.info("Agent {} was born {}", this.getName(), System.currentTimeMillis());

        log.info("Agent {} was born {}", this.getName(), System.currentTimeMillis());

    }

}
