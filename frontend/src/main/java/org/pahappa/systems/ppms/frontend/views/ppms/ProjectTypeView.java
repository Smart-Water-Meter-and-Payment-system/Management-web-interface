package org.pahappa.systems.ppms.frontend.views.ppms;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.pahappa.systems.ppms.backend.core.services.ProjectService;
import org.pahappa.systems.ppms.backend.core.services.ProjectTypeService;
import org.pahappa.systems.ppms.backend.core.utils.CustomSearchUtils;
import org.pahappa.systems.ppms.backend.models.project.Project;
import org.pahappa.systems.ppms.backend.models.project.ProjectType;
import org.sers.webutils.client.views.presenters.PaginatedTableView;
import org.sers.webutils.client.views.presenters.ViewPath;
import org.pahappa.systems.ppms.frontend.security.HyperLinks;
import org.pahappa.systems.ppms.frontend.views.MessageComposer;
import org.sers.webutils.model.RecordStatus;
import org.sers.webutils.model.exception.OperationFailedException;
import org.sers.webutils.model.exception.ValidationFailedException;
import org.sers.webutils.model.utils.SortField;
import org.sers.webutils.server.core.service.excel.reports.ExcelReport;
import org.sers.webutils.server.core.utils.ApplicationContextProvider;

import com.googlecode.genericdao.search.Search;

/**
 * 
 * @author Golder
 *
 */

@ManagedBean(name="projectTypeView")
@SessionScoped
@ViewPath(path= HyperLinks.PROJECT_TYPE_VIEW)
public class ProjectTypeView extends PaginatedTableView<ProjectType, ProjectTypeView,  ProjectTypeView> {

	private static final long serialVersionUID = 1L;
	private ProjectTypeService projectTypeService;
	private List<SortField> sortFields;
	private SortField selectedSortField;
	private String searchTerm;
	private List<Project> projects;
	private int projectsCount;
	private Search search = new Search().addFilterEqual("recordStatus", RecordStatus.ACTIVE);
	private ProjectType projectType = new ProjectType();

	@PostConstruct
	public void init() {
		this.projectTypeService = ApplicationContextProvider.getBean(ProjectTypeService.class);
		this.sortFields = Arrays.asList(new SortField[] {new SortField("Name Asc", "name", false), 
				new SortField("Name Desc", "name", true), new SortField("Description Asc", "description", false), 
				new SortField("Description Desc", "description", true)});
		super.setMaximumresultsPerpage(10);
		this.projects = ApplicationContextProvider.getBean(ProjectService.class).getAllInstances();
		this.projectsCount = ApplicationContextProvider.getBean(ProjectService.class).countInstances(search);
	}



	@Override
	public List<ExcelReport> getExcelReportModels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reloadFromDB(int offset, int limit, Map<String, Object> arg2) throws Exception {
		this.search = CustomSearchUtils.generateSearchObjectForProjectTypes(searchTerm, selectedSortField);
		super.setDataModels(this.projectTypeService.getInstances(search, offset, limit));

	}

	@Override
	public void reloadFilterReset() {
		this.search = CustomSearchUtils.generateSearchObjectForProjectTypes(searchTerm, selectedSortField);
		super.setTotalRecords(this.projectTypeService.countInstances(search));
	}

	public void updateProjectType(ProjectType projectType) throws ValidationFailedException, OperationFailedException {
		this.projectTypeService.saveInstance(projectType);
	}

	public void delete(ProjectType projectType) {
		try {
			this.projects = ApplicationContextProvider.getBean(ProjectService.class).getAllInstances();

			for(Project project:projects) {
				if(project.getProjectType().equals(projectType)) {
					throw new OperationFailedException("This project type cannot be deleted. It is attached to one or more projects");
				}
			}

			this.projectTypeService.deleteInstance(projectType);
			this.reloadFilterReset();
			MessageComposer.compose("Action Successful", projectType.getName() + " has been deleted");	
		} catch (Exception e) {
			MessageComposer.compose("Action Unsuccessful", e.getMessage());
		}
	}


	public ProjectTypeService getPeopleService() {
		return projectTypeService;
	}

	public void setProjectTypeService(ProjectTypeService projectTypeService) {
		this.projectTypeService = projectTypeService;
	}


	public List<SortField> getSortFields() {
		return sortFields;
	}
	public void setSortFields(List<SortField> sortFields) {
		this.sortFields = sortFields;
	}

	/**
	 * @return the selectedSortField
	 */
	public SortField getSelectedSortField() {
		return selectedSortField;
	}

	/**
	 * @param selectedSortField the selectedSortField to set
	 */
	public void setSelectedSortField(SortField selectedSortField) {
		this.selectedSortField = selectedSortField;
	}

	public ProjectType getProjectType() {
		return projectType;
	}

	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}



	public int getProjectsCount() {
		return projectsCount;
	}



	public void setProjectsCount(int projectsCount) {
		this.projectsCount = projectsCount;
	}


}
