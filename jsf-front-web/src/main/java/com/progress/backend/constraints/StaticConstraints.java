/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.progress.backend.constraints;

/**
 *
 * @author Home
 */
public class StaticConstraints {

    public static String USER_CONNECTION_FIRST_NODE = "writer-ear/writer-ejb/userFacade!com.project.interfaces.UserFacadeRemote";

    public static String URL_PKG_PREFIXES = "org.jboss.ejb.client.naming";
    public static String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
    public static String PROVIDER_URL_FRIST_NODE = "http-remoting://127.0.0.1:8080";
    public static String SECURITY_PRINCIPAL_FRIST_NODE = "armdev";
    public static String SECURITY_CREDENTIALS_FIRST_NODE = "1639654122";

    ///jms
    public static final String JMS_DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    public static final String JMS_DEFAULT_USERNAME = "armdev";
    public static final String JMS_DEFAULT_PASSWORD = "1639654122";
    public static final String JMS_INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
    public static final String JMS_PROVIDER_URL = "http-remoting://127.0.0.1:8080";

}
