package ru.mpei.lectAgent4;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

public class AgentSeller extends Agent {

    @Override
    protected void setup() {
        /**
         * делаем обращение
         */
        DFAgentDescription dfd = new DFAgentDescription();

        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType("selling_books");
        serviceDescription.setName(this.getLocalName());
        dfd.addServices(serviceDescription);
        /**
         * регистрация
         */
        try {
            DFService.register(this, dfd);
        } catch (FIPAException e) {
            throw new RuntimeException(e);
        }
    }
}
