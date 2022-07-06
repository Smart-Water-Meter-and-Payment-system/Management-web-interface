package org.pahappa.systems.ppms.backend.core.services.impl;

import org.apache.commons.lang.StringUtils;
import org.pahappa.systems.ppms.backend.core.services.SubmoduleService;
import org.pahappa.systems.ppms.backend.models.modules.Submodule;
import org.sers.webutils.model.exception.OperationFailedException;
import org.sers.webutils.model.exception.ValidationFailedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubmoduleImpl extends GenericServiceImpl<Submodule> implements SubmoduleService {

	@Override
	public Submodule saveInstance(Submodule submodule) throws ValidationFailedException {
		if(StringUtils.isBlank(submodule.getName()))
			throw new ValidationFailedException("Missing Module name");
		if(StringUtils.isBlank(submodule.getDescription()))
			throw new ValidationFailedException("Missing Module Description");
		return super.merge(submodule);
	}

	@Override
	public boolean isDeletable(Submodule entity) throws OperationFailedException {
		return true;
	}

}
