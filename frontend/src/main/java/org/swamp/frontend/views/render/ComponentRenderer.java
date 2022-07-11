package org.swamp.frontend.views.render;


import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.sers.webutils.model.security.User;
import org.sers.webutils.server.shared.SharedAppData;
import org.swamp.backend.models.security.PermissionConstants;

@ManagedBean(name = "componentRenderer")
@SessionScoped
public class ComponentRenderer implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean administrator = false;
	private boolean superAdmin = false;
	private boolean systemAdmin = false;
	private boolean hardwareTechnician = false;
	
	private User loggedInUser;

	@PostConstruct
	public void init() {
		this.loggedInUser = SharedAppData.getLoggedInUser();
		this.administrator = loggedInUser.hasAdministrativePrivileges();
		this.setSuperAdmin(loggedInUser.hasPermission(PermissionConstants.SUPER_ADMINISTRATOR));
		this.setSystemAdmin(loggedInUser.hasPermission(PermissionConstants.SYSTEM_ADMINISTRATOR));
		this.setHardwareTechnician(loggedInUser.hasPermission(PermissionConstants.HARDWARE_TECHNICIAN));
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

	public boolean isSuperAdmin() {
		return superAdmin;
	}

	public void setSuperAdmin(boolean superAdmin) {
		this.superAdmin = superAdmin;
	}

	public boolean isSystemAdmin() {
		return systemAdmin;
	}

	public void setSystemAdmin(boolean systemAdmin) {
		this.systemAdmin = systemAdmin;
	}

	public boolean isHardwareTechnician() {
		return hardwareTechnician;
	}

	public void setHardwareTechnician(boolean hardwareTechnician) {
		this.hardwareTechnician = hardwareTechnician;
	}
	
}