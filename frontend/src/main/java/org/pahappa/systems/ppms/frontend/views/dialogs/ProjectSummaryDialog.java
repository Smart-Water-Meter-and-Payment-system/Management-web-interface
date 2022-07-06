package org.pahappa.systems.ppms.frontend.views.dialogs;



/**
 * ProjectSummaryDialog is to display an overview of the saved project's details
 */



import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import org.pahappa.systems.ppms.backend.core.services.ProjectService;



import org.pahappa.systems.ppms.backend.models.project.Project;
import org.sers.webutils.server.core.utils.ApplicationContextProvider;

@ManagedBean(name = "dialogProjectSummary")
@SessionScoped
public class ProjectSummaryDialog extends DialogForm<Project>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String DIALOG_NAME = "dialogProjectSummary";
	private ProjectService projectService;


	public ProjectSummaryDialog() {
		super(DIALOG_NAME, 500, 300);
		this.projectService = ApplicationContextProvider.getBean(ProjectService.class);
	}

	@Override
	public void persist() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void resetModal() {
		super.resetModal();

		super.model = new Project();

	}

	public void setFormProperties() {
		super.setFormProperties();
	}

	/**
	 * @return the projectService
	 */
	public ProjectService getProjectService() {
		return projectService;
	}

	/**
	 * @param projectService the projectService to set
	 */
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}



}
