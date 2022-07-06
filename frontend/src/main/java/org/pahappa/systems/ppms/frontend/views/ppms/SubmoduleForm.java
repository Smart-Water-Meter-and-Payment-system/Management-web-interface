package org.pahappa.systems.ppms.frontend.views.ppms;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.pahappa.systems.ppms.backend.core.services.SubmoduleService;
import org.pahappa.systems.ppms.backend.core.services.ModuleService;
import org.pahappa.systems.ppms.frontend.security.HyperLinks;
import org.pahappa.systems.ppms.frontend.security.UiUtils;
import org.sers.webutils.client.views.presenters.ViewPath;
import org.sers.webutils.client.views.presenters.WebFormView;
import org.sers.webutils.model.exception.ValidationFailedException;
import org.sers.webutils.server.core.utils.ApplicationContextProvider;
import org.pahappa.systems.ppms.backend.models.modules.Submodule;
import org.pahappa.systems.ppms.backend.models.modules.Module;

@ManagedBean(name = "submoduleForm")
@SessionScoped
@ViewPath(path = HyperLinks.SUBMODULE_FORM)
public class SubmoduleForm extends WebFormView<Submodule, SubmoduleForm, SubmoduleView> {

	private static final long serialVersionUID = 1L;
	private SubmoduleService submoduleService;
	private Module module; 
	private List<Module> modules;
	private Module selectedModule;
	
	@Override
	@PostConstruct
	public void beanInit() {
		this.modules = ApplicationContextProvider.getBean(ModuleService.class).getAllInstances();
		this.submoduleService = ApplicationContextProvider.getBean(SubmoduleService.class);
		this.resetModal();
	}

	@Override
	public void pageLoadInit() {
		this.modules = ApplicationContextProvider.getBean(ModuleService.class).getAllInstances();
	}

	@Override
	public void persist() throws Exception {
		try {
			this.submoduleService.saveInstance(super.model);
			this.redirectTo(HyperLinks.SUBMODULE_VIEW);
		} catch (ValidationFailedException e) {
			UiUtils.ComposeFailure("Action Failed", e.getMessage());
			e.printStackTrace();
		}		
	}
	
	@Override
	public void resetModal() {
		super.resetModal();
		super.model = new Submodule();
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public Module getSelectedModule() {
		return selectedModule;
	}

	public void setSelectedModule(Module selectedModule) {
		this.selectedModule = selectedModule;
	}
	

}
