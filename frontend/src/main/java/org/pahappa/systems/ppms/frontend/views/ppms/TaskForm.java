package org.pahappa.systems.ppms.frontend.views.ppms;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.pahappa.systems.ppms.backend.core.services.ModuleService;
import org.pahappa.systems.ppms.backend.core.services.ProjectService;
import org.pahappa.systems.ppms.backend.core.services.SubmoduleService;
import org.pahappa.systems.ppms.backend.core.services.TaskService;
import org.pahappa.systems.ppms.frontend.security.HyperLinks;
import org.pahappa.systems.ppms.frontend.security.UiUtils;
import org.sers.webutils.client.views.presenters.ViewPath;
import org.sers.webutils.client.views.presenters.WebFormView;
import org.sers.webutils.model.exception.OperationFailedException;
import org.sers.webutils.model.exception.ValidationFailedException;
import org.sers.webutils.server.core.utils.ApplicationContextProvider;
import org.pahappa.systems.ppms.backend.models.enums.CompletionStatus;
import org.pahappa.systems.ppms.backend.models.modules.Module;
import org.pahappa.systems.ppms.backend.models.modules.Submodule;
import org.pahappa.systems.ppms.backend.models.modules.Task;
import org.pahappa.systems.ppms.backend.models.project.Project;

@ManagedBean(name = "taskForm")
@SessionScoped
@ViewPath(path = HyperLinks.TASK_FORM)
public class TaskForm extends WebFormView<Task, TaskForm, TaskView> {

	private static final long serialVersionUID = 1L;
	private TaskService taskService;
	private Submodule submodule; 
	private SubmoduleService submoduleService;
	private List<Submodule> submodules;
	private List<Task> tasks; 
	private List<CompletionStatus> taskStatuses;
	private Submodule selectedSubmodule;
	private ModuleService moduleService;
	private List<Module> modules;
	private ProjectService projectService;
	private List<Project> projects;
	
	@Override
	@PostConstruct
	public void beanInit() {
		this.submodules = ApplicationContextProvider.getBean(SubmoduleService.class).getAllInstances();
		this.modules = ApplicationContextProvider.getBean(ModuleService.class).getAllInstances();
		this.setProjects(ApplicationContextProvider.getBean(ProjectService.class).getAllInstances());
		this.setTaskStatuses(Arrays.asList(CompletionStatus.values()));
		this.taskService = ApplicationContextProvider.getBean(TaskService.class);
		this.submoduleService = ApplicationContextProvider.getBean(SubmoduleService.class);
		this.moduleService = ApplicationContextProvider.getBean(ModuleService.class);
		this.setProjectService(ApplicationContextProvider.getBean(ProjectService.class));
		this.resetModal();
		this.countMoney();
		this.updateStatus();
	}

	@Override
	public void pageLoadInit() {
		this.setTaskStatuses(Arrays.asList(CompletionStatus.values()));
		this.submodules = ApplicationContextProvider.getBean(SubmoduleService.class).getAllInstances();
	}

	@Override
	public void persist() throws Exception {
		try {
			this.taskService.saveInstance(super.model);
			this.countMoney();
			this.updateStatus();
			this.redirectTo(HyperLinks.TASK_VIEW);
		} catch (ValidationFailedException e) {
			UiUtils.ComposeFailure("Action Failed", e.getMessage());
			e.printStackTrace();
		}		
	}
	
	@Override
	public void resetModal() {
		super.resetModal();
		super.model = new Task();
	}
	
