package org.pahappa.systems.ppms.backend.models.project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.pahappa.systems.ppms.backend.models.ProjectModules;
import org.pahappa.systems.ppms.backend.models.enums.CompletionStatus;

/**
 * @author ttc
 *
 * This model will hold all projects created on the system.
 */
@Entity
@Table(name = "projects")
public class Project extends ProjectModules {

	private static final long serialVersionUID = 1L;
	private CompletionStatus projectStatus = CompletionStatus.PENDING;
	private boolean isReference;
    private ProjectType projectType;

    /**
     * 
     * @return true or false (whether is a reference or not)
     */
	@Column(name = "is_reference")
	public boolean isIsReference() {
		return isReference;
	}

	/**
	 * 
	 * @param isReference the isReference value to set
	 */
	public void setIsReference(boolean isReference) {
		this.isReference = isReference;
	}

	/**
	 * 
	 * @return the projectStatus
	 */
	@Column(name = "project_status", columnDefinition = "varchar(25) default 'PENDING'")
    @Enumerated(value = EnumType.STRING)
	public CompletionStatus getProjectStatus() {
		return projectStatus;
	}

	/**
	 * 
	 * @param projectStatus the projectStatus to set
	 */
	public void setProjectStatus(CompletionStatus projectStatus) {
		this.projectStatus = projectStatus;
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
		return obj instanceof Project && (super.getId() != null) ? super.getId().equals(((Project) obj).getId()) : (obj == this);
	}

    @ManyToOne
    @JoinColumn(name = "projectTypeId", referencedColumnName="id", nullable=false)
	public ProjectType getProjectType() {
		return projectType;
	}

	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}
}

