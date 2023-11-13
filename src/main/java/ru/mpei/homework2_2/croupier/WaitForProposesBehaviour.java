package ru.mpei.homework2_2.croupier;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import lombok.extern.slf4j.Slf4j;
import ru.mpei.homework2_2.FailBehaviour;
import ru.mpei.homework2_2.croupier.helper.behaviour.WaitAnswerBehaviour;
import ru.mpei.homework2_2.croupier.helper.behaviour.WaitBehaviour;
import ru.mpei.homework2_2.croupier.helper.behaviour.WhoIsWinner;

/**
 * параллельное поведение ожидания ставок
 * (продолжение последует, если хотя бы 1 поставил)
 */
@Slf4j
public class WaitForProposesBehaviour extends ParallelBehaviour {

    public WaitForProposesBehaviour(){
        super(WHEN_ANY);
    }
    private Behaviour receiveBeh, wakerBeh;
    private WhoIsWinner whoIsWinner = new WhoIsWinner();
    @Override
    public void onStart() {
        receiveBeh = new WaitAnswerBehaviour(whoIsWinner);
        wakerBeh = new WaitBehaviour(myAgent, 10000);
        this.addSubBehaviour(receiveBeh);
        this.addSubBehaviour(wakerBeh);
    }

    @Override
    public int onEnd() {
        if (receiveBeh.done() && whoIsWinner.getBet() > -1){
            log.info(myAgent.getLocalName()+": Received all proposes, winner is "+whoIsWinner.getNameLocalWinner());
            myAgent.addBehaviour(new SendContractBehaviour(whoIsWinner.getNameWinner(), whoIsWinner.getNameLocalWinner()));
        }else{
            if (whoIsWinner.getCount() >= 1 && whoIsWinner.getBet() > -1) {
                log.info(myAgent.getLocalName() + ": Time is up! Received not all proposes, winner is " + whoIsWinner.getNameLocalWinner());
                myAgent.addBehaviour(new SendContractBehaviour(whoIsWinner.getNameWinner(), whoIsWinner.getNameLocalWinner()));
            }else if (whoIsWinner.getBet() < 0){
                log.warn(myAgent.getLocalName() + ": Absent need bet more 0");
                myAgent.addBehaviour(new FailBehaviour("Auction was failed"));
            }else{
                log.warn(myAgent.getLocalName() + ": Time is up!");
                myAgent.addBehaviour(new FailBehaviour("Auction was failed!"));
            }

        }
        return 0;
    }
}
