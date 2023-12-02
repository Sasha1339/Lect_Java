package ru.mpei.homework2.croupier;

import jade.core.behaviours.FSMBehaviour;
import ru.mpei.homework2.FailBehaviour;
import ru.mpei.homework2.SuccessBehaviour;
import ru.mpei.homework2.croupier.helper.behaviour.WhoIsWinner;
import ru.mpei.homework2.croupier.SendContractBehaviour;
import ru.mpei.homework2.croupier.WaitForAcceptContractBehaviour;

public class CroupierBehaviourFSM extends FSMBehaviour {

    private static final String FIRST_STATE = "firstState"
            , WAIT_PROPOSES = "waitProposes"
            , SEND_CONTRACT = "sendContract"
            , SUCCESS = "success"
            , FAIL = "fail"
            , WAIT_ACCEPT = "waitAccept";

    @Override
    public void onStart() {
        WhoIsWinner whoIsWinner = new WhoIsWinner();
        this.registerFirstState(new StartAuctionBehaviour(), FIRST_STATE);
        this.registerState(new WaitForProposesBehaviour(whoIsWinner), WAIT_PROPOSES);
        this.registerState(new SendContractBehaviour(whoIsWinner) ,SEND_CONTRACT);
        this.registerState(new WaitForAcceptContractBehaviour(), WAIT_ACCEPT);
        this.registerLastState(new FailBehaviour("Auction was failed"), FAIL);
        this.registerLastState(new SuccessBehaviour(), SUCCESS);

        this.registerDefaultTransition(FIRST_STATE, WAIT_PROPOSES);
        this.registerTransition(WAIT_PROPOSES, FAIL, 1);
        this.registerTransition(WAIT_PROPOSES, SEND_CONTRACT, 3);
        this.registerDefaultTransition(SEND_CONTRACT, WAIT_ACCEPT);
        this.registerTransition(WAIT_ACCEPT, FAIL, 1);
        this.registerTransition(WAIT_ACCEPT, SUCCESS, 2);
    }

}
