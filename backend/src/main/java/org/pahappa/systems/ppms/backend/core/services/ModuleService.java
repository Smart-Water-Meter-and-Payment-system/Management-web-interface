package org.pahappa.systems.ppms.backend.core.services;

import java.util.List;
import org.pahappa.systems.ppms.backend.models.modules.Module;

/**
 * Responsible for CRUD operations on {@link Module}
 * 
 * @author ttc
 *
 */
public interface ModuleService extends GenericService<Module> {

	/**
	 * Gets all Modules from the database
	 * @return
	 */
	List<Module> getAllModules();

}
