package com.progress.web.auth;

import com.progress.backend.connections.BackendConnectionManager;
import com.progress.backend.jms.QueueClientBean;

import com.project.dto.UserDTO;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author armena
 */
@ManagedBean(name = "userLoginBean")
@ViewScoped
public class UserLoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty("#{backendConnectionManager}")
    private BackendConnectionManager backendConnectionManager = null;

    public UserLoginBean() {
    }

    public static void main(String args[]) {
        UserLoginBean bean = new UserLoginBean();
        bean.doSend();
    }

    public String doCall() {
        try {
            UserDTO user = new UserDTO();
            user.setEmail("mail@" + System.currentTimeMillis() + ".com");
            user.setFirstname("fname" + System.currentTimeMillis());
            user.setLastname("lname" + System.currentTimeMillis());
            user.setPassword("password");
            user.setRegisteredDate(new Date(System.currentTimeMillis()));
            user.setStatus(1);
            backendConnectionManager.getUserFacadeRemote().save(user);
            int result1 = backendConnectionManager.getUserFacadeRemote().count();
            System.out.println("user count is  " + result1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String doSend() {
        try {
            UserLoginBean bean = new UserLoginBean();
            bean.doSendMessage();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String doSendMessage() {
        try {
            QueueClientBean.sendTextMessage("Hello Queue", "jms/queue/PaymentNotification");
            QueueClientBean.sendTextMessage("Hello all subscirbers, Brazil won!", "jms/topic/Subscribers");
             QueueClientBean.sendTextMessage("Hello all subscirbers, GERMANY WIN !", "jms/topic/Subscribers");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void setBackendConnectionManager(BackendConnectionManager backendConnectionManager) {
        this.backendConnectionManager = backendConnectionManager;
    }

}
