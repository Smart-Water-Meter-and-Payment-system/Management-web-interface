package org.swamp.frontend.views;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.sers.webutils.client.controllers.WebAppExceptionHandler;
import org.sers.webutils.client.views.presenters.ViewPath;
import org.sers.webutils.model.security.User;
import org.sers.webutils.model.utils.SortField;
import org.sers.webutils.server.core.service.UserService;
import org.sers.webutils.server.core.utils.ApplicationContextProvider;
import org.sers.webutils.server.shared.SharedAppData;
import org.swamp.frontend.security.HyperLinks;

import com.googlecode.genericdao.search.Search;


@ManagedBean(name = "dashboard")
@ViewScoped
@ViewPath(path = HyperLinks.DASHBOARD)
public class Dashboard extends WebAppExceptionHandler implements Serializable {

    private static final long serialVersionUID = 1L;
    private User loggedinUser;
    
    Search search = new Search();
    private String searchTerm;
	private SortField selectedSortField;
	
	//getting total users
	private UserService userService;
	private int userTotalRecords;
    
    @SuppressWarnings("unused")
    private String viewPath;
    
    @PostConstruct
    public void init() {
        loggedinUser = SharedAppData.getLoggedInUser();
		this.userService = ApplicationContextProvider.getBean(UserService.class);
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

	/**
	 * 
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * 
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 
	 * @return the userTotalRecords
	 */
	public int getUserTotalRecords() {
		return userTotalRecords;
	}

	/**
	 * 
	 * @param userTotalRecords the userTotalRecords to set
	 */
	public void setUserTotalRecords(int userTotalRecords) {
		this.userTotalRecords = userTotalRecords;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public SortField getSelectedSortField() {
		return selectedSortField;
	}

	public void setSelectedSortField(SortField selectedSortField) {
		this.selectedSortField = selectedSortField;
	}
}
