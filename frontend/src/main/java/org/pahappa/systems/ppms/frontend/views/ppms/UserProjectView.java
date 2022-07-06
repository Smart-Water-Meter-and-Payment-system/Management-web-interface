package org.pahappa.systems.ppms.frontend.views.ppms;

import com.googlecode.genericdao.search.Search;
import org.pahappa.systems.ppms.backend.core.services.UserProjectService;
import org.pahappa.systems.ppms.backend.core.utils.CustomSearchUtils;
import org.pahappa.systems.ppms.backend.models.UserProject;
import org.pahappa.systems.ppms.backend.models.project.Project;
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

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;
import java.util.Map;

/**
 * Front-end Handler for User Projects
 */
@ManagedBean(name = "userProjectView")
@SessionScoped
@ViewPath(path = HyperLinks.USER_PROJECT_VIEW)
public class UserProjectView extends PaginatedTableView<UserProject, UserProjectView, UserProjectView> {

    private static final long serialVersionUID = 1L;
    private UserProjectService userProjectService;

    private String searchTerm;
    private Search search = new Search().addFilterEqual("recordStatus", RecordStatus.ACTIVE);
    private List<SortField> sortFields;
    private SortField selectedSortField;
    private Project selectedProject = new Project();

    @PostConstruct
    public void init() {
        this.userProjectService = ApplicationContextProvider.getBean(UserProjectService.class);
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
        this.search = CustomSearchUtils.genereateSearchObjectForProjects(searchTerm, selectedSortField);
        super.setDataModels(this.userProjectService.getInstances(search, arg0, arg1));
    }

    @Override
    public void reloadFilterReset() {
        this.search = CustomSearchUtils.genereateSearchObjectForProjects(searchTerm, selectedSortField);
        super.setTotalRecords(this.userProjectService.countInstances(search));
    }

    public void updateUserProject(UserProject userProject) throws ValidationFailedException, OperationFailedException {
        this.userProjectService.saveInstance(userProject);
    }

    public void delete(UserProject userProject) {
        try {
            this.userProjectService.deleteInstance(userProject);
            reloadFilterReset();
            MessageComposer.compose("Action Successful", " has been deleted");
        } catch (Exception e) {
            MessageComposer.compose("Action Successful", e.getMessage());
        }
    }

    /**
     * @return
     */
    public UserProjectService getUserProjectService() {
        return userProjectService;
    }

    /**
     * @param userProjectService
     */
    public void setUserProjectService(UserProjectService userProjectService) {
        this.userProjectService = userProjectService;
    }

    /**
     * @return the searchTerm
     */
    public String getSearchTerm() {
        return searchTerm;
    }

    /**
     * @param searchTerm the searcTerm to set
     */
    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * @return the sortFields
     */
    public List<SortField> getSortFields() {
        return sortFields;
    }

    /**
     * @param sortFields the sortFields to set
     */
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

    /**
     * @return the selectedProject
     */
    public Project getSelectedProject() {
        return selectedProject;
    }

    /**
     * @param selectedProject the selectedProject to set
     */
    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }

    /**
     * @return
     */
    public Search getSearch() {
        return search;
    }

    /**
     * @param search
     */
    public void setSearch(Search search) {
        this.search = search;
    }

}