package com.progress.backend.connections;

import static com.progress.backend.constraints.StaticConstraints.INITIAL_CONTEXT_FACTORY;
import static com.progress.backend.constraints.StaticConstraints.PROVIDER_URL_FRIST_NODE;
import static com.progress.backend.constraints.StaticConstraints.SECURITY_CREDENTIALS_FIRST_NODE;
import static com.progress.backend.constraints.StaticConstraints.SECURITY_PRINCIPAL_FRIST_NODE;
import static com.progress.backend.constraints.StaticConstraints.URL_PKG_PREFIXES;
import static com.progress.backend.constraints.StaticConstraints.USER_CONNECTION_FIRST_NODE;

import com.project.interfaces.UserFacadeRemote;
import java.io.Serializable;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author armena
 */
@ManagedBean(name = "backendConnectionManager")
@ApplicationScoped

public class BackendConnectionManager implements Serializable {

    private static final long serialVersionUID = 5488290333587383193L;

    private Context context = null;
    private UserFacadeRemote userFacadeRemote;

    public BackendConnectionManager() {
    }

    @PostConstruct
    public void init() {
        Properties prop = new Properties();
        prop.put(Context.URL_PKG_PREFIXES, URL_PKG_PREFIXES);
        prop.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
        prop.put(Context.PROVIDER_URL, PROVIDER_URL_FRIST_NODE);
        prop.put(Context.SECURITY_PRINCIPAL, SECURITY_PRINCIPAL_FRIST_NODE);
        prop.put(Context.SECURITY_CREDENTIALS, SECURITY_CREDENTIALS_FIRST_NODE);
        prop.put("jboss.naming.client.ejb.context", true);
        try {
            context = new InitialContext(prop);
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }

    public UserFacadeRemote getUserFacadeRemote() throws NamingException {

        if (userFacadeRemote == null) {
            System.out.println("userFacadeRemote is null , do call again");
            userFacadeRemote = this.lookupUserFacadeRemoteFirstNode();
        }

        return userFacadeRemote;
    }

    private UserFacadeRemote lookupUserFacadeRemoteFirstNode() throws NamingException {
        String url = USER_CONNECTION_FIRST_NODE;
        UserFacadeRemote userFacadeRemote = (UserFacadeRemote) context.lookup(url);
        if (userFacadeRemote != null) {
            return userFacadeRemote;
        } else {
            System.out.println("Remmote server down, find another instance");
        }
        return null;
    }

    @PreDestroy
    private void closeContext() throws NamingException {
        if (context != null) {
            context.close();
        }
    }

}
