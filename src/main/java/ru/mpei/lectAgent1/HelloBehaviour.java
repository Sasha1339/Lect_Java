package ru.mpei.lectAgent1;

import jade.core.behaviours.OneShotBehaviour;

/**
 * Поведение которое будет делать один раз что-то
 */

public class HelloBehaviour extends OneShotBehaviour {

    @Override
    public void action() {
        System.out.println("Hello all, I'm "+this.myAgent.getLocalName());
    }
}
