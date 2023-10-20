package ru.mpei.lectAgent2;

import jade.core.Agent;
import ru.mpei.lectAgent1.ReceiveMsgBehaviour;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AgentPing extends Agent {


    /**
     * задание №5
     */

    @Override
    protected void setup(){
        System.out.println("Agent" +this.getLocalName()+" was born");
        AgentService.registerAgent(this, "PingerType");

        this.addBehaviour(new SendPingBehaviour());
        this.addBehaviour(new ReceivePongBehaviour());

        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); // универсальный логгер (можно указать свой логгер
        // и разделять по имени, различая их
        //logger.info("Agent" +this.getLocalName()+" was born");
        logger.log(Level.INFO, "Agent" +this.getLocalName()+" was born");
    }

}
