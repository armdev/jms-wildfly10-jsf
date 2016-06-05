package com.writer.mdb;

import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author Home
 */
@MessageDriven(name = "SubscribersTopicQueueMDB", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/topic/Subscribers"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class SubscribersTopicQueueMDB implements MessageListener {

    private final static Logger LOGGER = Logger.getLogger(SubscribersTopicQueueMDB.class.toString());

    public SubscribersTopicQueueMDB() {
        System.out.println("Topic: SubscribersTopicQueueMDB  INIT ");
    }

    /**
     * @param rcvMessage
     * @see MessageListener#onMessage(Message)
     */
    @Override
    public void onMessage(Message rcvMessage) {
        TextMessage msg = null;
        try {
            if (rcvMessage instanceof TextMessage) {
                msg = (TextMessage) rcvMessage;
                System.out.println("SubscribersTopicQueueMDB Topic Received => " + msg.getText());
            } else {
                System.out.println("SubscribersTopicQueueMDB: Message of wrong type: " + rcvMessage.getClass().getName());
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
