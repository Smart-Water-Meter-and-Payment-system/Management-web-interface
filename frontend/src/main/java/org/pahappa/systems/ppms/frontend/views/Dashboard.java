package org.pahappa.systems.ppms.frontend.views;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.pahappa.systems.ppms.frontend.security.HyperLinks;
import org.sers.webutils.client.controllers.WebAppExceptionHandler;
import org.sers.webutils.client.views.presenters.ViewPath;
import org.sers.webutils.model.security.User;
import org.sers.webutils.server.shared.SharedAppData;


@ManagedBean(name = "dashboard")
@ViewScoped
@ViewPath(path = HyperLinks.DASHBOARD)
public class Dashboard extends WebAppExceptionHandler implements Serializable {

    private static final long serialVersionUID = 1L;
    private User loggedinUser;
    
    @SuppressWarnings("unused")
    private String viewPath;
    
    

    @PostConstruct
    public void init() {
        loggedinUser = SharedAppData.getLoggedInUser();
    }

    public User getLoggedinUser() {
        return loggedinUser;
    }

    public void setLoggedinUser(User loggedinUser) {
        this.loggedinUser = loggedinUser;
    }

    /**
     * @return the viewPath
     */
    public String getViewPath() {
        return Dashboard.class.getAnnotation(ViewPath.class).path();
    }

    /**
     * @param viewPath the viewPath to set
     */
    public void setViewPath(String viewPath) {
        this.viewPath = viewPath;
    }

   
    
}
