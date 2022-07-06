package org.pahappa.systems.ppms.backend.models.modules;

import org.pahappa.systems.ppms.backend.models.ProjectModules;
import org.pahappa.systems.ppms.backend.models.enums.CompletionStatus;

import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

/**
 * @author ttc
 *
 * This model will consist of the various submodules attached to a module of a project.
 */
@Entity
@Table(name = "submodules")
public class Submodule extends ProjectModules {

    private static final long serialVersionUID = 1L;
    private CompletionStatus submoduleStatus = CompletionStatus.PENDING;
    private Module module;

    /**
     *
     * @return the  submoduleStatus
     */
    @Column(name = "submodule_status", columnDefinition = "varchar(25) default 'PENDING'")
    @Enumerated(value = EnumType.STRING)
    public CompletionStatus getSubmoduleStatus() {
        return submoduleStatus;
    }

    /**
     *
     * @param submodule_status the submodule_status to set
     */
    public void setSubmoduleStatus(CompletionStatus submoduleStatus) {
        this.submoduleStatus = submoduleStatus;
    }

    /**
     *
     * @return the module
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "module_id")
    public Module getModule() {
        return module;
    }

    /**
     *
     * @param module the module to set
     */
    public void setModule(Module module) {
        this.module = module;
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
		return obj instanceof Submodule && (super.getId() != null) ? super.getId().equals(((Submodule) obj).getId()) : (obj == this);
	}

}
