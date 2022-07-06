package org.pahappa.systems.ppms.backend.core.services.impl;

import org.pahappa.systems.ppms.backend.core.services.UserProjectService;
import org.pahappa.systems.ppms.backend.models.UserProject;
import org.pahappa.systems.ppms.backend.models.project.Project;
import org.sers.webutils.model.exception.OperationFailedException;
import org.sers.webutils.model.exception.ValidationFailedException;
import org.sers.webutils.model.security.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class UserProjectServiceImpl
        extends GenericServiceImpl<UserProject> implements
        UserProjectService {

    /**
     * Saves an instance of a User Project
     * @param instance
     * @return
     * @throws ValidationFailedException
     * @throws OperationFailedException
     */
    @Override
    public UserProject saveInstance(UserProject instance) throws ValidationFailedException, OperationFailedException {
        if (instance.getUser().getId() == null) {
            throw new ValidationFailedException("User is not set");
        } else if (instance.getProject().getId() == null) {
            throw new ValidationFailedException("Project is not set");
        } else if (instance.getRole().getId() == null) {
            throw new ValidationFailedException("Role is not set");
        } else {
            return super.merge(instance);
        }
    }

    /**
     * Check if Entity is deletable
     *
     * @param userProject
     * @return
     * @throws OperationFailedException
     */
    @Override
    public boolean isDeletable(UserProject userProject) throws OperationFailedException {
        return true;
    }

    /**
     * @param user
     * @return
     */
    @Override
    public List<UserProject> getUserProjects(User user) {
        return super.searchByPropertyEqual("user", user);
    }

    /**
     * Returns the number of projects an employee is assigned to
     *
     * @param user
     * @return
     */
    @Override
    public int countUserProjects(User user) {
        return getUserProjects(user).size();
    }

    @Override
    public List<UserProject> getProjectTeam(Project project) {
        return super.searchByPropertyEqual("project", project);
    }
}
