package org.pahappa.systems.ppms.backend.models;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.sers.webutils.model.BaseEntity;

/**
 * @author ttc
 *
 * This model helps create attributes that are common to Project, Module, Submodule
 * and Task model. It will be extended in those models so that they can get those attributes.
 */
@MappedSuperclass
public class ProjectModules extends BaseEntity {

	/**
	 * The {@link ProjectModules#descriptionFile} will hold a url to a file uploaded and stored on some storage bucket.
	 * 
	 * The {@link #eloeDeveloper} represents the Estimated Level of Effort that will be defined by a developer for a project.
	 * 
	 * The {@link #eloeTechnical} represents the Estimated Level of Effort that will be defined by a technical manager for a project.
	 * 
	 * The {@link #actualLoe} represents the Actual Level of Effort that is composed at the end of the project.
	 * 
	 * The {@link #estimatedCost} and {@link #actualCost} are estimates and the actual costs of the project respectively.
	 */
    private static final long serialVersionUID = 1L;
    private String name;
    private String description;
    private String descriptionFile;
    private int eloeTechnical;
    private int eloeDeveloper;
    private int actualLoe;
    private int estimatedCost;
    private int actualCost;

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

    /**
     *
     * @return the descriptionFile
     */
    @Column(name = "descriptionFile")
    public String getDescriptionFile() {
        return descriptionFile;
    }

    /**
     *
     * @param descriptionFile the descriptionFile to set.
     * This will be a link to a file uploaded and stored on a specific storage bucket.
     */
    public void setDescriptionFile(String descriptionFile) {
        this.descriptionFile = descriptionFile;
    }

    /**
     *
     * @return the eloeTechnical
     */
    @Column(name = "eloeTechnical", columnDefinition = "int(20)")
    public int getEloeTechnical() {
        return eloeTechnical;
    }

    /**
     *
     * @param eloeTechnical the eloeTechnical to set
     */
    public void setEloeTechnical(int eloeTechnical) {
        this.eloeTechnical = eloeTechnical;
    }

    /**
     *
     * @return the eloeDeveloper
     */
    @Column(name = "eloeDeveloper", columnDefinition = "int(20)")
    public int getEloeDeveloper() {
        return eloeDeveloper;
    }

    /**
     *
     * @param eloeDeveloper the eloeDeveloper to set
     */
    public void setEloeDeveloper(int eloeDeveloper) {
        this.eloeDeveloper = eloeDeveloper;
    }

    /**
     *
     * @return the actualLoe
     */
    @Column(name = "actualLoe", columnDefinition = "int(20)")
    public int getActualLoe() {
        return actualLoe;
    }

    /**
     *
     * @param actualLoe the actualLoe to set
     */
    public void setActualLoe(int actualLoe) {
        this.actualLoe = actualLoe;
    }

    /**
     *
     * @return the estimatedCost
     */
    @Column(name = "estimatedCost", columnDefinition = "int(20)")
    public int getEstimatedCost() {
        return estimatedCost;
    }

    /**
     *
     * @param estimatedCost the estimatedCost to set
     */
    public void setEstimatedCost(int estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    /**
     *
     * @return the actualCost
     */
    @Column(name = "actualCost", columnDefinition = "int(20)")
    public int getActualCost() {
        return actualCost;
    }

    /**
     *
     * @param actualCost the actualCost to set
     */
    public void setActualCost(int actualCost) {
        this.actualCost = actualCost;
    }
	
}
