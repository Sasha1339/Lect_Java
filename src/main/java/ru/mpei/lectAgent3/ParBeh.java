package ru.mpei.lectAgent3;

import jade.core.behaviours.ParallelBehaviour;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParBeh extends ParallelBehaviour {


    ReceiveMesBehav receive;
    TmelsBehaviour times;
    public ParBeh(){
        super(WHEN_ANY);
    }

    @Override
    public void onStart() {
        receive = new ReceiveMesBehav();
        this.addSubBehaviour(receive);
        times = new TmelsBehaviour(myAgent, 5000);
        this.addSubBehaviour(times);
    }
    /**
     * как понять какое поведение закончилось?
     */

    @Override
    public int onEnd() {
        if (receive.done()){
            log.info("Поведение певрое закончилось");
        }else{
            log.info("Time is up");
        }
        return 0;
    }
}
