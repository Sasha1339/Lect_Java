package ru.mpei.homework2_2;

import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;
import ru.mpei.homework2_2.croupier.StartAuctionBehaviour;
import ru.mpei.homework2_2.croupier.WaitForAcceptContractBehaviour;
import ru.mpei.homework2_2.croupier.WaitForProposesBehaviour;
import ru.mpei.homework2_2.sellers.WaitForContractBehaviour;
import ru.mpei.homework2_2.sellers.WaitForInvite;


@Slf4j
public class FunctionAgent extends Agent {

    @Override
    protected void setup() {

        log.info(this.getLocalName()+": I was born");
        AgentService.registerAgent(this, "Auction");

        if (this.getLocalName().equals("Croupier")){
            this.addBehaviour(new StartAuctionBehaviour());
            this.addBehaviour(new WaitForProposesBehaviour());
            this.addBehaviour(new WaitForAcceptContractBehaviour());
        } else {
            this.addBehaviour(new WaitForInvite());
            this.addBehaviour(new WaitForContractBehaviour());
        }


    }
}
