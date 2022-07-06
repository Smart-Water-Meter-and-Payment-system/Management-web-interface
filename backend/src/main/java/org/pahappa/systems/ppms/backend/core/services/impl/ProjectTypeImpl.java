package org.pahappa.systems.ppms.backend.core.services.impl;

import org.apache.commons.lang.StringUtils;
import org.pahappa.systems.ppms.backend.core.services.ProjectTypeService;
import org.pahappa.systems.ppms.backend.models.project.ProjectType;
import org.sers.webutils.model.exception.OperationFailedException;
import org.sers.webutils.model.exception.ValidationFailedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Golder
 *
 */
@Service
@Transactional
public class ProjectTypeImpl extends GenericServiceImpl<ProjectType> implements ProjectTypeService{

	@Override
	public ProjectType saveInstance(ProjectType entityInstance) throws ValidationFailedException {
		if(StringUtils.isBlank(entityInstance.getName()))
			throw new ValidationFailedException("Missing Project Type name");
		if(StringUtils.isBlank(entityInstance.getDescription()))
			throw new ValidationFailedException("Missing Project Type Description");
		return super.save(entityInstance);
		
	}

	@Override
	public boolean isDeletable(ProjectType entity) throws OperationFailedException {	
		return true;
	}
   
}
