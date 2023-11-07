package ru.mpei.lectAgent4;

import jade.core.AID;
import jade.core.Agent;
import jade.core.messaging.TopicManagementHelper;
import lombok.SneakyThrows;

public class TopicHelper {

    @SneakyThrows
    public static AID register(Agent a, String topicName){
        TopicManagementHelper tmh;
        AID topic;
        tmh = (TopicManagementHelper) a.getHelper(TopicManagementHelper.SERVICE_NAME);
        topic = tmh.createTopic(topicName);
        tmh.register(topic);
        return topic;
    }

}
