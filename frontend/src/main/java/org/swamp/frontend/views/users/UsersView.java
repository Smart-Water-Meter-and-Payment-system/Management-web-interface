package org.swamp.frontend.views.users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.PrimeFaces;
import org.sers.webutils.client.views.presenters.PaginatedTableView;
import org.sers.webutils.client.views.presenters.ViewPath;
import org.sers.webutils.model.Gender;
import org.sers.webutils.model.exception.OperationFailedException;
import org.sers.webutils.model.exception.ValidationFailedException;
import org.sers.webutils.model.security.Role;
import org.sers.webutils.model.security.User;
import org.sers.webutils.server.core.service.RoleService;
import org.sers.webutils.server.core.service.UserService;
import org.sers.webutils.server.core.service.excel.reports.ExcelReport;
import org.sers.webutils.server.core.utils.ApplicationContextProvider;
import org.sers.webutils.server.core.utils.MailService;
import org.sers.webutils.server.shared.SharedAppData;
import org.swamp.backend.core.utils.CustomSearchUtils;
import org.swamp.frontend.security.HyperLinks;
import org.swamp.frontend.security.UiUtils;

@ManagedBean(name = "usersView")
@SessionScoped
@ViewPath(path = HyperLinks.USERS_VIEW)
public class UsersView extends PaginatedTableView<User, UsersView, UsersView> {

    private static final long serialVersionUID = 1L;
    private UserService userService;
    private String searchTerm;
    private List<Gender> genders= new ArrayList<>();
    private User selectedUser;
    private List<Role> rolesList= new ArrayList<Role>();
    private Set<Role> selectedRolesList= new HashSet<>();
    private MailService mailservice;
    private int customPropOneNumber;
    private List<Role> roles, selectedRoles = new ArrayList<Role>();
    
    @PostConstruct
    @Override
    public void init() {
        this.userService = ApplicationContextProvider.getApplicationContext().getBean(UserService.class);
        super.setMaximumresultsPerpage(10);
        this.rolesList=ApplicationContextProvider.getApplicationContext().getBean(RoleService.class).getRoles();
        this.genders= Arrays.asList(Gender.values());
        this.customPropOneNumber = 0;
        this.roles = ApplicationContextProvider.getApplicationContext().getBean(RoleService.class).getRoles();
    }

    @Override
    public void reloadFromDB(int offset, int limit, Map<String, Object> filters) throws Exception {
    	List<String> roleIds = new ArrayList<String>();
		for(Role role : this.selectedRoles) {
			roleIds.add(role.getId());
		}
        if (SharedAppData.getLoggedInUser() != null && SharedAppData.getLoggedInUser().hasPermission(org.sers.webutils.model.security.PermissionConstants.PERM_ADMINISTRATOR)) {
            super.setDataModels(userService.getUsers(CustomSearchUtils.generateSearchObjectForUsers(this.searchTerm, null, roleIds), offset, limit));
        }
    }

    @Override
    public void reloadFilterReset() {
		List<String> roleIds = new ArrayList<String>();
		for(Role role : this.selectedRoles) {
			roleIds.add(role.getId());
		}
        if (SharedAppData.getLoggedInUser() != null && SharedAppData.getLoggedInUser().hasPermission(org.sers.webutils.model.security.PermissionConstants.PERM_ADMINISTRATOR)) {
            super.setTotalRecords(
                    this.userService.countUsers(CustomSearchUtils.generateSearchObjectForUsers(this.searchTerm, null, roleIds)));
            this.resetInput();
        }
    }

    @Override
    public List<ExcelReport> getExcelReportModels() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getFileName() {
        // TODO Auto-generated method stub
        return null;
    }

    public void saveSelectedUser() {
        this.selectedUser.setRoles(this.selectedRolesList);
        try {
        	final String subject = "SWAMP ACCOUNT";
        	final String emailContent = "Hey " + this.selectedUser.getFirstName()+"! your account has been created successfully! "
        			+ "Username: " + this.selectedUser.getUsername() 
        			+ "Password: " + this.selectedUser.getClearTextPassword();
        	final String recipient = this.selectedUser.getEmailAddress();
        	
            userService.saveUser(this.selectedUser);
            PrimeFaces.current().executeScript("PF('selected_user_dialog').hide()");
            PrimeFaces.current().ajax().update("usersView:usersTable");
            
//            ExecutorService service = Executors.newFixedThreadPool(1);
//            service.submit(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						EmailClient.sendMail(recipient, subject, emailContent);
//					} catch (Exception e) {
//						e.printStackTrace();
//					} 
//				}
//			});
            
            UiUtils.showMessageBox("Action successful","SwampUser account created. Log-in details sent to user's email");
           // mailservice.sendPasswordChangeMail(this.selectedUser);
            
        } catch (ValidationFailedException ex) {
            UiUtils.ComposeFailure("Action failed",ex.getLocalizedMessage());
            Logger.getLogger(UsersView.class.getName()).log(Level.SEVERE, null, ex);
        }
        reloadFilterReset();
    }
    
    
    public void deleteSelectedUser(User user) throws ValidationFailedException, OperationFailedException {
        user.setUsername(user.getUsername()+"_deleted");
        userService.deleteUser(user);
    }

    public void loadSelectedUser(User user) {
        if (user != null) {
            this.selectedUser = user;
            this.selectedRolesList= new HashSet<>(user.getRoles());

        } else {
            this.selectedRolesList= new HashSet<>();
            this.selectedUser = new User();
        }
    }
    
    

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    private void resetInput() {
        this.searchTerm = "";
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<Role> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Role> rolesList) {
        this.rolesList = rolesList;
    }

    public List<Gender> getGenders() {
        return genders;
    }

    public void setGenders(List<Gender> genders) {
        this.genders = genders;
    }

    public Set<Role> getSelectedRolesList() {
        return selectedRolesList;
    }

    public void setSelectedRolesList(Set<Role> selectedRolesList) {
        this.selectedRolesList = selectedRolesList;
    }

	public int getCustomPropOneNumber() {
		return customPropOneNumber;
	}

	public void setCustomPropOneNumber(int customPropOneNumber) {
		this.customPropOneNumber = customPropOneNumber;
	}

	public MailService getMailservice() {
		return mailservice;
	}

	public void setMailservice(MailService mailservice) {
		this.mailservice = mailservice;
	}

	public List<Role> getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(List<Role> selectedRoles) {
		this.selectedRoles = selectedRoles;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
    
}
