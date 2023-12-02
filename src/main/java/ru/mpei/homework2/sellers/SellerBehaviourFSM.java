package ru.mpei.homework2.sellers;

import jade.core.behaviours.FSMBehaviour;
import ru.mpei.homework2.FailBehaviour;
import ru.mpei.homework2.SuccessBehaviour;
import ru.mpei.homework2.croupier.SendContractBehaviour;
import ru.mpei.homework2.croupier.StartAuctionBehaviour;
import ru.mpei.homework2.croupier.WaitForAcceptContractBehaviour;
import ru.mpei.homework2.croupier.WaitForProposesBehaviour;
import ru.mpei.homework2.croupier.helper.behaviour.WhoIsWinner;
import ru.mpei.homework2.sellers.helper.behaviour.InfoCroupier;
import ru.mpei.homework2.sellers.SendAnswerBehaviour;
import ru.mpei.homework2.sellers.WaitForContractBehaviour;
import ru.mpei.homework2.sellers.SendContractYouWin;

public class SellerBehaviourFSM extends FSMBehaviour {

    private static final String FIRST_STATE = "firstState"
            , SEND_ANSWER = "sendAnswer"
            , SEND_CONTRACT_WIN = "sendContract"
            , SEND_CONTRACT_WIN_REJ = "sendContractRej"
            , FAIL = "fail"
            , WAIT_CONTRACT = "waitContract";

    @Override
    public void onStart() {
        InfoCroupier infoCroupier = new InfoCroupier();
        this.registerFirstState(new WaitForInvite(infoCroupier), FIRST_STATE);
        this.registerState(new SendAnswerBehaviour(infoCroupier), SEND_ANSWER);
        this.registerState(new WaitForContractBehaviour(),WAIT_CONTRACT);
        this.registerLastState(new SendContractYouWin(infoCroupier), SEND_CONTRACT_WIN);
        this.registerLastState(new SendContractWinAndReject(infoCroupier), SEND_CONTRACT_WIN_REJ);
        this.registerLastState(new FailBehaviour("I was failed auction!"), FAIL);

        this.registerDefaultTransition(FIRST_STATE, SEND_ANSWER);
        this.registerDefaultTransition(SEND_ANSWER, WAIT_CONTRACT);
        this.registerTransition(WAIT_CONTRACT, SEND_CONTRACT_WIN, 0);
        this.registerTransition(WAIT_CONTRACT, SEND_CONTRACT_WIN_REJ, 1);
        this.registerTransition(WAIT_CONTRACT, FAIL, 2);
        this.registerTransition(WAIT_CONTRACT, FAIL, 3);
    }
}
