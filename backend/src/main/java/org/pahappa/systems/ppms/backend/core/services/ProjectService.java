package org.pahappa.systems.ppms.backend.core.services;

import java.util.List;
import org.pahappa.systems.ppms.backend.models.project.Project;

/**
 * Responsible for CRUD operations on {@link Project}
 * 
 * @author ttc
 *
 */
public interface ProjectService extends GenericService<Project> {
	
	/**
	 * Gets all projects from the database
	 * @return
	 */
	List<Project> getAllProjects();

}
