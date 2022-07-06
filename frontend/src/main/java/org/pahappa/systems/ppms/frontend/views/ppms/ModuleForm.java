package org.pahappa.systems.ppms.frontend.views.ppms;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.pahappa.systems.ppms.backend.core.services.ModuleService;
import org.pahappa.systems.ppms.backend.core.services.ProjectService;
import org.pahappa.systems.ppms.frontend.security.HyperLinks;
import org.pahappa.systems.ppms.frontend.security.UiUtils;
import org.sers.webutils.client.views.presenters.ViewPath;
import org.sers.webutils.client.views.presenters.WebFormView;
import org.sers.webutils.model.exception.ValidationFailedException;
import org.sers.webutils.server.core.utils.ApplicationContextProvider;
import org.pahappa.systems.ppms.backend.models.modules.Module;
import org.pahappa.systems.ppms.backend.models.project.Project;

@ManagedBean(name = "moduleForm")
@SessionScoped
@ViewPath(path = HyperLinks.MODULE_FORM)
public class ModuleForm extends WebFormView<Module, ModuleForm, ModuleView> {

	private static final long serialVersionUID = 1L;
	private ModuleService moduleService;
	private Project project; 
	private List<Project> projects;
	private Project selectedProject;
	
	@Override
	@PostConstruct
	public void beanInit() {
		this.projects = ApplicationContextProvider.getBean(ProjectService.class).getAllProjects();
		this.moduleService = ApplicationContextProvider.getBean(ModuleService.class);
		this.resetModal();
	}

	@Override
	public void pageLoadInit() {
		this.projects = ApplicationContextProvider.getBean(ProjectService.class).getAllProjects();
	}

	@Override
	public void persist() throws Exception {
		try {
			this.moduleService.saveInstance(super.model);
			//UiUtils.showMessageBox("Action successfull", "Module saved successfully");
			this.redirectTo(HyperLinks.MODULE_VIEW);
		} catch (ValidationFailedException e) {
			UiUtils.ComposeFailure("Action Failed", e.getMessage());
			e.printStackTrace();
		}		
	}
	
	@Override
	public void resetModal() {
		super.resetModal();
		super.model = new Module();
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project getSelectedProject() {
		return selectedProject;
	}

	public void setSelectedProject(Project selectedProject) {
		this.selectedProject = selectedProject;
	}
	

}
