package org.pahappa.systems.ppms.backend.models.project;

import org.sers.webutils.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author ttc
 * 
 * This model will hold all project_types created on the system.
 */
@Entity
@Table(name = "project_types")
public class ProjectType extends BaseEntity {

    private static final long serialVersionUID = 1L;
    private String name;
    private String description;
    
    /**
     *
     * @return the name
     */
    @Column(name = "name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return the description
     */
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
		return obj instanceof ProjectType && (super.getId() != null) ? super.getId().equals(((ProjectType) obj).getId()) : (obj == this);
	}

      
   
}
