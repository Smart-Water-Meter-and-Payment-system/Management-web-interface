package org.pahappa.systems.ppms.backend.models.enums;

public enum CompletionStatus {

	/**
	 * Marking a project/module/submodule as pending.
	 * Basically pending to be completed.
	 */
    PENDING("Pending"),
    
    /**
     * Marking a project/module/submodule as complete.
     * This will be a transition from pending.
     * This will be used if all development of a project if finished/complete.
     */
    COMPLETE("Complete");

    private String name;

    CompletionStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public String setName(String name) {
		return name;
	}

    @Override
    public String toString() {
        return this.name;
    }
	
}