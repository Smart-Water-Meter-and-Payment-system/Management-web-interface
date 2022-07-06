package org.pahappa.systems.ppms.frontend.views.users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.pahappa.systems.ppms.backend.core.services.impl.CustomApplicationSettings;
import org.pahappa.systems.ppms.frontend.security.HyperLinks;
import org.pahappa.systems.ppms.frontend.security.UiUtils;
import org.sers.webutils.client.views.presenters.ViewPath;
import org.sers.webutils.client.views.presenters.WebFormView;
import org.sers.webutils.model.exception.OperationFailedException;
import org.sers.webutils.model.exception.ValidationFailedException;
import org.sers.webutils.model.security.Role;
import org.sers.webutils.model.security.User;
import org.sers.webutils.server.core.service.RoleService;
import org.sers.webutils.server.core.service.UserService;
import org.sers.webutils.server.core.utils.ApplicationContextProvider;
import org.sers.webutils.server.core.utils.MailUtils;
import org.sers.webutils.server.shared.SharedAppData;
import org.springframework.beans.BeansException;

@ManagedBean(name = "userForm")
@SessionScoped
@ViewPath(path = HyperLinks.USER_FORM)
public class UserForm extends WebFormView<User, UserForm, UsersView> {

    private static final long serialVersionUID = 1L;
    private UserService userService;
    //	private Role selectedRole;
    private List<Role> selectedRoles = new ArrayList<Role>();
    private List<Role> roles;

    private boolean newView;

    @Override
    @PostConstruct
    public void beanInit() {
        userService = ApplicationContextProvider.getBean(UserService.class);
        roles = ApplicationContextProvider.getBean(RoleService.class).getRoles();
        ApplicationContextProvider.getBean(CustomApplicationSettings.class);
    }

    @Override
    public void persist() throws Exception {
        if (SharedAppData.getLoggedInUser() != null && SharedAppData.getLoggedInUser().hasAdministrativePrivileges()) {
          
            User existingWithUsername = userService.getUserByUsername(super.getModel().getUsername());

         
            if (existingWithUsername != null && !existingWithUsername.getId().equals(super.model.getId())) {
                throw new ValidationFailedException("User with that username already exists");
            }

            if (!MailUtils.Util.getInstance().isValidEmail(super.getModel().getEmailAddress())) {
                throw new ValidationFailedException("Invalid user email address");
            }

            userService.saveUser(super.getModel());

        }
    }

    @Override
    public void resetModal() {
        super.resetModal();
        super.model = new User();
    }

    @Override
    public void setFormProperties() {
        super.setFormProperties();
    }

    public void deleteUser(User user) throws BeansException,
            OperationFailedException, IOException, ValidationFailedException {
//		this.churchService.deleteUser(user);
        this.redirectTo(HyperLinks.USERS_VIEW);
    }

    @Override
    public void pageLoadInit() {
        // TODO Auto-generated method stub
    }

    /**
     * @return the selectedRoles
     */
    public List<Role> getSelectedRoles() {
        return selectedRoles;
    }

    /**
     * @param selectedRoles the selectedRoles to set
     */
    public void setSelectedRoles(List<Role> selectedRoles) {
        this.selectedRoles = selectedRoles;
    }

    /**
     * @return the roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addSelectedRoles() throws ValidationFailedException {
        if (selectedRoles.size() > 0) {
            for (Role role : selectedRoles) {
                super.getModel().addRole(role);
            }
            this.userService.saveUser(super.getModel());
            UiUtils.showMessageBox("User roles updated", "Action Successful");
        }
    }

    public void removeUserRole(Role role) {
        System.out.println("In remove user role function");

        try {
            super.getModel().removeRole(role);
            this.userService.saveUser(super.getModel());
            UiUtils.showMessageBox("User roles updated", "Action Successful");
        } catch (ValidationFailedException e) {
            UiUtils.showMessageBox(e.getMessage(), "Action failed");
        }
    }

    /**
     * @return the newView
     */
    public boolean isNewView() {
        return newView;
    }

    /**
     * @param newView the newView to set
     */
    public void setNewView(boolean newView) {
        this.newView = newView;
    }

}
