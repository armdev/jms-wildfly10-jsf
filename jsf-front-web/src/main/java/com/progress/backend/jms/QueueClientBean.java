package com.progress.backend.jms;

import static com.progress.backend.constraints.StaticConstraints.JMS_DEFAULT_CONNECTION_FACTORY;
import static com.progress.backend.constraints.StaticConstraints.JMS_DEFAULT_PASSWORD;
import static com.progress.backend.constraints.StaticConstraints.JMS_DEFAULT_USERNAME;
import static com.progress.backend.constraints.StaticConstraints.JMS_INITIAL_CONTEXT_FACTORY;
import static com.progress.backend.constraints.StaticConstraints.JMS_PROVIDER_URL;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class QueueClientBean implements Serializable {

    private static final Logger log = Logger.getLogger(QueueClientBean.class.getName());
    private static final long serialVersionUID = -1746038087357050046L;

    public QueueClientBean() {
    }


    public static void sendTextMessage(String message, String passedDestination) {
        if (message == null || passedDestination == null) {
            return;
        }
        Context namingContext = null;

        try {
            String userName = JMS_DEFAULT_USERNAME;
            String password = JMS_DEFAULT_PASSWORD;
            // Set up the namingContext for the JNDI lookup
            final Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY, JMS_INITIAL_CONTEXT_FACTORY);
            env.put(Context.PROVIDER_URL, JMS_PROVIDER_URL);
            // env.put(Context.SECURITY_PRINCIPAL, userName);
            // env.put(Context.SECURITY_CREDENTIALS, password);
            namingContext = new InitialContext(env);
            // Perform the JNDI lookups
            String connectionFactoryString = JMS_DEFAULT_CONNECTION_FACTORY;
            System.out.println("+@@Attempting to acquire connection factory \"" + connectionFactoryString + "\"");
            ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(connectionFactoryString);
            System.out.println("+@@@Found connection factory \"" + connectionFactoryString + "\" in JNDI " + connectionFactory.toString());

            String destinationString = passedDestination;
            System.out.println("+@@Attempting to acquire destination \"" + destinationString + "\"");
            Destination destination = (Destination) namingContext.lookup(destinationString);
            System.out.println("+@@@Found destination \"" + destinationString + "\" in JNDI");

            int count = 2;
            String content = message;
            //System.out.println("userName " + userName);
            //System.out.println("password " + password);
            try (JMSContext context = connectionFactory.createContext()) {
                System.out.println("***************Sending to  " + destinationString + " messages with content: " + content + " *********************");
                 context.createProducer().send(destination, content);
             
            }
            //return true;
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            if (namingContext != null) {
                try {
                    namingContext.close();
                } catch (NamingException e) {
                    e.printStackTrace();
                }
            }
            // return false;
        }
    }

}
