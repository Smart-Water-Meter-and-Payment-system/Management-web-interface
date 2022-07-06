package org.pahappa.systems.ppms.backend.core.services;



import org.pahappa.systems.ppms.backend.models.UserProject;
import org.pahappa.systems.ppms.backend.models.project.Project;
import org.sers.webutils.model.security.User;

import java.util.List;

public interface UserProjectService extends GenericService<UserProject> {
    //


    /**
     * Find all projects where an employee is associated
     *
     * @param user
     * @return
     */
    public List<UserProject> getUserProjects(User user);


    /**
     * Returns the number of projects an employee is assigned to
     *
     * @param user
     * @return
     */
    public int countUserProjects(User user);

    /**
     * Get All Employees associated with a project
     *
     * @param project
     * @return
     */
    public List<UserProject> getProjectTeam(Project project);
}
