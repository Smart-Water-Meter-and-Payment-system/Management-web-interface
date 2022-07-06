package org.pahappa.systems.ppms.backend.core.services.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.pahappa.systems.ppms.backend.core.services.ModuleService;
import org.pahappa.systems.ppms.backend.models.modules.Module;
import org.sers.webutils.model.RecordStatus;
import org.sers.webutils.model.exception.OperationFailedException;
import org.sers.webutils.model.exception.ValidationFailedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;

@Service
@Transactional
public class ModuleImpl extends GenericServiceImpl<Module> implements ModuleService {
	
	@Override
	public List<Module> getAllModules() {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		return super.search(search);
	}

	@Override
	public Module saveInstance(Module module) throws ValidationFailedException {
		if(StringUtils.isBlank(module.getName()))
			throw new ValidationFailedException("Missing Module name");
		if(StringUtils.isBlank(module.getDescription()))
			throw new ValidationFailedException("Missing Module Description");
		return super.merge(module);
	}

	@Override
	public boolean isDeletable(Module entity) throws OperationFailedException {
		return true;
	}

}
