package controller;

import org.apache.log4j.Logger;

import utilities.InputScanner;

public enum ControllerActions {
	
	CREATE("To save a new record to the database"),
	READ ("To read a record from the database"),
	READALL ("To read all records from the database"),
	UPDATE("To update a record on the database with new information"),
	DELETE("To remove a record from the database"),
	RETURN("To return to table selection");

	public static final Logger LOGGER = Logger.getLogger(ControllerActions.class);
	
	private String descriptor;
	
	ControllerActions(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getDescriptor() {
		return this.name() + ": " + this.descriptor;
	}
	
	public static void printActions() {
		for (ControllerActions action : ControllerActions.values()) {
			LOGGER.info(action.getDescriptor());
		}
	}
	
	public static ControllerActions getControllerAction() {
		ControllerActions action;
		while (true) {
			try {
				action = ControllerActions.valueOf(InputScanner.getInput().toUpperCase());
				break;
			} catch (IllegalArgumentException iae) {
				LOGGER.error("Invalid selection please try again");
			}
		}
		return action;
	}

}
