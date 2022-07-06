package org.pahappa.systems.ppms.backend.models.modules;

import org.pahappa.systems.ppms.backend.models.ProjectModules;
import org.pahappa.systems.ppms.backend.models.enums.CompletionStatus;
import org.sers.webutils.model.security.User;

import javax.persistence.*;
import java.util.Set;

/**
 * @author ttc
 *
 * This model will hold the various tasks attached to the submodule of a project.
 */
@Entity
@Table(name = "tasks")
public class Task extends ProjectModules {

    private static final long serialVersionUID = 1L;
    private CompletionStatus taskStatus = CompletionStatus.PENDING;
    private Submodule submodule;
    private Set<User> users;

    /**
     *
     * @return the taskStatus
     */
    @Column(name = "task_status", columnDefinition = "varchar(25) default 'PENDING'")
    @Enumerated(value = EnumType.STRING)
    public CompletionStatus getTaskStatus() {
        return taskStatus;
    }

    /**
     *
     * @param taskStatus the taskStatus to set
     */
    public void setTaskStatus(CompletionStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     *
     * @return the submodule
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "submodule_id")
    public Submodule getSubmodule() {
        return submodule;
    }

    /**
     *
     * @param submodule the submodule to set
     */
    public void setSubmodule(Submodule submodule) {
        this.submodule = submodule;
    }

    /**
     *
     * @return the users
     */
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER )
    @JoinTable(
            name = "task_users",
            joinColumns = {@JoinColumn(
                    name = "task_id"
            )},

            inverseJoinColumns = {@JoinColumn(
                    name = "user_id"
            )}
    )
    public Set<User> getUsers() {
        return users;
    }

    /**
     *
     * @param users the users to set
     */
    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
