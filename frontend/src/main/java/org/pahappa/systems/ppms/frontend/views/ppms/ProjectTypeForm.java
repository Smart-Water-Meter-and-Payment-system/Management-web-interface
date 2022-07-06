package org.pahappa.systems.ppms.frontend.views.ppms;

import org.pahappa.systems.ppms.backend.core.services.ProjectTypeService;
import org.pahappa.systems.ppms.backend.models.project.ProjectType;
import org.sers.webutils.client.views.presenters.ViewPath;
import org.pahappa.systems.ppms.frontend.security.HyperLinks;
import org.sers.webutils.client.views.presenters.WebFormView;
import org.sers.webutils.model.exception.OperationFailedException;
import org.sers.webutils.model.exception.ValidationFailedException;
import org.sers.webutils.server.core.utils.ApplicationContextProvider;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * 
 * @author Golder
 *
 */
@ManagedBean(name="projectTypeForm")
@SessionScoped
@ViewPath(path= HyperLinks.PROJECT_TYPE_FORM)
public class ProjectTypeForm extends WebFormView<ProjectType, ProjectTypeForm, ProjectTypeView> {

    private static final long serialVersionUID = 1L;
    private ProjectTypeService projectTypeService;
   

    @Override
    public void persist() throws ValidationFailedException, OperationFailedException {
    	this.projectTypeService.saveInstance(super.model);
    }

    @Override
    @PostConstruct
    public void beanInit() {
        this.projectTypeService = ApplicationContextProvider.getBean(ProjectTypeService.class);
        this.resetModal();
    }

    @Override
    public void pageLoadInit() {
    	// TODO Auto-generated method stub
    }

    public void resetModal(){
        super.resetModal();
        super.model = new ProjectType();
    }
    
    
}
