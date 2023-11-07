package ru.mpei.lectAgent4;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

import java.util.Arrays;
import java.util.List;

public class AgentBuyer extends Agent {

    @Override
    protected void setup() {
        try {
            Thread.sleep(1000); /** так делать не надо*/
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription searchingService = new ServiceDescription();
        searchingService.setType("selling_books");
        dfd.addServices(searchingService);


        try {
            DFAgentDescription[] search =  DFService.search(this, dfd);
            List<AID> aid = Arrays.stream(search).map(e -> e.getName()).toList();
            System.out.println(aid);
        } catch (FIPAException e) {
            throw new RuntimeException(e);
        }

    }
}
