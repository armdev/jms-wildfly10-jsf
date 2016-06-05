package com.writer.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.annotation.Resource;

/**
 *
 * @author Home
 */
@MessageDriven(name = "PaymentNotificationMDB", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/queue/PaymentNotification"),
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class PaymentNotificationMDB implements MessageListener {

    @Resource
    private MessageDrivenContext mdc;

    public PaymentNotificationMDB() {
         System.out.println("Queue: PaymentNotificationQueue INIT ");
    }

    @Override
    public void onMessage(Message message) {
        TextMessage msg = null;
        try {
            if (message instanceof TextMessage) {
                msg = (TextMessage) message;
                System.out.println("Queue PaymentNotificationQueue RECEIVED => " + msg.getText());
            } else {
                System.out.println("Queue PaymentNotificationQueue: Message of wrong type: " + message.getClass().getName());
            }
        } catch (JMSException e) {
            e.printStackTrace();
            mdc.setRollbackOnly();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
