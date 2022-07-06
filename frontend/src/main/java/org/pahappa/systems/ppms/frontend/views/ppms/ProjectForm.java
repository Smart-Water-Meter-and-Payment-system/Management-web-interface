package org.pahappa.systems.ppms.frontend.views.ppms;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.pahappa.systems.ppms.backend.core.services.ProjectService;
import org.pahappa.systems.ppms.backend.core.services.ProjectTypeService;
import org.pahappa.systems.ppms.backend.models.project.Project;
import org.pahappa.systems.ppms.backend.models.project.ProjectType;
import org.pahappa.systems.ppms.frontend.security.HyperLinks;
import org.pahappa.systems.ppms.frontend.security.UiUtils;
import org.sers.webutils.client.views.presenters.ViewPath;
import org.sers.webutils.client.views.presenters.WebFormView;
import org.sers.webutils.model.exception.ValidationFailedException;
import org.sers.webutils.server.core.utils.ApplicationContextProvider;

@ManagedBean(name = "projectForm")
@SessionScoped
@ViewPath(path = HyperLinks.PROJECT_FROM)
public class ProjectForm extends WebFormView<Project, ProjectForm, ProjectView> {
	
	private static final long serialVersionUID = 1L;
	private ProjectService projectService;
	private ProjectType projectType;
	private List<ProjectType> projectTypes;
	private ProjectType selectedProjectType;

	@Override
	@PostConstruct
	public void beanInit() {
		this.projectService = ApplicationContextProvider.getBean(ProjectService.class);
		this.projectTypes = ApplicationContextProvider.getBean(ProjectTypeService.class).getAllInstances();
		this.resetModal();
	}

	@Override
	public void pageLoadInit() {
		this.projectTypes = ApplicationContextProvider.getBean(ProjectTypeService.class).getAllInstances();
	}

	@Override
	public void persist() throws Exception {
		try {
			this.projectService.saveInstance(super.model);
			this.redirectTo(HyperLinks.PROJECT_VIEW);
		} catch (ValidationFailedException e) {
			UiUtils.ComposeFailure("Action Failed", e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public void resetModal() {
		super.resetModal();
		super.model = new Project();
	}

	public ProjectType getSelectedProjectType() {
		return selectedProjectType;
	}

	public void setSelectedProjectType(ProjectType selectedProjectType) {
		this.selectedProjectType = selectedProjectType;
	}

	public ProjectType getProjectType() {
		return projectType;
	}

	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}

	public List<ProjectType> getProjectTypes() {
		return projectTypes;
	}

	public void setProjectTypes(List<ProjectType> projectTypes) {
		this.projectTypes = projectTypes;
	}
	
}
