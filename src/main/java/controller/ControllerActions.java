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
	
	private String description;
	
	ControllerActions(String descriptor) {
		this.description = descriptor;
	}

	public String getDescription() {
		return this.name() + ": " + this.description;
	}
	
	public static String printActions() {
		String tests = "";
		for (ControllerActions action : ControllerActions.values()) {
			tests += action.getDescription();
			LOGGER.info(action.getDescription());
		}
		return tests;
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
