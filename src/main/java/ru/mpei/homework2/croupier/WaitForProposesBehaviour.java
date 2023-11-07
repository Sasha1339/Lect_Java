package ru.mpei.homework2.croupier;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import lombok.extern.slf4j.Slf4j;
import ru.mpei.homework2.FailBehaviour;
import ru.mpei.homework2.croupier.helper.behaviour.WaitAnswerBehaviour;
import ru.mpei.homework2.croupier.helper.behaviour.WaitBehaviour;
import ru.mpei.homework2.croupier.helper.behaviour.WhoIsWinner;

/**
 * параллельное поведение ожидания ставок
 * (продоллжение последует, если хотя бы 1 поставил)
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
            log.info(myAgent.getLocalName()+": Received all proposes, winner is "+whoIsWinner.getNameWinner());
            myAgent.addBehaviour(new SendContractBehaviour(whoIsWinner.getNameWinner()));
        }else{
            if (whoIsWinner.getCount() >= 1 && whoIsWinner.getBet() > -1) {
                log.info(myAgent.getLocalName() + ": Time is up! Received not all proposes, winner is " + whoIsWinner.getNameWinner());
                myAgent.addBehaviour(new SendContractBehaviour(whoIsWinner.getNameWinner()));
            }else if (whoIsWinner.getBet() < 0){
                log.warn(myAgent.getLocalName() + ": Absent need bet more 0");
                myAgent.addBehaviour(new FailBehaviour());
            }else{
                log.warn(myAgent.getLocalName() + ": Time is up!");
                myAgent.addBehaviour(new FailBehaviour());
            }

        }
        return 0;
    }
}
