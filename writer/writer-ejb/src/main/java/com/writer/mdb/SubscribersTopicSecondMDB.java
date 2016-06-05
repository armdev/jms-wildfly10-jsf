/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@MessageDriven(name = "SubscribersTopicSecondMDB", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/topic/Subscriber"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class SubscribersTopicSecondMDB implements MessageListener {

    private final static Logger LOGGER = Logger.getLogger(SubscribersTopicSecondMDB.class.toString());

    public SubscribersTopicSecondMDB() {
         System.out.println("TOPIC: SubscribersTopicSecondMDB (Second)  INIT ");
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
                System.out.println("SubscribersTopicSecondMDB: Received Message from topic(Second)=> " + msg.getText());
            } else {
                System.out.println("SubscribersTopicSecondMDB: Message of wrong type(Second)=>  " + rcvMessage.getClass().getName());
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

}
