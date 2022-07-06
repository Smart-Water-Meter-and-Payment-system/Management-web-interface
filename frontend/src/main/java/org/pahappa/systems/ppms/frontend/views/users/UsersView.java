package org.pahappa.systems.ppms.frontend.views.users;

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

import org.pahappa.systems.ppms.backend.core.utils.CustomSearchUtils;
import org.pahappa.systems.ppms.frontend.security.HyperLinks;
import org.pahappa.systems.ppms.frontend.security.UiUtils;
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
import org.sers.webutils.server.shared.SharedAppData;

@ManagedBean(name = "usersView")
@SessionScoped
@ViewPath(path = HyperLinks.USERS_VIEW)
public class UsersView extends PaginatedTableView<User, UsersView, UsersView> {

    private static final long serialVersionUID = 1L;
    private UserService userService;
    private String searchTerm;
    private List<Gender> genders= new ArrayList<>();
    private User selectedUser;
    private List<Role> rolesList= new ArrayList();
     private Set<Role> selectedRolesList= new HashSet<>();

    @PostConstruct
    @Override
    public void init() {
        this.userService = ApplicationContextProvider.getApplicationContext().getBean(UserService.class);
        super.setMaximumresultsPerpage(10);
        this.rolesList=ApplicationContextProvider.getApplicationContext().getBean(RoleService.class).getRoles();
        this.genders= Arrays.asList(Gender.values());
    }

    @Override
    public void reloadFromDB(int offset, int limit, Map<String, Object> filters) throws Exception {
        if (SharedAppData.getLoggedInUser() != null && SharedAppData.getLoggedInUser().hasAdministrativePrivileges()) {
            super.setDataModels(userService.getUsers(CustomSearchUtils.genereateSearchObjectForUsers(this.searchTerm, null), offset, limit));
        }
    }

    @Override
    public void reloadFilterReset() {
        if (SharedAppData.getLoggedInUser() != null && SharedAppData.getLoggedInUser().hasAdministrativePrivileges()) {
            super.setTotalRecords(
                    this.userService.countUsers(CustomSearchUtils.genereateSearchObjectForUsers(this.searchTerm, null)));
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
            userService.saveUser(this.selectedUser);
            PrimeFaces.current().executeScript("PF('selected_user_dialog').hide()");
            PrimeFaces.current().ajax().update("usersView:usersTable");
            UiUtils.showMessageBox("Action successful","User account created. Log-in details sent to user's email");
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
    
    

    
}
