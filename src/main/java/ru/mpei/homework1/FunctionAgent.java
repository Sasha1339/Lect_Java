package ru.mpei.homework1;

import jade.core.AID;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class FunctionAgent extends Agent {
    @Override
    protected void setup() {

        log.info(this.getLocalName()+": Я родился!");
        AgentService.registerAgent(this, "Agents");

        List<AID> agents = AgentService.findAgent(this, "Agents");

        if (agents.get(0).getName().equals(this.getName())){
            this.addBehaviour(new SendStartSetBehaviour());
            this.addBehaviour(new ReceivedYourSetBehaviour());
        }else{
            this.addBehaviour(new ReceivedStartSetBehaviour());
        }

    }


}
