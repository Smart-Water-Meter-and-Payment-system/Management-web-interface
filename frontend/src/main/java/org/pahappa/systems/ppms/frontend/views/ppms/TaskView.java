package org.pahappa.systems.ppms.frontend.views.ppms;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.pahappa.systems.ppms.backend.core.services.TaskService;
import org.pahappa.systems.ppms.backend.core.utils.CustomSearchUtils;
import org.pahappa.systems.ppms.backend.models.modules.Task;
import org.pahappa.systems.ppms.frontend.security.HyperLinks;
import org.pahappa.systems.ppms.frontend.views.MessageComposer;
import org.sers.webutils.client.views.presenters.PaginatedTableView;
import org.sers.webutils.client.views.presenters.ViewPath;
import org.sers.webutils.model.RecordStatus;
import org.sers.webutils.model.exception.OperationFailedException;
import org.sers.webutils.model.exception.ValidationFailedException;
import org.sers.webutils.model.utils.SortField;
import org.sers.webutils.server.core.service.excel.reports.ExcelReport;
import org.sers.webutils.server.core.utils.ApplicationContextProvider;

import com.googlecode.genericdao.search.Search;

@ManagedBean(name = "taskView")
@SessionScoped
@ViewPath(path = HyperLinks.TASK_VIEW)
public class TaskView extends PaginatedTableView<Task, TaskView, TaskForm> {

	private static final long serialVersionUID = 1L;
	private TaskService taskService;
	private String searchTerm;
	private Search search = new Search().addFilterEqual("recordStatus", RecordStatus.ACTIVE);

	private List<SortField> sortFields;
	private SortField selectedSortField;
	
	@PostConstruct
	public void init() {
		this.taskService = ApplicationContextProvider.getBean(TaskService.class);

		this.sortFields = Arrays.asList(new SortField[] { new SortField("Name Asc", "name", false),
				new SortField("Name Desc", "name", true), new SortField("Description Asc", "description", false),
				new SortField("Description Desc", "description", true), new SortField("Submodule Asc", "submodule", false),
				new SortField("Submodule Desc", "submodule", true), });
		super.setMaximumresultsPerpage(10);
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
	public void reloadFromDB(int arg0, int arg1, Map<String, Object> arg2) throws Exception {
		this.search = CustomSearchUtils.genereateSearchObjectForTasks(searchTerm, selectedSortField);
		super.setDataModels(this.taskService.getInstances(search, arg0, arg1));
	}
	
	@Override
	public void reloadFilterReset() {
		this.search = CustomSearchUtils.genereateSearchObjectForTasks(searchTerm, selectedSortField);
		super.setTotalRecords(this.taskService.countInstances(search));
	}
	
	public void updateProject(Task task) throws ValidationFailedException, OperationFailedException {
		this.taskService.saveInstance(task);
	}

	public void delete(Task task) {
		try {
			this.taskService.deleteInstance(task);
			reloadFilterReset();
			MessageComposer.compose("Action Successful", task.getName() + " has been deleted");
		} catch (Exception e) {
			MessageComposer.compose("Action Successful", e.getMessage());
		}
	}
	
	/**
	 * 
	 * @return the taskService
	 */
	public TaskService getTaskService() {
		return taskService;
	}

	/**
	 * 
	 * @param taskService the taskService to set
	 */
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	/**
	 * 
	 * @return the searchTerm
	 */
	public String getSearchTerm() {
		return searchTerm;
	}

	/**
	 * 
	 * @param searchTerm the searcTerm to set
	 */
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	
	/**
	 * 
	 * @return the sortFields
	 */
	public List<SortField> getSortFields() {
		return sortFields;
	}

	/**
	 * 
	 * @param sortFields the sortFields to set
	 */
	public void setSortFields(List<SortField> sortFields) {
		this.sortFields = sortFields;
	}

	/**
	 * 
	 * @return the selectedSortField
	 */
	public SortField getSelectedSortField() {
		return selectedSortField;
	}

	/**
	 * 
	 * @param selectedSortField the selectedSortField to set
	 */
	public void setSelectedSortField(SortField selectedSortField) {
		this.selectedSortField = selectedSortField;
	}
	
	/**
	 * 
	 * @return
	 */
	public Search getSearch() {
		return search;
	}

	/**
	 * 
	 * @param search
	 */
	public void setSearch(Search search) {
		this.search = search;
	}

}
