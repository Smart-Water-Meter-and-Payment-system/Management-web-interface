package org.pahappa.systems.ppms.backend.core.services.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.pahappa.systems.ppms.backend.core.services.ProjectService;
import org.pahappa.systems.ppms.backend.models.project.Project;
import org.sers.webutils.model.RecordStatus;
import org.sers.webutils.model.exception.OperationFailedException;
import org.sers.webutils.model.exception.ValidationFailedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;

@Service
@Transactional
public class ProjectImpl extends GenericServiceImpl<Project> implements ProjectService {

	@Override
	public List<Project> getAllProjects() {
		Search search = new Search();
		search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);
		return super.search(search);
	}
	
	@Override
	public Project saveInstance(Project project) throws ValidationFailedException {
		if(StringUtils.isBlank(project.getName()))
			throw new ValidationFailedException("Missing Project name");
		if(StringUtils.isBlank(project.getDescription()))
			throw new ValidationFailedException("Missing Project Description");
		return super.save(project);
	}

	@Override
	public boolean isDeletable(Project project) throws OperationFailedException {
		return true;
	}
	
}