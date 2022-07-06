package org.pahappa.systems.ppms.backend.models;

import org.pahappa.systems.ppms.backend.models.project.Project;
import org.sers.webutils.model.BaseEntity;
import org.sers.webutils.model.security.Role;
import org.sers.webutils.model.security.User;

import javax.persistence.*;

/**
 * Associative for {@link User}, {@link Project} and thier {@link Role}
 * Defines the relationship - User has a given role on a given Project i.e: the roles are
 * assigned as per-project basis.
 */
@Entity
@Table(name = "user_projects")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserProject extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Employee
    private User user;

    //Project they are assigned to
    private Project project;

    //The role they possess on the project
    private Role role;


    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "project_id")
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @ManyToOne
    @JoinColumn(name = "role_id")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
