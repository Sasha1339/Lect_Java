package ru.mpei.homework2_2.croupier;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import lombok.extern.slf4j.Slf4j;
import ru.mpei.homework2_2.FailBehaviour;
import ru.mpei.homework2_2.SuccessBehaviour;
import ru.mpei.homework2_2.croupier.helper.behaviour.WaitAnswerContractAndReject;
import ru.mpei.homework2_2.croupier.helper.behaviour.WaitAnswerContractBehaviour;
import ru.mpei.homework2_2.croupier.helper.behaviour.WaitBehaviour;


/**
 * параллльеное поведение, ожидания подтверждения контракта
 */
@Slf4j
public class WaitForAcceptContractBehaviour extends ParallelBehaviour {

    public WaitForAcceptContractBehaviour(){
        super(WHEN_ANY);
    }
    private Behaviour receiveBeh, wakerBeh, receiveFail;
    @Override
    public void onStart() {
        receiveBeh = new WaitAnswerContractBehaviour();
        receiveFail = new WaitAnswerContractAndReject();
        wakerBeh = new WaitBehaviour(myAgent, 10000);
        this.addSubBehaviour(receiveBeh);
        this.addSubBehaviour(receiveFail);
        this.addSubBehaviour(wakerBeh);
    }

    @Override
    public int onEnd() {
        if (receiveBeh.done()){
            myAgent.addBehaviour(new SuccessBehaviour());
        }else{
            myAgent.addBehaviour(new FailBehaviour("Auction was failed!"));
        }
        return 0;
    }

}
