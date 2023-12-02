package ru.mpei.lectAgent3.fsm;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;
import ru.mpei.lectAgent3.ReceiveMesBehav;
import ru.mpei.lectAgent3.TmelsBehaviour;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class WaitBehaviour extends ParallelBehaviour {

    private List<ACLMessage> answer = new ArrayList<>();

    ACLMessage bestOffer;


    public WaitBehaviour(){
        super(WHEN_ANY);
    }

    @Override
    public void onStart() {
        new Behaviour() {

            double bestPrice = -1;

            @Override
            public void action() {
                ACLMessage rec = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.PROPOSE));
                if (rec != null) {
                    String stringP = rec.getContent();
                    double price = -1;
                    try {
                        price = Double.parseDouble(stringP);
                    } catch (NumberFormatException e) {
                        //log.warn("Авдаыдва");
                    }
                    if (price > 0 && price < bestPrice) {
                        bestPrice = price;
                        bestOffer = rec;
                    }
                }

            }

            @Override
            public boolean done() {
                return false;
            }

        };



    }

}
