package ru.mpei.homework2_2.sellers;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import lombok.extern.slf4j.Slf4j;
import ru.mpei.homework2_2.FailBehaviour;
import ru.mpei.homework2_2.croupier.helper.behaviour.WaitBehaviour;
import ru.mpei.homework2_2.sellers.helper.behaviour.InfoCroupier;
import ru.mpei.homework2_2.sellers.helper.behaviour.WaitForContractYouLose;
import ru.mpei.homework2_2.sellers.helper.behaviour.WaitForContractYouWin;

/**
 * поведение паралллельное ожидания контракта и решения о дальнейшем его принятии
 */
@Slf4j
public class WaitForContractBehaviour extends ParallelBehaviour {


    public WaitForContractBehaviour(){
        super(WHEN_ANY);
    }
    private Behaviour receiveBehWin, receiveBehLose, wakerBeh;

    private InfoCroupier infoCroupier = new InfoCroupier();
    @Override
    public void onStart() {
        receiveBehWin = new WaitForContractYouWin(infoCroupier);
        receiveBehLose = new WaitForContractYouLose();
        wakerBeh = new WaitBehaviour(myAgent, 15000);
        this.addSubBehaviour(receiveBehWin);
        this.addSubBehaviour(receiveBehLose);
        this.addSubBehaviour(wakerBeh);
    }

    @Override
    public int onEnd() {
        if (receiveBehWin.done()){
            int choice = (int)(Math.random() * 3);
            switch (choice){
                case 0: // agree
                        myAgent.addBehaviour(new SendContractYouWin(infoCroupier.getNameCroupier()));
                break;
                case 1: // reject
                        myAgent.addBehaviour(new SendContractWinAndReject(infoCroupier.getNameCroupier()));
                break;
                case 2:
                    log.warn(myAgent.getLocalName()+ ": I'm ignore!");
                break;
            }
        }else{
            myAgent.addBehaviour(new FailBehaviour("I was failed auction"));
        }
        return 0;
    }

}
