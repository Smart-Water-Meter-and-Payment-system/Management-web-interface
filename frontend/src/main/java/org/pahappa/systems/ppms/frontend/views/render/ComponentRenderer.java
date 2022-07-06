package org.pahappa.systems.ppms.frontend.views.render;


import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.pahappa.systems.ppms.backend.models.security.PermissionConstants;
import org.sers.webutils.model.security.User;
import org.sers.webutils.server.shared.SharedAppData;

@ManagedBean(name = "componentRenderer")
@SessionScoped
public class ComponentRenderer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean administrator = false;
	private boolean ppmsDeveloper = false;
	private boolean ppmsTechManager = false;
	private boolean salesperson = false;
	private boolean projectLead = false;
	private User loggedInUser;

	@PostConstruct
	public void init() {
		this.loggedInUser = SharedAppData.getLoggedInUser();
		this.administrator = loggedInUser.hasAdministrativePrivileges();
		this.setPpmsDeveloper(loggedInUser.hasRole(PermissionConstants.PERM_PPMS_DEV));
		this.setPpmsTechManager(loggedInUser.hasRole(PermissionConstants.PERM_PPMS_TECHMANAGER));
		this.setProjectLead(loggedInUser.hasRole(PermissionConstants.PERM_PPMS_TECHMANAGER));

	}

	/**
	 * @return the loggedInUser
	 */
	public User getLoggedInUser() {
		return loggedInUser;
	}

	/**
	 * @param loggedInUser the loggedInUser to set
	 */
	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	/**
	 * @return the administrator
	 */
	public boolean isAdministrator() {
		return administrator;
	}

	/**
	 * @param administrator the administrator to set
	 */
	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}
	
	
	/**
	 * 
	 * @return the ppms developer
	 */
	public boolean isPpmsDeveloper() {
		return ppmsDeveloper;
	}

	/**
	 * 
	 * @param ppmsDeveloper the ppmsDeveloper to set
	 */
	public void setPpmsDeveloper(boolean ppmsDeveloper) {
		this.ppmsDeveloper = ppmsDeveloper;
	}


	/**
	 * @return the salesperson
	 */
	public boolean isSalesperson() {
		return salesperson;
	}

	/**
	 * @param salesperson the salesperson to set
	 */
	public void setSalesperson(boolean salesperson) {
		this.salesperson = salesperson;
	}

	/**
	 * @return the ppmsTechManager
	 */
	public boolean isPpmsTechManager() {
		return ppmsTechManager;
	}

	/**
	 * @param ppmsTechManager the ppmsTechManager to set
	 */
	public void setPpmsTechManager(boolean ppmsTechManager) {
		this.ppmsTechManager = ppmsTechManager;
	}

	/**
	 * @return the projectLead
	 */
	public boolean isProjectLead() {
		return projectLead;
	}

	/**
	 * @param projectLead the projectLead to set
	 */
	public void setProjectLead(boolean projectLead) {
		this.projectLead = projectLead;
	}
	
}