	/**
	 * Calculating Money to update all the dependents of tasks
	 */
	public void countMoney() {
		//Updating submodule
		this.submodules = ApplicationContextProvider.getBean(SubmoduleService.class).getAllInstances();
		this.tasks = ApplicationContextProvider.getBean(TaskService.class).getAllInstances();
		for(Submodule submodule: this.submodules) {
			System.out.println("Submodule being checked: "+ submodule.getName());
			int totalEloeTechnical = 0;
			int totalEloeDeveloper = 0;
			int totalActualLeo = 0;
			int totalEstimatedCost = 0;
			int totalActualCost = 0;
			for(Task task: this.tasks) {
				if(task.getSubmodule().equals(submodule)) {
					System.out.println("Task "+ task.getName() +" of submodule "+ submodule.getName());
					totalEloeTechnical += task.getEloeTechnical();
					totalEloeDeveloper += task.getEloeDeveloper();
					totalActualLeo += task.getActualLoe();
					totalEstimatedCost += task.getEstimatedCost();
					totalActualCost += task.getActualCost();
				}
			}
			try {
				System.out.println("Total Estimated Cost: "+ totalEstimatedCost);
				submodule.setEloeTechnical(totalEloeTechnical);
				submodule.setEloeDeveloper(totalEloeDeveloper);
				submodule.setActualLoe(totalActualLeo);
				submodule.setEstimatedCost(totalEstimatedCost);
				submodule.setActualCost(totalActualCost);
				this.submoduleService.saveInstance(submodule);
			} catch (ValidationFailedException | OperationFailedException e) {
				e.printStackTrace();
			}
		}
		
		//Updating module
		this.modules = ApplicationContextProvider.getBean(ModuleService.class).getAllInstances();
		for(Module module: this.modules) {
			System.out.println("Module being checked: "+ module.getName());
			int totalEloeTechnical = 0;
			int totalEloeDeveloper = 0;
			int totalActualLeo = 0;
			int totalEstimatedCost = 0;
			int totalActualCost = 0;
			for(Submodule submodule: this.submodules) {
				if(submodule.getModule().equals(module)) {
					System.out.println("Submodule "+ submodule.getName() +" of module "+ module.getName());
					totalEloeTechnical += submodule.getEloeTechnical();
					totalEloeDeveloper += submodule.getEloeDeveloper();
					totalActualLeo += submodule.getActualLoe();
					totalEstimatedCost += submodule.getEstimatedCost();
					totalActualCost += submodule.getActualCost();
				}
			}
			try {
				System.out.println("Total Estimated Cost: "+ totalEstimatedCost);
				module.setEloeTechnical(totalEloeTechnical);
				module.setEloeDeveloper(totalEloeDeveloper);
				module.setActualLoe(totalActualLeo);
				module.setEstimatedCost(totalEstimatedCost);
				module.setActualCost(totalActualCost);
				this.moduleService.saveInstance(module);
			} catch (ValidationFailedException | OperationFailedException e) {
				e.printStackTrace();
			}
		}
		
		//Updating project
		this.projects = ApplicationContextProvider.getBean(ProjectService.class).getAllInstances();
		for(Project project: this.projects) {
			System.out.println("Project being checked: "+ project.getName());
			int totalEloeTechnical = 0;
			int totalEloeDeveloper = 0;
			int totalActualLeo = 0;
			int totalEstimatedCost = 0;
			int totalActualCost = 0;
			for(Module module: this.modules) {
				if(module.getProject().equals(project)) {
					System.out.println("Module "+ module.getName() +" of project "+ project.getName());
					totalEloeTechnical += module.getEloeTechnical();
					totalEloeDeveloper += module.getEloeDeveloper();
					totalActualLeo += module.getActualLoe();
					totalEstimatedCost += module.getEstimatedCost();
					totalActualCost += module.getActualCost();
				}
			}
			try {
				System.out.println("Total Estimated Cost: "+ totalEstimatedCost);
				project.setEloeTechnical(totalEloeTechnical);
				project.setEloeDeveloper(totalEloeDeveloper);
				project.setActualLoe(totalActualLeo);
				project.setEstimatedCost(totalEstimatedCost);
				project.setActualCost(totalActualCost);
				this.projectService.saveInstance(project);
			} catch (ValidationFailedException | OperationFailedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Checking task status to update all dependent statuses of models.
	 */
	public void updateStatus() {
		//Updating submodule
		this.submodules = ApplicationContextProvider.getBean(SubmoduleService.class).getAllInstances();
		this.tasks = ApplicationContextProvider.getBean(TaskService.class).getAllInstances();
		for(Submodule submodule: this.submodules) {
			int checker = 0;
			for(Task task: this.tasks) {
				if(task.getSubmodule().equals(submodule)) {
					if(task.getTaskStatus().equals(CompletionStatus.PENDING)) {
						checker = 0;
						break;
					} else {
						checker = 1;
					}	
				}
			}
			if(checker == 1) {
				submodule.setSubmoduleStatus(CompletionStatus.COMPLETE);
				try {
					this.submoduleService.saveInstance(submodule);
				} catch (ValidationFailedException | OperationFailedException e) {
					e.printStackTrace();
				}
			} else {
				submodule.setSubmoduleStatus(CompletionStatus.PENDING);
				try {
					this.submoduleService.saveInstance(submodule);
				} catch (ValidationFailedException | OperationFailedException e) {
					e.printStackTrace();
				}
			}
		}
		
		//Updating module
		this.modules = ApplicationContextProvider.getBean(ModuleService.class).getAllInstances();
		for(Module module: this.modules) {
			int checker = 0;
			for(Submodule submodule: this.submodules) {
				if(submodule.getModule().equals(module)) {
					if(submodule.getSubmoduleStatus().equals(CompletionStatus.PENDING)) {
						checker = 0;
						break;
					} else {
						checker = 1;
					}	
				}
			}
			if(checker == 1) {
				module.setModuleStatus(CompletionStatus.COMPLETE);
				try {
					this.moduleService.saveInstance(module);
				} catch (ValidationFailedException | OperationFailedException e) {
					e.printStackTrace();
				}
			} else {
				module.setModuleStatus(CompletionStatus.PENDING);
				try {
					this.moduleService.saveInstance(module);
				} catch (ValidationFailedException | OperationFailedException e) {
					e.printStackTrace();
				}
			}
		}
		
		//Updating project
		this.projects = ApplicationContextProvider.getBean(ProjectService.class).getAllInstances();
		for(Project project: this.projects) {
			int checker = 0;
			for(Module module: this.modules) {
				if(module.getProject().equals(project)) {
					if(module.getModuleStatus().equals(CompletionStatus.PENDING)) {
						checker = 0;
						break;
					} else {
						checker = 1;
					}
				}
			}
			if(checker == 1) {
				project.setProjectStatus(CompletionStatus.COMPLETE);
				try {
					this.projectService.saveInstance(project);
				} catch (ValidationFailedException | OperationFailedException e) {
					e.printStackTrace();
				}
			} else {
				project.setProjectStatus(CompletionStatus.PENDING);
				try {
					this.projectService.saveInstance(project);
				} catch (ValidationFailedException | OperationFailedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Submodule getSubmodule() {
		return submodule;
	}

	public void setSubmodule(Submodule submodule) {
		this.submodule = submodule;
	}

	public List<Submodule> getSubmodules() {
		return submodules;
	}

	public void setSubmodules(List<Submodule> submodules) {
		this.submodules = submodules;
	}

	public Submodule getSelectedSubmodule() {
		return selectedSubmodule;
	}

	public void setSelectedSubmodule(Submodule selectedSubmodule) {
		this.selectedSubmodule = selectedSubmodule;
	}

	public List<CompletionStatus> getTaskStatuses() {
		return taskStatuses;
	}

	public void setTaskStatuses(List<CompletionStatus> taskStatuses) {
		this.taskStatuses = taskStatuses;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public SubmoduleService getSubmoduleService() {
		return submoduleService;
	}

	public void setSubmoduleService(SubmoduleService submoduleService) {
		this.submoduleService = submoduleService;
	}

	public ModuleService getModuleService() {
		return moduleService;
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	

}
