package ru.mpei.homework2.croupier.helper.behaviour;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import ru.mpei.homework2.AgentService;

public class WaitAnswerBehaviour extends Behaviour {

    private WhoIsWinner whoIsWinner;
    public WaitAnswerBehaviour(WhoIsWinner whoIsWinner) {
        this.whoIsWinner = whoIsWinner;
    }
    @Override
    public void action() {
        ACLMessage message = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
        if (message != null){
            double newBet = Double.parseDouble(message.getContent());
            if (newBet > whoIsWinner.getBet()){
                whoIsWinner.setBet(newBet);
                whoIsWinner.setNameWinner(message.getSender().getName());
            }
            whoIsWinner.setCount(whoIsWinner.getCount()+1);
        }else {
            block();
        }
    }

    @Override
    public boolean done() {
        return whoIsWinner.getCount() == AgentService.findAgents(myAgent, "Auction").size() - 1;
    }
}
