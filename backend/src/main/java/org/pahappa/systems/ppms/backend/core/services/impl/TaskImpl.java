package org.pahappa.systems.ppms.backend.core.services.impl;

import org.apache.commons.lang.StringUtils;
import org.pahappa.systems.ppms.backend.core.services.TaskService;
import org.pahappa.systems.ppms.backend.models.modules.Task;
import org.sers.webutils.model.exception.OperationFailedException;
import org.sers.webutils.model.exception.ValidationFailedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskImpl extends GenericServiceImpl<Task> implements TaskService {

	@Override
	public Task saveInstance(Task task) throws ValidationFailedException {
		if(StringUtils.isBlank(task.getName()))
			throw new ValidationFailedException("Missing Module name");
		if(StringUtils.isBlank(task.getDescription()))
			throw new ValidationFailedException("Missing Module Description");
		return super.merge(task);
	}

	@Override
	public boolean isDeletable(Task entity) throws OperationFailedException {
		return true;
	}

}
