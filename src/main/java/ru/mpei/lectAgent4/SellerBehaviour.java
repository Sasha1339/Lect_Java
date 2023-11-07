package ru.mpei.lectAgent4;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SellerBehaviour extends Behaviour {

    /**
     * нацелен на то, чтобы получать сообщение из чата
     */
    /**
     * в он старте подписаться на этот топик
     */

    private String topicName;
    private double minPrice;
    private double currentPrice;

    private AID topic;

    private boolean finishAuction = false;

    public SellerBehaviour(String topicName, double minPrice){
        this.minPrice = minPrice;
        this.topicName = topicName;

    }

    @Override
    public void onStart() {
        topic = TopicHelper.register(myAgent, topicName);
        ACLMessage first = new ACLMessage(ACLMessage.INFORM);
        //отправляем сообщение в токпик!
        first.setContent(minPrice*2+"");
        currentPrice = minPrice*2;
        first.addReceiver(topic);
        myAgent.send(first);
    }

    @Override
    public void action() {
        /**
         * теперь принимаем сообщение только из этого топика
         */
        ACLMessage msg = myAgent.receive(MessageTemplate.MatchTopic(topic));
        //фильтруем?
        if (msg != null && !msg.getSender().equals(myAgent.getAID())){
            double other = Double.parseDouble(msg.getContent());
            if (other < currentPrice){
                if (other < minPrice){
                    //выходим из торгов
                    finishAuction = true;
                } else {
                    double random = 0.85 + 0.1*Math.random();
                    double myNewBet = other * random;
                }
            } else {
                log.info("ooho");
            }
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return finishAuction;
    }

    public void sendBet(){

    }

    /**
     * чтобы активровать топик - нужна регистрация
     * в строке - "-services jade.core.messaging.TopicManagementService"
     */
}
