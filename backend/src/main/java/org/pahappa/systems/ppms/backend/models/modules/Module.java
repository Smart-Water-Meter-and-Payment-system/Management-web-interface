package org.pahappa.systems.ppms.backend.models.modules;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import javax.persistence.CascadeType;
import org.pahappa.systems.ppms.backend.models.ProjectModules;
import org.pahappa.systems.ppms.backend.models.enums.CompletionStatus;
import org.pahappa.systems.ppms.backend.models.project.Project;

/**
 * @author ttc
 *
 * This model will consist of the various modules attached to a project or many projects.
 */
@Entity
@Table(name = "modules")
public class Module extends ProjectModules {

    private static final long serialVersionUID = 1L;
    private CompletionStatus moduleStatus = CompletionStatus.PENDING;
    private Project project;

    /**
     *
     * @return the moduleStatus
     */
    @Column(name = "module_Status", columnDefinition = "varchar(25) default 'PENDING'")
    @Enumerated(value = EnumType.STRING)
    public CompletionStatus getModuleStatus() {
        return moduleStatus;
    }

    /**
     *
     * @param moduleStatus the moduleStatus to set
     */
    public void setModuleStatus(CompletionStatus moduleStatus) {
        this.moduleStatus = moduleStatus;
    }

    /**
     *
     * @return the project
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "project_id")
    public Project getProject() {
        return project;
    }

    /**
     *
     * @param project the project to set
     */
    public void setProject(Project project) {
        this.project = project;
    }
    
	@Override
	public String toString() {
		return this.getName();
	}

	@Override
	public int hashCode() {
		return super.getId() != null ? this.getClass().hashCode() + super.getId().hashCode() : super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Module && (super.getId() != null) ? super.getId().equals(((Module) obj).getId()) : (obj == this);
	}
	
}
