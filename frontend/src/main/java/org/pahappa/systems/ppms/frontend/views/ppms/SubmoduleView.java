package org.pahappa.systems.ppms.frontend.views.ppms;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.pahappa.systems.ppms.backend.core.services.SubmoduleService;
import org.pahappa.systems.ppms.backend.core.utils.CustomSearchUtils;
import org.pahappa.systems.ppms.backend.models.modules.Submodule;
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

@ManagedBean(name = "submoduleView")
@SessionScoped
@ViewPath(path = HyperLinks.SUBMODULE_VIEW)
public class SubmoduleView extends PaginatedTableView<Submodule, SubmoduleView, SubmoduleForm> {

	private static final long serialVersionUID = 1L;
	private SubmoduleService submoduleService;
	private String searchTerm;
	private Search search = new Search().addFilterEqual("recordStatus", RecordStatus.ACTIVE);

	private List<SortField> sortFields;
	private SortField selectedSortField;
	
	@PostConstruct
	public void init() {
		this.submoduleService = ApplicationContextProvider.getBean(SubmoduleService.class);
		this.sortFields = Arrays.asList(new SortField[] { new SortField("Name Asc", "name", false),
				new SortField("Name Desc", "name", true), new SortField("Description Asc", "description", false),
				new SortField("Description Desc", "description", true), new SortField("Module Asc", "module", false),
				new SortField("Module Desc", "module", true),  });
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
		this.search = CustomSearchUtils.genereateSearchObjectForSubmodules(searchTerm, selectedSortField);
		super.setDataModels(this.submoduleService.getInstances(search, arg0, arg1));
	}
	
	@Override
	public void reloadFilterReset() {
		this.search = CustomSearchUtils.genereateSearchObjectForSubmodules(searchTerm, selectedSortField);
		super.setTotalRecords(this.submoduleService.countInstances(search));
	}
	
	public void updateProject(Submodule submodule) throws ValidationFailedException, OperationFailedException {
		this.submoduleService.saveInstance(submodule);
	}

	public void delete(Submodule submodule) {
		try {
			this.submoduleService.deleteInstance(submodule);
			reloadFilterReset();
			MessageComposer.compose("Action Successful", submodule.getName() + " has been deleted");
		} catch (Exception e) {
			MessageComposer.compose("Action Successful", e.getMessage());
		}
	}
	
	/**
	 * 
	 * @return the submoduleService
	 */
	public SubmoduleService getSubmoduleService() {
		return submoduleService;
	}

	/**
	 * 
	 * @param submoduleService the submoduleService to set
	 */
	public void setSubmoduleService(SubmoduleService submoduleService) {
		this.submoduleService = submoduleService;
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
