package com.enquero.step.constants;

public enum STEPConstants {
	
	SUBJECT ("subject"), 
	ADDINTAKEDATA ("{call demand_intake_add_data(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
	ADDGAPTECHDATA ("{call demand_gaptech_add_data(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
	
	String value;

	STEPConstants(String value) {
        this.value = value;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

}
