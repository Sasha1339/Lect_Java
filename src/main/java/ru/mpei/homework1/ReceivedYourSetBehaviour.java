package ru.mpei.homework1;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ReceivedYourSetBehaviour extends Behaviour {

    private HelperCalculateSet helperCalculateSet = new HelperCalculateSet();

    @Override
    public void action() {
        List<AID> agents = AgentService.findAgent(myAgent, "Agents");
        MessageTemplate messageTemplate = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        ACLMessage message = myAgent.receive(messageTemplate);

        if (message != null){
            String[] valueString = message.getContent().split(" ");
            int set = Integer.parseInt(valueString[3]);

            helperCalculateSet.calculateSet(set, message.getSender().getLocalName());
            helperCalculateSet.setCount(helperCalculateSet.getCount()+1);

            if (helperCalculateSet.getCount() == agents.size()-1){
                log.info(myAgent.getLocalName() +
                        ": Одержал победу в торгах: "+helperCalculateSet.getNameAgent() +
                        " со ставкой: "+helperCalculateSet.getTakeSetMax());
                myAgent.addBehaviour(new SendWinBehaviour(helperCalculateSet.getNameAgent()));
            }


        }

    }

    @Override
    public boolean done() {
        return false;
    }
}
