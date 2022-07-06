package org.pahappa.systems.ppms.frontend.views.ppms;

import org.pahappa.systems.ppms.backend.core.services.ProjectService;
import org.pahappa.systems.ppms.backend.core.services.UserProjectService;
import org.pahappa.systems.ppms.backend.models.UserProject;
import org.pahappa.systems.ppms.backend.models.project.Project;
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

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



@ManagedBean(name = "userProjectForm")
@SessionScoped
@ViewPath(path = HyperLinks.USER_PROJECT_FORM)
public class UserProjectForm extends WebFormView<UserProject, UserProjectForm, UserProjectView> {

    private static final long serialVersionUID = 1L;
    private UserProjectService userProjectService;
    private Project project;
    private List<Project> projects;
    private Project selectedProject;
    private Role role;
    private List<Role> roles;
    private Role selectedRole;
    private User user;
    private List<User> users;
    private User selectedUser;


    @Override
    @PostConstruct
    public void beanInit() {
        this.projects = ApplicationContextProvider.getBean(ProjectService.class).getAllProjects();
        this.roles = ApplicationContextProvider.getBean(RoleService.class).getRoles();
        try {
            this.users = ApplicationContextProvider.getBean(UserService.class).getUsers();
        } catch (OperationFailedException e) {
            e.printStackTrace();
        }
        this.userProjectService = ApplicationContextProvider.getBean(UserProjectService.class);

        this.resetModal();
    }

    @Override
    public void pageLoadInit() {
        this.projects = ApplicationContextProvider.getBean(ProjectService.class).getAllProjects();
    }

    @Override
    public void persist() throws Exception {
        try {
            this.userProjectService.saveInstance(super.model);
            //UiUtils.showMessageBox("Action successfull", "Module saved successfully");
            this.redirectTo(HyperLinks.USER_PROJECT_VIEW);
        } catch (ValidationFailedException e) {
            UiUtils.ComposeFailure("Action Failed", e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void resetModal() {
        super.resetModal();
        super.model = new UserProject();
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Project getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }

    public UserProjectService getUserProjectService() {
        return userProjectService;
    }

    public void setUserProjectService(UserProjectService userProjectService) {
        this.userProjectService = userProjectService;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Role getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(Role selectedRole) {
        this.selectedRole = selectedRole;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }
}
