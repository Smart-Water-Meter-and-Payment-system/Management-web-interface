package org.swamp.backend.constants;

public enum ChargeRateStatus {

    NO("No"),

    YES("Yes");

    private String name;

    ChargeRateStatus(String name) {
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