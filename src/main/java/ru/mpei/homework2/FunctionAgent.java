package ru.mpei.homework2;

import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;
import ru.mpei.homework2.croupier.CroupierBehaviourFSM;
import ru.mpei.homework2.croupier.StartAuctionBehaviour;
import ru.mpei.homework2.croupier.WaitForAcceptContractBehaviour;
import ru.mpei.homework2.croupier.WaitForProposesBehaviour;
import ru.mpei.homework2.sellers.SellerBehaviourFSM;
import ru.mpei.homework2.sellers.WaitForContractBehaviour;
import ru.mpei.homework2.sellers.WaitForInvite;


@Slf4j
public class FunctionAgent extends Agent {

    @Override
    protected void setup() {

        log.info(this.getLocalName()+": I was born");
        AgentService.registerAgent(this, "Auction");

        if (this.getLocalName().equals("Croupier")){
            this.addBehaviour(new CroupierBehaviourFSM());
        } else {
            this.addBehaviour(new SellerBehaviourFSM());
        }


    }
}
